package Grupo31.g31;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import com.github.javaparser.ParseException;

public class GUI extends JFrame{

	static Vector<String> headers = new Vector<String>();
	static DefaultTableModel model = null;
	static Vector<Vector<String>> data = new Vector<Vector<String>>();
	static JButton Import;
	static JButton CodeSmellsDetector;
	static JFileChooser jChooser;

	static ArrayList <Method> a;

	String choosertitle;
	static String fname;

	static int tableWidth = 0;
	static int tableHeight = 0;

	private Leitura_Projetos j= new Leitura_Projetos();
	private JButton btnNewButton;

	private JLabel lblNewLabel;
	private JPanel panel;

	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnNewButton_1;

	static int v1;
	static int v2;
	static int v3;
	static int v4;
	static int v5;

	JPanel panel3;

	JTextField valor3;
	JTextField valor4;
	JTextField valor5;

	//Long Method
	static String metricaL1;
	static String metricaL2;
	static String sinalL1;
	static String sinalL2;
	static String operadorL1;
	static String valorL1;
	static String valorL2;
	static String metricaL3;
	static String sinalL3;
	static String valorL3;
	static String operadorL2;

	ArrayList<String> historico = new ArrayList<String>();

	private JPanel panel2;

	public GUI() {

		super("Code Smells");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jChooser = new JFileChooser();

		jChooser = new JFileChooser();
		model = new DefaultTableModel(data, headers);

		tableWidth = model.getColumnCount() * 150;
		tableHeight = model.getRowCount() * 25;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{584, 0};
		gridBagLayout.rowHeights = new int[]{33, 0, 0, 0, 356, 27, 19, -21, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		lblNewLabel = new JLabel("CODE SMELLS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		btnNewButton_1 = new JButton("Extrair Métricas");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result;

				jChooser = new JFileChooser(); 
				jChooser.setCurrentDirectory(new java.io.File("."));
				jChooser.setDialogTitle(choosertitle);
				jChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jChooser.setAcceptAllFileFilterUsed(false);

				result = jChooser.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) { 

					fname = jChooser.getSelectedFile().toString().substring(jChooser.getSelectedFile().toString()
							.lastIndexOf("\\") + 1);


					j.lista(jChooser.getSelectedFile().toString());

				} else {
					System.out.println("No Selection");
				}			
			}
		});

		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		Import = new JButton("Selecionar Ficheiro Excel");
		Import.setBackground(Color.LIGHT_GRAY);
		buttonPanel.add(Import, BorderLayout.EAST);
		Import.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				jChooser.showOpenDialog(null);
				File file = jChooser.getSelectedFile();

				if (!file.getName().endsWith("xlsx")) {

					JOptionPane.showMessageDialog(null, "Please select only Excel file.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {

					ImportExcel.fillData(file);
					model = new DefaultTableModel(data, headers);
					tableWidth = model.getColumnCount() * 150;
					tableHeight = model.getRowCount() * 25;
					table.setPreferredSize(new Dimension(tableWidth, tableHeight));
					table.setModel(model);
				}
			}
		});

		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.insets = new Insets(0, 0, 5, 0);
		gbc_buttonPanel.anchor = GridBagConstraints.NORTH;
		gbc_buttonPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 3;
		getContentPane().add(buttonPanel, gbc_buttonPanel);

		btnNewButton = new JButton("Definir Regras");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setColumnCount(0);

				panel.revalidate();
				panel.repaint();

				JButton longM = new JButton("Regras Long Method");
				longM.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						panel2.removeAll();

						JComboBox metL1 = new JComboBox();
						metL1.addItem("LOC_class");
						metL1.addItem("NOM_class");
						metL1.addItem("WMX_class");
						panel2.add(metL1);


						JComboBox sinL1 = new JComboBox();
						sinL1.addItem(">");
						sinL1.addItem("<");
						panel2.add(sinL1);

						JTextField valL1 = new JTextField();
						panel2.add(valL1);
						valL1.setColumns(10);

						JComboBox operL1 = new JComboBox();
						operL1.addItem("NULL");
						operL1.addItem("AND");
						operL1.addItem("OR");
						panel2.add(operL1);

						JComboBox metL2 = new JComboBox();
						metL2.addItem("NULL");
						metL2.addItem("LOC_class");
						metL2.addItem("NOM_class");
						metL2.addItem("WMX_class");
						panel2.add(metL2);

						JComboBox sinL2 = new JComboBox();
						sinL2.addItem("NULL");
						sinL2.addItem(">");
						sinL2.addItem("<");
						panel2.add(sinL2);

						JTextField valL2 = new JTextField();
						panel2.add(valL2);
						valL2.setColumns(10);

						JComboBox operL2 = new JComboBox();
						operL2.addItem("NULL");
						operL2.addItem("AND");
						operL2.addItem("OR");
						panel2.add(operL2);

						JComboBox metL3 = new JComboBox();
						metL3.addItem("NULL");
						metL3.addItem("LOC_class");
						metL3.addItem("NOM_class");
						metL3.addItem("WMX_class");
						panel2.add(metL3);

						JComboBox sinL3 = new JComboBox();
						sinL3.addItem(">");
						sinL3.addItem("<");
						panel2.add(sinL3);

						JTextField valL3 = new JTextField();
						panel2.add(valL3);
						valL3.setColumns(10);

						panel2.revalidate();
						panel2.repaint();

			
						JButton submitLongMethod = new JButton("Submeter");
						submitLongMethod.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								
								
								metricaL1 = metL1.getSelectedItem().toString();
								sinalL1 = sinL1.getSelectedItem().toString();
								valorL1 = valL1.getText();
								operadorL1 = operL1.getSelectedItem().toString();
								metricaL2 = metL2.getSelectedItem().toString();
								sinalL2 = sinL2.getSelectedItem().toString();
								valorL2 = valL2.getText();
								operadorL2 = operL2.getSelectedItem().toString();
								metricaL3 = metL3.getSelectedItem().toString();
								sinalL3 = sinL3.getSelectedItem().toString();
								valorL3 = valL3.getText();
								
								if (operadorL1 == "NULL") {
									String rule = (metricaL1 + " " + sinalL1 + " " + valorL1);
									historico.add(rule);							
									JCheckBox checkRule = new JCheckBox(rule);
									
									panel3.add(checkRule);
								}
								
								if (operadorL1 != "NULL" && operadorL2 == "NULL") {
									String rule = (metricaL1 + " " + sinalL1 + " " + valorL1 + " " + operadorL1 + " " + metricaL2 + " " + sinalL2 + " " + valorL2);
									historico.add(rule);
									
									JCheckBox checkRule = new JCheckBox(rule);
									panel3.add(checkRule);
								
								}
								
								if (operadorL1 != "NULL" && operadorL2 != "NULL") {
									String rule = (metricaL1 + " " + sinalL1 + " " + valorL1 + " " + operadorL1 + " " + metricaL2 + " " + sinalL2 + " " + valorL2 + 
											" " + operadorL2 + " " + metricaL3 + " " + sinalL3 + " " + valorL3 );
									historico.add(rule);
									
									JCheckBox checkRule = new JCheckBox(rule);
									panel3.add(checkRule);
									
								}
								
								System.out.println("historico: " + historico);
							}
						});

						submitLongMethod.setBackground(Color.LIGHT_GRAY);
						panel.add(submitLongMethod);

					}
				});

				longM.setBackground(Color.LIGHT_GRAY);
				panel.add(longM);


				JButton GodC = new JButton("Regra God Class");
				GodC.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						panel2.removeAll();

						//						metrica1 = new JComboBox();
						//						metrica1.addItem("LOC_method");
						//						metrica1.addItem("CYLO_method");
						//						panel2.add(metrica1);

						JComboBox signal3 = new JComboBox();
						signal3.addItem(">");
						signal3.addItem("<");
						panel2.add(signal3);


						valor4 = new JTextField();
						panel2.add(valor4);
						valor4.setColumns(10);

						JComboBox op2 = new JComboBox();
						op2.addItem("AND");
						op2.addItem("OR");
						panel2.add(op2);

						JComboBox metrica2 = new JComboBox();
						metrica2.addItem("NULL");
						metrica2.addItem("LOC_method");
						metrica2.addItem("CYLO_method");
						panel2.add(metrica2);

						JComboBox signal4 = new JComboBox();
						signal4.addItem(">");
						signal4.addItem("<");
						panel2.add(signal4);

						valor5 = new JTextField();
						panel2.add(valor5);
						valor5.setColumns(10);

						panel2.revalidate();
						panel2.repaint();


						JButton submitG = new JButton("Submit");
						//						submit1.addActionListener(new ActionListener() {
						//							public void actionPerformed(ActionEvent e) {

						//								String m1 = metrica1.



					}
				});

				//						submit1.setBackground(Color.LIGHT_GRAY);
				//						panel.add(submit1);


				GodC.setBackground(Color.LIGHT_GRAY);
				panel.add(GodC);

				JButton updateHistorico = new JButton("Guardar Regras");
				updateHistorico.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						history.updateHistoric("C:\\Users\\inesv\\OneDrive\\Ambiente de Trabalho\\historico.txt" , historico);
						
					}
				
				});
				updateHistorico.setBackground(Color.LIGHT_GRAY);
				panel.add(updateHistorico);
			}
					
		});

		btnNewButton.setBackground(Color.LIGHT_GRAY);
		buttonPanel.add(btnNewButton);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		getContentPane().add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 5;
		getContentPane().add(panel, gbc_panel);

		panel2 = new JPanel();
		GridBagConstraints gbc_panel2 = new GridBagConstraints();
		gbc_panel2.insets = new Insets(0, 0, 5, 0);
		gbc_panel2.fill = GridBagConstraints.BOTH;
		gbc_panel2.gridx = 0;
		gbc_panel2.gridy = 6;
		getContentPane().add(panel2, gbc_panel2);
		setSize(800, 700);
		setResizable(true);
		setVisible(true);
		

		panel3 = new JPanel();
		GridBagConstraints gbc_panel3 = new GridBagConstraints();
		gbc_panel2.insets = new Insets(0, 0, 5, 0);
		gbc_panel2.fill = GridBagConstraints.BOTH;
		gbc_panel2.gridx = 0;
		gbc_panel2.gridy = 7;
		getContentPane().add(panel3, gbc_panel3);
		setSize(800, 700);
		setResizable(true);
		setVisible(true);
	}

	public static void main (String args[]) {
		new GUI();
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}