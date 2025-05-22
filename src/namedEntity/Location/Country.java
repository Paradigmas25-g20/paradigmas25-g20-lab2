package namedEntity.Location;

public class Country extends Location {

    protected static int frecuency=0 ;
    public Country(String name) {
        super(name);


        Country.frecuency++;

    }
    public int  getFerecuency() {return frequency;}
}
