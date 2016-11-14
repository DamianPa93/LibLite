package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.Author;
import application.model.DbConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddAuthorDialogController {
	private DbConnection dc; 
	private Connection conn;
	private Author author;
	
	@FXML
	private TextField nameText;
	@FXML
	private TextField surnameText;
	@FXML
	private TextField countryText;
	@FXML
	private DatePicker birthText;
	@FXML
	private TextField commentsText;
	
	@FXML
	private void handleAddButton() throws SQLException{
		if(nameText.getLength() == 0 ||
				surnameText.getLength() == 0){
			authorWarnings("Null values","Name and surname cannot be null");
		} else {
			author = new Author(nameText.getText(),surnameText.getText(),
					countryText.getText(),((TextField)birthText.getEditor()).getText(),
					commentsText.getText());
			System.out.println("We've got Author sir");
			showAuthor();
			if(checkUserInTable()){
				String sql = "insert into tbl_author(name,surname,country,birth_date,comments) "
						+ "values(?,?,?,?,?)";
				System.out.println(sql);
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, author.getName());
				pst.setString(2, author.getSurname());
				pst.setString(3, author.getCountry());
				pst.setString(4, author.getBirth());
				pst.setString(5, author.getComments());
				System.out.println("Sir we made it through psts!");
				
				pst.executeUpdate();
				authorSuccess();
				handleCancelButton();
			} else {
				authorWarnings("Already exists","Author arleady exists in database");
			}
		}
	}
	
	private boolean checkUserInTable() throws SQLException{
		String queryCheck = "select * from tbl_author a where "
				+ "a.name = '" + author.getName() + "' and a.surname = '" + author.getSurname() + "' ";
		
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
	
	private void showAuthor(){
		System.out.println(author.getName() + ", " +
				author.getSurname() + ", " +
				author.getCountry() + ", " +
				author.getBirth() + ", " +
				author.getComments());
	}
	
	private void authorWarnings(String headerText, String contentText){
		Alert alert = new Alert(AlertType.WARNING,"",ButtonType.OK, ButtonType.CLOSE);
		alert.setTitle("WARNING");
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	private void authorSuccess(){
		Alert alert = new Alert(AlertType.INFORMATION,"",ButtonType.OK);
		alert.setTitle("Success!");
		alert.setHeaderText("New author created.");
		alert.setContentText("Author has been created!");
		alert.showAndWait();
	}
}
