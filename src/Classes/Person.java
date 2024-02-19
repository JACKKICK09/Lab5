package Classes;

public class Person {
    private final String name;
    private final String passportID;
    private final Color1 eyeColor;
    private final Color2 hairColor;
    private final Country nationality;

    public Person(String name, String passportID, Color1 eyeColor, Color2 hairColor, Country nationality) {
        this.name = name;
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public String getPassportID() {
        return passportID;
    }

    public Color1 getEyeColor() {
        return eyeColor;
    }

    public Color2 getHairColor() {
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return "Classes.Person{name='" + name + "', passportID='" + passportID + "', eyeColor=" + eyeColor +
                ", hairColor=" + hairColor + ", nationality=" + nationality + '}';
    }

    @Override
    public int hashCode(){
        return name.hashCode() + passportID.hashCode() + eyeColor.hashCode() + hairColor.hashCode()
                + nationality.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj instanceof Person personObj) {
            return name.equals(personObj.getName()) && passportID.equals(personObj.getPassportID()) &&
                    eyeColor.equals(personObj.getEyeColor()) && hairColor.equals(personObj.getHairColor()) &&
                    nationality.equals(personObj.getNationality());
        }
        return false;
    }

}
