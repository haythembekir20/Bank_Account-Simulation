package ClientThings;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jfoenix.controls.JFXTextField;

import helpers.DbConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import models.Client;

public class addclientController implements Initializable  {

    @FXML
    private JFXTextField prenomfield;

    

    @FXML
    private JFXTextField nomfield;

    @FXML
    private JFXTextField adressefield;
    
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Client client = null;
    private boolean update;
    int clientid;

    @FXML
    void save(MouseEvent event) {
    	connection = DbConnect.getConnect();
        String nom = nomfield.getText();
        String prenom = prenomfield.getText();
        String adress = adressefield.getText();
        

        if (nom.isEmpty() || prenom.isEmpty() || adress.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();

        }
    }

    private void insert() {
    	   try {

               preparedStatement = connection.prepareStatement(query);
               preparedStatement.setString(1, nomfield.getText());
               preparedStatement.setString(2, prenomfield.getText());
               preparedStatement.setString(3, adressefield.getText());
              
               preparedStatement.execute();

           } catch (SQLException ex) {
               Logger.getLogger(addclientController.class.getName()).log(Level.SEVERE, null, ex);
           }
		
	}

	private void getQuery() {
if (update == false) {
            
            query = "INSERT INTO `client`( `NomCli`, `PrenomCli`, `adrCli`) VALUES (?,?,?)";

        }else{
            query = "UPDATE `client` SET "
                    + "`NomCli`=?,"
                    + "`PrenomCli`=?,"
                    + "`adrCli`=? WHERE idClient = '"+clientid+"'";
        }
		
	}

	@FXML
    void clean() {
		nomfield.setText(null);
		prenomfield.setText(null);
		adressefield.setText(null);
		
    }
	void setTextField(int id, String NomCli, String PrenomCli, String adrCli) {

		clientid = id;
        nomfield.setText(NomCli);
        prenomfield.setText(PrenomCli);
        adressefield.setText(adrCli);
        

    }
	
	void setUpdate(boolean b) {
        this.update = b;

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

