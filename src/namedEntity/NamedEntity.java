package namedEntity;


/*Esta clase modela la nocion de entidad nombrada*/

import namedEntity.Company.Company;
import namedEntity.DateNe.DateNe;
import namedEntity.Event.Event;
import namedEntity.Location.Address;
import namedEntity.Location.City;
import namedEntity.Location.Country;
import namedEntity.Location.Location;
import namedEntity.Other.Other;
import namedEntity.Person.Name;
import namedEntity.Person.Person;
import namedEntity.Person.Surname;
import namedEntity.Person.Title;
import namedEntity.Product.Product;

public class NamedEntity {
	String name;
//	String category;
//	String subcategory;
	protected static int frequency = 0;


	
	public NamedEntity(String name) {
		super();
		this.name = name;
//		this.category = category;
//		this.category = subcategory;
		// Hago el contador de ocurrencias ESTÁTICO, para que aumente cada vez
		// que creo algún objeto que herede de NamedEntity.
		NamedEntity.frequency++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return name;
	}

	public void setCategory(String name) {
		this.name = name;
	}

	public static int getFrequency() {
		return frequency;
	}

//	public void setFrequency(int frequency) {
//		this.frequency = frequency;
//	}

//	public void incFrequency() {
//		this.frequency++;
//	}
	public static void create(String entityToCreate) {
		switch (entityToCreate) {
			case "Name" -> new Name(entityToCreate);
			case "Surname" -> new Surname(entityToCreate);
			case "Title" -> new Title(entityToCreate);
			case "Country" -> new Country(entityToCreate);
			case "City" -> new City(entityToCreate);
			case "Address" -> new Address(entityToCreate);
			case "Company" -> new Company(entityToCreate);
			case "Event" -> new Event(entityToCreate);
			case "Date" -> new DateNe(entityToCreate);
			default -> new Other(entityToCreate);
		}

	}

	public String toString() {
		return "ObjectNamedEntity [name=" + name + ", frequency=" + frequency + "]";
	}

	public static void prettyPrint() {
		if (NamedEntity.getFrequency() != 0) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Named Entities recognized in all feeds:");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Total:" + " " + NamedEntity.getFrequency());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			if (Person.getFrequency() != 0) {
				System.out.println("Person:" + " " + Person.getFrequency());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Name" + " " + Name.getFrequency());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Surname:" + " " + Surname.getFrequency());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Title:" + " " + Title.getFrequency());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
			if (Location.getFrequency() != 0) {
				System.out.println("Location:" + " " + Location.getFrequency());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Country:" + " " + Country.getFrequency());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("City:" + " " + City.getFrequency());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Address:" + " " + Address.getFrequency());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
			if (Company.getFrequency() != 0) {
				System.out.println("Company:" + " " + Company.getFrequency());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
			if (Product.getFrequency() != 0) {
				System.out.println("Product:" + " " + Product.getFrequency());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
			if (Event.getFrequency() != 0) {
				System.out.println("Event:" + " " + Event.getFrequency());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
			if (Product.getFrequency() != 0) {
				System.out.println("Product:" + " " + Product.getFrequency());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
			if (DateNe.getFrequency() != 0) {
				System.out.println("Date:" + " " + DateNe.getFrequency());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
			if (Other.getFrequency() != 0) {
				System.out.println("Other:" + " " + Other.getFrequency());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
		}
	}

	public static void main(String[] args) {
	}
}



