package application;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import application.model.Author;
import application.model.BookDetail;
import application.model.Category;
import application.model.DbConnection;
import application.model.Loan;
import application.model.Publisher;
import application.model.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EditController implements Initializable {
	
	static Stage dialogStage;
	
	public EditController(){}
	
	public EditController getEditController(){
		return this;
	}
	
	//Search edirUser tableView
	@FXML
	private TableView<User> editTableUsers;	
	
	@FXML
	private TableColumn<User, Integer> editTab1Col1; //ID
	
	@FXML
	private TableColumn<User, String> editTab1Col2; //USERNAME
	
	@FXML
	private TableColumn<User, String> editTab1Col3; //PASSWORD
	
	@FXML
	private TableColumn<User, String> editTab1Col4; //NAME
	
	@FXML
	private TableColumn<User, String> editTab1Col5; //SURNAME
	
	@FXML
	private TableColumn<User, String> editTab1Col6; //CITY
	
	@FXML
	private TableColumn<User, String> editTab1Col7; //STREET
	
	@FXML
	private TableColumn<User, String> editTab1Col8; //APARTMENT
	
	@FXML
	private TableColumn<User, String> editTab1Col9; //POSTAL CODE
	
	@FXML
	private TableColumn<User, String> editTab1Col10; //PHONE
	
	@FXML
	private TableColumn<User, String> editTab1Col11; //EMAIL
	
	@FXML
	private TableColumn<User, String> editTab1Col12; //SECID
	
	@FXML
	private TableColumn<User, String> editTab1Col13; //STATUS
	
	@FXML
	private TextField editText;
	
	private ObservableList<User> dataUser;
	private DbConnection dc; 
	private Connection conn;
	
	//Search editBooks tableView
	@FXML
	private TableView<BookDetail> editTableBooks;
	
	@FXML
	private TableColumn<BookDetail, Integer> editTab2Col1; //ID
	
	@FXML
	private TableColumn<BookDetail, String> editTab2Col2; //TITLE
	
	@FXML
	private TableColumn<BookDetail, String> editTab2Col3; //CATEGORY
	
	@FXML
	private TableColumn<BookDetail, String> editTab2Col4; //AUTHOR
	
	@FXML
	private TableColumn<BookDetail, String> editTab2Col5; //PUBLISHER
	
	@FXML
	private TableColumn<BookDetail, String> editTab2Col6; //ISBN
	
	@FXML
	private TableColumn<BookDetail, String> editTab2Col7; //DATE OF PUBLICATION
	
	@FXML
	private TableColumn<BookDetail, Integer> editTab2Col8; //RATING
	
	@FXML
	private TableColumn<BookDetail, Integer> editTab2Col9; //COMMENTS
	
	@FXML
	private TextField editText2;
	
	private ObservableList<BookDetail> dataBooks;
	
	//Search editCategories tableView
	@FXML
	private TableView<Category> editTableCategories;
		
	@FXML
	private TableColumn<Category, Integer> editTab3Col1; //ID
	
	@FXML
	private TableColumn<Category, String> editTab3Col2; //CATEGORY
	
	@FXML
	private TextField editText3;
	
	private ObservableList<Category> dataCategories;
	
	//Search editAuthors tableView
	@FXML
	private TableView<Author> editTableAuthors;
			
	@FXML
	private TableColumn<Author, Integer> editTab4Col1; //ID
	
	@FXML
	private TableColumn<Author, String> editTab4Col2; //NAME
	
	@FXML
	private TableColumn<Author, String> editTab4Col3; //SURNAME
	
	@FXML
	private TableColumn<Author, String> editTab4Col4; //COUNTRY
	
	@FXML
	private TableColumn<Author, String> editTab4Col5; //BIRTHDATE
	
	@FXML
	private TableColumn<Author, String> editTab4Col6; //COMMENTS
	
	@FXML
	private TextField editText4;
	
	private ObservableList<Author> dataAuthors;
	
	//Search edirPublishers tableView
	@FXML
	private TableView<Publisher> editTablePublishers;	
		
	@FXML
	private TableColumn<Publisher, Integer> editTab5Col1; //ID
	
	@FXML
	private TableColumn<Publisher, String> editTab5Col2; //NAME
	
	@FXML
	private TableColumn<Publisher, String> editTab5Col3; //SURNAME
	
	@FXML
	private TableColumn<Publisher, String> editTab5Col4; //ORGANIZATION
	
	@FXML
	private TableColumn<Publisher, String> editTab5Col5; //COUNTRY
	
	@FXML
	private TextField editText5;
	
	private ObservableList<Publisher> dataPublishers;
	
	@FXML
	private TextField editText6;
	
	@FXML
	private TableView<Loan> editTableLoans;
	
	@FXML
	private TableColumn<Loan, Integer> editTab6Col1; //ID
	
	@FXML
	private TableColumn<Loan, String> editTab6Col2; //BORROWER
	
	@FXML
	private TableColumn<Loan, String> editTab6Col3; //BOOK
	
	@FXML
	private TableColumn<Loan, String> editTab6Col4; //LOAN DATE
	
	@FXML
	private TableColumn<Loan, String> editTab6Col5; //RETURN DATE
	
	@FXML
	private TableColumn<Loan, String> editTab6Col6; //COMMENTS
	
	private ObservableList<Loan> dataLoans;
	
	@FXML
	private Button redeem;
	
	
	
	@FXML
	private TabPane editTabPane;
	
	SingleSelectionModel<Tab> selectionModel;
	
	
	
	
	
	
	@FXML
	public void initializeLoansDB() throws SQLException{
		System.out.println("We are at loan");
		dataLoans = FXCollections.observableArrayList();
		
		String searchText = editText6.getText();
		
		String sql = "select * from ( "
				+ "select l.id, concat(u.name, ' ',u.surname, ', ',u.socsecnumber) borrower, "
				+ "concat(b.title, ', ',b.isbn) book, l.loan_date, l.return_date, l.comments, l.user_id, l.book_id "
				+ "from tbl_loan l "
				+ "left join tbl_book b on l.book_id = b.id "
				+ "left join tbl_user u on l.user_id = u.id) x "
				+ "where x.borrower like '%" + searchText + "%' "
				+ "or x.book like '%" + searchText + "%'";
		
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery(sql);
		
		while(rs.next()){
			dataLoans.add(new Loan(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
					rs.getInt(7),rs.getInt(8)));
		}
		
		editTab6Col1.setCellValueFactory(new PropertyValueFactory<>("id"));
		editTab6Col2.setCellValueFactory(new PropertyValueFactory<>("borrower"));
		editTab6Col3.setCellValueFactory(new PropertyValueFactory<>("title"));
		editTab6Col4.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));
		editTab6Col5.setCellValueFactory(new PropertyValueFactory<>("returnTo"));
		editTab6Col6.setCellValueFactory(new PropertyValueFactory<>("comments"));
		
		editTableLoans.setItems(null);
		editTableLoans.setItems(dataLoans);
	}
	
	@FXML
	public void initializeUsersDB() throws SQLException{
		dataUser = FXCollections.observableArrayList();
		
		String searchText = editText.getText();
		
		String sql = "select u.id, u.username, u.password, u.name, u.surname, u.city, u.street, "
				+ "u.apartment_num, u.postal_code, u.phone, u.email, u.socsecnumber, u.status "
				+ "from tbl_user u "
				+ "where u.username like '%" + searchText + "%' "
				+ "or u.surname like '%" + searchText + "%' "
				+ "or u.socsecnumber like '%" + searchText + "%'";
		
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery(sql);
		
		while(rs.next()){
			dataUser.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
					rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12),rs.getString(13)));
			System.out.println(rs.getString(1) + "|" + rs.getString(2));
		} 
		
		editTab1Col1.setCellValueFactory(new PropertyValueFactory<>("id"));
		editTab1Col2.setCellValueFactory(new PropertyValueFactory<>("login"));
		editTab1Col3.setCellValueFactory(new PropertyValueFactory<>("password"));
		editTab1Col4.setCellValueFactory(new PropertyValueFactory<>("name"));
		editTab1Col5.setCellValueFactory(new PropertyValueFactory<>("surname"));
		editTab1Col6.setCellValueFactory(new PropertyValueFactory<>("city"));
		editTab1Col7.setCellValueFactory(new PropertyValueFactory<>("street"));
		editTab1Col8.setCellValueFactory(new PropertyValueFactory<>("apartment"));
		editTab1Col9.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
		editTab1Col10.setCellValueFactory(new PropertyValueFactory<>("phone"));
		editTab1Col11.setCellValueFactory(new PropertyValueFactory<>("email"));
		editTab1Col12.setCellValueFactory(new PropertyValueFactory<>("secId"));
		editTab1Col13.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		editTableUsers.setItems(null);
		editTableUsers.setItems(dataUser);
	}
	
	@FXML
	private void initializeBooksDB() throws SQLException{
		dataBooks = FXCollections.observableArrayList();
		
		String searchText = editText2.getText();
		
		String sql = "select x.* from "
				+ "(select b.id, b.title, group_concat(c.category separator ', ') category, "
				+ "concat(a.name, ' ',a.surname) author, "
				+ "trim(concat(ifnull(p.name, ''),' ', "
				+ "ifnull(p.second_name, ''),' ',ifnull(p.organization, ''))) publisher, "
				+ " b.isbn, b.date_of_publication, b.book_rating, b.comments , b.id_category_1, b.id_category_2, b.id_category_3,b.id_author, b.id_publisher "
				+ "from tbl_book b "
				+ "left join tbl_category c on (b.id_category_1 = c.id "
				+ "or b.id_category_2 = c.id "
				+ " or b.id_category_3 = c.id) "
				+ "left join tbl_author a on b.id_author = a.id "
				+ "left join tbl_publisher p on b.id_publisher = p.id "
				+ "group by b.id, b.title) x "
				+ "where x.isbn like '%"+ searchText +"%' "
				+ "or x.title like '%"+ searchText +"%' "
				+ "or x.author like '%"+ searchText +"%' "
				+ "or x.category like '%"+ searchText +"%'";
		
		
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery(sql);
		
		while(rs.next()){
			dataBooks.add(new BookDetail(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
					rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9), rs.getInt(10),rs.getInt(11),rs.getInt(12),
					rs.getInt(13),rs.getInt(14)));
			System.out.println(rs.getString(1) + "|" + rs.getString(2));
		} 
		
		editTab2Col1.setCellValueFactory(new PropertyValueFactory<>("id"));
		editTab2Col2.setCellValueFactory(new PropertyValueFactory<>("title"));
		editTab2Col3.setCellValueFactory(new PropertyValueFactory<>("category"));
		editTab2Col4.setCellValueFactory(new PropertyValueFactory<>("author"));
		editTab2Col5.setCellValueFactory(new PropertyValueFactory<>("publisher"));
		editTab2Col6.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		editTab2Col7.setCellValueFactory(new PropertyValueFactory<>("dateOfPublication"));
		editTab2Col8.setCellValueFactory(new PropertyValueFactory<>("rating"));
		editTab2Col9.setCellValueFactory(new PropertyValueFactory<>("comments"));
		
		editTableBooks.setItems(null);
		editTableBooks.setItems(dataBooks); 
	}
	
	@FXML
	private void initializeCategoriesDB() throws SQLException{
		dataCategories = FXCollections.observableArrayList();
		
		String searchText = editText3.getText();
		
		String sql = "select * from tbl_category where category like '%" + searchText + "%'";
		
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery(sql);
		
		while(rs.next()){
			dataCategories.add(new Category(rs.getInt(1),rs.getString(2)));
			System.out.println(rs.getString(1) + "|" + rs.getString(2));
		} 
		
		editTab3Col1.setCellValueFactory(new PropertyValueFactory<>("id"));
		editTab3Col2.setCellValueFactory(new PropertyValueFactory<>("category"));
		
		editTableCategories.setItems(null);
		editTableCategories.setItems(dataCategories);
	}
	
	@FXML
	private void initializeAuthorsDB() throws SQLException{
		dataAuthors = FXCollections.observableArrayList();
		
		String searchText = editText4.getText();
		
		String sql = "select a.id, a.name, a.surname, a.country, a.birth_date, a.comments "
				+ "from tbl_author a "
				+ "where a.surname like '%" + searchText + "%' "
				+ "or a.name like '%" + searchText + "%' "
				+ "or a.country like '%" + searchText + "%'";
		
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery(sql);
		
		while(rs.next()){
			dataAuthors.add(new Author(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
			System.out.println(rs.getString(1) + "|" + rs.getString(2));
		} 
		
		editTab4Col1.setCellValueFactory(new PropertyValueFactory<>("id"));
		editTab4Col2.setCellValueFactory(new PropertyValueFactory<>("name"));
		editTab4Col3.setCellValueFactory(new PropertyValueFactory<>("surname"));
		editTab4Col4.setCellValueFactory(new PropertyValueFactory<>("country"));
		editTab4Col5.setCellValueFactory(new PropertyValueFactory<>("birth"));
		editTab4Col6.setCellValueFactory(new PropertyValueFactory<>("comments"));
		
		editTableAuthors.setItems(null);
		editTableAuthors.setItems(dataAuthors);
	}
	
	@FXML
	private void initializePublishersDB() throws SQLException{
		dataPublishers = FXCollections.observableArrayList();
		
		String searchText = editText5.getText();
		
		String sql = "select p.id, p.name, p.second_name, p.organization, p.country "
				+ "from tbl_publisher p "
				+ "where p.name like '%" + searchText + "%' "
				+ "or p.second_name like '%" + searchText + "%' "
				+ " or p.organization like '%" + searchText + "%'";
		
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery(sql);
		
		while(rs.next()){
			dataPublishers.add(new Publisher(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			System.out.println(rs.getString(1) + "|" + rs.getString(2));
		} 
		
		editTab5Col1.setCellValueFactory(new PropertyValueFactory<>("id"));
		editTab5Col2.setCellValueFactory(new PropertyValueFactory<>("name"));
		editTab5Col3.setCellValueFactory(new PropertyValueFactory<>("surname"));
		editTab5Col4.setCellValueFactory(new PropertyValueFactory<>("organization"));
		editTab5Col5.setCellValueFactory(new PropertyValueFactory<>("country"));
		
		editTablePublishers.setItems(null);
		editTablePublishers.setItems(dataPublishers);
	}
	
	
	
	private boolean deleteWarning(){
		Alert alert = new Alert(AlertType.WARNING,"",ButtonType.YES, ButtonType.NO);
		alert.setTitle("WARNING");
		alert.setHeaderText("Confirm delete item.");
		alert.setContentText("Are you sure to delete this item?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.YES) return true;
		else return false;
	}
	
	private void deleteCategory() throws SQLException{
		Category category = editTableCategories.getSelectionModel().getSelectedItem();
		if(editTableCategories.getSelectionModel().getSelectedIndex() >= 0){
			
			if(deleteWarning()){
				System.out.println(selectionModel.getSelectedIndex() + " " + category.getId());
				String sql = "delete from tbl_category where id = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, category.getId());
				pst.executeUpdate();
				initializeCategoriesDB();
			}
		}
		else System.out.println("NO ROW SELECTED");
	}
	
	private void deletePublisher() throws SQLException{
		Publisher publisher = editTablePublishers.getSelectionModel().getSelectedItem();
		if(editTablePublishers.getSelectionModel().getSelectedIndex() >= 0){
			
			if(deleteWarning()){
				System.out.println(selectionModel.getSelectedIndex() + " " + publisher.getId());
				String sql = "delete from tbl_publisher where id = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, publisher.getId());
				pst.executeUpdate();
				initializePublishersDB();
			}
		}
		else System.out.println("NO ROW SELECTED");
	}
	
	private void deleteLoan() throws SQLException{
		Loan loan = editTableLoans.getSelectionModel().getSelectedItem();
		if(editTableLoans.getSelectionModel().getSelectedIndex() >= 0){
			if(deleteWarning()){
				System.out.println(selectionModel.getSelectedIndex() + " " + loan.getId());
				String sql = "delete from tbl_loan where id = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, loan.getId());
				pst.executeUpdate();
				initializeLoansDB();
			}
		}
		else System.out.println("NO ROW SELECTED");
	}
	
	private void editPublisher() throws IOException, SQLException{
		Publisher publisher = editTablePublishers.getSelectionModel().getSelectedItem();
		if(editTablePublishers.getSelectionModel().getSelectedIndex() >= 0){
			
			System.out.println("Wa are before initialize edit scene");
			
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("EditPublisherDialog.fxml"));
			EditPublisherDialogController controller = new EditPublisherDialogController(publisher);
			loader.setController(controller);
			Parent root = (Parent)loader.load();
			//Stage dialogStage = new Stage();
			dialogStage = new Stage();
			dialogStage.setScene(new Scene(root));
			dialogStage.showAndWait();
		}
		else System.out.println("NO ROW SELECTED");
	}
	
	private void editAuthor() throws IOException{
		Author author = editTableAuthors.getSelectionModel().getSelectedItem();
		if(editTableAuthors.getSelectionModel().getSelectedIndex() >= 0){
			
			System.out.println("Wa are before initialize edit scene");
			
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("EditAuthorDialog.fxml"));
			EditAuthorDialogController controller = new EditAuthorDialogController(author);
			loader.setController(controller);
			Parent root = (Parent)loader.load();
			dialogStage = new Stage();
			dialogStage.setScene(new Scene(root));
			dialogStage.showAndWait();
		}
		else System.out.println("NO ROW SELECTED");
	}
	
	private void editCategory() throws SQLException, IOException{
		Category category = editTableCategories.getSelectionModel().getSelectedItem();
		if(editTableCategories.getSelectionModel().getSelectedIndex() >= 0){
			System.out.println("We are in edit category Sir!");
			
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("EditCategoryDialog.fxml"));
			EditCategoryController controller = new EditCategoryController(category);
			loader.setController(controller);
			Parent root = (Parent)loader.load();
			dialogStage = new Stage();
			dialogStage.setScene(new Scene(root));
			dialogStage.showAndWait();
		}
		else System.out.println("NO ROW SELECTED");
	}
	
	private void editUser() throws IOException{
		User user = editTableUsers.getSelectionModel().getSelectedItem();
		if(editTableUsers.getSelectionModel().getSelectedIndex() >= 0){
			System.out.println("We are in edit user Sir!");
			
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("EditUserDialog.fxml"));
			EditUserDialogController controller = new EditUserDialogController(user);
			loader.setController(controller);
			Parent root = (Parent)loader.load();
			dialogStage = new Stage();
			dialogStage.setScene(new Scene(root));
			dialogStage.showAndWait();
		}
		else System.out.println("NO ROW SELECTED");
	}
	
	private void editBook() throws IOException{
		BookDetail book = editTableBooks.getSelectionModel().getSelectedItem();
		if(editTableBooks.getSelectionModel().getSelectedIndex() >= 0){
			System.out.println("We are in edit user Sir!");
			
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("EditBookDialog.fxml"));
			EditBookDialogController controller = new EditBookDialogController(book);
			loader.setController(controller);
			Parent root = (Parent)loader.load();
			dialogStage = new Stage();
			dialogStage.setScene(new Scene(root));
			dialogStage.showAndWait();
		}
		else System.out.println("NO ROW SELECTED");
	}
	
	private void editLoan() throws IOException{
		Loan loan = editTableLoans.getSelectionModel().getSelectedItem();
		if(editTableLoans.getSelectionModel().getSelectedIndex() >= 0){
			System.out.println("We are in edit user Sir!");
			
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("EditLoanDialog.fxml"));
			EditLoanDialogController controller = new EditLoanDialogController(loan);
			loader.setController(controller);
			Parent root = (Parent)loader.load();
			dialogStage = new Stage();
			dialogStage.setScene(new Scene(root));
			dialogStage.showAndWait();
		}
		else System.out.println("NO ROW SELECTED");
	}
	
	private void addLoanHistory() throws SQLException{
		Loan loan = editTableLoans.getSelectionModel().getSelectedItem();
		if(editTableLoans.getSelectionModel().getSelectedIndex() >= 0){
			System.out.println("We are loan -> history!");
			String sql_insert = "insert into tbl_loan_history(user_id, book_id, loan_date, return_date,comments) "
					+ "select user_id, book_id, loan_date, return_date, comments "
					+ "from tbl_loan where id = " + loan.getId() + " ";
			
			PreparedStatement pst = conn.prepareStatement(sql_insert);
			pst.executeUpdate();
			
			String sql_delete = "delete from tbl_loan where id = " + loan.getId() + " ";
			pst = conn.prepareStatement(sql_delete);
			pst.executeUpdate();
		}
		else System.out.println("NO ROW SELECTED");
	}
	
	@FXML
	private void handleRedeemButton() throws SQLException{
		addLoanHistory();
		//redeem.setVisible(false);
	}
	
	private void deleteAuthor() throws SQLException{
		Author author = editTableAuthors.getSelectionModel().getSelectedItem();
		if(editTableAuthors.getSelectionModel().getSelectedIndex() >= 0){
			
			if(deleteWarning()){
				System.out.println(selectionModel.getSelectedIndex() + " " + author.getId());
				String sql = "delete from tbl_author where id = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, author.getId());
				pst.executeUpdate();
				initializeAuthorsDB();
			}
		}
		else System.out.println("NO ROW SELECTED");
	}
	
	private void deleteBook() throws SQLException{
		BookDetail book = editTableBooks.getSelectionModel().getSelectedItem();
		if(editTableBooks.getSelectionModel().getSelectedIndex() >= 0){
			
			if(deleteWarning()){
				System.out.println(selectionModel.getSelectedIndex() + " " + book.getId());
				String sql = "delete from tbl_book where id = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, book.getId());
				pst.executeUpdate();
				initializeBooksDB();
			}
		}
		else System.out.println("NO ROW SELECTED");
	}
	
	private void deleteUser() throws SQLException{
		User user = editTableUsers.getSelectionModel().getSelectedItem();
		if(editTableUsers.getSelectionModel().getSelectedIndex() >= 0){
			
			if(deleteWarning()){
				System.out.println(selectionModel.getSelectedIndex() + " " + user.getId());
				String sql = "delete from tbl_user where id = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, user.getId());
				pst.executeUpdate();
				initializeUsersDB();
			}
		}
		else System.out.println("NO ROW SELECTED");
	}
	
	@FXML
	private void handleDeleteButton() throws SQLException{
		selectionModel = editTabPane.getSelectionModel();
		
		if(selectionModel.getSelectedIndex() == 2)
			deleteCategory();
		else if(selectionModel.getSelectedIndex() == 0)
			deleteUser();
		else if(selectionModel.getSelectedIndex() == 1)
			deleteBook();
		else if(selectionModel.getSelectedIndex() == 3)
			deleteAuthor();
		else if(selectionModel.getSelectedIndex() == 4)
			deletePublisher();
		else if(selectionModel.getSelectedIndex() == 5)
			deleteLoan();
		else System.out.println("NOPE"); 
	}
	
	private void addCategory() throws SQLException{
		TextInputDialog dialog = new TextInputDialog("new category");
		dialog.setTitle("Add category");
		dialog.setHeaderText("You can now insert new category");
		dialog.setContentText("Please enter new category:");
		Optional<String> result = dialog.showAndWait();
		
		if(result.isPresent()){
			String category = result.get();
			String queryCheck = "select * from tbl_category where category = '" + category + "'";
			
			PreparedStatement checkPst = conn.prepareStatement(queryCheck);
			ResultSet checkRs = checkPst.executeQuery(queryCheck);
			
			if(checkRs.next()) addCategory();
			else{
				String sql = "insert into tbl_category(category) values(?)";
				System.out.println(sql);
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, category);
				pst.executeUpdate();
				initializeCategoriesDB();
			}	
		}
	}
	
	
	private void addPublisher() throws SQLException, IOException{
		Main.showAddPublisherDialog();
		System.out.println("After publusher dialog closed");
		//initializePublishersDB();
	}
	
	private void addAuthor() throws SQLException, IOException{
		Main.showAddAuthorDialog();
		System.out.println("After author dialog closed");
		//initializeAuthorsDB();
	}
		
	private void addUser() throws SQLException, IOException{
		Main.showAddUserDialog();
		System.out.println("After dialog closed");
		//initializeUsersDB();
	}
		
		
	private void addBook() throws SQLException, IOException {
		Main.showAddBookDialog();
		System.out.println("After dialog closed");
		//initializeBooksDB();
	}
	
	private void addLoan() throws IOException{
		System.out.println("We are at loan");
		Main.showAddLoanDialog();
	}
	
	@FXML
	private void handleAddButton() throws SQLException, IOException{
		selectionModel = editTabPane.getSelectionModel();
		
		if(selectionModel.getSelectedIndex() == 2)
			addCategory();
		else if(selectionModel.getSelectedIndex() == 4)
			addPublisher();
		else if(selectionModel.getSelectedIndex() == 3)
			addAuthor();
		else if(selectionModel.getSelectedIndex() == 1)
			addBook();
		else if(selectionModel.getSelectedIndex() == 0)
			addUser();
		else if(selectionModel.getSelectedIndex() == 5)
			addLoan();
		else System.out.println("NOPE"); 
	}
	
	@FXML
	private void handleEditButton() throws IOException, SQLException{
		selectionModel = editTabPane.getSelectionModel();
		
		if(selectionModel.getSelectedIndex() == 4)
			editPublisher();
		if(selectionModel.getSelectedIndex() == 3)
			editAuthor();
		if(selectionModel.getSelectedIndex() == 2)
			editCategory();
		if(selectionModel.getSelectedIndex() == 0)
			editUser();
		if(selectionModel.getSelectedIndex() == 1)
			editBook();
		if(selectionModel.getSelectedIndex() == 5)
			editLoan();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		dc = new DbConnection();
		conn = dc.connect();
		
		redeem.setVisible(false);
		
		selectionModel = editTabPane.getSelectionModel();
		
		editTabPane.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
		    	if(selectionModel.getSelectedIndex() == 5)
		    		redeem.setVisible(true);
		    	else
		    		redeem.setVisible(false);
		    }
		});
		
		redeem.setStyle("-fx-background-color: "
				+ " linear-gradient(#f0ff35, #a9ff00), "
				+ " radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%); "
				+ "-fx-background-radius: 6, 5; "
				+ "-fx-background-insets: 0, 1; "
				+ "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 ); "
				+ "-fx-text-fill: #395306; ");
	}
}
