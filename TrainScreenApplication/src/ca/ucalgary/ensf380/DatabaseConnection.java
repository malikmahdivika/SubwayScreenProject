package ca.ucalgary.ensf380;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
	
	// Private Data Members Initialization To Hold Values Required To Access Our SQL Database Called "subway":
	
	private static final String URL = "jdbc:mysql://localhost:3306/subway"; // The Location Of Our Database On The Internet Server To Gain Access Through Connection To The Database.
	private static final String USERNAME = "root"; // The Username Used To Gain Access To Our SQL Database That Contains Three Tables Of Information That We Imported Onto From The SQL Workbench.
	private static final String PASSWORD = "Soumik_15/"; // The Password USed To Gain Access To Our SQL Database That Contains Three Tables Of Information That We Imported Onto From The SQL Workbench.
	
	// Public Getter Member Function/Method To Initialize And Return A Connection To Our SQL Database Called "subway":
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	// This Is The Public Main Function/Method Used To Test The Connection To Our SQL Database, If There Is No Connection Then A SQLException Will Be Thrown Notifying Failed Database Connection Otherwise The Connection Message Will Show:
	
	
	 
	 public static void main(String[] args) {
	    try {
	        Connection connection = DatabaseConnection.getConnection();
	        if (connection != null) {
	            System.out.println("Connected to the database!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	

}
