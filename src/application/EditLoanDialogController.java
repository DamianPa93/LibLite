package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import application.model.BookDetail;
import application.model.DbConnection;
import application.model.Loan;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class EditLoanDialogController implements Initializable{
	private DbConnection dc; 
	private Connection conn;
	private Loan loan;
	
	private ObservableList<User> userList;
	private ObservableList<BookDetail> bookList;
	
	@FXML
	private Button buttonSave;
	@FXML
	private Button buttonCancel;
	
	/*@FXML
	private ComboBox<User> borrowerCombo;
	
	@FXML
	private ComboBox<BookDetail> bookCombo; */
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private DatePicker datePicker2;
	
	@FXML
	private TextField commentsText;
	
	@FXML
	private TextField userText;
	
	@FXML
	private TextField bookText;
	
	public EditLoanDialogController(Loan loan){
		this.loan = loan;
	}
	
	private User userCheck(String text){
		for(User u : userList){
			if(u.toString().equals(text)){
				return u;
			}
		}
		return null;
	}
	
	private BookDetail bookCheck(String text){
		for(BookDetail b : bookList){
			if(b.toString().equals(text)){
				return b;
			}
		}
		return null;
	}
	
	/*@FXML
	private void handleSaveButton() throws SQLException{
		System.out.println("LOAN ADD");
		if(borrowerCombo.getSelectionModel().getSelectedItem()==null || bookCombo.getSelectionModel().getSelectedItem()==null
				|| ((TextField)datePicker.getEditor()).getText().length() == 0 || ((TextField)datePicker2.getEditor()).getText().length() == 0)
			loanWarnings("Null or empty brackets", "Only comments field might be empty. Fill the rest.");
		else {
			System.out.println("We are at save button");
			String sql = "update tbl_loan set user_id = ? ,book_id = ? , loan_date = ?, return_date = ?, comments = ? "
					+ "where id = " + loan.getId();
			
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setInt(1, borrowerCombo.getSelectionModel().getSelectedItem().getId());
			pst.setInt(2, bookCombo.getSelectionModel().getSelectedItem().getId());
			pst.setString(3, ((TextField)datePicker.getEditor()).getText());
			pst.setString(4, ((TextField)datePicker2.getEditor()).getText());
			pst.setString(5, commentsText.getText()); 
			
			pst.executeUpdate();
			
			loanSuccess(); 
			handleCancelButton();
		}
	}*/
	
	@FXML
	private void handleSaveButton() throws SQLException{
		System.out.println("LOAN ADD");
		if(userText.getLength() == 0 || bookText.getLength() == 0
				|| ((TextField)datePicker.getEditor()).getText().length() == 0 || ((TextField)datePicker2.getEditor()).getText().length() == 0)
			loanWarnings("Null or empty brackets", "Only comments field might be empty. Fill the rest.");
		else if(commentsText.getLength() > 45)
			loanWarnings("Too long (" + commentsText.getLength() + ")","Value for 'Comments' is too long. Max string length is 45");
		else {
			System.out.println("We are at save button");
			
			String sql = "update tbl_loan set user_id = ? ,book_id = ? , loan_date = ?, return_date = ?, comments = ? "
					+ "where id = " + loan.getId();
			
			User u = userCheck(userText.getText());
			BookDetail b = bookCheck(bookText.getText());
			
			if(u == null || b == null)
				loanWarnings("Invalid values", "User or book does not exists");
			else {
				PreparedStatement pst = conn.prepareStatement(sql);
				
				pst.setInt(1, u.getId());
				pst.setInt(2, b.getId());
				pst.setString(3, ((TextField)datePicker.getEditor()).getText());
				pst.setString(4, ((TextField)datePicker2.getEditor()).getText());
				pst.setString(5, commentsText.getText()); 
				
				pst.executeUpdate();
				
				loanSuccess(); 
				handleCancelButton();
			}
		}
	}
	
	@FXML
	private void handleCancelButton(){
		System.out.println("Closing");
		EditController.dialogStage.close();
	}
	
	/*
	private void fillBoxes() throws SQLException{
		userList = FXCollections.observableArrayList();
		bookList = FXCollections.observableArrayList();
		
		String sql = "select id, username, name, surname from tbl_user";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery(sql);
		
		while(rs.next()){
			userList.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
		}
		
		for(User u : userList){
			if(u.getId() == loan.userId)
				borrowerCombo.getSelectionModel().select(u);
		} 
			
		
		sql = "select b.id, concat(a.name, ' ',a.surname) author, b.title, b.isbn "
				+ "from tbl_book b left join tbl_author a on b.id_author = a.id "
				+ "where (b.id not in (select book_id from tbl_loan) or b.id = " + loan.bookId  + ")";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery(sql);
		
		while(rs.next()){
			bookList.add(new BookDetail(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
		}
		
		for(BookDetail b : bookList){
			if(b.getId() == loan.bookId)
				bookCombo.getSelectionModel().select(b);
		}
		
		borrowerCombo.setItems(userList);
		bookCombo.setItems(bookList);
	} */
	
	private void fillText() throws SQLException{
		userList = FXCollections.observableArrayList();
		bookList = FXCollections.observableArrayList();
		
		String sql = "select id, username, name, surname from tbl_user where status = 'A'";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery(sql);
		
		while(rs.next()){
			userList.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
		}
		
		for(User u : userList){
			if(u.getId() == loan.userId)
				//borrowerCombo.getSelectionModel().select(u);
				userText.setText(u.toString());
		} 
			
		
		sql = "select b.id, concat(a.name, ' ',a.surname) author, b.title, b.isbn "
				+ "from tbl_book b left join tbl_author a on b.id_author = a.id "
				+ "where (b.id not in (select book_id from tbl_loan) or b.id = " + loan.bookId  + ")";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery(sql);
		
		while(rs.next()){
			bookList.add(new BookDetail(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
		}
		
		for(BookDetail b : bookList){
			if(b.getId() == loan.bookId)
				//bookCombo.getSelectionModel().select(b);
				bookText.setText(b.toString());
		}
		
		//borrowerCombo.setItems(userList);
		//bookCombo.setItems(bookList);
		TextFields.bindAutoCompletion(userText, userList);
		TextFields.bindAutoCompletion(bookText, bookList);
	}
	
	private void loanWarnings(String headerText, String contentText){
		Alert alert = new Alert(AlertType.WARNING,"",ButtonType.OK, ButtonType.CLOSE);
		alert.setTitle("WARNING");
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	private void loanSuccess(){
		Alert alert = new Alert(AlertType.INFORMATION,"",ButtonType.OK);
		alert.setTitle("Success!");
		alert.setHeaderText("Loan changed.");
		alert.setContentText("Loan has been altered!");
		alert.showAndWait();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		dc = new DbConnection();
		conn = dc.connect();
		
		try {
			fillText();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(loan.getdateFrom().length() > 0){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			LocalDate localDate = LocalDate.parse(loan.getdateFrom(), formatter);
			datePicker.setValue(localDate);
		}
		if(loan.getReturnTo().length() > 0){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			LocalDate localDate = LocalDate.parse(loan.getReturnTo(), formatter);
			datePicker2.setValue(localDate);
		} 
		
		commentsText.setText(loan.getComments());
		
		buttonSave.setOnAction((event) -> {
			System.out.println("event");
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
