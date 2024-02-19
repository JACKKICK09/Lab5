package commands;

import Bugs.WrongAmountElements;
import utility.Console;
import utility.ManagerOfCollections;

import java.io.File;
import java.io.IOException;

public class save extends Command{
    private final ManagerOfCollections managerOfCollections;
    ManagerOfCollections fileName;

    public save(ManagerOfCollections managerOfCollections, ManagerOfCollections fileName) {
        super("save", "сохранить коллекцию в файл");
        this.managerOfCollections = managerOfCollections;
        this.fileName = fileName;

    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean doIt(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountElements();
            managerOfCollections.saveCollection(fileName);
            return true;
        } catch (WrongAmountElements | IOException exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
