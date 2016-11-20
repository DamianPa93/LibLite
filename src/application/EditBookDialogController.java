package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import application.model.Author;
import application.model.BookDetail;
import application.model.Category;
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
import javafx.scene.control.Button;

public class EditBookDialogController implements Initializable{
	private DbConnection dc; 
	private Connection conn;
	private BookDetail book;
	
	private ObservableList<Category> categoryList;
	private ObservableList<Publisher> publisherList;
	private ObservableList<Author> authorList;
	
	@FXML
	private TextField titleText;
	@FXML
	private ComboBox<Category> categoryCombo1;	
	@FXML
	private ComboBox<Category> categoryCombo2;
	@FXML
	private ComboBox<Category> categoryCombo3;
	@FXML
	private ComboBox<Author> authorCombo;
	@FXML
	private ComboBox<Publisher> publisherCombo;
	@FXML
	private TextField isbnText;
	@FXML
	private DatePicker datePicker;
	@FXML
	private ComboBox ratingCombo;
	@FXML
	private TextField commentsText;
	@FXML
	private Button buttonSave;
	@FXML
	private Button buttonCancel;
	
	public EditBookDialogController(BookDetail book){
		this.book = book;
	}
	
	@FXML
	private void handleSaveButton() throws SQLException{
		System.out.println("We are before first check");
		if(titleText.getLength() == 0 || isbnText.getLength() == 0){
			bookWarnings("Fill empty brackets", "Title and ISBN number cannot be null");
		} else {
			System.out.println("We are at save button");
			if(checkBookInTable()){
				System.out.println("We've got Book sir");
				
				String sql = "update tbl_book set id_category_1 = ? ,"
						+ "id_category_2 = ? ,"
						+ "id_category_3 = ? ,"
						+ "id_author = ? ,"
						+ "id_publisher = ? ,"
						+ "isbn = ? ,"
						+ "title = ? ,"
						+ "date_of_publication = ? ,"
						+ "book_rating = ? ,"
						+ "comments = ? "
						+ "where id = " + book.getId();
				
				PreparedStatement pst = conn.prepareStatement(sql);
				
				if(categoryCombo1.getSelectionModel().getSelectedItem() == null) pst.setNull(1, Types.INTEGER);
					else pst.setInt(1, categoryCombo1.getSelectionModel().getSelectedItem().getId());
				if(categoryCombo2.getSelectionModel().getSelectedItem() == null) pst.setNull(2, Types.INTEGER);
					else pst.setInt(2, categoryCombo2.getSelectionModel().getSelectedItem().getId());
				if(categoryCombo3.getSelectionModel().getSelectedItem() == null) pst.setNull(3, Types.INTEGER);
				 	else pst.setInt(3, categoryCombo3.getSelectionModel().getSelectedItem().getId());
				if(authorCombo.getSelectionModel().getSelectedItem() == null) pst.setNull(4, Types.INTEGER);
					else pst.setInt(4, authorCombo.getSelectionModel().getSelectedItem().getId());
				if(publisherCombo.getSelectionModel().getSelectedItem() == null) pst.setNull(5, Types.INTEGER);
					else pst.setInt(5, publisherCombo.getSelectionModel().getSelectedItem().getId());
				pst.setString(6, isbnText.getText());
				pst.setString(7, titleText.getText());
				pst.setString(8, ((TextField)datePicker.getEditor()).getText());
				if(ratingCombo.getValue() == null) pst.setNull(9, Types.INTEGER);
					else pst.setInt(9, Integer.parseInt(ratingCombo.getSelectionModel().getSelectedItem().toString()));
				pst.setString(10, commentsText.getText()); 
				
				System.out.println(ratingCombo.getValue());
				
				pst.executeUpdate();
				
				bookSuccess(); 
				handleCancelButton();
			}
			else bookWarnings("Arleady in base","Book arelady is in database");
		}
	}
	
	@FXML
	private void handleCancelButton(){
		System.out.println("Closing");
		EditController.dialogStage.close();
	}
	
	private void fillBoxes() throws SQLException{
		categoryList = FXCollections.observableArrayList();
		publisherList = FXCollections.observableArrayList();
		authorList = FXCollections.observableArrayList();
		
		String sql = "select * from tbl_category";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery(sql);
				
		while(rs.next()){
			categoryList.add(new Category(rs.getInt(1),rs.getString(2)));
		} 
		//
		for(Category c : categoryList){
			if(c.getId() == book.cat1Id)
				categoryCombo1.getSelectionModel().select(c);
			if(c.getId() == book.cat2Id)
				categoryCombo2.getSelectionModel().select(c);
			if(c.getId() == book.cat3Id)
				categoryCombo3.getSelectionModel().select(c);
		}
		//categoryCombo1.getSelectionModel().select(categoryList.));
		//
				
		sql = "select id, name, surname from tbl_author";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery(sql);
				
		while(rs.next()){
			authorList.add(new Author(rs.getInt(1),rs.getString(2),rs.getString(3)));
		} 
		
		for(Author a : authorList){
			if(a.getId() == book.authId)
				authorCombo.getSelectionModel().select(a);
		}
				
		sql = "select p.id, trim(concat(ifnull(p.name, ''),' ', "
				+ "ifnull(p.second_name, ''),' ',ifnull(p.organization, ''))) name "
				+ "from tbl_publisher p";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery(sql);
			
		while(rs.next()){
			publisherList.add(new Publisher(rs.getInt(1),rs.getString(2)));
		} 
		
		for(Publisher p : publisherList){
			if(p.getId() == book.pubId)
				publisherCombo.getSelectionModel().select(p);
		}
		
		categoryCombo1.setItems(categoryList);
		categoryCombo2.setItems(categoryList);
		categoryCombo3.setItems(categoryList);
		authorCombo.setItems(authorList);
		publisherCombo.setItems(publisherList);
		ratingCombo.getItems().addAll(0,1,2,3,4,5);
		ratingCombo.getSelectionModel().select(book.getRating());
	}
	
	private boolean checkBookInTable() throws SQLException{
		String queryCheck = "select * from tbl_book where isbn = '" + isbnText.getText() + "'";
		
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
		alert.setHeaderText("Book altered");
		alert.setContentText("Book successful updated!");
		alert.showAndWait();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		dc = new DbConnection();
		conn = dc.connect();
		
		try {
			fillBoxes();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(book.cat1Id);
		
		//categoryCombo1.getSelectionModel().select();
		
		titleText.setText(book.getTitle());
		isbnText.setText(book.getIsbn());
		if(book.getDateOfPublication().length() > 0){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			LocalDate localDate = LocalDate.parse(book.getDateOfPublication(), formatter);
			datePicker.setValue(localDate);
		}
		
		commentsText.setText(book.getComments());
		
		buttonSave.setOnAction((event) -> {
			System.out.println("event");
			System.out.println(book.getId());
			try {
				handleSaveButton();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		});
		
		
		buttonCancel.setOnAction((event) -> {
			handleCancelButton();
		}); 
	}
}
