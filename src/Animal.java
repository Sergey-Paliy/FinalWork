import java.util.ArrayList;
import java.util.List;

public class Animal {
    private String name;
    private int age;
    private List<String> commands = new ArrayList<>();

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void executeCommand(String command) {
        System.out.println(name + " - " + command);
    }

    public void learnCommand(String newCommand) {
        commands.add(newCommand);
        System.out.println(name + " is learning a new command: " + newCommand);
    }
    public List<String> getCommands() {
        return commands;
    }


    public String getAnimalType() {
        return "Animal";
    }

    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Type: " + getAnimalType();
    }
}
