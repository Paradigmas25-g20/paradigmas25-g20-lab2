package namedEntity.Person;

import namedEntity.NamedEntity;

public class Person extends NamedEntity {
    String id;
    protected static int frequency = 0;

    public Person (String name) {
        super(name);
        Person.frequency++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static int getFrequency() {
        return frequency;
    }
}
