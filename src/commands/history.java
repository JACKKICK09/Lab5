package commands;

import Bugs.WrongAmountElements;
import utility.Console;

public class history extends Command {
    public history() {
        super("history", "вывести историю использованных команд");
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
