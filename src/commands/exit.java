package commands;

import Bugs.WrongAmountElements;
import utility.Console;

public class exit extends Command {
    public exit() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public boolean doIt(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountElements();
            return true;
        } catch (WrongAmountElements exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
