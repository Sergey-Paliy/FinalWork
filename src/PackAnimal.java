// Файл PackAnimal.java
public class PackAnimal extends Animal {
    public PackAnimal(String name, int age) {
        super(name, age);
    }

    @Override
    public String getAnimalType() {
        return "Pack Animal";
    }
}

