package commands;

public interface Describable {
    String getName();
    String getDescription();
    boolean doIt(String e);
}