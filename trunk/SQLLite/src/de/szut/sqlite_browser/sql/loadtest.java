package de.szut.sqlite_browser.sql;

import java.sql.*;

public class loadtest {
	
	public static void main( String args[] )
	  {
	    Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:h:/eclipse/workspace/SQLLite/db/world.db3");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	    try {
			ResultSet rt = c.getMetaData().getTables(null, null, "%", null);
			while (rt.next()){
				System.out.println(rt.getString(3));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM Country;");
			while (rs.next()){
				
				
//				System.out.print(rs.getString(1) + "   ");
//				System.out.print(rs.getString(2) + "   ");
//				System.out.print(rs.getString(3) + "   ");
//				System.out.print(rs.getString(4) + "   ");
//				System.out.print(rs.getString(5) + "   ");
//				System.out.println(rs.getString(6) + "   ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
