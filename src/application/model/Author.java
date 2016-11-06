package application.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Author {
	private final StringProperty name;
	private final StringProperty birth;
	private final StringProperty country;
	private final StringProperty comments;
	
	public Author(String name, String birth, String country, String comments){
		this.name = new SimpleStringProperty(name);
		this.birth = new SimpleStringProperty(birth);
		this.country = new SimpleStringProperty(country);
		this.comments = new SimpleStringProperty(comments);
	}
	
	public String getName(){return name.get();}
	public String getBirth(){return birth.get();}
	public String getCountry(){return country.get();}
	public String getComments(){return comments.get();}
	
	public void setName(String value){
		name.set(value);
	}
	
	public void setBirth(String value){
		birth.set(value);
	}
	
	public void setCountry(String value){
		country.set(value);
	}
	
	public void setComments(String value){
		comments.set(value);
	}
	
	public StringProperty nameProperty(){return name;}
	public StringProperty birthProperty(){return birth;}
	public StringProperty countryProperty(){return country;}
	public StringProperty commentsProperty(){return comments;}
}
