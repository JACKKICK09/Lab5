package commands;

import Bugs.EmptyFile;
import Bugs.InCorrectInput;
import Bugs.PanelNotFound;
import Bugs.WrongAmountElements;
import Classes.Product;
import utility.Console;
import utility.ManagerOfCollections;
import utility.Panel;

import java.time.LocalDateTime;

public class remove_lower extends Command {
    private final ManagerOfCollections managerOfCollections;
    private final Panel panel;

    public remove_lower(ManagerOfCollections managerOfCollections, Panel panel) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.managerOfCollections = managerOfCollections;
        this.panel = panel;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean doIt(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountElements();
            if (managerOfCollections.collectionSize() == 0) throw new EmptyFile();
            Product productTo = new Product(
                    managerOfCollections.generateNextId(),
                    panel.InputName(),
                    panel.askCoordinates(),
                    LocalDateTime.now(),
                    panel.InputPrice(),
                    panel.InputManufacture(),
                    panel.InputUnitOfMeasure(),
                    panel.IputPerson()
            );
            Product productFrom = managerOfCollections.getByValue(productTo);
            if (productFrom == null) throw new PanelNotFound();
            managerOfCollections.removeLower(productFrom);
            Console.println("Солдаты успешно удалены!");
            return true;
        } catch (WrongAmountElements exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (EmptyFile exception) {
            Console.printerror("Коллекция пуста!");
        } catch (PanelNotFound exception) {
            Console.printerror("Такого продукта в коллекции нет");
        } catch (InCorrectInput exception) {}
        return false;
    }

}
