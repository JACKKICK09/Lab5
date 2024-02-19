package utility;

import Bugs.EmptyFile;
import Classes.Person;
import Classes.Product;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class ManagerOfCollections {
    private static Vector<Product> ProductCollections = new Vector<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;

    private final FileReaderWriter fileReaderWriter;

    public ManagerOfCollections(FileReaderWriter fileReaderWriter){
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileReaderWriter = fileReaderWriter;

        loadCollection();
    }


    public Vector<Product> getProductCollections(){
        return ProductCollections;
    }

    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public String collectionType(){
        return ProductCollections.getClass().getName();
    }

    public int collectionSize(){
        return ProductCollections.size();
    }

    public Product getFirst(){
        if (ProductCollections.isEmpty()) return null;
        return  ProductCollections.firstElement();
    }

    public Product getLast(){
        if (ProductCollections.isEmpty()) return null;
        return ProductCollections.lastElement();
    }

    public Product getById(Integer id){
        for (Product product : ProductCollections){
            if (product.getId() == id) return product;
        }
        return null;
    }

    public Product getByValue(Product product){
        for (Product p : ProductCollections){
            if (p.equals(product)) return  p;
        }
        return null;
    }

    public int countLessThanOwner(Person owner) {
        int count = 0;
        for (Product product : ProductCollections) {
            Person productOwner = product.getOwner();
            if (productOwner != null && productOwner.getName().compareTo(owner.getName()) < 0) {
                count++;
            }
        }
        return count;
    }

    public List<Product> filterStartsWithName(String substring) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : ProductCollections) {
            if (product.getName().startsWith(substring)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }


    public List<Product> printFieldDescendingUnitOfMeasure() {
        List<Product> sortedProducts = new ArrayList<>();
        try {
            if (ProductCollections == null || ProductCollections.isEmpty()) {
                throw new EmptyFile();
            }

            sortedProducts.addAll(ProductCollections);
            sortedProducts.sort(Comparator.comparing(Product::getUnitOfMeasure).reversed());
        } catch (EmptyFile e) {
            Console.printerror("Коллекция пуста");
        }
        return sortedProducts;
    }



    public int generateNextId() {
        if (ProductCollections.isEmpty()) return 1;
        return ProductCollections.lastElement().getId() + 1;
    }

    public void addToCollection(Product product) {
       ProductCollections.add(product);
    }

    public void removeFromCollection(Product product) {
        ProductCollections.remove(product);
    }

    public void removeLower(Product product) {
        ProductCollections.removeIf(marine -> marine.compareTo(product) < 0);
    }

    public void clearCollection() {
        ProductCollections.clear();
    }

    public void saveCollection(ManagerOfCollections managerOfCollections) throws IOException {
        try {
            fileReaderWriter.write(managerOfCollections.getProductCollections());
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        lastSaveTime = LocalDateTime.now();
    }
    private void loadCollection() {
        ProductCollections = fileReaderWriter.readFromFile();
        lastInitTime = LocalDateTime.now();
    }



    @Override
    public String toString() {
        if (ProductCollections.isEmpty()) return "Коллекция пуста!";

        String info = "";
        for (Product p : ProductCollections) {
            info += p;
            if (p != ProductCollections.lastElement()) info += "\n\n";
        }
        return info;
    }
}
