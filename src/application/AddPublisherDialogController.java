package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.model.DbConnection;
import application.model.Publisher;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddPublisherDialogController {
	private DbConnection dc; 
	private Connection conn;
	private Publisher publisher;
	
	@FXML
	private TextField nameText;
	@FXML
	private TextField surnameText;
	@FXML
	private TextField organizationText;
	@FXML
	private TextField countryText;
	
	@FXML
	private void handleAddButton() throws SQLException{
		if(nameText.getLength() == 0 && surnameText.getLength() == 0 && organizationText.getLength() == 0){
			publisherWarnings("Null values","Insert name value at least into one bracket");
		} else {
			publisher = new Publisher(nameText.getText(),surnameText.getText(),organizationText.getText(),countryText.getText());
			showPublisher();
			if(checkPublisherInTable()){
				String sql = "insert into tbl_publisher(name,second_name,organization,country) values(?,?,?,?)";
				System.out.println(sql);
				PreparedStatement pst = conn.prepareStatement(sql);
				
				pst.setString(1, publisher.getName());
				pst.setString(2, publisher.getSurname());
				pst.setString(3, publisher.getOrganization());
				pst.setString(4, publisher.getCountry());
				
				System.out.println("Sir we made it through psts!");
				pst.executeUpdate();
				
				publisherSuccess();
				handleCancelButton();
			} else {
				publisherWarnings("Arleady in base","Publisher arelady is in database");
			}
		}
	}
	
	private void showPublisher(){
		System.out.println(publisher.getName() + ", " +
				publisher.getSurname() + ", " +
				publisher.getOrganization() + ", " +
				publisher.getCountry());
	}
	
	private boolean checkPublisherInTable() throws SQLException{	
		String queryCheck = "select x.* from ( "
				+ "select p.id, trim(concat(ifnull(p.name, ''),' ', "
				+ " ifnull(p.second_name, ''),' ',ifnull(p.organization, ''))) name "
				+ "from tbl_publisher p) x "
				+ "where lower(x.name) = lower(trim('" + publisher.getName() + " " + publisher.getSurname()
				+ publisher.getOrganization() + "'))";
		
		dc = new DbConnection();
		conn = dc.connect();
		
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
	
	@FXML
	private void handleCancelButton(){
		System.out.println("Closing");
		Main.dialogStage.close();
	}
	
	private void publisherWarnings(String headerText, String contentText){
		Alert alert = new Alert(AlertType.WARNING,"",ButtonType.OK, ButtonType.CLOSE);
		alert.setTitle("WARNING");
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	private void publisherSuccess(){
		Alert alert = new Alert(AlertType.INFORMATION,"",ButtonType.OK);
		alert.setTitle("Success!");
		alert.setHeaderText("New publisher created.");
		alert.setContentText("Publisher has been created!");
		alert.showAndWait();
	}
}
