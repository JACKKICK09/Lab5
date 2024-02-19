package commands;

import Bugs.WrongAmountElements;
import utility.Console;

public class execute_script extends Command {
    public execute_script() {
        super("execute_script <file_name>", "исполнить скрипт из указанного файла");
    }

    @Override
    public boolean doIt(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountElements();
            Console.println("Выполняю скрипт '" + argument + "'...");
            return true;
        } catch (WrongAmountElements exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
