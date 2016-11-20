package application;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage primaryStage;
	public static Stage dialogStage;
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
		loader.setLocation(Main.class.getResource("LibLayout.fxml"));
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
	
	public static void showMainLibrary() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("LibraryOverview.fxml"));
		BorderPane mainLibrary = loader.load();
		mainBorderLayout.setCenter(mainLibrary);
	}
	
	public static void showMainEdit() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("EditLayout.fxml"));
		BorderPane mainEdit = loader.load();
		mainBorderLayout.setCenter(mainEdit);
	}
	
	// Add dialogs
	public static void showAddUserDialog() throws IOException{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddUserDialog.fxml"));
		Parent root = (Parent)loader.load();
		dialogStage = new Stage();
		dialogStage.setScene(new Scene(root));
		dialogStage.showAndWait();
	}
	
	public static void showAddPublisherDialog() throws IOException{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddPublisherDialog.fxml"));
		Parent root = (Parent)loader.load();
		dialogStage = new Stage();
		dialogStage.setScene(new Scene(root));
		dialogStage.showAndWait();
	}
	
/*	public static void showEditPublisherDialog() throws IOException{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("EditPublisherDialog.fxml"));
		Parent root = (Parent)loader.load();
		dialogStage = new Stage();
		dialogStage.setScene(new Scene(root));
		dialogStage.showAndWait();
	} */
	
	public static void showAddAuthorDialog() throws IOException{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddAuthorDialog.fxml"));
		Parent root = (Parent)loader.load();
		dialogStage = new Stage();
		dialogStage.setScene(new Scene(root));
		dialogStage.showAndWait();
	}
	
	public static void showAddBookDialog() throws IOException{
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddBookDialog.fxml"));
		Parent root = (Parent)loader.load();
		dialogStage = new Stage();
		dialogStage.setScene(new Scene(root));
		dialogStage.showAndWait();
	}
	//
	public static void main(String[] args) {
		launch(args);
	}
}
