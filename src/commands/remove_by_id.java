package commands;

import Bugs.EmptyFile;
import Bugs.PanelNotFound;
import Bugs.WrongAmountElements;
import Classes.Product;
import utility.Console;
import utility.ManagerOfCollections;

public class remove_by_id extends Command {
    private final ManagerOfCollections collectionManager;

    public remove_by_id(ManagerOfCollections collectionManager) {
        super("remove_by_id <ID>", "удалить элемент из коллекции по ID");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean doIt(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountElements();
            if (collectionManager.collectionSize() == 0) throw new EmptyFile();
            int id = Integer.parseInt(argument);
            Product product = collectionManager.getById(id);
            if (product == null) throw new PanelNotFound();
            collectionManager.removeFromCollection(product);
            Console.println("Продукт успешно удален!");
            return true;
        } catch (WrongAmountElements Eexception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (EmptyFile exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID должен быть представлен числом!");
        } catch (PanelNotFound exception) {
            Console.printerror("Продукта с таким ID в коллекции нет!");
        }
        return false;
    }
}
