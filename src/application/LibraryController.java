package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.model.Author;
import application.model.Book;
import application.model.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class LibraryController implements Initializable
{
	
	@FXML
	private TableView<Book> libraryTable;
	
	@FXML
	private TableColumn<Book, String> libraryIsbnColumn;
	
	@FXML
	private TableColumn<Book, String> libraryAuthorColumn;
	
	@FXML
	private TableColumn<Book, String> libraryTitleColumn;
	
	@FXML
	private TableColumn<Book, String> libraryCategoryColumn;
	
	@FXML
	private TextField bookText;
	
	private ObservableList<Book> data;
	private DbConnection dc; 
	private Connection conn;
	
	@FXML
	private TableView<Author> libraryTable2;
	
	@FXML
	private TableColumn<Author, String> libraryNameColumn;
	
	@FXML
	private TableColumn<Author, String> libraryBirthColumn;
	
	@FXML
	private TableColumn<Author, String> libraryCountryColumn;
	
	@FXML
	private TableColumn<Author, String> libraryCommentsColumn;
	
	@FXML
	private TextField authorText;
	
	private ObservableList<Author> data2; 
	

	
	@FXML
	private void initializeLibraryDB() throws SQLException{
		data = FXCollections.observableArrayList();
		
		String searchText = bookText.getText();
		
		String sql = "select distinct a.isbn, b.name, a.title, group_concat(c.category separator ', ')"
				+ " from tbl_book a, tbl_author b, tbl_category c"
				+ " where a.id_author = b.id"
				+ " and title like '%" + searchText + "%'" 
				+ " and (a.id_category_1 = c.id OR a.id_category_2 = c.id OR a.id_category_3 = c.id)"
				+ " group by a.isbn, b.name, a.title"; 
		
		//ResultSet rs = conn.createStatement().executeQuery(sql);
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery(sql);
		
		while(rs.next()){
			data.add(new Book(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(3)));
			System.out.println(rs.getString(1) + "|" + rs.getString(2));
		} 
		
		
		libraryIsbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		libraryAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		libraryTitleColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
		libraryCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		libraryTable.setItems(null);
		libraryTable.setItems(data); 
	}
	
	@FXML
	private void initializeAuthorDB() throws SQLException{
		data2 = FXCollections.observableArrayList();
		
		String searchText = authorText.getText();
		
		String sql = "select concat(name, ' ',surname) as name, birth_date, country, comments "
				+ "from tbl_author where concat(name, ' ',surname) like '%" + searchText + "%'";
		
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery(sql);
		
		while(rs.next()){
			System.out.println(rs.getString(1) + "|" + rs.getString(2));
			data2.add(new Author(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		
		libraryNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		libraryBirthColumn.setCellValueFactory(new PropertyValueFactory<>("birth"));
		libraryCountryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
		libraryCommentsColumn.setCellValueFactory(new PropertyValueFactory<>("comments"));
		
		libraryTable2.setItems(null);
		libraryTable2.setItems(data2);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		dc = new DbConnection();
		conn = dc.connect();
		/*try {
			initializeLibraryDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
