package dbconnection;

import java.sql.DriverManager;
import java.sql.Connection;


public class db {
	private static final String URL="jdbc:mysql://localhost:3306/tictactoe";
	private static final String USER="root";
	private static final String PASSWORD="mysql";
	
	public static Connection getConnection()
	{
		try {
			return DriverManager.getConnection(URL,USER,PASSWORD);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	

}
