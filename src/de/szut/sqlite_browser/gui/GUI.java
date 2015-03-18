package de.szut.sqlite_browser.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JTextField;

import java.awt.GridLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;


public class GUI extends JFrame {
	private JTextField commandField;
	private JTable dataTable;
	private JTextField limitFrom;
	private JTextField limitTo;
	private JTree databaseTree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 868, 582);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem openDatabaseItem = new JMenuItem("Open");
		fileMenu.add(openDatabaseItem);
		JPanel panel = new JPanel();
		setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel connectionPanel = new JPanel();
		panel.add(connectionPanel, BorderLayout.SOUTH);
		connectionPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblConnection = new JLabel("MySQL connectet ");
		lblConnection.setHorizontalAlignment(SwingConstants.RIGHT);
		connectionPanel.add(lblConnection, BorderLayout.NORTH);
		
		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane, BorderLayout.CENTER);
		
		JPanel dataPanel = new JPanel();
		splitPane.setRightComponent(dataPanel);
		dataPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel limitPanel = new JPanel();
		dataPanel.add(limitPanel, BorderLayout.SOUTH);
		limitPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JCheckBox checkBoxLimit = new JCheckBox("Limit");
		limitPanel.add(checkBoxLimit);
		
		limitFrom = new JTextField();
		limitFrom.setText("von");
		limitPanel.add(limitFrom);
		
		limitTo = new JTextField();
		limitTo.setText("bis");
		limitPanel.add(limitTo);
		
		JPanel commandPanel = new JPanel();
		dataPanel.add(commandPanel, BorderLayout.NORTH);
		commandPanel.setLayout(new BorderLayout(0, 0));
		
		commandField = new JTextField();
		commandPanel.add(commandField);
		
		JScrollPane tableScrollPane = new JScrollPane();
		dataPanel.add(tableScrollPane, BorderLayout.CENTER);
		
		dataTable = new JTable();
		dataTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		tableScrollPane.setViewportView(dataTable);
		
		JScrollPane treeScrollPane = new JScrollPane();
		splitPane.setLeftComponent(treeScrollPane);
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Test");
		createNodes(top);
		databaseTree = new JTree(top);
		treeScrollPane.setViewportView(databaseTree);
	}
	private void createNodes(DefaultMutableTreeNode top){
		DefaultMutableTreeNode category = null;
		DefaultMutableTreeNode book = null;
		
		category  = new DefaultMutableTreeNode("Test");
		top.add(category);
		
		book = new DefaultMutableTreeNode("book");
		category.add(book);
	}

}
