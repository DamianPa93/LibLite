package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	private final StringProperty login;
	private final StringProperty name;
	private final StringProperty address;
	private final StringProperty phone;
	private final StringProperty email;
	private final StringProperty pesel;
	private final IntegerProperty books;
	private final IntegerProperty orders;
	private final StringProperty status;
	//
	private final IntegerProperty id;
	private final StringProperty password;
	private final StringProperty surname;
	private final StringProperty city;
	private final StringProperty street;
	private final StringProperty apartment;
	private final StringProperty postalCode;
	private final IntegerProperty secId;
	//public String peselString;
	private StringProperty peselString;
	
	public User(int id ,String login, String name, String surname){
		this.login = new SimpleStringProperty(login);
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
		//
		this.password = null;
		this.city = null;
		this.street = null;
		this.apartment = null;
		this.postalCode = null;
		this.phone = null;
		this.email = null;
		this.pesel = null;
		this.status = null;
		this.orders = null;
		this.address = null;
		this.books = null;
		this.secId = null;
	}
	
	public User(String login, String password, String name, String surname, String city, String street,
			String apartment, String postalCode, String phone, String email, String pesel, String status){
		this.login = new SimpleStringProperty(login);
		this.password = new SimpleStringProperty(password);
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
		this.city = new SimpleStringProperty(city);
		this.street = new SimpleStringProperty(street);
		this.apartment = new SimpleStringProperty(apartment);
		this.postalCode = new SimpleStringProperty(postalCode);
		this.phone = new SimpleStringProperty(phone);
		this.email = new SimpleStringProperty(email);
		this.pesel = new SimpleStringProperty(pesel);
		this.status = new SimpleStringProperty(status);
		this.orders = null;
		this.address = null;
		this.id = null;
		this.books = null;
		this.secId = null;
	}
	
	public User(String login, String name, String address, String phone, String email,
				String pesel, int books, int orders, String status){
		this.login = new SimpleStringProperty(login);
		this.name = new SimpleStringProperty(name);
		this.address = new SimpleStringProperty(address);
		this.phone = new SimpleStringProperty(phone);
		this.email = new SimpleStringProperty(email);
		this.pesel = new SimpleStringProperty(pesel);
		this.books = new SimpleIntegerProperty(books);
		this.orders = new SimpleIntegerProperty(orders);
		this.status = new SimpleStringProperty(status);
		//
		this.id = null;
		this.password = null;
		this.surname = null;
		this.city = null;
		this.street = null;
		this.apartment = null;
		this.postalCode = null;
		this.secId = null;
	}
	
	public User(int id, String login, String password, String name, String surname, String city, String street,
				String apartment, String postalCode, String phone, String email, int secId, String status){
		this.id = new SimpleIntegerProperty(id);
		this.login = new SimpleStringProperty(login);
		this.password = new SimpleStringProperty(password);
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
		this.city = new SimpleStringProperty(city);
		this.street = new SimpleStringProperty(street);
		this.apartment = new SimpleStringProperty(apartment);
		this.postalCode = new SimpleStringProperty(postalCode);
		this.phone = new SimpleStringProperty(phone);
		this.email = new SimpleStringProperty(email);
		this.secId = new SimpleIntegerProperty(secId);
		this.status = new SimpleStringProperty(status);
		//
		this.books = null;
		this.address = null;
		this.pesel = null;
		this.orders = null;
	}
	
	public User(int id, String login, String password, String name, String surname, String city, String street,
			String apartment, String postalCode, String phone, String email, String secId, String status){
	this.id = new SimpleIntegerProperty(id);
	this.login = new SimpleStringProperty(login);
	this.password = new SimpleStringProperty(password);
	this.name = new SimpleStringProperty(name);
	this.surname = new SimpleStringProperty(surname);
	this.city = new SimpleStringProperty(city);
	this.street = new SimpleStringProperty(street);
	this.apartment = new SimpleStringProperty(apartment);
	this.postalCode = new SimpleStringProperty(postalCode);
	this.phone = new SimpleStringProperty(phone);
	this.email = new SimpleStringProperty(email);
	this.secId = null;
	this.peselString = new SimpleStringProperty(secId);
	this.status = new SimpleStringProperty(status);
	//
	this.books = null;
	this.address = null;
	this.pesel = null;
	this.orders = null;
}
	
	public String getLogin(){return login.get();}
	public String getName(){return name.get();}
	public String getAddress(){return address.get();}
	public String getPhone(){return phone.get();}
	public String getEmail(){return email.get();}
	public String getPesel(){return pesel.get();}
	public int getBooks(){return books.get();}
	public int getOrders(){return orders.get();}
	public String getStatus(){return status.get();}
	//
	public int getId(){return id.get();}
	public String getPassword(){return password.get();}
	public String getSurname(){return surname.get();}
	public String getCity(){return city.get();}
	public String getStreet(){return street.get();}
	public String getApartment(){return apartment.get();}
	public String getPostalCode(){return postalCode.get();}
	public int getSecId(){return secId.get();}
	//
	public String getPeselString(){return peselString.get();}
	
	public void setLogin(String value){login.set(value);}
	public void setName(String value){name.set(value);}
	public void setAddress(String value){address.set(value);}
	public void setPhone(String value){phone.set(value);}
	public void setEmail(String value){email.set(value);}
	public void setPesel(String value){pesel.set(value);}
	public void setBooks(int value){books.set(value);}
	public void setOrders(int value){orders.set(value);}
	public void setStatus(String value){status.set(value);}
	//
	public void setId(int value){id.set(value);}
	public void setPassword(String value){password.set(value);}
	public void setSurname(String value){surname.set(value);}
	public void setCity(String value){city.set(value);}
	public void setStreet(String value){street.set(value);}
	public void setApartment(String value){apartment.set(value);}
	public void setPostalCode(String value){postalCode.set(value);}
	public void setSecId(int value){secId.set(value);}
	//
	public void setPeselString(String value){peselString.set(value);}
	
	public StringProperty loginProperty(){return login;}
	public StringProperty nameProperty(){return name;}
	public StringProperty addressProperty(){return address;}
	public StringProperty phoneProperty(){return phone;}
	public StringProperty emailProperty(){return email;}
	public StringProperty peselProperty(){return pesel;}
	public IntegerProperty booksProperty(){return books;}
	public IntegerProperty ordersProperty(){return orders;}
	public StringProperty statusProperty(){return status;}
	//
	public IntegerProperty idProperty(){return id;}
	public StringProperty passwordProperty(){return password;}
	public StringProperty surnameProperty(){return surname;}
	public StringProperty cityProperty(){return city;}
	public StringProperty streetProperty(){return street;}
	public StringProperty apartmentProperty(){return apartment;}
	public StringProperty postalCodeProperty(){return postalCode;}
	public IntegerProperty secIdProperty(){return secId;}
	//
	public StringProperty peselStringProperty(){return peselString;}
	
	@Override
	public String toString(){
		return getLogin() + ", " + getName() + " " + getSurname();
	}
}
