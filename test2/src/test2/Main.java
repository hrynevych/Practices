package test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

public class Main {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
		 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hospital?" +
		                                    "user=root");
		 Statement stmt = conn.createStatement();
		 ResultSet rs = null;// = stmt.executeQuery("SELECT * FROM roles");
		 if (stmt.execute("SELECT * FROM patients")) {
			 rs = stmt.getResultSet();
			 while (rs.next()) {
				 Date o = rs.getDate(4);
				 Date no = new Date(o.getTime());
				 System.out.println(" " + no);
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
