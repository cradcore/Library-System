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

public class L5RegisterWindow {

	private JFrame frmRegister;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private Lab5 l;

	/**
	 * Launch the application.
	 */
	public void register() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					L5RegisterWindow window = new L5RegisterWindow(l);
					window.frmRegister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public L5RegisterWindow(Lab5 l) {
		this.l = l;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegister = new JFrame();
		frmRegister.setTitle("Register");
		frmRegister.setBounds(100, 100, 586, 407);
		frmRegister.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegister.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First name:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Lato", Font.PLAIN, 18));
		lblNewLabel.setBounds(53, 11, 105, 32);
		frmRegister.getContentPane().add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setFont(new Font("Lato", Font.PLAIN, 18));
		lblLastName.setBounds(53, 47, 105, 32);
		frmRegister.getContentPane().add(lblLastName);
		
		JLabel lblDob = new JLabel("DOB:");
		lblDob.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDob.setFont(new Font("Lato", Font.PLAIN, 18));
		lblDob.setBounds(53, 84, 105, 32);
		frmRegister.getContentPane().add(lblDob);
		
		JLabel lblGendermf = new JLabel("Gender (M/F):");
		lblGendermf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGendermf.setFont(new Font("Lato", Font.PLAIN, 18));
		lblGendermf.setBounds(10, 119, 148, 32);
		frmRegister.getContentPane().add(lblGendermf);
		
		JLabel lblNoteDobMust = new JLabel("Note: DOB must be entered as MM/DD/YYYY (Ex: 01/04/1996)");
		lblNoteDobMust.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoteDobMust.setFont(new Font("Lato", Font.PLAIN, 12));
		lblNoteDobMust.setBounds(10, 162, 548, 32);
		frmRegister.getContentPane().add(lblNoteDobMust);
		
		textField = new JTextField();
		textField.setBounds(168, 16, 169, 29);
		frmRegister.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(168, 50, 169, 29);
		frmRegister.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(168, 84, 169, 29);
		frmRegister.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(168, 122, 169, 29);
		frmRegister.getContentPane().add(textField_3);

		
		JLabel label = new JLabel("REGISTER MESSAGE");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Lato", Font.PLAIN, 18));
		label.setBounds(10, 278, 548, 32);
		frmRegister.getContentPane().add(label);
		label.setVisible(false);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			boolean alreadyRegistered = false;
			public void actionPerformed(ActionEvent e) {
				if(alreadyRegistered)
					return;
				alreadyRegistered = true;
				String fn = textField.getText();
				String ln = textField_1.getText();
				String dob = textField_2.getText();
				String g = textField_3.getText();
				int id = l.addMember(fn, ln, dob, g);
				if(id == -1) 
					label.setText("Error adding Member. Please ensure the info is correct");
				else
					label.setText("Success! Your Member ID is " + id + ". Please exit this window and log in.");
				label.setVisible(true);
			}
		});
		btnRegister.setFont(new Font("Lato", Font.PLAIN, 18));
		btnRegister.setBounds(394, 59, 111, 40);
		frmRegister.getContentPane().add(btnRegister);
	}

}









