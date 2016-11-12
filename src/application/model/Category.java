package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {
	private final IntegerProperty id;
	private final StringProperty category;
	
	public Category(int id, String category){
		this.id = new SimpleIntegerProperty(id);
		this.category = new SimpleStringProperty(category);
	}
	
	public Category(String category){
		this.category = new SimpleStringProperty(category);
		this.id = null;
	}
	
	public int getId(){return id.get();}
	public String getCategory(){return category.get();}
	
	public void setId(int value){id.set(value);}
	public void setCategory(String value){category.set(value);}
	
	public IntegerProperty idProperty(){return id;}
	public StringProperty categoryProperty(){return category;}
	
	@Override
	public String toString(){
		return getCategory();
	}
}
