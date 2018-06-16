package L5Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class L5SearchResultsWindow {

	private JFrame frmSearchResults;
	private Lab5 l;
	private String ISBN;

	/**
	 * Launch the application.
	 */
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					L5SearchResultsWindow window = new L5SearchResultsWindow(new Lab5(), "1");
//					window.frmSearchResults.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public void register() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					L5SearchResultsWindow window = new L5SearchResultsWindow(l, ISBN);
					window.frmSearchResults.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public L5SearchResultsWindow(Lab5 l, String ISBN) {
		this.l = l;
		this.ISBN = ISBN;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	private void initialize() {
		frmSearchResults = new JFrame();
		frmSearchResults.setTitle("Search Results");
		frmSearchResults.setBounds(100, 100, 997, 449);
		frmSearchResults.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Search Results:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lato", Font.PLAIN, 18));
		frmSearchResults.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = l.searchISBN(ISBN);
			int totalCopies = 0;
			
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		frmSearchResults.getContentPane().add(list, BorderLayout.CENTER);
	}

}
