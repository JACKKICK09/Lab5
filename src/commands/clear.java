package commands;

import Bugs.WrongAmountElements;
import utility.ManagerOfCollections;
import utility.Console;

public class clear extends Command{
    private final ManagerOfCollections managerOfCollections;

    public clear(ManagerOfCollections managerOfCollections) {
        super("\nclear", "очистить коллекцию");
        this.managerOfCollections = managerOfCollections;
    }

    @Override
    public boolean doIt(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountElements();
            managerOfCollections.clearCollection();
            Console.println("Коллекция очищена!");
            return true;
        } catch (WrongAmountElements exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }

}
