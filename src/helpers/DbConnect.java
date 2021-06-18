package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*
* @author hocin
*/
public class DbConnect {

	
private static String HOST="127.0.0.1";
private static int PORT = 3306;

private static String DB_NAME="fx_Project";
private static String USERNAME="root";
private static String PASSWORD="";
private static Connection connection;

public static Connection getConnect() {
	 try {
         connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST,PORT,DB_NAME),USERNAME,PASSWORD);
     } catch (SQLException ex) {
         Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
     }
         
         return  connection;	
}



	
}
