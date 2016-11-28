package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.model.Category;
import application.model.DbConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class EditCategoryController implements Initializable{
	private DbConnection dc; 
	private Connection conn;
	private Category category;
	
	@FXML
	private TextField categoryText;
	@FXML
	private Button buttonSave;
	@FXML
	private Button buttonCancel;

	
	public EditCategoryController(Category category){
		this.category = category;
	}
	
	@FXML
	private void handleSaveButton() throws SQLException{
		if(categoryText.getText().length() == 0)
			categoryWarnings("Null value","Insert category value into bracket"); 
		else {
			if(checkCategoryInTable()){
				String sql = "update tbl_category set category = ? where id = " + category.getId();
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, categoryText.getText());
				pst.executeUpdate();
				
				categorySuccess(); 
				handleCancelButton();
			} else
				categoryWarnings("Arleady in base","Category arelady is in database");
		}
	}
	
	@FXML
	private void handleCancelButton(){
		System.out.println("Closing");
		EditController.dialogStage.close();
	}
	
	private boolean checkCategoryInTable() throws SQLException{	
		String queryCheck = "select * from tbl_category where category = '" + categoryText.getText() + "' "
				+ "and id <> " + category.getId() + " ";
		
		PreparedStatement checkPst = conn.prepareStatement(queryCheck);
		ResultSet checkRs = checkPst.executeQuery(queryCheck);
		System.out.println("We've passed query make sir");
		if(checkRs.next()){
			checkRs.close();
			checkPst.close();
			System.out.println("Publisher in database");
			return false;
		}
		else{
			checkRs.close();
			checkPst.close();
			System.out.println("Publisher NOT in database");
			return true;
		}
	} 
	
	private void categoryWarnings(String headerText, String contentText){
		Alert alert = new Alert(AlertType.WARNING,"",ButtonType.OK, ButtonType.CLOSE);
		alert.setTitle("WARNING");
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	private void categorySuccess(){
		Alert alert = new Alert(AlertType.INFORMATION,"",ButtonType.OK);
		alert.setTitle("Success!");
		alert.setHeaderText("Category altered");
		alert.setContentText("Category successful updated!");
		alert.showAndWait();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		categoryText.setText(category.getCategory());
		dc = new DbConnection();
		conn = dc.connect();
		
		buttonSave.setOnAction((event) -> {
			System.out.println("event");
			try {
				System.out.println(category.getId());
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
