package namedEntity.Location;

public class Address extends Location {
    private String city;
    protected static int frequency = 0;

    public Address(String name) {
        super(name);
        Address.frequency++;
    }
    public static int getFrequency() {return frequency;}

    public  void setCity(String capital) {this.city = capital;}

    public String getCity() {return this.city;}
}
