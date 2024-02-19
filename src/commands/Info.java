package commands;

import Bugs.WrongAmountElements;
import utility.Console;
import utility.ManagerOfCollections;

import java.time.LocalDateTime;

public class Info extends Command{
    private final ManagerOfCollections managerOfCollections;

    public Info(ManagerOfCollections managerOfCollections) {
        super("info", "вывести информацию о коллекции");
        this.managerOfCollections = managerOfCollections;
    }

    @Override
    public boolean doIt(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountElements();
            LocalDateTime lastInitTime = managerOfCollections.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                    lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

            LocalDateTime lastSaveTime = managerOfCollections.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                    lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

            Console.println("Сведения о коллекции:");
            Console.println(" Тип: " + managerOfCollections.collectionType());
            Console.println(" Количество элементов: " + managerOfCollections.collectionSize());
            Console.println(" Дата последнего сохранения: " + lastSaveTimeString);
            Console.println(" Дата последней инициализации: " + lastInitTimeString);
            return true;
        } catch (WrongAmountElements exception) {
            Console.println("Использование: '" + getName() + "'");
        }
        return false;
    }
}
