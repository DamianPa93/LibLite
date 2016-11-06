package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;


public class LibController {
	
	
	@FXML
	private void handleLibrary() throws IOException{
		Main.showMainLibrary();
	}
}
