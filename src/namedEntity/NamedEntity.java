package namedEntity;


/*Esta clase modela la nocion de entidad nombrada*/

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

	public String toString() {
		return "ObjectNamedEntity [name=" + name + ", frequency=" + frequency + "]";
	}
	public void prettyPrint(){
		System.out.println(this.getName() + " " + this.getFrequency());
	}
	
	
}



