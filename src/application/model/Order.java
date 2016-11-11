package application.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Order {
	private final StringProperty orderer;
	private final StringProperty title;
	private final StringProperty orderDate;
	private final StringProperty status;
	private final StringProperty comments;
	
	public Order(String orderer, String title, String orderDate, String status, String comments){
		this.orderer = new SimpleStringProperty(orderer);
		this.title = new SimpleStringProperty(title);
		this.orderDate = new SimpleStringProperty(orderDate);
		this.status = new SimpleStringProperty(status);
		this.comments = new SimpleStringProperty(comments);
	}
	
	public String getOrderer(){return orderer.get();}
	public String getTitle(){return title.get();}
	public String getOrderDate(){return orderDate.get();}
	public String getStatus(){return status.get();}
	public String getComments(){return comments.get();}
	
	public void setOrderer(String value){orderer.set(value);}
	public void setTitle(String value){title.set(value);}
	public void setOrderDate(String value){orderDate.set(value);}
	public void setStatus(String value){status.set(value);}
	public void setComments(String value){comments.set(value);}
	
	public StringProperty ordererProperty(){return orderer;}
	public StringProperty titleProperty(){return title;}
	public StringProperty orderDateProperty(){return orderDate;}
	public StringProperty statusProperty(){return status;}
	public StringProperty commentsProperty(){return comments;}
}
