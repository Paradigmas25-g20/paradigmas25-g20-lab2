package namedEntity.DateNe;

import namedEntity.NamedEntity;

public class DateNe extends NamedEntity {
    String precise;
    String canonicalForm;
    protected static int frequency = 0;

    public DateNe(String name) {
        super(name);
        DateNe.frequency++;
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
