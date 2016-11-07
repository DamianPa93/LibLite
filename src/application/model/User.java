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
	
	public void setLogin(String value){login.set(value);}
	public void setName(String value){name.set(value);}
	public void setAddress(String value){address.set(value);}
	public void setPhone(String value){phone.set(value);}
	public void setEmail(String value){email.set(value);}
	public void setPesel(String value){pesel.set(value);}
	public void setBooks(int value){books.set(value);}
	public void setOrders(int value){orders.set(value);}
	public void setStatus(String value){status.set(value);}
	
	public StringProperty loginProperty(){return login;}
	public StringProperty nameProperty(){return name;}
	public StringProperty addressProperty(){return address;}
	public StringProperty phoneProperty(){return phone;}
	public StringProperty emailProperty(){return email;}
	public StringProperty peselProperty(){return pesel;}
	public IntegerProperty booksProperty(){return books;}
	public IntegerProperty ordersProperty(){return orders;}
	public StringProperty statusProperty(){return status;}
}
