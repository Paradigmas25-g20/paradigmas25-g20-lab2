package namedEntity.Location;

public class Address extends Location {
    private String city;
    protected static int frecuency=0 ;
    public Address(String name) {
        super(name);


        Country.frecuency++;

    }
    public int  getFerecuency() {return frequency;}


    public  void setCity(String capital) {this.city = capital;}
    public String getCity() {return this.city;}
}
