package L5Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class L5GUI {

	private JFrame frmLibraryDatabase;
	private JTextField textField;
	private Lab5 l;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					L5GUI window = new L5GUI();
					window.frmLibraryDatabase.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private boolean checkID(String ID) {
		return l.doesIDExist(ID);
	}
	
	private void registerNewMember() {
		L5RegisterWindow r = new L5RegisterWindow(l);
		r.register();
		
	}
	

	/**
	 * Create the application.
	 */
	public L5GUI() {
		l = new Lab5();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLibraryDatabase = new JFrame();
		frmLibraryDatabase.setTitle("Library Database");
		frmLibraryDatabase.setBounds(100, 100, 911, 511);
		frmLibraryDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLibraryDatabase.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to the Library Database GUI!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lato Semibold", Font.BOLD, 28));
		lblNewLabel.setBounds(10, 11, 873, 78);
		frmLibraryDatabase.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("To begin, please enter your Member ID: ");
		lblNewLabel_1.setFont(new Font("Lato", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(139, 118, 349, 59);
		frmLibraryDatabase.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Lato", Font.PLAIN, 18));
		textField.setBounds(473, 135, 156, 34);
		frmLibraryDatabase.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("MemberID is not valid. You can try again, or press the button below to register.");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Lato", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(37, 255, 799, 53);
		frmLibraryDatabase.getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerNewMember();
			}
		});
		btnNewButton_1.setFont(new Font("Lato", Font.PLAIN, 18));
		btnNewButton_1.setBounds(372, 308, 113, 34);
		frmLibraryDatabase.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setVisible(false);
		
		JButton btnNewButton = new JButton("Go");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String memberIDText = textField.getText();
				if (checkID(memberIDText)) {
					textField.setText("");
					lblNewLabel_2.setVisible(false);
					btnNewButton_1.setVisible(false);
					L5LookupWindow lw = new L5LookupWindow(l, Integer.parseInt(memberIDText));
					lw.register();
				}
				else {
					lblNewLabel_2.setVisible(true);
					btnNewButton_1.setVisible(true);
				}
			}
		});
		btnNewButton.setFont(new Font("Lato", Font.PLAIN, 18));
		btnNewButton.setBounds(639, 135, 102, 34);
		frmLibraryDatabase.getContentPane().add(btnNewButton);
		

		
	}
}
