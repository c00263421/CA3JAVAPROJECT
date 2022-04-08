package ie.itcarlow;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField txtuser;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	 * Create the application.
	 */
	public Login() {
		initialize();
		
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	/**
	 * Create the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		userLabel.setBounds(51, 162, 67, 14);
		contentPane.add(userLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordLabel.setBounds(51, 204, 81, 14);
		contentPane.add(passwordLabel);
		
		txtuser = new JTextField();
		txtuser.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtuser.setBounds(144, 159, 279, 20);
		contentPane.add(txtuser);
		txtuser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordField.setBounds(142, 201, 281, 20);
		contentPane.add(passwordField);
		
		JCheckBox checkBox = new JCheckBox("show password");
		checkBox.setForeground(Color.BLUE);
		checkBox.setBackground(Color.ORANGE);
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//To Do check box handling code
				if(checkBox.isSelected())
				{
					passwordField.setEchoChar((char)0);
				}
				else
					passwordField.setEchoChar('*');
			}
		});
		checkBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		checkBox.setBounds(144, 228, 136, 23);
		contentPane.add(checkBox);
		
		JButton loginButton = new JButton("login");
		loginButton.setForeground(Color.BLUE);
		loginButton.setBackground(Color.ORANGE);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//To Do login handling code
				String username= txtuser.getText();
				String password= new String(passwordField.getPassword());
				//check Error in login
				if(username.isEmpty() || password.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Username / Password should not be empty", "Error",JOptionPane.ERROR_MESSAGE);
					}
				//Start the login process
				else
					{
						customerLogin(username, password);
					}
			}

			private void customerLogin(String username, String password) {
				// Create method for customer login
				Connection dbcon=DBConnection.connectDB();
				try 
				{
					PreparedStatement st =(PreparedStatement)dbcon.prepareStatement("select * from administrator WHERE username=? and password=?");
					st.setString(1, username);
					st.setString(2, password);
					ResultSet res= st.executeQuery();
					if(res.next()) {
						//display Home page after login
						dispose();
						Home h= new Home();
						h.setTitle("Home");
						h.setVisible(true);
					}
				} 
				catch(SQLException e) {
					
					Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		});
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		loginButton.setBounds(144, 277, 81, 23);
		contentPane.add(loginButton);
		
		JButton closeButton = new JButton("close");
		closeButton.setBackground(Color.ORANGE);
		closeButton.setForeground(Color.RED);
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//To Do close handling code
				int a=JOptionPane.showConfirmDialog(null, "D you really want to close Application","Select",JOptionPane.YES_NO_CANCEL_OPTION);
			if(a==0)
			{
				System.exit(0);
			}
			}
		});
		closeButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		closeButton.setBounds(235, 277, 89, 23);
		contentPane.add(closeButton);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 56));
		lblNewLabel_2.setBounds(164, 47, 180, 72);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Registeration r= new Registeration();
				r.setTitle("Register a user");
				r.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(334, 277, 89, 23);
		contentPane.add(btnNewButton);
	}
}
