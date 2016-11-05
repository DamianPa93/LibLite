package application;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage primaryStage;
	private static BorderPane mainBorderLayout;
	private static AnchorPane mainAnchorLayout;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("LibLite");
		showLoginView();
		
	}
	
	public static void showMainView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("LibMenu1.fxml"));
		mainBorderLayout = loader.load();
		Scene scene = new Scene(mainBorderLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void showLoginView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("LoginOverview.fxml"));
		mainAnchorLayout = loader.load();
		Scene scene = new Scene(mainAnchorLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
