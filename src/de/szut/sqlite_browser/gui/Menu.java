package de.szut.sqlite_browser.gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.szut.sqlite_browser.sql.Model;

public class Menu extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 413811856288548755L;
	private static final String CHOOSER_TITLE = "Please choose a db3 file";
	private static final String DB_FOLDER = "db";
	private static final FileFilter DB_FILTER = new FileNameExtensionFilter(
			"DB3 File", "db3");

	public Menu(Model model) {

		JMenu fileMenu = new JMenu("File");
		add(fileMenu);

		JMenu dataBaseMenu = new JMenu("Database");
		add(dataBaseMenu);

		JMenuItem disconnectItem = new JMenuItem("Disconnect");
		dataBaseMenu.add(disconnectItem);
		disconnectItem.addActionListener(e -> {
			model.closeConnection();
		});

		JMenuItem openDatabaseItem = new JMenuItem("Open");
		fileMenu.add(openDatabaseItem);
		openDatabaseItem.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle(CHOOSER_TITLE);
			fileChooser.setCurrentDirectory(new File(DB_FOLDER));
			fileChooser.setFileFilter(DB_FILTER);
			int option = fileChooser.showOpenDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				model.openConnection(fileChooser.getSelectedFile()
						.getAbsolutePath(), fileChooser.getSelectedFile()
						.getName());
			}

		});
	}
}
