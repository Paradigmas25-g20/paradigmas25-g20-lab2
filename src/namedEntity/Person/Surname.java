package namedEntity.Person;

public class Surname extends Person {
    String canonicalForm;
    String origin;
    protected static int frequency = 0;

    public Surname(String name) {
        super(name);
        Surname.frequency++;
    }

    public void setCanonicalForm(String canonicalForm) { this.canonicalForm = canonicalForm; }

    public String getCanonicalForm() { return this.canonicalForm; }

    public void setOrigin(String origin) { this.origin = origin; }

    public String getOrigin() { return this.origin; }

    public static int getFrequency() { return frequency; }

}
