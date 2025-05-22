package namedEntity.Event;

import namedEntity.NamedEntity;

import java.util.Date;

public class Event extends NamedEntity {
    String canonicalForm;
    Date date;
    boolean recurrent;
    protected static int frequency = 0;

    public Event(String name) {
        super(name);
    }

    public void setCanonicalForm(String canonicalForm) {
        this.canonicalForm = canonicalForm;
    }

    public String getCanonicalForm() {
        return canonicalForm;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setRecurrent(boolean recurrent) {
        this.recurrent = recurrent;
    }

    public boolean isRecurrent() {
        return recurrent;
    }

    public static int getFrequency() { return frequency; }
}
