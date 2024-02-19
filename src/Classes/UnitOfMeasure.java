package Classes;

public enum UnitOfMeasure {
    CENTIMETERS,
    SQUARE_METERS,
    LITERS,
    GRAMS;

    public static String nameList() {
        String nameList = "";
        for (UnitOfMeasure unitOfMeasureType : values()) {
            nameList += unitOfMeasureType.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
