package namedEntity.Person;

public class Name extends Person {
    String canonicalForm;
    String origin;
    String nickname;
    protected static int frequency = 0;

    public Name(String name) {
        super(name);
        Name.frequency++;
    }

    public void setCanonicalForm(String canonicalForm) { this.canonicalForm = canonicalForm; }

    public String getCanonicalForm() { return canonicalForm; }

    public void setOrigin(String origin) { this.origin = origin; }

    public String getOrigin() { return origin; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getNickname() { return nickname; }

    public static int getFrequency() { return frequency; }
}

