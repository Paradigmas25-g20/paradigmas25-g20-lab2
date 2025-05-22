package namedEntity.Person;

public class Title extends Person {
    String canonicalForm;
    String professional;
    protected static int frequency = 0;

    public Title(String name) {
        super(name);
        Title.frequency++;
    }

    public void setCanonicalForm(String canonicalForm) { this.canonicalForm = canonicalForm; }

    public String getCanonicalForm() { return this.canonicalForm; }

    public void setProfessional(String professional) { this.professional = professional; }

    public String getProfessional() { return professional; }

    public static int getFrequency() { return frequency; }

}

