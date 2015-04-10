package de.szut.sqlite_browser.sql;

import java.sql.*;

public class Connector {
	private Connection connection;

	public Connector() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connection(String path) throws SQLException {
		connection = DriverManager.getConnection("jdbc:sqlite:" + path);
	}

	public ResultSet executeQuery(String query) throws SQLException {
		if (connection != null){
			return connection.createStatement().executeQuery(query);
		}else {
			return null;
		}
	}

	public ResultSet getTables() throws SQLException {
		return connection.getMetaData().getTables(null, null, "%", null);
	}

	public void closeConnetion() throws SQLException {
		if (connection != null){
			connection.close();
		}	
	}
}
