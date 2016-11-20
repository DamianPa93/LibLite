package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookDetail {
	private final StringProperty isbn;
	private final StringProperty author;
	private final StringProperty category;
	private final StringProperty title;
	private final StringProperty publisher;
	private final StringProperty dateOfPublication;
	private final IntegerProperty rating;
	private final StringProperty comments;
	private final IntegerProperty id;
	public Category cat;
	public Category cat2;
	public Category cat3;
	public Publisher pub;
	public Author auth;
	public String strRating;
	public int cat1Id;
	public int cat2Id;
	public int cat3Id;
	public int authId;
	public int pubId;
	
	public BookDetail(String isbn, String author, String category, String title, String publisher, String dateOfPublication, 
				  int rating, String comments){
		
		this.isbn = new SimpleStringProperty(isbn);
		this.author = new SimpleStringProperty(author);
		this.category = new SimpleStringProperty(category);
		this.title = new SimpleStringProperty(title);
		this.publisher = new SimpleStringProperty(publisher);
		this.dateOfPublication = new SimpleStringProperty(dateOfPublication);
		this.rating = new SimpleIntegerProperty(rating);
		this.comments = new SimpleStringProperty(comments);
		this.id = null;
	}
	
	public BookDetail(String isbn, Author author, Category category, Category category2, Category category3,
					  String title, Publisher publisher, String dateOfPublication, 
					  String strRating, String comments){
	
	this.isbn = new SimpleStringProperty(isbn);
	this.author = null;
	this.auth = author;
	this.category = null;
	this.cat = category;
	this.cat2 = category2;
	this.cat3 = category3;
	this.title = new SimpleStringProperty(title);
	this.publisher = null;
	this.pub = publisher;
	this.dateOfPublication = new SimpleStringProperty(dateOfPublication);
	this.strRating = strRating;
	this.comments = new SimpleStringProperty(comments);
	this.id = null;
	this.rating = null;
}
	
	public BookDetail(int id, String title, String category, String author, String publisher, String isbn, String dateOfPublication,
			int rating, String comments, int cat1Id, int cat2Id, int cat3Id, int authId,int pubId){
		this.id = new SimpleIntegerProperty(id);
		this.title = new SimpleStringProperty(title);
		this.category = new SimpleStringProperty(category);
		this.author = new SimpleStringProperty(author);
		this.publisher = new SimpleStringProperty(publisher);
		this.isbn = new SimpleStringProperty(isbn);
		this.dateOfPublication = new SimpleStringProperty(dateOfPublication);
		this.rating = new SimpleIntegerProperty(rating);
		this.comments = new SimpleStringProperty(comments);
		//
		this.cat1Id = cat1Id;
		this.cat2Id = cat2Id;
	    this.cat3Id = cat3Id;
	    this.authId = authId;
	    this.pubId = pubId;
	}

	public String getIsbn(){return isbn.get();}
	public String getAuthor(){return author.get();}
	public String getCategory(){return category.get();}
	public String getTitle(){return title.get();}
	public String getPublisher(){return publisher.get();}
	public String getDateOfPublication(){return dateOfPublication.get();}
	public int getRating(){return rating.get();}
	public String getComments(){return comments.get();}
	public int getId(){return id.get();}
	
	public void setIsbn(String value){isbn.set(value);}
	public void setAuthor(String value){author.set(value);}
	public void setCategory(String value){category.set(value);}
	public void setTitle(String value){title.set(value);}
	public void setPublisher(String value){publisher.set(value);}
	public void setDateOfPublication(String value){dateOfPublication.set(value);}
	public void setRating(int value){rating.set(value);}
	public void setComments(String value){comments.set(value);}
	public void setId(int value){id.set(value);}
	
	public StringProperty isbnProperty(){return isbn;}
	public StringProperty authorProperty(){return author;}
	public StringProperty categoryProperty(){return category;}
	public StringProperty titleProperty(){return title;}
	public StringProperty publisherProperty(){return publisher;}
	public StringProperty dateOfPublicationProperty(){return dateOfPublication;}
	public IntegerProperty ratingProperty(){return rating;}
	public StringProperty commentsProperty(){return comments;}
	public IntegerProperty idProperty(){return id;}
}
