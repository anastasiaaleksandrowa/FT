import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

