package namedEntity.Product;

import namedEntity.NamedEntity;

public class Product extends NamedEntity {
    boolean commercial;
    String producer;
    protected static int frequency = 0;

    public Product (String name) {
        super(name);
        Product.frequency++;
    }

    public void setCommercial(boolean commercial) {
        this.commercial = commercial;
    }

    public boolean getCommercial() {
        return commercial;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProducer() {
        return producer;
    }

    public static int getFrequency() { return frequency; }
}