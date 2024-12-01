import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Создаем людей
        Person alice = new Person("Alice");
        Person bob = new Person("Bob");
        Person charlie = new Person("Charlie");

        // Структура семьи
        alice.addChild(bob);
        alice.addChild(charlie);

        // Создаем генеалогическое древо
        FamilyTree familyTree = new FamilyTree();
        familyTree.addPerson(alice);
        familyTree.addPerson(bob);
        familyTree.addPerson(charlie);

        // Запись в файл
        FileHandler fileHandler = new FileHandlerImpl();
        fileHandler.writeToFile(familyTree, "family_tree.dat");

        // Чтение из файла
        FamilyTree loadedFamilyTree = fileHandler.readFromFile("family_tree.dat");
        List<Person> childrenOfAlice = loadedFamilyTree.getChildrenOf("Alice");

        // Выводим детей Альисы
        if (childrenOfAlice != null) {
            System.out.println("Дети " + alice.getName() + ": " + 
                childrenOfAlice.stream().map(Person::getName).reduce((a, b) -> a + ", " + b).orElse("нет детей"));
        }
    }

    @Override
    public String toString() {
        return "Main []";
    }
}

class Person implements Serializable {
    private static final long serialVersionUID = 1L; // Для сериализации
    private String name;
    private List<Person> children;

    public Person(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(Person child) {
        this.children.add(child);
    }

    public List<Person> getChildren() {
        return this.children;
    }

    public String getName() {
        return this.name;
    }
}

class FamilyTree implements Serializable {
    private static final long serialVersionUID = 1L; // Для сериализации
    private Map<String, Person> people;

    public FamilyTree() {
        this.people = new HashMap<>();
    }

    public void addPerson(Person person) {
        this.people.put(person.getName(), person);
    }

    public Person findPerson(String name) {
        return this.people.get(name);
    }

    public List<Person> getChildrenOf(String personName) {
        Person person = findPerson(personName);
        if (person != null) {
            return person.getChildren();
        } else {
            return null;
        }
    }

    public Map<String, Person> getPeople() {
        return people; // Добавлено для поддержки сериализации
    }
}

