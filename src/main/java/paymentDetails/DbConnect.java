package paymentDetails;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {
	
	
	private static String url = "jdbc:mysql://localhost:3306/payment_details";
	private static String unName = "root";
	private static String pass = "45615";
	private static Connection con;
	
	public static Connection getconnection() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(url, unName, pass);
			
		} catch (Exception e) {
			
			System.out.println("Connection faild!!");
		}
		
		return con;
	}

}
