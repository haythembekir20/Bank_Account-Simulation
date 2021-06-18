package CompteThings;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import helpers.DbConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import models.compte;

public class virementController implements Initializable {

	@FXML
    private ComboBox<Integer> numcptbox2;

    @FXML
    private TextField montantfiled;

    @FXML
    private Button transfertbtn;

    @FXML
    private ComboBox<Integer> numcptbox1;
    
    String query=null;
    Connection connection =null;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    
    public void getAllIdAccount() {
		try {
			ObservableList<Integer> allId = FXCollections.observableArrayList();
			query = "SELECT num_compte FROM compte";
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			List<compte> listCompte = new ArrayList<compte>();
			while (resultSet.next()) {
				compte c = new compte();
				c.setNum_compte(resultSet.getInt("num_compte"));
				listCompte.add(c);
			}
			Iterator<compte> it = listCompte.iterator();
			while (it.hasNext()) {
				allId.add(it.next().getNum_compte());
				
			}
			
			numcptbox1.setItems(allId);
			numcptbox2.setItems(allId);
			
			
						} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    String query1=null;
    String query2=null;
    
    
    ResultSet resultSet1 = null ;
    ResultSet resultSet2 = null ;
    
    @FXML
    void transfertevent(MouseEvent event) {
    	Integer box1 = numcptbox1.getSelectionModel().getSelectedItem();
    	Integer box2 = numcptbox2.getSelectionModel().getSelectedItem();
    	if( montantfiled.getText().equals("") || numcptbox1.getValue()== null || numcptbox2.getValue()== null ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();

        }
    	else if(numcptbox1.getValue()==numcptbox2.getValue()) {
    		 Alert alert = new Alert(Alert.AlertType.ERROR, "Please it the same account", ButtonType.OK);
             alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
             alert.show();
    	}
try {
    		
            
            query1 = " SELECT * FROM `compte` WHERE `num_compte`  = " + box1 + " ";
            query2 = " SELECT * FROM `compte` WHERE `num_compte`  = " + box2 + " ";
            Connection connect_object = DbConnect.getConnect();
            preparedStatement = connect_object.prepareStatement(query1);
            resultSet1= preparedStatement.executeQuery();
            preparedStatement = connect_object.prepareStatement(query2);
            resultSet2 = preparedStatement.executeQuery();
            
            while (resultSet1.next()){
            	double retraitsolde= resultSet1.getDouble("solde")-Integer.parseInt(montantfiled.getText());
            	if(  Integer.parseInt(montantfiled.getText()) < 0 || retraitsolde <resultSet1.getDouble("decouvertMax") ) {
            		
            		Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill retraitsolde"+retraitsolde, ButtonType.OK);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.show();
            	}
           	 else {
                    String sql_query = "UPDATE `compte` SET `solde` = `solde` -" + montantfiled.getText() +  " WHERE `num_compte`  = " + box1 + "  ";
                    String sql_query1 = "UPDATE `compte` SET `solde` = `solde` +" + montantfiled.getText() +  " WHERE `num_compte`  = " + box2 + "  ";
                    try(Statement statement = connect_object.createStatement()  ){
                    	Statement statement1 = connect_object.createStatement();
                        statement.executeUpdate(sql_query);
                        statement1.executeUpdate(sql_query1);
                    }
                    catch (Exception e){

                    }
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                    
                }
             
            	
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(cred_deb_iterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		connection = DbConnect.getConnect();
	     
	    this.getAllIdAccount();
		
		
	}

}
