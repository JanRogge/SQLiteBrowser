package de.szut.sqlite_browser.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import de.szut.sqlite_browser.sql.Model;

public class Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4875651530836884893L;
	private JTextField commandField;
	private JTable dataTable;
	private JTextField limitFrom;
	private JTextField limitTo;
	private JTree databaseTree;
	private DefaultMutableTreeNode top;
	private Model model;
	private JLabel lblConnection;
	private boolean limit;

	public Panel() {
		this.setLayout(new BorderLayout(0, 0));

		JPanel connectionPanel = new JPanel();
		this.add(connectionPanel, BorderLayout.SOUTH);
		connectionPanel.setLayout(new BorderLayout(0, 0));

		lblConnection = new JLabel("No Connection ");
		lblConnection.setHorizontalAlignment(SwingConstants.RIGHT);
		connectionPanel.add(lblConnection);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(150);
		this.add(splitPane, BorderLayout.CENTER);

		JPanel dataPanel = new JPanel();
		splitPane.setRightComponent(dataPanel);
		dataPanel.setLayout(new BorderLayout(0, 0));

		JPanel limitPanel = new JPanel();
		dataPanel.add(limitPanel, BorderLayout.SOUTH);
		limitPanel.setLayout(new GridLayout(0, 3, 0, 0));

		JCheckBox checkBoxLimit = new JCheckBox("Limit");
		checkBoxLimit.addActionListener(e -> {
			if (checkBoxLimit.isSelected()) {
				limit = true;
			} else {
				limit = false;
			}
			System.out.println(limitFrom.getText());
			System.out.println(limit);
		});
		limitPanel.add(checkBoxLimit);

		limitFrom = new JTextField();
		limitFrom.setToolTipText("Lower Limit");
		limitPanel.add(limitFrom);

		limitTo = new JTextField();
		limitTo.setToolTipText("Upper Limit");
		limitPanel.add(limitTo);

		JPanel commandPanel = new JPanel();
		dataPanel.add(commandPanel, BorderLayout.NORTH);
		commandPanel.setLayout(new BorderLayout(0, 0));

		commandField = new JTextField();
		commandField.addActionListener(e -> {
			model.executeQuery(commandField.getText(), true);
		});
		commandPanel.add(commandField);

		JScrollPane tableScrollPane = new JScrollPane();
		dataPanel.add(tableScrollPane, BorderLayout.CENTER);

		dataTable = new JTable();
		dataTable.getTableHeader().setReorderingAllowed(false);
		dataTable.setAutoCreateRowSorter(true);
		dataTable.setEnabled(false);
		tableScrollPane.setViewportView(dataTable);

		JScrollPane treeScrollPane = new JScrollPane();
		splitPane.setLeftComponent(treeScrollPane);

		top = new DefaultMutableTreeNode("Database");
		databaseTree = new JTree(top);
		databaseTree.addTreeSelectionListener(e -> {
			DefaultMutableTreeNode n = (DefaultMutableTreeNode) databaseTree
					.getLastSelectedPathComponent();
			if (lblConnection.getText().contains("Successful")) {
				if (n != null && n.getChildCount() == 0) {
					model.executeQuery("'" + (String) n.getUserObject() + "'",
							false);
				}
			}

		});
		databaseTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		treeScrollPane.setViewportView(databaseTree);
	}

	public void nodes(ArrayList<String> tableNames) {
		for (String name : tableNames) {
			top.add(new DefaultMutableTreeNode(name));
		}
		repaint();
	}

	public void updateDataList(Object[][] data, String[] columnNames) {
		dataTable.setModel(new DefaultTableModel(data, columnNames));
		repaint();
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setStatus(String status) {
		lblConnection.setText(status);
	}

	public void resetTree() {
		int x = top.getChildCount();
		for (int i = 0; i < x; i++) {
			top.remove(0);
		}
		databaseTree.repaint();
		databaseTree.collapsePath(new TreePath(top.getPath()));
	}

	public boolean getLimitStatus() {
		return limit;
	}

	public String getLimitFrom() {
		return limitFrom.getText();
	}

	public String getLimitTo() {
		return limitTo.getText();
	}

	public void errormsg(String error) {
		JOptionPane.showMessageDialog(this, error, "An Error occurred",
				JOptionPane.ERROR_MESSAGE);
	}
}
