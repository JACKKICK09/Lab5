package commands;

import Bugs.EmptyFile;
import Bugs.InCorrectInput;
import Bugs.WrongAmountElements;
import Classes.Product;
import utility.Console;
import utility.ManagerOfCollections;
import utility.Panel;

import java.util.List;

public class fillterstars extends Command {
    private final ManagerOfCollections managerOfCollections;
    private final Panel panel;

    public fillterstars(ManagerOfCollections managerOfCollections, Panel panel) {
        super("filter_starts_with_name name", "вывести элементы, значение поля name которых начинается с заданной подстроки");
        this.managerOfCollections = managerOfCollections;
        this.panel = panel;
    }

    @Override
    public boolean doIt(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountElements();
            if (managerOfCollections.collectionSize() == 0) throw new EmptyFile();

            String substringToFilter = panel.inputString("Введите подстроку для фильтрации по полю name:");

            List<Product> filteredProducts = managerOfCollections.filterStartsWithName(substringToFilter);

            if (filteredProducts.isEmpty()) {
                Console.println("Нет элементов, удовлетворяющих условиям фильтрации.");
            } else {
                Console.println("Элементы, значение поля name которых начинается с заданной подстроки:");
                for (Product product : filteredProducts) {
                    Console.println(product.toString());
                }
            }

            return true;
        } catch (WrongAmountElements exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (EmptyFile exception) {
            Console.printerror("Коллекция пуста!");
        }

        return false;
    }
}

