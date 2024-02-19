package commands;

import Bugs.EmptyFile;
import Bugs.PanelNotFound;
import Bugs.WrongAmountElements;
import Classes.Product;
import utility.CommandMan;
import utility.Console;
import utility.ManagerOfCollections;

public class remove_at extends Command {
    private final ManagerOfCollections collectionManager;

    public remove_at(ManagerOfCollections collectionManager) {
        super("remove_at index", "удалить элемент из коллекции по index");
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
            Console.printerror("Index должен быть представлен числом!");
        } catch (PanelNotFound exception) {
            Console.printerror("Продукта по такому index в коллекции нет!");
        }
        return false;
    }
}
