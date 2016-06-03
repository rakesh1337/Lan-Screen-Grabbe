
import java.sql.*;


public class DataConnection {
	public static Connection connect() throws SQLException{
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		String dbName = "StudentDB";
	    String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		Connection con=DriverManager.getConnection(connectionURL);
		return con;
	}
}
