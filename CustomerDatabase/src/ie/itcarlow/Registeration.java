package ie.itcarlow;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Registeration extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtcontactNo;
	private JTextField txtemail;
	private JTextField txtaddress;
	private JTextField txtUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registeration frame = new Registeration();
					frame.setSize(560, 400);
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

		
	/**
	 * Create the frame.
	 */
	public Registeration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 484);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer Registration");
		lblNewLabel.setBounds(10, 11, 536, 60);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 55));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(30, 82, 75, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contact No");
		lblNewLabel_2.setBounds(30, 115, 108, 14);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(30, 140, 62, 14);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setBounds(30, 174, 75, 14);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("User Name");
		lblNewLabel_5.setBounds(30, 208, 75, 14);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Password");
		lblNewLabel_6.setBounds(30, 242, 75, 14);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblNewLabel_6);
		
		txtName = new JTextField();
		txtName.setToolTipText("Enter Name");
		txtName.setBounds(111, 82, 258, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtcontactNo = new JTextField();
		txtcontactNo.setToolTipText("Enter Contact Number");
		txtcontactNo.setBounds(111, 113, 258, 20);
		contentPane.add(txtcontactNo);
		txtcontactNo.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setToolTipText("Enter Email");
		txtemail.setBounds(111, 140, 258, 20);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		txtaddress = new JTextField();
		txtaddress.setToolTipText("Enter Address");
		txtaddress.setBounds(111, 171, 258, 20);
		contentPane.add(txtaddress);
		txtaddress.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("Enter User Name");
		txtUsername.setBounds(111, 205, 258, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton BTLoginButton = new JButton("Back To Login");
		BTLoginButton.setBounds(94, 284, 146, 23);
		BTLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login l=new Login();
				l.setVisible(true);
				
			}
		});
		BTLoginButton.setForeground(Color.RED);
		BTLoginButton.setBackground(Color.LIGHT_GRAY);
		BTLoginButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(BTLoginButton);
		
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(267, 284, 101, 23);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname=txtName.getText();
				String fcontactNo=txtcontactNo.getText();
				String femail=txtemail.getText();
				String faddress=txtaddress.getText();
				String fusername=txtUsername.getText();
				String fpassword= new String(passwordField.getPassword());
				
				if(fname.isEmpty() || fcontactNo.isEmpty() || femail.isEmpty() || faddress.isEmpty() || fusername.isEmpty() || fpassword.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Fill up the form Properly", "Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					CustomerRegister(fname,fcontactNo,femail,faddress,fusername,fpassword);
				}
				
			}

			private void CustomerRegister(String fname, String fcontactNo, String femail, String faddress,
					String fusername, String fpassword) {
				// TODO Auto-generated method stub
				Connection dbcon=DBConnection.connectDB();
				if(dbcon != null) {
					try {
						PreparedStatement st =(PreparedStatement)dbcon.prepareStatement("INSERT INTO customer(name,contactNo,email,address,username,password) VALUES(?,?,?,?,?,?)");
						st.setString(1, fname);
						st.setString(2, fcontactNo);
						st.setString(3, femail);
						st.setString(4, faddress);
						st.setString(5, fusername);
						st.setString(6, fpassword);
						
						int res = st.executeUpdate();
						JOptionPane.showMessageDialog(null, "User data inserted. ","Success", JOptionPane.INFORMATION_MESSAGE);	
					}
					catch(Exception e) {
						Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
					}
				}
					else {
						System.out.println("The connection not available. ");
					}
				}
			
		});
		registerButton.setBackground(Color.LIGHT_GRAY);
		registerButton.setForeground(Color.BLUE);
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(registerButton);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Enter Password");
		passwordField.setBounds(111, 233, 258, 20);
		contentPane.add(passwordField);
	}
}
