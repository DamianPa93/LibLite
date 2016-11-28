package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.model.DbConnection;
import application.model.Publisher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import application.model.Author;
import application.model.BookDetail;
import application.model.Category;
import org.controlsfx.*;
import org.controlsfx.control.textfield.TextFields;


public class AddBookDialogController implements Initializable{
	private DbConnection dc; 
	private Connection conn;
	private BookDetail book;

	private ObservableList<Category> categoryList;
	private ObservableList<Publisher> publisherList;
	private ObservableList<Author> authorList;
	
	@FXML
	private TextField titleText;
	/*@FXML
	private ComboBox<Category> categoryCombo1;	
	@FXML
	private ComboBox<Category> categoryCombo2;
	@FXML
	private ComboBox<Category> categoryCombo3;
	@FXML
	private ComboBox<Author> authorCombo;
	@FXML
	private ComboBox<Publisher> publisherCombo; */
	@FXML
	private TextField isbnText;
	@FXML
	private DatePicker datePicker;
	@FXML
	private ComboBox ratingCombo;
	@FXML
	private TextField commentsText;
	@FXML
	private TextField amountText;
	@FXML
	private TextField testText;
	
	private Category checkCategory(String text){
		for(Category c : categoryList){
			if(c.getCategory().equals(text)){
				return c;
			}
		}
		return null;
	}
	
	private Author checkAuthor(String text){
		for(Author a : authorList){
			if(a.toString().equals(text))
				return a;
		}
		return null;
	}
	
	private Publisher checkPublisher(String text){
		for(Publisher p : publisherList){
			if(p.toString().equals(text))
				return p;
		}
		return null;
	}
	
	/*@FXML
	private void handleAddButton() throws SQLException{
		if(titleText.getLength() == 0 || isbnText.getLength() == 0){
			bookWarnings("Fill empty brackets", "Title and ISBN number cannot be null");
		} else if(amountText.getLength() == 0 || Integer.parseInt(amountText.getText()) <= 0)
			bookWarnings("Invalid book number", "Fill book amount");
		else {
			System.out.println(testText.getText());
			
			//Category cat = checkCategory(testText.getText());
			//System.out.println(cat.getCategory() + " " + cat.getId());
			//Author a = checkAuthor(testText.getText());
			//
			Publisher p = checkPublisher(testText.getText());
			book = new BookDetail(isbnText.getText(),authorCombo.getSelectionModel().getSelectedItem(),categoryCombo1.getSelectionModel().getSelectedItem(),
					categoryCombo2.getSelectionModel().getSelectedItem(),categoryCombo3.getSelectionModel().getSelectedItem(),
					titleText.getText(),publisherCombo.getSelectionModel().getSelectedItem(), ((TextField)datePicker.getEditor()).getText(),
					ratingCombo.getSelectionModel().getSelectedItem().toString(),commentsText.getText());
			System.out.println("We've got Book sir");
			
			if(checkBookInTable()){
				String sql = "insert into tbl_book(id_category_1,id_category_2,id_category_3,id_author,id_publisher,"
						+ "isbn,title,date_of_publication,book_rating,comments) "
						+ "values(?,?,?,?,?,?,?,?,?,?)";
				System.out.println(sql);
				PreparedStatement pst = conn.prepareStatement(sql);
				
				if(book.cat == null)
					pst.setNull(1, Types.INTEGER);
				else pst.setInt(1, book.cat.getId());
				if(book.cat2 == null)
					pst.setNull(2, Types.INTEGER);
				else pst.setInt(2, book.cat2.getId());
				if(book.cat3 == null)
					pst.setNull(3, Types.INTEGER);
				else pst.setInt(3, book.cat3.getId());
				if(book.auth == null)
					pst.setNull(4, Types.INTEGER);
				else pst.setInt(4, book.auth.getId());
				if(book.pub == null)
					pst.setNull(5, Types.INTEGER);
				else pst.setInt(5, book.pub.getId());
				pst.setString(6, book.getIsbn());
				pst.setString(7, book.getTitle());
				pst.setString(8, book.getDateOfPublication());
				pst.setInt(9, Integer.parseInt(book.strRating));
				if(book.getComments().length() == 0)
					pst.setNull(10, Types.INTEGER);
				else pst.setString(10, book.getComments());
				
				System.out.println("Sir we made it through psts!");
				
				for(int i = 0; i < Integer.parseInt(amountText.getText()); i++)
					pst.executeUpdate();
				
				
				
				bookSuccess();
				handleCancelButton();
				
			} else {
				bookWarnings("Arleady exists", "Book with this ISBN number arleady exists in database");
			}
		} 
	} */
	
