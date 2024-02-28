// Файл Pets.java
public class Pets extends Animal {
    public Pets(String name, int age) {
        super(name, age);
    }

    @Override
    public String getAnimalType() {
        return "Pet";
    }
}
