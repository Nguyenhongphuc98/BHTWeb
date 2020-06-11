/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhtweb.dbconnection;

import java.sql.Connection;

import javax.sql.DataSource;
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
	
	public static Connection getConnection() throws Exception {
		Connection connection = null;
		try {
			DataSource dSource = (DataSource) new InitialContext().lookup(MYSQL_POOL_JNDI);
			connection = dSource.getConnection();
		}catch (Exception e) {
			// TODO: handle exception
			throw new Exception("Không thể truy cập đến Database Server ... " + e.getMessage());
		}
		return connection;
	}
	
}
