package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Publisher {
	private final StringProperty name;
	private final StringProperty country;
	private final IntegerProperty pubs;
	//
	private final IntegerProperty id;
	private final StringProperty surname;
	private final StringProperty organization;
	
	public Publisher(String name, String country, int pubs){
		this.name = new SimpleStringProperty(name);
		this.country = new SimpleStringProperty(country);
		this.pubs = new SimpleIntegerProperty(pubs);
		//
		this.id = null;
		this.surname = null;
		this.organization = null;
	}
	
	public Publisher(int id, String name, String surname, String organization, String country){
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
		this.organization = new SimpleStringProperty(organization);
		this.country = new SimpleStringProperty(country);
		//
		this.pubs = null;
	}
	
	public String getName(){return name.get();}
	public String getCountry(){return country.get();}
	public int getPubs(){return pubs.get();}
	//
	public int getId(){return id.get();}
	public String getSurname(){return surname.get();}
	public String getOrganization(){return organization.get();}
	
	public void setName(String value){name.set(value);}
	public void setCountry(String value){country.set(value);}
	public void setPubs(int value){pubs.set(value);}
	//
	public void setId(int value){id.set(value);}
	public void setSurname(String value){surname.set(value);}
	public void setOrganization(String value){organization.set(value);}
	
	public StringProperty nameProperty(){return name;}
	public StringProperty countryProperty(){return country;}
	public IntegerProperty pubsProperty(){return pubs;}
	//
	public IntegerProperty idProperty(){return id;}
	public StringProperty surnameProperty(){return surname;}
	public StringProperty organizationProperty(){return organization;}
}
