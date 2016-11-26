package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import application.model.BookDetail;
import application.model.DbConnection;
import application.model.Loan;
import application.model.User;
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

public class AddLoanDialogController implements Initializable{
	private DbConnection dc; 
	private Connection conn;
	private Loan loan;
	
	private ObservableList<User> userList;
	private ObservableList<BookDetail> bookList;
	
	@FXML
	private ComboBox<User> borrowerCombo;
	
	@FXML
	private ComboBox<BookDetail> bookCombo;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private TextField commentsText;
	
	@FXML
	private void handleAddButton() throws SQLException{
		System.out.println("LOAN ADD");
		if(borrowerCombo.getSelectionModel().getSelectedItem()==null || bookCombo.getSelectionModel().getSelectedItem()==null
				|| ((TextField)datePicker.getEditor()).getText().length() == 0)
			loanWarnings("Null or empty brackets", "Only comments field might be empty. Fill the rest.");
		else {
			loan = new Loan(borrowerCombo.getSelectionModel().getSelectedItem(), bookCombo.getSelectionModel().getSelectedItem(),
					((TextField)datePicker.getEditor()).getText(), commentsText.getText());
			System.out.println("We've got Loan sir");
			
			String sql = "insert into tbl_loan(user_id, book_id, loan_date, return_date, comments) "
					+ "values (?,?,?,?,?)";
			//date_format(DATE_ADD(sysdate(),INTERVAL 14 DAY), '%d.%m.%Y')
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setInt(1, loan.user.getId());
			pst.setInt(2, loan.book.getId());
			pst.setString(3, loan.getdateFrom());
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			String dateInString = loan.getdateFrom();
			Calendar cal = Calendar.getInstance();		
			try {
				Date date = formatter.parse(dateInString);
				cal.setTime(date);
				cal.add(Calendar.DATE, 14);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			pst.setString(4, formatter.format(cal.getTime()).toString());
			
			
			if(loan.getComments().length() == 0)
				pst.setNull(5, Types.INTEGER);
			else pst.setString(5, loan.getComments());
			
			
			
			
			pst.executeUpdate();
			loanSuccess();
			handleCancelButton();
		}
	}
	
	private void fillBoxes() throws SQLException{
		userList = FXCollections.observableArrayList();
		bookList = FXCollections.observableArrayList();
		
		String sql = "select id, username, name, surname from tbl_user";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery(sql);
		
		while(rs.next()){
			userList.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
		}
		
		sql = "select b.id, concat(a.name, ' ',a.surname) author, b.title, b.isbn "
				+ "from tbl_book b left join tbl_author a on b.id_author = a.id "
				+ "where b.id not in (select book_id from tbl_loan)";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery(sql);
		
		while(rs.next()){
			bookList.add(new BookDetail(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
		}
		
		borrowerCombo.setItems(userList);
		bookCombo.setItems(bookList);
		
	}
	
	@FXML
	private void handleCancelButton(){
		System.out.println("Closing");
		Main.dialogStage.close();
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
		alert.setHeaderText("New loan created.");
		alert.setContentText("Loan has been created!");
		alert.showAndWait();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		dc = new DbConnection();
		conn = dc.connect();
		
		try {
			fillBoxes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}