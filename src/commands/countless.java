package commands;

import Bugs.EmptyFile;
import Bugs.InCorrectInput;
import Bugs.WrongAmountElements;
import Classes.Person;
import utility.Console;
import utility.ManagerOfCollections;
import utility.Panel;

public class countless extends Command {
    private final ManagerOfCollections managerOfCollections;
    private final Panel panel;

    public countless(ManagerOfCollections managerOfCollections, Panel panel) {
        super("count_less_than_owner owner", "вывести количество элементов, значение поля owner которых меньше заданного");
        this.managerOfCollections = managerOfCollections;
        this.panel = panel;
    }

    @Override
    public boolean doIt(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountElements();
            if (managerOfCollections.collectionSize() == 0) throw new EmptyFile();

            Person ownerToCompare = panel.IputPerson(); // Замените на ваш метод ввода owner

            int count = managerOfCollections.countLessThanOwner(ownerToCompare);
            Console.println("Количество элементов, значение поля owner которых меньше заданного: " + count);
            return true;
        } catch (WrongAmountElements exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (EmptyFile exception) {
            Console.printerror("Коллекция пуста!");
        } catch (InCorrectInput exception) {}
        return false;
    }
}

