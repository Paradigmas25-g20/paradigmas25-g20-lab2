package namedEntity.Other;

import namedEntity.NamedEntity;

public class Other extends NamedEntity {
    String comments;
    protected static int frequency = 0;

    public Other (String name) {
        super(name);
        Other.frequency++;
    }

    public void setComments(String comments) { this.comments = comments; }

    public String getComments() { return comments; }

    public static int getFrequency() { return frequency; }
}
