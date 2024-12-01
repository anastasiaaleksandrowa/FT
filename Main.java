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

        // Получаем детей Алисы
        List<Person> childrenOfAlice = familyTree.getChildrenOf("Alice");

        // Выводим детей Алисы
        if (childrenOfAlice != null) {
            System.out.println("Дети " + alice.getName() + ": " + 
                childrenOfAlice.stream().map(Person::getName).reduce((a, b) -> a + ", " + b).orElse("нет детей"));
        } else {
            System.out.println("Человек не найден.");
        }
    }

    @Override
    public String toString() {
        return "Main []";
    }
}

class Person {
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

class FamilyTree {
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
}

