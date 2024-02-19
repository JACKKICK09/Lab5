package utility;

import java.time.LocalDateTime;
import java.util.*;
import java.io.*;

import Classes.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;


public class FileReaderWriter {

    public static String envVariable;
    private ManagerOfCollections managerOfCollections;


    public FileReaderWriter(String envVariable) {
        FileReaderWriter.envVariable = envVariable;
    }

    // Запись коллекции в CSV с использованием BufferedOutputStream
    public void write(Collection<Product> collection) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(envVariable));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("id", "name", "x", "y", "creationDate", "price", "manufactureCost", "unitOfMeasure",
                             "owner_name", "owner_passportID", "owner_eyeColor", "owner_hairColor", "owner_nationality"))) {

            for (Product product : collection) {
                csvPrinter.printRecord(
                        product.getId(),
                        product.getName(),
                        product.getCoordinates().getX(),
                        product.getCoordinates().getY(),
                        product.getCreationDate(),
                        product.getPrice(),
                        product.getManufactureCost(),
                        product.getUnitOfMeasure(),
                        product.getOwner().getName(),
                        product.getOwner().getPassportID(),
                        product.getOwner().getEyeColor(),
                        product.getOwner().getHairColor(),
                        product.getOwner().getNationality()
                );
            }

            csvPrinter.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public Vector<Product> readFromFile() {
        Vector<Product> dataVector = new Vector<>();

        try (Scanner fileReader = new Scanner(new File(envVariable))) {
            String headerLine = "";
            headerLine = fileReader.nextLine();

            String[] headers = headerLine.split(",");

            CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(headers).withIgnoreSurroundingSpaces();

            while (fileReader.hasNextLine()) {
                String line = "";
                line = fileReader.nextLine();


                try {
                    CSVRecord record = CSVParser.parse(line, csvFormat).getRecords().get(0);

                    Coordinates coordinates = new Coordinates(
                            Double.parseDouble(record.get("x")),
                            Long.parseLong(record.get("y"))
                    );

                    Person owner = new Person(
                            record.get("owner_name"),
                            record.get("owner_passportID"),
                            Color1.valueOf(record.get("owner_eyeColor")),
                            record.isSet("owner_hairColor") ? Color2.valueOf(record.get("owner_hairColor")) : null,
                            record.isSet("owner_nationality") ? Country.valueOf(record.get("owner_nationality")) : null
                    );

                    Product product = new Product(
                            Integer.parseInt(record.get("id")),
                            record.get("name"),
                            coordinates,
                            LocalDateTime.parse(record.get("creationDate")),
                            Float.parseFloat(record.get("price")),
                            Long.parseLong(record.get("manufactureCost")),
                            UnitOfMeasure.valueOf(record.get("unitOfMeasure")),
                            owner
                    );

                    dataVector.add(product);
                } catch (IOException e) {
                    System.err.println("Ошибка при разборе строки CSV: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println("Файл пуст");
        }
        return dataVector;
    }


    public static String[] getHeader() {
        return new String[]{"id", "name", "x", "y", "creationDate", "price", "manufactureCost", "unitOfMeasure",
                "owner_name", "owner_passportID", "owner_eyeColor", "owner_hairColor", "owner_nationality"};
    }

}
