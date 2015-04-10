package de.szut.sqlite_browser.gui;

import javax.swing.JFrame;

public class GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8100478810283864000L;

	/**
	 * Create the frame.
	 */
	public GUI(Menu menu, Panel panel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 868, 582);
		setContentPane(panel);
		setJMenuBar(menu);
		setVisible(true);
	}

}
