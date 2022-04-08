package ie.itcarlow;
import java.sql.*;

public class DBConnection {
	
	static final String DB_URL="jdbc:mysql://localhost/customerdatabase";
	static final String USER="root";
	static final String PASS="";
	
	public static Connection connectDB() {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(DB_URL,USER,PASS);
			return con;
		}
		catch (Exception e)
		{
			System.out.println("There were error while connecting to db.");
			return null;
		}
	}

}