	//
	@FXML
	private void handleAddButton() throws SQLException{
		if(titleText.getLength() == 0 || isbnText.getLength() == 0){
			bookWarnings("Fill empty brackets", "Title and ISBN number cannot be null");
		} else if(amountText.getLength() == 0 || Integer.parseInt(amountText.getText()) <= 0)
			bookWarnings("Invalid book number", "Fill book amount");
		else {
			System.out.println(testText.getText());
			
			Category c1 = checkCategory(cc1.getText());
			Category c2 = checkCategory(cc1.getText());
			Category c3 = checkCategory(cc1.getText());
			Author a = checkAuthor(aa1.getText());
			Publisher p = checkPublisher(pp1.getText());
			
			book = new BookDetail(isbnText.getText(),a,c1,
					c2,c3,
					titleText.getText(),p, dateCombo.getSelectionModel().getSelectedItem().toString(),
					ratingCombo.getSelectionModel().getSelectedItem().toString(),commentsText.getText());
			System.out.println("We've got Book sir");
			
			if(checkBookInTable()){
				String sql = "insert into tbl_book(id_category_1,id_category_2,id_category_3,id_author,id_publisher,"
						+ "isbn,title,date_of_publication,book_rating,comments) "
						+ "values(?,?,?,?,?,?,?,?,?,?)";
				System.out.println(sql);
				PreparedStatement pst = conn.prepareStatement(sql);
				
				if(book.cat == null)
					pst.setNull(1, Types.INTEGER);
				else pst.setInt(1, book.cat.getId());
				if(book.cat2 == null)
					pst.setNull(2, Types.INTEGER);
				else pst.setInt(2, book.cat2.getId());
				if(book.cat3 == null)
					pst.setNull(3, Types.INTEGER);
				else pst.setInt(3, book.cat3.getId());
				if(book.auth == null)
					pst.setNull(4, Types.INTEGER);
				else pst.setInt(4, book.auth.getId());
				if(book.pub == null)
					pst.setNull(5, Types.INTEGER);
				else pst.setInt(5, book.pub.getId());
				pst.setString(6, book.getIsbn());
				pst.setString(7, book.getTitle());
				pst.setString(8, book.getDateOfPublication());
				pst.setInt(9, Integer.parseInt(book.strRating));
				if(book.getComments().length() == 0)
					pst.setNull(10, Types.INTEGER);
				else pst.setString(10, book.getComments());
				
				System.out.println("Sir we made it through psts!");
				
				for(int i = 0; i < Integer.parseInt(amountText.getText()); i++)
					pst.executeUpdate();
				
				
				
				bookSuccess();
				handleCancelButton();
				
			} else {
				bookWarnings("Arleady exists", "Book with this ISBN number arleady exists in database");
			}
		} 
	}
	//
	
	@FXML
	private TextField cc1;
	@FXML
	private TextField cc2;
	@FXML
	private TextField cc3;
	@FXML
	private TextField aa1;
	@FXML
	private TextField pp1;
	@FXML
	private ComboBox dateCombo;
	
