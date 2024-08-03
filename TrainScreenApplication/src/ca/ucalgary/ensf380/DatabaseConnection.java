package ca.ucalgary.ensf380;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DatabaseConnection {
	
	// Private Data Members Initialization To Hold Values Required To Access Our SQL Database Called "subway":
	
	private static final String URL = "jdbc:mysql://localhost:3306/subway"; // The Location Of Our Database On The Internet Server To Gain Access Through Connection To The Database.
	private static final String USERNAME = "root"; // The Username Used To Gain Access To Our SQL Database That Contains Three Tables Of Information That We Imported Onto From The SQL Workbench.
	private static final String PASSWORD = "Waterfall@17"; // The Password USed To Gain Access To Our SQL Database That Contains Three Tables Of Information That We Imported Onto From The SQL Workbench.
	
	// Public Getter Member Function/Method To Initialize And Return A Connection To Our SQL Database Called "subway":
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	// This Is The Public Main Function/Method Used To Test The Connection To Our SQL Database, If There Is No Connection Then A SQLException Will Be Thrown Notifying Failed Database Connection Otherwise The Connection Message Will Show:
<<<<<<< HEAD

	public static void main(String[] args) {
=======
	
/**
	 public static void main(String[] args) {
>>>>>>> a0d0071865405324ff492a79314ad1e927f98ac5
	    try {
	        Connection connection = DatabaseConnection.getConnection();
	        if (connection != null) {
	            System.out.println("Connected to the database!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
<<<<<<< HEAD
**/
=======
<<<<<<< HEAD
	public List<Advertisement> fetchAdvertisements() throws SQLException {
	    List<Advertisement> ads = new ArrayList<>();
	    String query = "SELECT * FROM advertisements";
	    ResultSet resultSet = executeQuery(query);
	    while (resultSet.next()) {
	        Advertisement ad = new Advertisement();
	        ad.setId(resultSet.getInt("id"));
	        ad.setAdName(resultSet.getString("ad_name"));
	        ad.setAdType(resultSet.getString("ad_type"));
	        ad.setAdFile(resultSet.getBytes("ad_file"));
	        ads.add(ad);
	    }
	    disconnect(resultSet.getStatement().getConnection());
	    return ads;
	}

	private ResultSet executeQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

=======
	
>>>>>>> a0d0071865405324ff492a79314ad1e927f98ac5

>>>>>>> 101f250fc4e5fbdf87b44864462896ca638e0c74
}
