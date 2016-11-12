package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Author {
	private final StringProperty name;
	private final StringProperty birth;
	private final StringProperty country;
	private final StringProperty comments;
	//
	private final IntegerProperty id;
	private final StringProperty surname;
	
	//public String strId;
	
	public Author(int id, String name, String surname){
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
		this.birth = null;
		this.country = null;
		//this.id = null;
		this.comments = null;
	}
	
	public Author(String name, String birth, String country, String comments){
		this.name = new SimpleStringProperty(name);
		this.birth = new SimpleStringProperty(birth);
		this.country = new SimpleStringProperty(country);
		this.comments = new SimpleStringProperty(comments);
		//
		this.id = null;
		this.surname = null;
	}
	
	public Author(int id, String name, String surname, String country, String birth, String comments){
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
		this.country = new SimpleStringProperty(country);
		this.birth = new SimpleStringProperty(birth);
		this.comments = new SimpleStringProperty(comments);
	}
	
	public Author(String name, String surname, String country, String birth, String comments){
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
		this.country = new SimpleStringProperty(country);
		this.birth = new SimpleStringProperty(birth);
		this.comments = new SimpleStringProperty(comments);
		//
		this.id = null;
	}
	
	public String getName(){return name.get();}
	public String getBirth(){return birth.get();}
	public String getCountry(){return country.get();}
	public String getComments(){return comments.get();}
	//
	public int getId(){return id.get();}
	public String getSurname(){return surname.get();}
	
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
	//
	public void setId(int value){
		id.set(value);
	}
	
	public void setSurname(String value){
		surname.set(value);
	}
	
	public StringProperty nameProperty(){return name;}
	public StringProperty birthProperty(){return birth;}
	public StringProperty countryProperty(){return country;}
	public StringProperty commentsProperty(){return comments;}
	//
	public IntegerProperty idProperty(){return id;}
	public StringProperty surnameProperty(){return surname;}
	
	@Override
	public String toString(){
		return name.get() + ", " + surname.get();
	}
}
