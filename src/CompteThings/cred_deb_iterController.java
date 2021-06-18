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

public class cred_deb_iterController implements Initializable {

    

    @FXML
    private TextField montantflied;

    @FXML
    private Button crediterbtn;

    @FXML
    private ComboBox<Integer> numcomptecombobox;
    
    @FXML
    private Button debiterbtn;
    
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
			
			numcomptecombobox.setItems(allId);
			
			
						} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
    
    double debitMax;
    		
    @FXML
    void debitercliked(MouseEvent event) {
    	
    	Integer box = numcomptecombobox.getSelectionModel().getSelectedItem();
    	
		if( montantflied.getText().equals("") || numcomptecombobox.getValue()== null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();

        }
try {
    		
            
            query = " SELECT * FROM `compte` WHERE `num_compte`  = " + box + " ";
            Connection connect_object = DbConnect.getConnect();
            preparedStatement = connect_object.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
            	double retraitsolde= resultSet.getDouble("solde")-Integer.parseInt(montantflied.getText());
            	if( Integer.parseInt(montantflied.getText()) >= resultSet.getDouble("debitMax") || Integer.parseInt(montantflied.getText()) < 0 || retraitsolde <resultSet.getDouble("decouvertMax") ) {
            		
            		Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill retraitsolde"+retraitsolde, ButtonType.OK);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.show();
            	}
           	 else {
                    String sql_query = "UPDATE `compte` SET `solde` = `solde` -" + montantflied.getText() +  " WHERE `num_compte`  = " + box + "  ";

                    try(Statement statement = connect_object.createStatement()){
                        statement.executeUpdate(sql_query);
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

    @FXML
    void crediteraction(MouseEvent event) {
    	Integer box = numcomptecombobox.getSelectionModel().getSelectedItem();
    	 if( montantflied.getText().equals("") || numcomptecombobox.getValue()== null ) {
             Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill all text fields!", ButtonType.OK);
             alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
             alert.show();

         }
    	 else {
              String sql_query = "UPDATE `compte` SET `solde` = `solde` +" + montantflied.getText() +  " WHERE `num_compte`  = " + box + "  ";
             Connection connect_object = DbConnect.getConnect();

             try(Statement statement = connect_object.createStatement()){
                 statement.executeUpdate(sql_query);
             }
             catch (Exception e){

             }
             Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
             stage.close();
             
         }

    }
   
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		connection = DbConnect.getConnect();
	     
	    this.getAllIdAccount();
		
	}

}



