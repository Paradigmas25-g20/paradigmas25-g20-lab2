package namedEntity.Date;

import namedEntity.NamedEntity;

public class Date extends NamedEntity {
    String precise;
    String canonicalForm;
    protected static int frequency = 0;

    public Date(String name) {
        super(name);
        Date.frequency++;
    }

    public void setPrecise(String precise) {
        this.precise = precise;
    }

    public String getPrecise() {
        return precise;
    }

    public void setCanonicalForm(String canonicalForm) {
        this.canonicalForm = canonicalForm;
    }

    public String getCanonicalForm() {
        return canonicalForm;
    }

    public static int getFrequency() {
        return frequency;
    }
}
