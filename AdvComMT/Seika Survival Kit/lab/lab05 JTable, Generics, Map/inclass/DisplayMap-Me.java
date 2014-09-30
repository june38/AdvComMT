package com.lab05.HW;

//5631363921 Sutatta Adisornvorakij


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class DisplayMap extends JFrame implements ItemListener{
	// width and height of the main window
	private final static int MAINWINDOW_WIDTH = 600;
	private final static int MAINWINDOW_HEIGHT = 600;

	// width and height of any dialog window to be created
	private final static int DIALOG_WIDTH = 500;
	private final static int DIALOG_HEIGHT = 135;

	// default width of any created text field
	private final static int TEXTFIELD_WIDTH = 25;

	private Map<Integer, String> m;

	// top panel
	private JPanel topPanel;
	private JButton addButton,removeButton;
	private JTextField searchField;

	// center panel
	private JPanel centerPanel;
	private JScrollPane scrollPane;
	private JTable table;
	private TableModel originalTableModel;  // you must read TableModel and AbstractTableModel
	private TableModel tableModel;

	// add new data -> dialog window
	private JFrame addWindow;
	private JPanel topAddWindow, bottomAddWindow;
	private JTextField idFill, nameFill;
	private JButton okAdd, cancelAdd;

	private Container cp;
	private boolean isNum=false;
	

	public DisplayMap() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		m = new TreeMap<Integer, String>();
		m.put(01, "Jon Doe");
		cp = getContentPane();

		// create and add top panel
		topPanel = new JPanel();
		addButton = new JButton("Add Student");
		removeButton = new JButton("remove selected rows");
		searchField = new JTextField();
		searchField.setColumns(10);
		topPanel.add(addButton,BorderLayout.WEST);
		topPanel.add(new JLabel("  "));
		topPanel.add(searchField,BorderLayout.CENTER);
		topPanel.add(new JLabel("  "));
		topPanel.add(removeButton,BorderLayout.EAST);
		cp.add(topPanel, BorderLayout.NORTH);

		// create and add center panel
		centerPanel = new JPanel();

		tableModel = new AbstractTableModel() {
			private String[] columnNames = { "Student ID", "Student Name" };

			public String getColumnName(int index) {
				return columnNames[index];
			}
			public boolean isCellEditable(int row, int col){
				return true;
			}

			// Return a value at the specified position in the table.
			// Student must navigate the map, which is a source data, and return
			// a correct result.
			// For student to do!
			@Override
			public Object getValueAt(int row, int column) {
				int rrow=0;
				for(Map.Entry<Integer, String> entry: m.entrySet()) {
					if(rrow==row){
						if(column==0){
							return entry.getKey();
							
						}else{
							return entry.getValue();
						
						}
					}
					rrow++;
				}
				return rrow;
			}
			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return m.size();
			}

			@Override
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return 2;
			}
			public void setValueAt(Object aValue, int rowIndex, int colIndex){
				Set<Integer> keyList = m.keySet();
				Integer[] keyArray = keyList.toArray(new Integer[keyList.size()]);
				if(colIndex == 0){
					//key
					for(int i=0; i<keyList.size(); i++){
						if(rowIndex == i){		
							m.put(Integer.parseInt((String)aValue),m.remove(keyArray[i])); 
						}
					}	
				}else{
					//value
					for(int i=0; i<keyList.size(); i++){
							if(rowIndex == i){
								m.put(keyArray[i], (String) aValue);							
							}
					}	
				}
				fireTableCellUpdated(rowIndex, colIndex);	
			}		
		};

		
		table = new JTable(tableModel);
		// table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(table);
		centerPanel.add(scrollPane);
		cp.add(centerPanel, BorderLayout.CENTER);

		// Create window for adding new data - This is our own dialog box.
		addWindow = new JFrame("Add new data, duplicates are overwritten.");
		addWindow.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
		addWindow.setResizable(false);
		topAddWindow = new JPanel();
		topAddWindow.setLayout(new GridLayout(1, 2));
		idFill = new JTextField();
		idFill.setColumns(TEXTFIELD_WIDTH);
		nameFill = new JTextField();
		nameFill.setColumns(TEXTFIELD_WIDTH);
		topAddWindow.add(idFill);
		topAddWindow.add(nameFill);
		bottomAddWindow = new JPanel();
		bottomAddWindow.setLayout(new GridLayout(1, 6));
		okAdd = new JButton("Add");
		cancelAdd = new JButton("Cancel");
		bottomAddWindow.add(new JLabel());
		bottomAddWindow.add(okAdd);
		bottomAddWindow.add(new JLabel());
		bottomAddWindow.add(new JLabel());
		bottomAddWindow.add(cancelAdd);
		bottomAddWindow.add(new JLabel());
		addWindow.setLayout(new GridLayout(3, 1));
		addWindow.add(topAddWindow);
		addWindow.add(new JLabel());
		addWindow.add(bottomAddWindow, BorderLayout.EAST);
		addWindow.setVisible(false);

		// behavior when the "Add Student" button is clicked
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int topLeftX = getX();
				int topLeftY = getY();
				int xDialogPosition = topLeftX
						+ (MAINWINDOW_WIDTH - DIALOG_WIDTH) / 2;
				int yDialogPosition = topLeftY
						+ (MAINWINDOW_HEIGHT - DIALOG_HEIGHT) / 2;

				addWindow.setLocation(xDialogPosition, yDialogPosition);
				addWindow.setVisible(true);

			}
		});

		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int [] a = table.getSelectedRows();
				Set ab = m.keySet();
				Object [] abc = ab.toArray();
				for(int i:a){
					
					m.remove(abc[i]);
					table.repaint();
				}
				
			}
		});
		// behavior when the "Add" button in the dialog is clicked.
		// It won't do anything unless both text fields in the dialog box are
		// filled in. More details are in the lab sheet.
		// For student to do!
		okAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(idFill.getText().equals("")||nameFill.getText().equals("")){
					
				}else{
					put(Integer.parseInt(idFill.getText()),nameFill.getText());
			        idFill.setText(null);
			        nameFill.setText(null);
			        table.repaint();
			        addWindow.setVisible(false);
				}
	
		        
		        
			}

			private void put(int parseInt, String text) {
				m.put(parseInt, text);
				
			}
		});

		// behavior when the "Cancel" button in the dialog is clicked.
		cancelAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				idFill.setText("");
				nameFill.setText("");
				addWindow.setVisible(false);
			}
		});

		// Behavior when search (pressing Enter in the search field on top of
		// screen)
		// *If "" is typed in, it should not do anything.
		// *If an integer is in the search field:
		// - If the integer is one of the keys in the map, open a dialog window showing the value associated with that key.
		// - If the integer is not one of the keys, open a dialog window showing that there is no matching student name.
		// *Otherwise, open a dialog window showing that the input is not a student ID.
		// For student to do!
		searchField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(searchField.getText().equals("")){
					
				}else{
					String input = searchField.getText();
					boolean check = false;
					for(int i =0 ; i<input.length();i++){
						if(!(48<=input.charAt(i)&&input.charAt(i)<=57)){
							check = true;
						}
					}
					if(!check){
						String t = m.get(Integer.parseInt(searchField.getText()));
						if(t != null){
							JOptionPane.showMessageDialog(null, "The Student name is "+ t);
						}else{
							JOptionPane.showMessageDialog(null, "There is no matching student name.");
						}
					}else{

						JOptionPane.showMessageDialog(null, "The input is not a student ID");
					}
						
				}	
			}
		});

		setSize(MAINWINDOW_WIDTH, MAINWINDOW_HEIGHT);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setDefaultLookAndFeelDecorated(false);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DisplayMap app = new DisplayMap();
			}
		});

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

}
