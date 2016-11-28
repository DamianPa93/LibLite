package application;

import java.io.IOException;

import javafx.fxml.FXML;


public class LibController {
	
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
}
