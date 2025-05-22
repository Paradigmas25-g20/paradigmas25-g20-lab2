package namedEntity.Location;

public class Country extends Location {
    String capital;
    protected static int frequency = 0;

    public Country(String name) {
        super(name);
        Country.frequency++;
    }

    public void setCapital(String capital) { this.capital = capital; }

    public String getCapital() { return capital; }

    public static int  getFrequency() {return frequency;}
}
