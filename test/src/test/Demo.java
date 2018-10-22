package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
		 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hospital?" +
		                                    "user=root");
		 Statement stmt = conn.createStatement();
		 ResultSet rs = null;// = stmt.executeQuery("SELECT * FROM roles");
		 if (stmt.execute("SELECT * FROM roles")) {
			 rs = stmt.getResultSet();
			 while (rs.next()) {
				 System.out.println(rs.getInt(1));
				 System.out.println(rs.getString(2));
			 }
		 }
			    // it is a good idea to release
			    // resources in a finally{} block
			    // in reverse-order of their creation
			    // if they are no-longer needed

			    if (rs != null) {
			        try {
			            rs.close();
			        } catch (SQLException sqlEx) { } // ignore

			        rs = null;
			    }

			    if (stmt != null) {
			        try {
			            stmt.close();
			        } catch (SQLException sqlEx) { } // ignore

			        stmt = null;
			    }
	}
}
