package application.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
	private final StringProperty isbn;
	private final StringProperty author;
	private final StringProperty category;
	private final StringProperty title;
	
	public Book(String isbn, String author, String category, String title){
		
		this.isbn = new SimpleStringProperty(isbn);
		this.author = new SimpleStringProperty(author);
		this.category = new SimpleStringProperty(category);
		this.title = new SimpleStringProperty(title);
	}

	public String getIsbn(){return isbn.get();}
	public String getAuthor(){return author.get();}
	public String getCategory(){return category.get();}
	public String getTitle(){return title.get();}
	
	public void setIsbn(String value){
		isbn.set(value);
	}
	
	public void setAuthor(String value){
		author.set(value);
	}
	
	public void setCategory(String value){
		category.set(value);
	}
	
	public void setTitle(String value){
		title.set(value);
	}
	
	public StringProperty isbnProperty(){return isbn;}
	public StringProperty authorProperty(){return author;}
	public StringProperty categoryProperty(){return category;}
	public StringProperty titleProperty(){return title;}
}
