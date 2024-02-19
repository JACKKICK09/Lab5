package Classes;

public enum Color1 {
    GREEN,
    BLACK,
    YELLOW;

    public static String nameList() {
        String nameList = "";
        for (Color1 colorType : values()) {
            nameList += colorType.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
