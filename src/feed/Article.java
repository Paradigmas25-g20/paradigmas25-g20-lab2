package feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import namedEntity.Location.Address;
import namedEntity.Location.City;
import namedEntity.Location.Country;
import namedEntity.Location.Location;
import namedEntity.NamedEntity;
import namedEntity.Other.Other;
import namedEntity.Person.Name;
import namedEntity.Person.Person;
import namedEntity.Person.Surname;
import namedEntity.Person.Title;
import namedEntity.heuristic.Heuristic;
import namedEntity.heuristic.QuickHeuristic;

/*Esta clase modela el contenido de un articulo (ie, un item en el caso del rss feed) */

public class Article {
	private String title;
	private String text;
	private Date publicationDate;
	private String link;
	
	private List<NamedEntity> namedEntityList = new ArrayList<NamedEntity>();
	
	
	public Article(String title, String text, Date publicationDate, String link) {
		super();
		this.title = title;
		this.text = text;
		this.publicationDate = publicationDate;
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		return "Article [title=" + title + ", text=" + text + ", publicationDate=" + publicationDate + ", link=" + link
				+ "]";
	}
	
	
	
	public NamedEntity getNamedEntity(String namedEntity){
		for (NamedEntity n: namedEntityList){
			if (n.getName().compareTo(namedEntity) == 0){				
				return n;
			}
		}
		return null;
	}

	// Modificar para el contador acumulativo
	public void computeNamedEntities(Heuristic h){
		String text = this.getTitle() + " " +  this.getText();

		String charsToRemove = ".,;:()'!?\n";
		for (char c : charsToRemove.toCharArray()) {
			text = text.replace(String.valueOf(c), "");
		}
		for (String s: text.split(" ")) {
			if (h.isEntity(s)){
				NamedEntity.create(h.detectConcreteCategory(s));
			}
		}
	}

	public void prettyPrint() {
		System.out.println("**********************************************************************************************");
		System.out.println("Title: " + this.getTitle());
		System.out.println("Publication Date: " + this.getPublicationDate());
		System.out.println("Link: " + this.getLink());
		System.out.println("Text: " + this.getText());
		System.out.println("**********************************************************************************************");
		
	}
	
	public static void main(String[] args) {
		  Article a = new Article("Donald Trump makes a fool in Russia",
			  "In a reunion with Messi and Roger, who just got back from Barcelona, Trump took a bad choice.",
			  new Date(),
			  "https://www.nytimes.com/2023/04/05/technology/bowie-hbcu-tech-intern-pipeline.html"
			  );
		  Heuristic h = new QuickHeuristic();
		  a.computeNamedEntities(h);
		  System.out.println(NamedEntity.getFrequency());
		  System.out.println(Person.getFrequency());
		  System.out.println(Name.getFrequency());
		  System.out.println(Surname.getFrequency());
		  System.out.println(Title.getFrequency());
		  System.out.println(Location.getFrequency());
		  System.out.println(Country.getFrequency());
		  System.out.println(City.getFrequency());
		  System.out.println(Address.getFrequency());
		  System.out.println(Other.getFrequency());
		  a.prettyPrint();
	}
	
	
}



