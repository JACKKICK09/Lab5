package commands;

import Bugs.InCorrectInput;
import Bugs.LimitException;
import Classes.Product;
import utility.ManagerOfCollections;
import utility.Panel;
import utility.Console;

import java.time.LocalDateTime;
import java.util.Scanner;

public class add extends Command{


    private final ManagerOfCollections managerOfCollections;
    private final Panel panel;
    public add(ManagerOfCollections managerOfCollections, Panel panel){
        super("add {element}", "добавить новый элемент в коллекцию");
        this.managerOfCollections = managerOfCollections;
        this.panel = panel;
    }



    @Override
    public boolean doIt(String argument) {
        try {
            if (!argument.isEmpty()) throw new LimitException();
            managerOfCollections.addToCollection(new Product(
                    managerOfCollections.generateNextId(),
                    panel.InputName(),
                    panel.askCoordinates(),
                    LocalDateTime.now(),
                    panel.InputPrice(),
                    panel.InputManufacture(),
                    panel.InputUnitOfMeasure(),
                    panel.IputPerson()
            ));
            Console.println("Продукт успешно добавлен!");
            return true;
        } catch (LimitException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (InCorrectInput exception) {}
        return false;
    }
}
