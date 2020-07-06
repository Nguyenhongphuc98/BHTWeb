package bhtweb.dbaccess;

import java.sql.ResultSet;
import java.sql.Statement;

import bhtweb.entities.BHTSubject;

public class TestConnectionStringMapper extends DBMapper {
	
	public TestConnectionStringMapper() throws Exception {
		super();
	}
	
	public void printConnectionParam() {
		try {     
            Statement stmt = getConnection().createStatement();
            String sqlStr = "SHOW VARIABLES WHERE Variable_name LIKE 'character\\_set\\_%' OR Variable_name LIKE 'collation%';\r\n" + 
            		"";
            ResultSet rs = stmt.executeQuery(sqlStr); // Send the query to the server
            int count = 0;
            while (rs != null && rs.next()) {
                
            	String variable_name = rs.getString(1);
            	String value = rs.getString(2);
            	
            	System.out.println("Variable : " + variable_name + " value = " + value);
            	
            }          
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}