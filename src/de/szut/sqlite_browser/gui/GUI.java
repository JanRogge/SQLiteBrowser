package de.szut.sqlite_browser.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTree;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;


public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3950604164601877185L;
	private JPanel contentPane;
	private JTextField queryField;
	private JTable contentTable;
	private JTextField limitfrom;
	private JTextField limitto;
	private JTree contentTree;
	private JLabel lblConnection;
	private JCheckBox checkLimit;
	private JScrollPane scrollpanecontentTabel;
	private JScrollPane scrollpanecontentTree;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem openFileItem;

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
		setBounds(100, 100, 800, 620);
		setResizable(false);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		openFileItem = new JMenuItem("Open");
		menuFile.add(openFileItem);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollpanecontentTree = new JScrollPane();
		scrollpanecontentTree.setBounds(5, 4, 146, 536);
		contentPane.add(scrollpanecontentTree);
		
		scrollpanecontentTabel = new JScrollPane();
		scrollpanecontentTabel.setBounds(161, 34, 623, 506);
		contentPane.add(scrollpanecontentTabel);
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Test");
		createNodes(top);
		contentTree = new JTree(top);
		scrollpanecontentTree.setViewportView(contentTree);
		
		queryField = new JTextField();
		queryField.setBounds(161, 4, 623, 20);
		contentPane.add(queryField);
		queryField.setColumns(10);
		
		contentTable = new JTable();
		contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollpanecontentTabel.setViewportView(contentTable);
		contentTable.setModel(new DefaultTableModel(new Object[][] {},new String[] {}));
		
		lblConnection = new JLabel("Connection");
		lblConnection.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConnection.setBounds(657, 544, 127, 22);
		contentPane.add(lblConnection);
		
		checkLimit = new JCheckBox("Limit");
		checkLimit.setBounds(5, 544, 75, 23);
		contentPane.add(checkLimit);
		
		limitfrom = new JTextField();
		limitfrom.setText("von");
		limitfrom.setBounds(86, 545, 150, 20);
		contentPane.add(limitfrom);
		
		limitto = new JTextField();
		limitto.setText("bis");
		limitto.setBounds(248, 545, 150, 20);
		contentPane.add(limitto);
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
