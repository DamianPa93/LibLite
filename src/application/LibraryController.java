package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.model.Author;
import application.model.Book;
import application.model.BookDetail;
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
	//Search book tableView
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
	//
	
	//Search author tableView
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
	//
	
	// Advanced search book tableView
	@FXML
	private TableView<BookDetail> advancedTable;
	
	@FXML
	private TableColumn<BookDetail, String> advancedIsbnCol;
	
	@FXML
	private TableColumn<BookDetail, String> advancedAuthorCol;
	
	@FXML
	private TableColumn<BookDetail, String> advancedTitleCol;
	
	@FXML
	private TableColumn<BookDetail, String> advancedCatCol;
	
	@FXML
	private TableColumn<BookDetail, String> advancedPublisherCol;
	
	@FXML
	private TableColumn<BookDetail, String> advancedDatPublCol;
	
	@FXML
	private TableColumn<BookDetail, Integer> advancedRatingCol;
	
	@FXML
	private TableColumn<BookDetail, String> advancedCommCol;
	
	@FXML
	private TableColumn<BookDetail, Integer> advancedOrderIdCol;
	
	@FXML
	private TableColumn<BookDetail, String> advancedOrderDtCol;
	
	@FXML
	private TableColumn<BookDetail, String> advancedLoanDtCol;
	
	@FXML
	private TextField advancedText;
	
	private ObservableList<BookDetail> dataAdvancedBook;
	
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
			data.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
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
	
	@FXML
	private void initializeAdvancedBookDB() throws SQLException{
		dataAdvancedBook = FXCollections.observableArrayList();
		
		String searchText = advancedText.getText();
		
		String sql = "select b.isbn, concat(a.name, ' ', a.surname) as author, b.title, " +
				   "group_concat(c.category separator ', ') as category, " +
			       "concat(p.name, ' ', p.second_name, ' ', p.organization) as publisher, " +
			       "b.date_of_publication, b.book_rating, b.comments, o.id, o.order_date, " +
			       "l.loan_date " +
			"from tbl_book b join tbl_author a on b.id_author = a.id " +
			"join tbl_category c on (b.id_category_1 = c.id " +
									 "OR b.id_category_2 = c.id " +
									 "OR b.id_category_3 = c.id) " +
			"left join tbl_publisher p on b.id_publisher = p.id " +
			"left join tbl_order o on b.id = o.book_id " +
			"left join tbl_loan l on b.id = l.book_id " +
			"where "   +
			"(category like '%" + searchText + "%' or b.isbn like '%" + searchText + "%' or concat(a.name, ' ', a.surname) like '%" + searchText + "%' " +
			 "or b.title like '%" + searchText + "%' or concat(p.name, ' ', p.second_name, ' ', p.organization) like '%" + searchText + "%' " +
			 "or b.book_rating like '%" + searchText + "%') group by 1,2,3,5,9,11";
		
		System.out.println(searchText);
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery(sql);
		
		while(rs.next()){
			System.out.println(rs.getString(3) + "|" + rs.getString(4));
			dataAdvancedBook.add(new BookDetail(rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),
					rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getInt(9),
					rs.getString(10),rs.getString(11)));
		} 
		
		advancedIsbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		advancedAuthorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		advancedTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		advancedCatCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		advancedPublisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		advancedDatPublCol.setCellValueFactory(new PropertyValueFactory<>("dateOfPublication"));
		advancedRatingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
		advancedCommCol.setCellValueFactory(new PropertyValueFactory<>("comments"));
		advancedOrderIdCol.setCellValueFactory(new PropertyValueFactory<>("orderId"));
		advancedOrderDtCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		advancedLoanDtCol.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
		
		advancedTable.setItems(null);
		advancedTable.setItems(dataAdvancedBook);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		dc = new DbConnection();
		conn = dc.connect();
	}
}
