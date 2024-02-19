package Classes;

public enum Color2 {
    GREEN,
    RED,
    BLUE,
    WHITE,
    BROWN;

    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (Color2 color2Type : values()) {
            nameList.append(color2Type.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
