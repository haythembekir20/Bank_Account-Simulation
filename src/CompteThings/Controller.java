package CompteThings;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Client;
import models.compte;

import java.io.IOException;
//java libraries
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import ClientThings.tableviewController;
import helpers.DbConnect;


public class Controller implements Initializable {

 
	@FXML
    private TableView<compte> main_table;

    @FXML
    private TableColumn<compte, String> decouvertmax_column;

    @FXML
    private Button update_btn;

    

    

    @FXML
    private Button revert_button;

    @FXML
    private TableColumn<compte, String> num_column;

    @FXML
    private TextField num_text;

    @FXML
    private TextField deb_max_text;

    @FXML
    private TableColumn<compte, String> idcli_column;

    @FXML
    private TextField decmax_text;
    
    @FXML
    private ComboBox<Integer> idclientbox;
    
    @FXML
    private ComboBox<Integer> idclientboxcrud;

    @FXML
    private TableColumn<compte, String> debitmax_column;

    @FXML
    private TableColumn<compte, String> sitcompet_column;

    @FXML
    private TableColumn<compte, String> solde_column;

    @FXML
    private Button create_btn;

    @FXML
    private Button delete_btn;

    @FXML
    private Button get_button;
 
    String query=null;
    Connection connection =null;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
public void getAllIdAccount() {
		try {
			ObservableList<Integer> allId = FXCollections.observableArrayList();
			query = "SELECT idClient FROM client";
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			List<Client> listCompte = new ArrayList<Client>();
			while (resultSet.next()) {
				Client c = new Client();
				c.setIdClient(resultSet.getInt("idClient"));
				listCompte.add(c);
			}
			Iterator<Client> it = listCompte.iterator();
			while (it.hasNext()) {
				allId.add(it.next().getIdClient());
				
			}
			
			idclientbox.setItems(allId);
			idclientboxcrud.setItems(allId);
			
						} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


 
 public ObservableList<compte> getcomptes(){

     ObservableList<compte> comptes = FXCollections.observableArrayList();
     Connection connect = DbConnect.getConnect();;
     String sql_query = "SELECT * FROM compte";
     
     try(Statement statement = connect.createStatement()){
         ResultSet result_set = statement.executeQuery(sql_query);
         while(result_set.next()){
        	 compte comptes_queried = new  compte(
        			 result_set.getInt("num_compte"),
        			 result_set.getDouble("solde"),
        			 result_set.getDouble("decouvertMax"),
        			 result_set.getDouble("debitMax"),
        			 result_set.getString("situation_compte"),
        			 result_set.getInt("idClient"));
        	 comptes.add(comptes_queried);
         }
     }
     catch(Exception e){
         System.out.println("Error:" + e.getMessage());
     }
     return comptes;
 }

 @FXML
 void refreshtable() throws SQLException {
	 makecomptesdecouvert();
	 getcomptes();
	 pushcomptesOntoTable();
 }
 @FXML
public void getmaxsolde(MouseEvent event) {
	 ObservableList<compte> comptes = FXCollections.observableArrayList();
     Connection connect = DbConnect.getConnect();
     
     String sql_query = "SELECT * FROM `compte` where solde =(select max(solde) from compte) ";


     try(Statement statement = connect.createStatement()){
         ResultSet result_set = statement.executeQuery(sql_query);
         while(result_set.next()){
        	 compte comptes_queried = new  compte(
        			 result_set.getInt("num_compte"),
        			 result_set.getDouble("solde"),
        			 result_set.getDouble("decouvertMax"),
        			 result_set.getDouble("debitMax"),
        			 result_set.getString("situation_compte"),
        			 result_set.getInt("idClient"));
        	 comptes.add(comptes_queried);
         }
         num_column.setCellValueFactory(new PropertyValueFactory<>("num_compte"));
         solde_column.setCellValueFactory(new PropertyValueFactory<>("solde"));
         decouvertmax_column.setCellValueFactory(new PropertyValueFactory<>("decouvertMax"));
         debitmax_column.setCellValueFactory(new PropertyValueFactory<>("debitMax"));
         sitcompet_column.setCellValueFactory(new PropertyValueFactory<>("situation_compte"));
         idcli_column.setCellValueFactory(new PropertyValueFactory<>("idClient"));
         main_table.setItems(comptes);
     }
     catch(Exception e){
         System.out.println("Error:" + e.getMessage());
     }
     
 
 }
 
 @FXML
 public void getcomptedecouvert(MouseEvent event) {
 	 ObservableList<compte> comptes = FXCollections.observableArrayList();
      Connection connect = DbConnect.getConnect();
      
      String sql_query = "SELECT * FROM `compte` where situation_compte='rouge' ";


      try(Statement statement = connect.createStatement()){
          ResultSet result_set = statement.executeQuery(sql_query);
          while(result_set.next()){
         	 compte comptes_queried = new  compte(
         			 result_set.getInt("num_compte"),
         			 result_set.getDouble("solde"),
         			 result_set.getDouble("decouvertMax"),
         			 result_set.getDouble("debitMax"),
         			 result_set.getString("situation_compte"),
         			 result_set.getInt("idClient"));
         	 comptes.add(comptes_queried);
          }
          num_column.setCellValueFactory(new PropertyValueFactory<>("num_compte"));
          solde_column.setCellValueFactory(new PropertyValueFactory<>("solde"));
          decouvertmax_column.setCellValueFactory(new PropertyValueFactory<>("decouvertMax"));
          debitmax_column.setCellValueFactory(new PropertyValueFactory<>("debitMax"));
          sitcompet_column.setCellValueFactory(new PropertyValueFactory<>("situation_compte"));
          idcli_column.setCellValueFactory(new PropertyValueFactory<>("idClient"));
          main_table.setItems(comptes);
      }
      catch(Exception e){
          System.out.println("Error:" + e.getMessage());
      }
      
  
  }
 
 public ObservableList<compte> getcomptesForGetButton(){

     ObservableList<compte> comptes = FXCollections.observableArrayList();
     Connection connect = DbConnect.getConnect();
     Integer box = idclientbox.getSelectionModel().getSelectedItem();
     String sql_query = "SELECT * FROM compte WHERE idClient = " + box + "";


     try(Statement statement = connect.createStatement()){
         ResultSet result_set = statement.executeQuery(sql_query);
         while(result_set.next()){
        	 compte comptes_queried = new  compte(
        			 result_set.getInt("num_compte"),
        			 result_set.getDouble("solde"),
        			 result_set.getDouble("decouvertMax"),
        			 result_set.getDouble("debitMax"),
        			 result_set.getString("situation_compte"),
        			 result_set.getInt("idClient"));
        	 comptes.add(comptes_queried);
         }
     }
     catch(Exception e){
         System.out.println("Error:" + e.getMessage());
     }
     return comptes;
 }

 public void pushcomptesOntoTableForGetButton(){

     ObservableList<compte> comptes = getcomptesForGetButton();

     num_column.setCellValueFactory(new PropertyValueFactory<>("num_compte"));
     solde_column.setCellValueFactory(new PropertyValueFactory<>("solde"));
     decouvertmax_column.setCellValueFactory(new PropertyValueFactory<>("decouvertMax"));
     debitmax_column.setCellValueFactory(new PropertyValueFactory<>("debitMax"));
     sitcompet_column.setCellValueFactory(new PropertyValueFactory<>("situation_compte"));
     idcli_column.setCellValueFactory(new PropertyValueFactory<>("idClient"));
     main_table.setItems(comptes);
 }

 public void pushcomptesOntoTable(){

     ObservableList<compte> comptes = getcomptes();

     num_column.setCellValueFactory(new PropertyValueFactory<>("num_compte"));
     solde_column.setCellValueFactory(new PropertyValueFactory<>("solde"));
     decouvertmax_column.setCellValueFactory(new PropertyValueFactory<>("decouvertMax"));
     debitmax_column.setCellValueFactory(new PropertyValueFactory<>("debitMax"));
     sitcompet_column.setCellValueFactory(new PropertyValueFactory<>("situation_compte"));
     idcli_column.setCellValueFactory(new PropertyValueFactory<>("idClient"));

     main_table.setItems(comptes);
 }

 public void createcompte() throws SQLException {
	 Integer idclientbox = idclientboxcrud.getSelectionModel().getSelectedItem();
     if(num_text.getText().equals("") || deb_max_text.getText().equals("") ||  decmax_text.getText().equals("")  || idclientboxcrud.getValue()== null ) {
         Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
         alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
         alert.show();
     }
     else if(Integer.parseInt(deb_max_text.getText())<0   || Integer.parseInt(decmax_text.getText()) >0) {
    	 Alert alert = new Alert(Alert.AlertType.ERROR, "Please check Debit Or decouvert Max!", ButtonType.OK);
         alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
         alert.show();
     }
     else{
         String sql_query = "INSERT INTO compte VALUES(" + num_text.getText() + ",0," + decmax_text.getText() + ",'" + deb_max_text.getText() + "','','" + idclientbox + "')";
         establishSQLConnection(sql_query);
         pushcomptesOntoTable();
     }
 }
 public void mouseClicked(MouseEvent mouseEvent) {

	 compte compte = (compte) main_table.getSelectionModel().getSelectedItem();

	 num_text.setText(String.valueOf(compte.getNum_compte()));
	 deb_max_text.setText(String.valueOf(compte.getDebitMax()));
	 decmax_text.setText(String.valueOf(compte.getDecouvertMax()));
	 idclientboxcrud.setValue(compte.getIdClient());
	 
 }

 public void updatecompte() throws SQLException {
	 Integer idclientbox = idclientboxcrud.getSelectionModel().getSelectedItem();
     if(num_text.getText().equals("") || decmax_text.getText().equals("") || deb_max_text.getText().equals("") || idclientboxcrud.getValue()== null ) {
         Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
         alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
         alert.show();

     }
    
     else {
         String sql_query = "UPDATE compte SET decouvertMax = " + decmax_text.getText() + ",debitMax = '" + deb_max_text.getText() + "', idClient  = '" + idclientbox + "' WHERE num_compte  = " + num_text.getText() + "";
         establishSQLConnection(sql_query);
         pushcomptesOntoTable();
     }
 }

 private void deletecompte() throws SQLException {
	 
     if(num_text.getText().equals("") || decmax_text.getText().equals("") || deb_max_text.getText().equals("") || idclientboxcrud.getValue()== null ){

         Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row in the table or add an ID in the text field to delete!", ButtonType.OK);
         alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
         alert.show();
     }
     else{
         String sql_query = "DELETE FROM compte WHERE num_compte = " + num_text.getText() + "";
         establishSQLConnection(sql_query);
         pushcomptesOntoTable();
     }
 }

 public void getcomptesByID() throws SQLException{
	 Integer box = idclientbox.getSelectionModel().getSelectedItem();
     if(idclientbox.getValue()== null ){

         Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter an ID to retrieve corresponding compte entity!", ButtonType.OK);
         alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
         alert.show();
     }
     else{
         String sql_query = "SELECT * FROM compte WHERE idClient  = " + box + "";
         establishSQLConnection(sql_query);
         pushcomptesOntoTableForGetButton();
     }
 }

 
 private void establishSQLConnection(String sql_query) throws SQLException {

     Connection connect_object = DbConnect.getConnect();

     try(Statement statement = connect_object.createStatement()){
         statement.executeUpdate(sql_query);
     }
     catch (Exception e){

     }
 }

 
 public void buttonPressed(javafx.event.ActionEvent actionEvent) throws SQLException {

     
     if (actionEvent.getSource() == create_btn ){
         createcompte();
     }
     else if(actionEvent.getSource() == update_btn){
         updatecompte();
     }

     else if(actionEvent.getSource() == delete_btn){
         deletecompte();
     }

     else if (actionEvent.getSource() == get_button){
         getcomptesByID();
     }

     else if(actionEvent.getSource() == revert_button){
         pushcomptesOntoTable();
         
     }
 }



 
 @Override
 public void initialize(URL location, ResourceBundle resources) {
	 connection = DbConnect.getConnect();
	 try {
		makecomptesdecouvert();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     pushcomptesOntoTable();
    this.getAllIdAccount();
 }
 public void  makecomptesdecouvert() throws SQLException{

	 query = " SELECT * FROM `compte` ";
     Connection connect_object = DbConnect.getConnect();
     preparedStatement = connect_object.prepareStatement(query);
     resultSet = preparedStatement.executeQuery();
     while (resultSet.next()){
    	 if(resultSet.getDouble("solde")<0) {
    		  String sql_query = "UPDATE `compte` SET `situation_compte` = 'rouge' WHERE `num_compte`  = " + resultSet.getDouble("num_compte") + "  ";
              

              try(Statement statement = connect_object.createStatement()){
                  statement.executeUpdate(sql_query);
              }
              catch (Exception e){

              }
    	 }
    	 else {
    		 String sql_query = "UPDATE `compte` SET `situation_compte` = 'normal' WHERE `num_compte`  = " + resultSet.getDouble("num_compte") + "  ";
             

             try(Statement statement = connect_object.createStatement()){
                 statement.executeUpdate(sql_query);
             }
             catch (Exception e){

             }
    		 
    	 }
     }
	
 }
 
 @FXML
 void getAddView(MouseEvent event) {
 	try {
         Parent parent = FXMLLoader.load(getClass().getResource("/CompteThings/consultercompte.fxml"));
         Scene scene = new Scene(parent);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.initStyle(StageStyle.UTILITY);
         stage.show();
     } catch (IOException ex) {
         Logger.getLogger(tableviewController.class.getName()).log(Level.SEVERE, null, ex);
     }

 }

 @FXML
 void getAddViewOperation(MouseEvent event) {
 	try {
         Parent parent = FXMLLoader.load(getClass().getResource("/CompteThings/Transfertcompte.fxml"));
         Scene scene = new Scene(parent);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.initStyle(StageStyle.UTILITY);
         stage.show();
     } catch (IOException ex) {
         Logger.getLogger(tableviewController.class.getName()).log(Level.SEVERE, null, ex);
     }

 }

}
