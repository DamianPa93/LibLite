package application.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Loan {
	private final StringProperty borrower;
	private final StringProperty title;
	private final StringProperty isbn;
	private final StringProperty dateFrom;
	private final StringProperty returnTo;
	private final StringProperty comments;
	
	public Loan(String borrower, String title, String isbn, String dateFrom, String returnTo, String comments){
		this.borrower = new SimpleStringProperty(borrower);
		this.title = new SimpleStringProperty(title);
		this.isbn = new SimpleStringProperty(isbn);
		this.dateFrom = new SimpleStringProperty(dateFrom);
		this.returnTo = new SimpleStringProperty(returnTo);
		this.comments = new SimpleStringProperty(comments);
	}
	
	public String getBorrower(){return borrower.get();}
	public String getTitle(){return title.get();}
	public String getIsbn(){return isbn.get();}
	public String getdateFrom(){return dateFrom.get();}
	public String getReturnTo(){return returnTo.get();}
	public String getComments(){return comments.get();}
	
	public void setBorrower(String value){borrower.set(value);}
	public void setTitle(String value){title.set(value);}
	public void setIsbn(String value){isbn.set(value);}
	public void setDateFrom(String value){dateFrom.set(value);}
	public void setReturnTo(String value){returnTo.set(value);}
	public void setComments(String value){comments.set(value);}
	
	public StringProperty borrowerProperty(){return borrower;}
	public StringProperty titleProperty(){return title;}
	public StringProperty isbnProperty(){return isbn;}
	public StringProperty dateFromProperty(){return dateFrom;}
	public StringProperty returnToProperty(){return returnTo;}
	public StringProperty commentsProperty(){return comments;}
}
