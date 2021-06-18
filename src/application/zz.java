package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ClientThings.tableviewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class zz {

    @FXML
    void qq(MouseEvent event) {
    	try {
            Parent parent = FXMLLoader.load(getClass().getResource("/ClientThings/tableview.fxml"));
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
    void viewcompte(MouseEvent event) {
    	try {
            Parent parent = FXMLLoader.load(getClass().getResource("/CompteThings/compte.fxml"));
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
