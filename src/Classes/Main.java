package Classes;

import commands.*;
import utility.*;

import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)) {

            Panel panel = new Panel(userScanner);
            if (args.length == 0){
                Console.println("Введите путь к файлу");
                System.exit(1);
            }
            FileReaderWriter fileManager = new FileReaderWriter(args[0]);
            ManagerOfCollections collectionManager = new ManagerOfCollections(fileManager);
            CommandMan commandManager = new CommandMan(
                    new Help(),
                    new Info(collectionManager),
                    new show(collectionManager),
                    new add(collectionManager, panel),
                    new update(collectionManager, panel),
                    new remove_by_id(collectionManager),
                    new clear(collectionManager),
                    new save(collectionManager, collectionManager),
                    new execute_script(),
                    new exit(),
                    new remove_at(collectionManager),
                    new remove_lower(collectionManager, panel),
                    new history(),
                    new countless(collectionManager, panel),
                    new fillterstars(collectionManager, panel),
                    new print_field(collectionManager)
            );
            Console console = new Console(commandManager, userScanner, panel);

            console.interactiveMode();
        }
    }
}