	private void fillText() throws SQLException{
		categoryList = FXCollections.observableArrayList();
		publisherList = FXCollections.observableArrayList();
		authorList = FXCollections.observableArrayList();
		
		String sql = "select * from tbl_category";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery(sql);
				
		while(rs.next()){
			categoryList.add(new Category(rs.getInt(1),rs.getString(2)));
		} 
				
		sql = "select id, name, surname from tbl_author";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery(sql);
				
		while(rs.next()){
			authorList.add(new Author(rs.getInt(1),rs.getString(2),rs.getString(3)));
		} 
				
		sql = "select p.id, trim(concat(ifnull(p.name, ''),' ', "
				+ "ifnull(p.second_name, ''),' ',ifnull(p.organization, ''))) name "
				+ "from tbl_publisher p";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery(sql);
			
		while(rs.next()){
			publisherList.add(new Publisher(rs.getInt(1),rs.getString(2)));
		} 
		
		TextFields.bindAutoCompletion(cc1, categoryList);
		TextFields.bindAutoCompletion(cc2, categoryList);
		TextFields.bindAutoCompletion(cc3, categoryList);
		TextFields.bindAutoCompletion(aa1, authorList);
		TextFields.bindAutoCompletion(pp1, publisherList);
		
		List year = new ArrayList();
		for(int i = 2017; i > 1899 ;i--)
			year.add(Integer.toString(i));
		
		dateCombo.getItems().addAll(year);
		
		ratingCombo.getItems().addAll(5,4,3,2,1,0);
		ratingCombo.setValue(5);
	}
	
	/*private void fillBoxes() throws SQLException{
		categoryList = FXCollections.observableArrayList();
		publisherList = FXCollections.observableArrayList();
		authorList = FXCollections.observableArrayList();
		
		String sql = "select * from tbl_category";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery(sql);
				
		while(rs.next()){
			categoryList.add(new Category(rs.getInt(1),rs.getString(2)));
		} 
				
		sql = "select id, name, surname from tbl_author";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery(sql);
				
		while(rs.next()){
			authorList.add(new Author(rs.getInt(1),rs.getString(2),rs.getString(3)));
		} 
				
		sql = "select p.id, trim(concat(ifnull(p.name, ''),' ', "
				+ "ifnull(p.second_name, ''),' ',ifnull(p.organization, ''))) name "
				+ "from tbl_publisher p";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery(sql);
			
		while(rs.next()){
			publisherList.add(new Publisher(rs.getInt(1),rs.getString(2)));
		} 
		
		TextFields.bindAutoCompletion(testText, publisherList);
		
		categoryCombo1.setItems(categoryList);
		categoryCombo2.setItems(categoryList);
		categoryCombo3.setItems(categoryList);
		authorCombo.setItems(authorList);
		publisherCombo.setItems(publisherList);
		ratingCombo.getItems().addAll(5,4,3,2,1,0);
		ratingCombo.setValue(5);
	}*/
	
	private boolean checkBookInTable() throws SQLException{
		String queryCheck = "select * from tbl_book where isbn = '" + book.getIsbn() + "'";
		
		dc = new DbConnection();
		conn = dc.connect();
		
		PreparedStatement checkPst = conn.prepareStatement(queryCheck);
		ResultSet checkRs = checkPst.executeQuery(queryCheck);
		System.out.println("We've passed query make sir");
		if(checkRs.next()){
			checkRs.close();
			checkPst.close();
			return false;
		}
		else{
			checkRs.close();
			checkPst.close();
			return true;
		}
	}
	
	@FXML
	private void handleCancelButton(){
		System.out.println("Closing");
		Main.dialogStage.close();
	}
	
	private void bookWarnings(String headerText, String contentText){
		Alert alert = new Alert(AlertType.WARNING,"",ButtonType.OK, ButtonType.CLOSE);
		alert.setTitle("WARNING");
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	private void bookSuccess(){
		Alert alert = new Alert(AlertType.INFORMATION,"",ButtonType.OK);
		alert.setTitle("Success!");
		alert.setHeaderText("New book created.");
		alert.setContentText("Book has been created!");
		alert.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		dc = new DbConnection();
		conn = dc.connect();
		
		try {
			//fillBoxes();
			fillText();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//
		//String[] possibleWords = {"Hai","Hello","Hi"};
		
		//TextFields.bindAutoCompletion(testText, possibleWords);
	}
}
