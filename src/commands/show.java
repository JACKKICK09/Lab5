package commands;

import Bugs.WrongAmountElements;
import utility.Console;
import utility.ManagerOfCollections;

public class show extends Command{
    private final ManagerOfCollections managerOfCollections;

    public show(ManagerOfCollections managerOfCollections) {
        super("show", "вывести все элементы коллекции");
        this.managerOfCollections = managerOfCollections;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean doIt(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountElements();
            Console.println(managerOfCollections);
            return true;
        } catch (WrongAmountElements exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}

