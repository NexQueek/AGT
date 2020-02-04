package application.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Class for Centralization for the Connection String
 * @author Nex
 *
 */
public class ConnectMe {
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	public ConnectMe(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//con=DriverManager.getConnection("jdbc:mysql://192.168.2.6/plz?useUnicode=true&useJDBCCompliantTim"
				//+ "ezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","agt","12345");
			con=DriverManager.getConnection("jdbc:mysql://localhost/plz?useUnicode=true&useJDBCCompliantTim"
				+ "ezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","1234");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Statement getStatement(){
		 stmt = null;
		try {
			stmt=con.createStatement();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
		
		return stmt;			
		
	}
	public Connection getC(){
		return con;
	}
	public void closeConnection(){
	
			
			
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
