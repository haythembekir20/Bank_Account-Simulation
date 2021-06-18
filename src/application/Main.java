package application;
	
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		 try {
			
	            Parent parent = FXMLLoader.load(getClass().getResource("/application/login.fxml"));
	            Scene scene = new Scene(parent);
	            primaryStage.setScene(scene);
	            primaryStage.initStyle(StageStyle.UTILITY);
	            primaryStage.show();
	        } catch (IOException ex) {
	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
