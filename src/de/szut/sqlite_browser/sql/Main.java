package de.szut.sqlite_browser.sql;

import de.szut.sqlite_browser.gui.Menu;
import de.szut.sqlite_browser.gui.GUI;
import de.szut.sqlite_browser.gui.Panel;

public class Main {
	public static void main(String[] args) {
		
		Panel panel = new Panel();
		Model model = new Model(panel);
		panel.setModel(model);
		Menu menu = new Menu(model);
		PropLoader prop = new PropLoader();
		new GUI(menu, panel, prop);
	}
}
	