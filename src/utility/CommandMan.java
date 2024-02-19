package utility;
import  java.util.*;
import java.io.*;

import Bugs.HistoryEmpty;
import commands.Command;
public class CommandMan {
    private final int History_size = 6;

    private final String[] CommandHistory = new String[History_size];
    private final List<Command> commands = new ArrayList<>();
    private final Command helpCommand;
    private final Command InfoCommand;
    private final Command showCommand;
    private final Command addCommand;
    private final Command updateCommand;
    private final Command remove_by_idCommand;
    private final Command clearCommand;
    private final Command saveCommand;
    private final Command doIt_scriptCommand;
    private final Command exitCommand;
    private final Command removeAtCommand;
    private final Command removeLowerCommand;
    private final Command historyCommand;
    private final Command countlessthanCommand;
    private final Command FSWNCommand;
    private final Command PFDUOMCommand;

    public CommandMan(Command helpCommand, Command infoCommand, Command showCommand, Command addCommand,
                      Command updateCommand, Command removeByIdCommand, Command clearCommand, Command saveCommand,
                      Command doItScriptCommand, Command exitCommand, Command removeAtCommand, Command removeLowerCommand,
                      Command historyCommand, Command countLessThanCommand, Command filterStartsWithNameCommand,
                      Command printFieldDescendingUnitOfMeasureCommand) {

        this.helpCommand = helpCommand;
        this.InfoCommand = infoCommand;
        this.showCommand = showCommand;
        this.addCommand = addCommand;
        this.updateCommand = updateCommand;
        this.remove_by_idCommand = removeByIdCommand;
        this.clearCommand = clearCommand;
        this.saveCommand = saveCommand;
        this.doIt_scriptCommand = doItScriptCommand;
        this.exitCommand = exitCommand;
        this.removeAtCommand = removeAtCommand;
        this.removeLowerCommand = removeLowerCommand;
        this.historyCommand = historyCommand;
        this.countlessthanCommand = countLessThanCommand;
        this.FSWNCommand = filterStartsWithNameCommand;
        this.PFDUOMCommand = printFieldDescendingUnitOfMeasureCommand;


        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(addCommand);
        commands.add(updateCommand);
        commands.add(removeByIdCommand);
        commands.add(clearCommand);
        commands.add(saveCommand);
        commands.add(doItScriptCommand);
        commands.add(exitCommand);
        commands.add(removeAtCommand);
        commands.add(removeLowerCommand);
        commands.add(historyCommand);
        commands.add(countLessThanCommand);
        commands.add(filterStartsWithNameCommand);
        commands.add(printFieldDescendingUnitOfMeasureCommand);

    }

    public String[] getCommandHistory() {
        return CommandHistory;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void addToHistory(String commandToList){
        for (Command command : commands){
            if (command.getName().split("")[0].equals(commandToList)){
                for (int i = History_size-1; i>0; i--){
                    CommandHistory[i] = CommandHistory[i-1];
                }
                CommandHistory[0] = commandToList;
            }
        }
    }

    public boolean noSuchCommand(String command) {
        System.out.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;
    }

    public boolean help(String a) {
        if (helpCommand.doIt(a)) {
            for (Command command : commands) {
                System.out.printf("Команда %s - %s%n", command.getName(), command.getDescription());
            }
            return true;
        } else {
            return false;
        }
    }


    public boolean doItInfo(String argument) {
        return InfoCommand.doIt(argument);
    }

    public boolean doItShow(String argument) {
        return showCommand.doIt(argument);
    }

    public boolean doItAdd(String argument) {
        return addCommand.doIt(argument);
    }

    public boolean doItUpdate(String argument) {
        return updateCommand.doIt(argument);
    }

    public boolean doItRemoveById(String argument) {
        return remove_by_idCommand.doIt(argument);
    }

    public boolean doItClear(String argument) {
        return clearCommand.doIt(argument);
    }

    public boolean doItSave(String argument) {
        return saveCommand.doIt(argument);
    }

    public boolean doItScript(String argument) {
        return doIt_scriptCommand.doIt(argument);
    }

    public boolean doItExit(String argument) {
        return exitCommand.doIt(argument);
    }

    public boolean doItRemoveAt(String argument) {
        return removeAtCommand.doIt(argument);
    }

    public boolean doItRemoveLower(String argument) {
        return removeLowerCommand.doIt(argument);
    }

    public boolean doItHistory(String argument) {
        return historyCommand.doIt(argument);
    }

    public boolean doItCountLessThan(String argument) {
        return countlessthanCommand.doIt(argument);
    }

    public boolean doItFSWN(String argument) {
        return FSWNCommand.doIt(argument);
    }

    public boolean doItPFDUOM(String argument) {
        return PFDUOMCommand.doIt(argument);
    }

    public boolean history(String argument) {
        if (historyCommand.doIt(argument)) {
            try {
                if (CommandHistory.length == 0) throw new HistoryEmpty();

                System.out.println("Последние использованные команды:");
                for (int i=0; i<CommandHistory.length; i++) {
                    if (CommandHistory[i] != null) System.out.println(" " + CommandHistory[i]);
                }
                return true;
            } catch (HistoryEmpty exception) {
                System.out.println("Ни одной команды еще не было использовано!");
            }
        }
        return false;
    }
}
