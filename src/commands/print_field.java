package commands;

import Bugs.EmptyFile;
import Bugs.WrongAmountElements;
import Classes.Product;
import utility.Console;
import utility.ManagerOfCollections;

import java.util.List;

public class print_field extends Command {
    private ManagerOfCollections managerOfCollections;
    public print_field(ManagerOfCollections managerOfCollections){
        super("print_field_descending_unit_of_measure", "вывести значения поля unitOfMeasure всех элементов в порядке убывания");
    }

    @Override
    public boolean doIt(String argument) {
        try {
            if (argument.isEmpty()) {
                throw new WrongAmountElements();
            }

            if (managerOfCollections == null) {
                throw new EmptyFile();
            }

            List<Product> sortedProducts = managerOfCollections.printFieldDescendingUnitOfMeasure();

            if (sortedProducts.isEmpty()) {
                Console.println("Коллекция пуста.");
            } else {
                Console.println("Значения поля unitOfMeasure всех элементов в порядке убывания:");
                for (Product product : sortedProducts) {
                    Console.println(product.getUnitOfMeasure().toString());
                }
            }

            return true;
        } catch (WrongAmountElements exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (EmptyFile e) {
            Console.printerror("Коллекция пуста");
        }

        return false;
    }

}

