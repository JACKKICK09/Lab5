package commands;

import Bugs.EmptyFile;
import Bugs.InCorrectInput;
import Bugs.PanelNotFound;
import Bugs.WrongAmountElements;
import Classes.Coordinates;
import Classes.Person;
import Classes.Product;
import Classes.UnitOfMeasure;
import utility.Console;
import utility.ManagerOfCollections;
import utility.Panel;

import java.time.LocalDateTime;

public class update extends Command{
    private final ManagerOfCollections managerOfCollections;
    private final Panel panel;

    public update(ManagerOfCollections managerOfCollections, Panel panel) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
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
            if (argument.isEmpty()) throw new WrongAmountElements();
            if (managerOfCollections.collectionSize() == 0) throw new EmptyFile();

            int id = Integer.parseInt(argument);
            Product oldProduct = managerOfCollections.getById(id);
            if (oldProduct == null) throw new PanelNotFound();

            String name = oldProduct.getName();
            Coordinates coordinates = oldProduct.getCoordinates();
            LocalDateTime creationDate = oldProduct.getCreationDate();
            float price = oldProduct.getPrice();
            long manufactureCost = oldProduct.getManufactureCost();
            UnitOfMeasure unitOfMeasure = oldProduct.getUnitOfMeasure();
            Person owner = oldProduct.getOwner();

            managerOfCollections.removeFromCollection(oldProduct);

            if (panel.askQuestion("Хотите изменить имя солдата?")) name = panel.InputName();
            if (panel.askQuestion("Хотите изменить координаты солдата?")) coordinates = panel.askCoordinates();
            if (panel.askQuestion("Хотите изменить здоровье солдата?")) price= panel.InputPrice();
            if (panel.askQuestion("Хотите изменить категорию солдата?")) manufactureCost = panel.InputManufacture();
            if (panel.askQuestion("Хотите изменить оружие дальнего боя солдата?")) unitOfMeasure = panel.InputUnitOfMeasure();
            if (panel.askQuestion("Хотите изменить оружие ближнего боя солдата?")) owner = panel.IputPerson();

            managerOfCollections.addToCollection(new Product(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    price,
                    manufactureCost,
                    unitOfMeasure,
                    owner

            ));
            Console.println("Солдат успешно изменен!");
            return true;
        } catch (WrongAmountElements exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (EmptyFile exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID должен быть представлен числом!");
        } catch (PanelNotFound exception) {
            Console.printerror("Солдата с таким ID в коллекции нет!");
        } catch (InCorrectInput exception) {}
        return false;
    }
}
