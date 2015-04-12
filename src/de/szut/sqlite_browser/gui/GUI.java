package de.szut.sqlite_browser.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import de.szut.sqlite_browser.sql.PropLoader;

public class GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8100478810283864000L;

	/**
	 * Create the frame.
	 */
	public GUI(Menu menu, Panel panel, PropLoader props) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(props.getWindowDimension());
		setContentPane(panel);
		setJMenuBar(menu);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				props.setWindowDimension(getBounds());
			}
		});
	}

}
