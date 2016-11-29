package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class LibController implements Initializable {
	
	@FXML
	private Button libraryButton;
	
	@FXML
	private void handleUsers() throws IOException{
		Main.showMainEdit();
	}
	
	@FXML
	private void handleText() throws IOException{
		Main.showAddUserDialog();
	}
	
	@FXML
	private void handleAbout() throws IOException{
		Main.about();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		libraryButton.defaultButtonProperty().bind(libraryButton.focusedProperty());
	}
}
