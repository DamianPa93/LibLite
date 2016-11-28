package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import application.model.Author;
import application.model.DbConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class EditAuthorDialogController implements Initializable{
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
	private Button saveButton;
	@FXML
	private Button cancelButton;
	
	public EditAuthorDialogController(Author author){
		this.author = author;
	}
	
	@FXML
	private void handleSaveButton() throws SQLException{
		if(nameText.getLength() == 0 ||
				surnameText.getLength() == 0){
			authorWarnings("Null values","Name and surname cannot be null");
		} else {
			if(checkAuthorInTable()){
				
				System.out.println(nameText.getText() + "|" + 
						surnameText.getText() + "|" +
						countryText.getText() + "|" +
						((TextField)birthText.getEditor()).getText() + "|" + 
						commentsText.getText());
				
				String sql = "update tbl_author "
						+ "set name = ?, "
						+ "surname = ?, "
						+ "country = ?, "
						+ "birth_date = ?, "
						+ "comments = ? "
						+ " where id = ? ;";
				System.out.println(sql);
				
				PreparedStatement pst = conn.prepareStatement(sql);
				
				pst.setString(1, nameText.getText());
				pst.setString(2, surnameText.getText());
				pst.setString(3, countryText.getText());
				pst.setString(4, ((TextField)birthText.getEditor()).getText());
				pst.setString(5, commentsText.getText());
				pst.setInt(6, author.getId());
				
				pst.executeUpdate();
				
				authorSuccess(); 
				handleCancelButton();
				
			} else 
				authorWarnings("Arleady in base","Author arelady is in database");
		}
	}
	
	@FXML
	private void handleCancelButton(){
		System.out.println("Closing");
		EditController.dialogStage.close();
	}
	
	private void showAuthor(){
		System.out.println(author.getName() + ", " +
				author.getSurname() + ", " +
				author.getCountry() + ", " +
				author.getBirth() + ", " +
				author.getComments());
	}
	
	private boolean checkAuthorInTable() throws SQLException{	
		String queryCheck = "select * from tbl_author a where "
				+ "(a.name = '" + nameText.getText() + "' and a.surname = '" + surnameText.getText() + "')"
						+ " and id <> " + author.getId() + " ";
		
		PreparedStatement checkPst = conn.prepareStatement(queryCheck);
		ResultSet checkRs = checkPst.executeQuery(queryCheck);
		System.out.println("We've passed query make sir");
		if(checkRs.next()){
			checkRs.close();
			checkPst.close();
			System.out.println("Author in database");
			return false;
		}
		else{
			checkRs.close();
			checkPst.close();
			System.out.println("Author NOT in database");
			return true;
		}
	} 
	
	private void authorSuccess(){
		Alert alert = new Alert(AlertType.INFORMATION,"",ButtonType.OK);
		alert.setTitle("Success!");
		alert.setHeaderText("Author altered");
		alert.setContentText("Author successful updated!");
		alert.showAndWait();
	}
	
	private void authorWarnings(String headerText, String contentText){
		Alert alert = new Alert(AlertType.WARNING,"",ButtonType.OK, ButtonType.CLOSE);
		alert.setTitle("WARNING");
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		nameText.setText(author.getName());
		surnameText.setText(author.getSurname());
		countryText.setText(author.getCountry());
		if(author.getBirth().length() > 0){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			LocalDate localDate = LocalDate.parse(author.getBirth(), formatter);
			birthText.setValue(localDate);
		}	
		commentsText.setText(author.getComments());
		
		dc = new DbConnection();
		conn = dc.connect();
		
		saveButton.setOnAction((event) -> {
			System.out.println("event");
			showAuthor();
			try {
				System.out.println(author.getId());
				handleSaveButton();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		});
		
		cancelButton.setOnAction((event) -> {
			handleCancelButton();
		}); 
	}
}
