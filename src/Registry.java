import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Registry {
    private static Map<String, List<String>> petClasses = new HashMap<>();
    private static Map<String, List<String>> packAnimalClasses = new HashMap<>();

    static {
        // Заполним начальные классы для pets и pack animals
        List<String> petClassList = new ArrayList<>();
        petClassList.add("Cat");
        petClassList.add("Dog");
        petClassList.add("Hamster");
        petClasses.put("Pets", petClassList);

        List<String> packAnimalClassList = new ArrayList<>();
        packAnimalClassList.add("Horse");
        packAnimalClassList.add("Donkey");
        packAnimalClassList.add("Camel");
        packAnimalClasses.put("PackAnimal", packAnimalClassList);
    }

    public static void main(String[] args) {
        try (Counter counter = new Counter()) {
            Scanner scanner = new Scanner(System.in);
            Animal pet = null;

            while (true) {
                System.out.println("\n=== Меню Реестра Домашних Животных ===");
                System.out.println("1. Завести новое животное");
                System.out.println("2. Список команд для животного");
                System.out.println("3. Обучить животное новой команде");
                System.out.println("4. Выход");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Выберите категорию животного:");
                        System.out.println("1. Pets");
                        System.out.println("2. PackAnimal");
                        int categoryChoice = scanner.nextInt();

                        if (categoryChoice == 1) {
                            pet = createPet(scanner);
                        } else if (categoryChoice == 2) {
                            pet = createPackAnimal(scanner);
                        } else {
                            System.out.println("Неверный выбор категории");
                        }
                        break;
                    case 2:
                        if (pet != null) {
                            System.out.println("Команды для " + pet.getAnimalType() + " " + pet.toString());
                            for (String command : pet.getCommands()) {
                                pet.executeCommand(command);
                            }
                        } else {
                            System.out.println("Животное не выбрано. Пожалуйста, сначала заведите новое животное.");
                        }
                        break;
                    case 3:
                        if (pet != null) {
                            System.out.println("Введите новую команду для обучения:");
                            String newCommand = scanner.next();
                            pet.learnCommand(newCommand);
                        } else {
                            System.out.println("Животное не выбрано. Пожалуйста, сначала заведите новое животное.");
                        }
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, введите число от 1 до 4.");
                        break;
                }

                counter.add(); // Увеличение счетчика при добавлении нового животного
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Animal createPet(Scanner scanner) {
        System.out.println("Выберите класс животного:");
        List<String> petClassList = petClasses.get("Pets");
        for (int i = 0; i < petClassList.size(); i++) {
            System.out.println((i + 1) + ". " + petClassList.get(i));
        }
        int petClassChoice = scanner.nextInt();

        if (petClassChoice >= 1 && petClassChoice <= petClassList.size()) {
            String className = petClassList.get(petClassChoice - 1);
            System.out.println("Введите имя животного:");
            String petName = scanner.next();
            scanner.nextLine(); // Очистка буфера после nextInt()

            System.out.println("Введите дату рождения животного в формате дд.мм.гггг:");
            String birthDateStr = scanner.nextLine();

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date birthDate = dateFormat.parse(birthDateStr);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(birthDate);

                int petAge = calculateAge(calendar.getTime(), new Date());

                Animal newPet = createAnimalInstance(className, petName, petAge);
                return newPet;
            } catch (ParseException e) {
                System.out.println("Неверный формат даты рождения.");
                return null;
            }
        } else {
            System.out.println("Неверный выбор класса");
            return null;
        }
    }

    private static Animal createPackAnimal(Scanner scanner) {
        System.out.println("Выберите класс животного:");
        List<String> packAnimalClassList = packAnimalClasses.get("PackAnimal");
        for (int i = 0; i < packAnimalClassList.size(); i++) {
            System.out.println((i + 1) + ". " + packAnimalClassList.get(i));
        }
        int packAnimalClassChoice = scanner.nextInt();

        if (packAnimalClassChoice >= 1 && packAnimalClassChoice <= packAnimalClassList.size()) {
            String className = packAnimalClassList.get(packAnimalClassChoice - 1);
            System.out.println("Введите имя животного:");
            String packAnimalName = scanner.next();
            scanner.nextLine(); // Очистка буфера после nextInt()

            System.out.println("Введите дату рождения животного в формате дд.мм.гггг:");
            String birthDateStr = scanner.nextLine();

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date birthDate = dateFormat.parse(birthDateStr);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(birthDate);

                int packAnimalAge = calculateAge(calendar.getTime(), new Date());

                Animal newPackAnimal = createAnimalInstance(className, packAnimalName, packAnimalAge);
                return newPackAnimal;
            } catch (ParseException e) {
                System.out.println("Неверный формат даты рождения.");
                return null;
            }
        } else {
            System.out.println("Неверный выбор класса");
            return null;
        }
    }

    private static Animal createAnimalInstance(String className, String name, int age) {
        switch (className) {
            case "Cat":
                return new Cat(name, age);
            case "Dog":
                return new Dog(name, age);
            case "Hamster":
                return new Hamster(name, age);
            case "Horse":
                return new Horse(name, age);
            case "Donkey":
                return new Donkey(name, age);
            case "Camel":
                return new Camel(name, age);
            default:
                System.out.println("Неверный класс животного");
                return null;
        }
    }

    private static int calculateAge(Date birthDate, Date currentDate) {
        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(birthDate);

        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(currentDate);

        int age = currentCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);

        if (currentCal.get(Calendar.DAY_OF_YEAR) < birthCal.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }

}

// ... (остальные классы без изменений)
