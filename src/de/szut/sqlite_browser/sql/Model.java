package de.szut.sqlite_browser.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import de.szut.sqlite_browser.gui.Panel;

public class Model {
	private Connector connector;
	private ArrayList<String> tableNames;
	private Panel panel;

	public Model(Panel panel) {
		connector = new Connector();
		tableNames = new ArrayList<String>();
		this.panel = panel;
	}

	/***
	 * Baut die Verbindung zur Datenbank auf, setzt den Status der Verbindnung
	 * sowie der JTree wird mit den Table Namen gefüllt
	 */
	public void openConnection(String path, String name) {
		try {
			connector.connection(path);
			ResultSet tables = connector.getTables();
			while (tables.next()) {
				tableNames.add(tables.getString(3));
			}
			panel.nodes(tableNames);
			panel.setStatus("Successful connected to " + name + " ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			panel.errormsg("Failed to Open the Database");
		}
	}

	/***
	 * Führt die Querys aus und fügt ggf. Teile zum Query hinzu
	 */
	public void executeQuery(String query, boolean fromTextfield) {
		Object[][] data;
		String[] column;
		String[] splitQuery;
		StringBuilder build;
		String query1;
		String rows;
		int introws = 0;
		ResultSet queryResult;
		ResultSet numberofRows;
		ResultSetMetaData queryResultMetaData;
		try {

			// Überprüfung ob der Query aus dem JTree kommt oder vom Benutzer
			// selber eingegeben wurde
			if (fromTextfield) {
				query1 = query;
				if (query1.contains("count(")) {
					rows = query;
					introws = 1;
				} else {
					build = new StringBuilder();
					splitQuery = query.split(" ");
					for (int i = 0; i < splitQuery.length; i++) {
						if (i != 1) {
							build.append(splitQuery[i]);
							build.append(" ");
						} else {
							if (splitQuery[i].contains(",")) {
								String[] tempsplit = splitQuery[i].split(",");
								build.append("count(");
								build.append(tempsplit[0]);
								build.append(")");
								build.append(" ");
							} else {
								build.append("count(");
								build.append(splitQuery[i]);
								build.append(") ");
							}

						}

					}
					rows = build.toString();
				}
			} else {
				query1 = "Select * from " + query;
				rows = "Select count(*) from " + query;
			}

			// Hinzufügen des Limits zum Statment
			if (panel.getLimitStatus()
					&& isNumeric(panel.getLimitFrom(), panel.getLimitTo())) {
				build = new StringBuilder();
				build.append(query1);
				build.append(" Limit ");
				if (panel.getLimitFrom().equals("")
						&& panel.getLimitTo().equals("")) {
					build = new StringBuilder();
					build.append(query1);
					query1 = build.toString();
				} else if (panel.getLimitFrom().equals("")) {
					build.append(panel.getLimitTo());
					query1 = build.toString();
				} else if (panel.getLimitTo().equals("")) {
					build.append(panel.getLimitFrom());
					query1 = build.toString();
				} else {
					build.append(panel.getLimitFrom());
					build.append(",");
					build.append(panel.getLimitTo());
					query1 = build.toString();
				}

			}
			queryResult = connector.executeQuery(query1);
			numberofRows = connector.executeQuery(rows);
			if (queryResult != null) {
				queryResultMetaData = queryResult.getMetaData();
				numberofRows.next();
				column = new String[queryResultMetaData.getColumnCount()];
				for (int i = 1; i <= queryResultMetaData.getColumnCount(); i++) {
					column[i - 1] = queryResultMetaData.getColumnName(i);
				}
				if (introws == 1) {
					data = new Object[1][queryResultMetaData.getColumnCount()];
				} else {
					data = new Object[numberofRows.getInt(1)][queryResultMetaData
							.getColumnCount()];
				}

				int counter = 0;
				while (queryResult.next()) {
					for (int i = 0; i < queryResultMetaData.getColumnCount(); i++) {
						data[counter][i] = queryResult.getString(i + 1);
					}
					counter++;
				}

				panel.updateDataList(data, column);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			panel.errormsg("Failed to exectue the Query. Maybe you type something wrong.");
		}
	}

	/***
	 * Beendet die Connection setzt die Status anzeige zurück sowiet restet die
	 * Tabel und den Baum
	 */
	public void closeConnection() {
		try {
			panel.setStatus("No Connection ");
			connector.closeConnetion();
			panel.updateDataList(null, null);
			panel.resetTree();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			panel.errormsg("Failed to Close the Connection");
		}
	}

	public boolean isNumeric(String limitFrom, String limitTo) {
		try {
			int from = Integer.parseInt(limitFrom);
			Integer.parseInt(limitTo);
			if (from < 0) {
				return false;
			}
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
