package namedEntity.Location;

import namedEntity.NamedEntity;

public class Location extends NamedEntity {

    protected static int frequency = 0;


    public Location(String name) {
        super(name);

        Location.frequency++;
    }

    public int  getFerecuency() {return frequency;}
}
