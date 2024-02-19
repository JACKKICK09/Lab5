package utility;

import Bugs.InsufficientRights;
import Bugs.ScriptRecursion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Console {
    private final CommandMan commandMan;
    private final Scanner userScanner;
    private final Panel panel;
    private final List<String> scriptStack = new ArrayList<>();

    public Console(CommandMan commandMan, Scanner userScanner, Panel panel) {
        this.commandMan = commandMan;
        this.userScanner = userScanner;
        this.panel = panel;
    }

    public void interactiveMode() {
        String[] userCommand = {"", ""};
        int commandStatus;
        try {
            do {
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandMan.addToHistory(userCommand[0]);
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2 && userScanner.hasNextLine());
        } catch (NoSuchElementException exception) {
            Console.printerror("Пользовательский ввод не обнаружен!");
        } catch (SecurityException e) {
            Console.printerror("Недостаточно прав: " + e.getMessage());
            System.exit(1);
        } catch (IllegalStateException exception) {
            Console.printerror("Непредвиденная ошибка!");
        }
    }



    public int scriptMode(String argument) {
        String[] userCommand = {"", ""};
        int commandStatus;
        scriptStack.add(argument);
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = panel.getScan();
            panel.setScan(scriptScanner);
            panel.setFileStatus();
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                Console.println(String.join(" ", userCommand));
                var isRecuion = false;
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) {
                            isRecuion = true;
                            Console.println("Скрипты не могут вызываться рекурсивно!");
                        }
                        ;
                    }
                }
                if (!isRecuion)
                    commandStatus = launchCommand(userCommand);
                else{
                    commandStatus = 0;
                }
            } while (scriptScanner.hasNextLine());
            panel.setScan(tmpScanner);
            panel.setUserStatus();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                Console.println("Проверьте скрипт на корректность введенных данных!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            Console.printerror("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            Console.printerror("Файл со скриптом пуст!");
        }catch (SecurityException e) {
                Console.printerror("Недостаточно прав: " + e.getMessage());
        } catch (IllegalStateException exception) {
            Console.printerror("Непредвиденная ошибка!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size() - 1);
        }
        return 1;
    }

    private int launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commandMan.help(userCommand[1])) return 1;
                break;
            case "info":
                if (!commandMan.doItInfo(userCommand[1])) return 1;
                break;
            case "show":
                if (!commandMan.doItShow(userCommand[1])) return 1;
                break;
            case "add":
                if (!commandMan.doItAdd(userCommand[1])) return 1;
                break;
            case "update":
                if (!commandMan.doItUpdate(userCommand[1])) return 1;
                break;
            case "remove_by_id":
                if (!commandMan.doItRemoveById(userCommand[1])) return 1;
                break;
            case "clear":
                if (!commandMan.doItClear(userCommand[1])) return 1;
                break;
            case "save":
                if (!commandMan.doItSave(userCommand[1])) return 1;
                break;
            case "execute_script":
                if (!commandMan.doItScript(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            case "print_field_descending_unit_of_measure":
                if (!commandMan.doItPFDUOM(userCommand[1])) return 1;
                break;
            case "remove_at":
                if (!commandMan.doItRemoveAt(userCommand[1])) return 1;
                break;
            case "remove_lower":
                if (!commandMan.doItRemoveLower(userCommand[1])) return 1;
                break;
            case "history":
                if (!commandMan.doItHistory(userCommand[1])) return 1;
                break;
            case "count_less_than_owner":
                if (!commandMan.doItCountLessThan(userCommand[1])) return 1;
                break;
            case "filter_starts_with_name":
                if (!commandMan.doItFSWN(userCommand[1])) return 1;
                break;
            case "exit":
                if (!commandMan.doItExit(userCommand[1])) return 1;
                else return 2;
            default:
                if (!commandMan.noSuchCommand(userCommand[0])) return 1;
        }
        return 0;
    }

    public static void print(String name, Object toOut) {
        System.out.print(toOut);
    }

    public static void printerror(Object toOut) {
        System.out.println("Error" + toOut);
    }

    public static void println(Object toOut) {
        System.out.println(toOut);
    }

}
