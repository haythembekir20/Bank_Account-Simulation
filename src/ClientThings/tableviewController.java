package ClientThings;
import javafx.geometry.Insets;
import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import helpers.DbConnect;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.Client;

/**
 * FXML Controller class
 *
 * @author hocin
 */
public class tableviewController implements Initializable {

    @FXML
    private TableColumn<Client, String> editCol;

    @FXML
    private TableColumn<Client, String> idCol;

    @FXML
    private TableColumn<Client, String> nameCol;

    @FXML
    private TableColumn<Client, String> adressCol;

    @FXML
    private TableColumn<Client, String> prenomCol;

    

    @FXML
    private TableView<Client> clientsTable;

    
    
    String query=null;
    Connection connection =null;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Client client = null ;
    
    ObservableList<Client>  ClientList = FXCollections.observableArrayList();
    
    @FXML
    void getAddView(MouseEvent event) {
    	try {
            Parent parent = FXMLLoader.load(getClass().getResource("/ClientThings/addclient.fxml"));
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
    void refreshtable() {
    	try {
    		ClientList.clear();
            
            query = "SELECT * FROM `Client`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
            	ClientList.add(new  Client(
                        resultSet.getInt("idClient"),
                        resultSet.getString("NomCli"),
                        resultSet.getString("PrenomCli"),
                        resultSet.getString("adrCli")));
            	clientsTable.setItems(ClientList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(tableviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void print(ActionEvent event) {

    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadDate();
		
	}

	private void loadDate() {
		connection = DbConnect.getConnect();
		refreshtable();
		idCol.setCellValueFactory(new PropertyValueFactory<>("idClient"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("NomCli"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("PrenomCli"));
		adressCol.setCellValueFactory(new PropertyValueFactory<>("adrCli"));
		
		
		
		
        Callback< TableColumn <Client, String>,  TableCell <Client, String>> cellFoctory = (TableColumn<Client, String> param) -> {
           
           final TableCell<Client, String> cell = new TableCell<Client, String>() {
               @Override
               public void updateItem(String item, boolean empty) {
                   super.updateItem(item, empty);
                   
                   if (empty) {
                       setGraphic(null);
                       setText(null);

                   } else {
                	   FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                       FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                       deleteIcon.setStyle(
                               " -fx-cursor: hand ;"
                               + "-glyph-size:28px;"
                               + "-fx-fill:#ff1744;"
                       );
                       editIcon.setStyle(
                               " -fx-cursor: hand ;"
                               + "-glyph-size:28px;"
                               + "-fx-fill:#00E676;"
                       );
                       deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                           try {
                               client = clientsTable.getSelectionModel().getSelectedItem();
                               query = "DELETE FROM `client` WHERE idClient  ="+client.getIdClient();
                               connection = DbConnect.getConnect();
                               preparedStatement = connection.prepareStatement(query);
                               preparedStatement.execute();
                               refreshtable();
                               
                           } catch (SQLException ex) {
                               Logger.getLogger(tableviewController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                          

                         

                       });
                       editIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                           client = clientsTable.getSelectionModel().getSelectedItem();
                           FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("/ClientThings/addclient.fxml"));
                           try {
                               loader.load();
                           } catch (IOException ex) {
                               Logger.getLogger(tableviewController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                           addclientController addclientController = loader.getController();
                           addclientController.setUpdate(true);
                           addclientController.setTextField(client.getIdClient(), client.getNomCli(), 
                        		   client.getPrenomCli(),client.getAdrCli());
                           Parent parent = loader.getRoot();
                           Stage stage = new Stage();
                           stage.setScene(new Scene(parent));
                           stage.initStyle(StageStyle.UTILITY);
                           stage.show();
                           

                          

                       });
                       HBox managebtn = new HBox(editIcon, deleteIcon);
                       managebtn.setStyle("-fx-alignment:center");
                       HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                       HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                       setGraphic(managebtn);

                       setText(null);

                   }
               }

           };

           return cell;
       };
        editCol.setCellFactory(cellFoctory);
        clientsTable.setItems(ClientList);
                    
        
        
	  }
    
	}

