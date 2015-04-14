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

	/**
	 * Baut die Verbindung zur Datenbank auf
	 * @param path Pfad der DB3 Datei
	 * @throws SQLException
	 */
	public void connection(String path) throws SQLException {
		connection = DriverManager.getConnection("jdbc:sqlite:" + path);
	}
	/**
	 * Führt den Übergebenen Query aus
	 * @param query Auszufühurender Befehlssatz
	 * @return Gibt ein ResultSet zurück wenn eine Verbindung besteht
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String query) throws SQLException {
		if (connection != null){
			return connection.createStatement().executeQuery(query);
		}else {
			return null;
		}
	}
	/**
	 * Gibt den Namen der Tablen aus
	 * @return Tablen Namen
	 * @throws SQLException
	 */
	public ResultSet getTables() throws SQLException {
		return connection.getMetaData().getTables(null, null, "%", null);
	}

	/**
	 * Trennt die Verbindung zur Datenbank falls eine vorhanden ist
	 * @throws SQLException
	 */
	public void closeConnetion() throws SQLException {
		if (connection != null){
			connection.close();
		}	
	}
}
