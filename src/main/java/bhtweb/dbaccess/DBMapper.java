/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhtweb.dbaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bhtweb.dbconnection.DBConnectionService;

/**
 *
 * @author NguyenHongPhuc
 * Tạo kết nối đến CSDL và trả về connetion
 * kết nối với DB cho các method trong dbaccess thực thi
 * Sử dụng cho mấy class khác trong package này kế thừa
 */
public class DBMapper {
    
	private Connection connection;
	
	public DBMapper() throws Exception {
		try {
			connection = DBConnectionService.getConnection();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("DBMapper Constructor Exception...");
			e.printStackTrace();
			throw e;
		}
	}
	
	public DBMapper(Connection connection) {
		this.connection = connection;
	}
	
	public void closeConnection () {
		try {
			connection.close();
			//System.out.println("DBMapper closed");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("DBMapper close connection exception ...");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection () {
		//Kiểm tra connection, nếu connection đã bị đóng thì ta restart lại connection mới.
		try {
			checkConnection();
		}catch (Exception e) {
			// TODO: handle exception
			try {
				System.out.println("SQL Connection Refreshing ...");
				connection = DBConnectionService.getConnection();
			}catch (Exception ex) {
				// TODO: handle exception
				System.out.println("DBMapper Constructor Exception...");
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	public void setConnection (Connection connection) {
		this.connection = connection;
	}
	
	public void checkConnection () throws Exception {
		
		PreparedStatement testConnection = connection.prepareStatement("SELECT 1");
		testConnection.executeQuery();
	}
	
}
