package L5Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class L5LookupWindow {

	private JFrame frmLookup;
	private Lab5 l;
	private JTextField textField;
	private int memID;
	public String ISBN;
	

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					L5LookupWindow window = new L5LookupWindow(new Lab5(), 1000);
					window.frmLookup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void register() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					L5LookupWindow window = new L5LookupWindow(l, memID);
					window.frmLookup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public L5LookupWindow(Lab5 l, int memID) {
		this.l = l;
		this.memID = memID;
		initialize();
	}
	
	private String getName() {
		return l.getMemberName(memID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String name = getName();
		frmLookup = new JFrame();
		frmLookup.setTitle("Lookup");
		frmLookup.setBounds(100, 100, 921, 552);
		frmLookup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLookup.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("What book would you like to look up?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lato", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 47, 883, 31);
		frmLookup.getContentPane().add(lblNewLabel);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Lato", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"ISBN", "Author", "Title"}));
		comboBox.setBounds(73, 129, 116, 29);
		frmLookup.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setFont(new Font("Lato", Font.PLAIN, 16));
		textField.setBounds(199, 129, 493, 31);
		frmLookup.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblWelcome = new JLabel("Welcome " + name + "!");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Lato", Font.PLAIN, 20));
		lblWelcome.setBounds(10, 11, 883, 31);
		frmLookup.getContentPane().add(lblWelcome);
		
		JLabel lblYouMaySearch = new JLabel("You may search by ISBN, title or Author");
		lblYouMaySearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouMaySearch.setFont(new Font("Lato", Font.PLAIN, 16));
		lblYouMaySearch.setBounds(10, 74, 883, 31);
		frmLookup.getContentPane().add(lblYouMaySearch);
		
		JLabel lblNewLabel_1 = new JLabel("SELECT BOOK");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Lato", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 239, 883, 31);
		frmLookup.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Lato", Font.PLAIN, 16));
		comboBox_1.setBounds(203, 133, 489, 25);
		frmLookup.getContentPane().add(comboBox_1);		
		comboBox_1.setVisible(false);
	
		JLabel lblNewLabel_2 = new JLabel("BOOK NOT FOUND");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Javanese Text", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(10, 169, 883, 25);
		frmLookup.getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchItem = comboBox_1.getSelectedItem().toString();
				String searchISBN = "";
				for(int i = 0; i < searchItem.length(); i++) {
					if(searchItem.charAt(i) == ',') {
						searchISBN = searchItem.substring(0, i);
						break;
					}
				}
				L5SearchResultsWindow sw = new L5SearchResultsWindow(l, searchISBN);
				sw.register();
				
			}
		});
		btnNewButton_1.setFont(new Font("Lato", Font.PLAIN, 16));
		btnNewButton_1.setBounds(702, 132, 94, 26);
		frmLookup.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setVisible(false);
		
		JButton btnNewButton_2 = new JButton("Close");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLookup.dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Lato", Font.PLAIN, 18));
		btnNewButton_2.setBounds(408, 205, 89, 23);
		frmLookup.getContentPane().add(btnNewButton_2);
		btnNewButton_2.setVisible(false);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchFor = textField.getText();
				if(searchFor.length() == 0)
					return;
				String searchBy = comboBox.getSelectedItem().toString();
				String[] ISBNs = l.search(searchBy, searchFor);
				if(ISBNs.length > 1) {
					if(searchBy.equals("Author"))
						lblNewLabel_1.setText("<html><center>This Author has several books.<br>Please select the one you want to search for</center></html>");
					else lblNewLabel_1.setText("<html><center>Multiple titles match your search.<br>Please select the one you want to search for and click search</center></html>");
					lblNewLabel_1.setVisible(true);
					textField.setVisible(false);
					
					String[] values = l.getInfoFromISBN(ISBNs).toArray(new String[0]);
					comboBox_1.setModel(new DefaultComboBoxModel(values));
					comboBox_1.setVisible(true);
					btnNewButton.setVisible(false);
					btnNewButton_1.setVisible(true);
					btnNewButton_2.setVisible(true);
					lblNewLabel_2.setVisible(false);
				}
				else if (ISBNs.length == 1){
					lblNewLabel_2.setVisible(false);
					L5SearchResultsWindow sw = new L5SearchResultsWindow(l, ISBNs[0]);
					sw.register();
				}
				else {
					lblNewLabel_2.setVisible(true);
					lblNewLabel_2.setText("Your search query returned no results. This means this " + searchBy + " does not currently exist in either library.");
				}
			}
		});
		btnNewButton.setFont(new Font("Lato", Font.PLAIN, 16));
		btnNewButton.setBounds(702, 129, 94, 29);
		frmLookup.getContentPane().add(btnNewButton);
		

		
	}
}
