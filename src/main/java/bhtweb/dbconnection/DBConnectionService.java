/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhtweb.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.Driver;

import javax.naming.InitialContext;

/**
 *
 * @author NguyenHongPhuc
 * Thực hiện kết nối CSDL thông qua connection pools
 * Khuyến khích tìm hiểu về object pools nhé, dùng trong nhiều nơi (project) lắm
 * 
 */
public class DBConnectionService {
    
	//Biến constant khai báo JNDI đến Pool MySQL (sử dụng pool của GlassFish).
	private static final String MYSQL_POOL_JNDI = "jdbc/j2ee_BHTWeb_MySQLConnection";
	
	protected static void LoadJDBCDriver() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			throw new Exception("Không tìm được MySQL JDBC Driver.");
		}
	}
	
	// temp
	protected static void loadJDBCDriver() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (java.lang.ClassNotFoundException e) {
			throw new Exception("SQL JDBC Driver not found ...");
		}
	}

	public static Connection getConnection() throws Exception {
//		Connection connection = null;
//		try {
//			DataSource dSource = (DataSource) new InitialContext().lookup(MYSQL_POOL_JNDI);
//			connection = dSource.getConnection();
//		}catch (Exception e) {
//			// TODO: handle exception
//			throw new Exception("Không thể truy cập đến Database Server ... " + e.getMessage());
//		}
//		return connection;
		
		Connection connect = null;
	    if (connect == null) {
	    loadJDBCDriver();
	    try {
	    String connectionString = "jdbc:mysql://javaee-bhtcnpm-db-mysql.mysql.database.azure.com:3306/bhtcnpm_db?useSSL=true&useUnicode=yes&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//	    connect = DriverManager.getConnection("jdbc:mysql://javaee-bhtcnpm-db-mysql.mysql.database.azure.com:3306/bhtcnpm_db?useSSL=true&requireSSL=false&serverTimezone=UTC",
//	    "bhtcnpm@javaee-bhtcnpm-db-mysql", "PXgiip4dQSt67p5");
	    connect = DriverManager.getConnection(connectionString,
	    		"bhtcnpm@javaee-bhtcnpm-db-mysql", "PXgiip4dQSt67p5");
	    System.out.println("Connection String : " + connectionString);
	    } catch (java.sql.SQLException e) {
	    throw new Exception("Can not access to Database Server ..." + e.getMessage());
	    }
	    }
	    return connect;
	}
	
}
