package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ClientThings.tableviewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class signincontroller {

    @FXML
    private TextField usernamefiled;

    @FXML
    private TextField pwdfiled;

    @FXML
    void signinaction(MouseEvent event) {
    	if(usernamefiled.getText().equals("admin") && usernamefiled.getText().equals("admin")) {
    	try {
            Parent parent = FXMLLoader.load(getClass().getResource("/application/application.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(tableviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    	}
    	else {
    		Alert alert = new Alert(Alert.AlertType.ERROR, "Your Password is False!", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    	}

    }

}

