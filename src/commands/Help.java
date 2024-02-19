package commands;

import Bugs.WrongAmountElements;
import utility.*;

public class Help extends Command{
    public Help() {
        super("help", "вывести справку по доступным командам");
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
