package namedEntity.Location;

public class City extends Location {
    private String country;
    private String capital;
    private int poblacion;
    protected static int frequency = 0;

    public City(String name) {
        super(name);
        City.frequency++;

    }

    public  void setCapital(String capital) {this.capital = capital;}
    public void setCountry(String country) {this.country = country;}
    public void setPoblacion(int poblacion) {this.poblacion = poblacion;}

    public String getCountry() {return this.country;}
    public String getCapital() {return this.capital;}
    public int getPoblacion() {return this.poblacion;}

    public static int getFrequency() { return frequency; }
}
