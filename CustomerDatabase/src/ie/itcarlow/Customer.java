package ie.itcarlow;

import java.awt.EventQueue; 





import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;


public class Customer extends JFrame {
	
	private JFrame frame;
	private JTextField txtCustomerName;
	private JTextField txtCustomerEmail;
	private JTextField txtCustomerContact;
	private JTextField txtCid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer frame = new Customer();
					frame.setSize(1080, 382);
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
	public Customer() {
		super();
		initialize();
		Connect ();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	private JTextField txtCustomerAddress;
	
	public Customer(String CustomerWindow)
	{
		super("CustomerWindow");
		initialize();
		Connect ();
		table_load();
	}
		
	public void Connect() 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/customerdatabase", "root","");
		}
		catch (ClassNotFoundException ex)
		{
			
		}
		
		catch(SQLException ex)
		{
			
		}
	}
	
	public void table_load()
	{
		try
		{
			pst= con.prepareStatement("select * from customer");
			rs = pst.executeQuery ();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		
		frame = new JFrame();
		frame.setVisible(true); 
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setForeground(Color.DARK_GRAY);
		frame.setSize(1100, 427);
		
		frame.getContentPane().setForeground(Color.GRAY);
		frame.setBounds(100, 100, 1100, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel InfoLabel = new JLabel("Customer Information");
		InfoLabel.setBounds(121, 0, 285, 46);
		InfoLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		InfoLabel.setForeground(Color.CYAN);
		frame.getContentPane().add(InfoLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 47, 401, 166);
		panel_1.setForeground(Color.LIGHT_GRAY);
		panel_1.setBorder(new TitledBorder(null, "Customer Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel CustomerLabel = new JLabel("Name");
		CustomerLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		CustomerLabel.setBounds(10, 25, 106, 30);
		panel_1.add(CustomerLabel);
		
		JLabel ContactLabel = new JLabel("Contact No");
		ContactLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		ContactLabel.setBounds(10, 51, 106, 30);
		panel_1.add(ContactLabel);
		
		JLabel EmailLabel = new JLabel("Email");
		EmailLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		EmailLabel.setBounds(10, 82, 106, 30);
		panel_1.add(EmailLabel);
		
		JLabel AddressLabel = new JLabel("Address");
		AddressLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		AddressLabel.setBounds(10, 123, 64, 14);
		panel_1.add(AddressLabel);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setBounds(192, 32, 170, 20);
		panel_1.add(txtCustomerName);
		txtCustomerName.setColumns(10);
		
		txtCustomerContact = new JTextField();
		txtCustomerContact.setColumns(10);
		txtCustomerContact.setBounds(192, 58, 170, 20);
		panel_1.add(txtCustomerContact);
		
		txtCustomerEmail = new JTextField();
		txtCustomerEmail.setColumns(10);
		txtCustomerEmail.setBounds(192, 89, 170, 20);
		panel_1.add(txtCustomerEmail);
		
		txtCustomerAddress = new JTextField();
		txtCustomerAddress.setBounds(192, 120, 170, 20);
		panel_1.add(txtCustomerAddress);
		txtCustomerAddress.setColumns(10);
		
		JButton SaveButton = new JButton("Save");
		SaveButton.setBackground(Color.ORANGE);
		SaveButton.setBounds(22, 293, 98, 30);
		SaveButton.setForeground(Color.BLUE);
		SaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			String name,contactNo,email,address ;
			
			name = txtCustomerName.getText();
			contactNo = txtCustomerContact.getText();
			email = txtCustomerEmail.getText();
			address = txtCustomerAddress.getText();
		
			
			try {
				pst = con.prepareStatement("insert into customer(name,contactNo,email,address) values(?,?,?,?)");
				pst.setString(1,name);
				pst.setString(2,contactNo);
				pst.setString(3,email);
				pst.setString(4,address);
				
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Record Added");
				
				table_load();
				
				txtCustomerName.setText("");
				txtCustomerContact.setText("");
				txtCustomerEmail.setText("");	
				txtCustomerAddress.setText("");
				txtCustomerName.requestFocus();
				
			}
			
			catch(Exception e1) 
			{
				Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
				JOptionPane.showMessageDialog(null, "Fill up the form Properly", "Error",JOptionPane.ERROR_MESSAGE);
			}
			}	
		});
		SaveButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(SaveButton);
		
		JButton ClearButton = new JButton("Clear");
		ClearButton.setBackground(Color.ORANGE);
		ClearButton.setBounds(130, 293, 78, 30);
		ClearButton.setForeground(Color.BLUE);
		ClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCustomerName.setText("");
				txtCustomerContact.setText("");
				txtCustomerEmail.setText("");
				txtCustomerAddress.setText("");
				txtCid.setText("");
				
			}
		});
		ClearButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(ClearButton);
		
		JButton ExitButton = new JButton("Exit");
		ExitButton.setBackground(Color.ORANGE);
		ExitButton.setBounds(218, 293, 78, 30);
		ExitButton.setForeground(Color.RED);
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		ExitButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(ExitButton);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 209, 401, 53);
		panel_3.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel CIDLabel = new JLabel("Customer ID");
		CIDLabel.setBounds(10, 25, 99, 17);
		CIDLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(CIDLabel);
		  
		
		txtCid = new JTextField();
		txtCid.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				
				try 
				{       
					String id =txtCid.getText();
					
					pst = con.prepareStatement("select name,contactNo,email,address from customer where id =?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next() == true)
					
				{
				
				String name= rs.getString(1);
				String contactNo = rs.getString(2);
				String email = rs.getString(3);
				String address = rs.getString(4);
				
				txtCustomerName.setText(name);
				txtCustomerContact.setText(contactNo);
				txtCustomerEmail.setText(email);
				txtCustomerAddress.setText(address);
				
			}
			
			
			else
			{
				txtCustomerName.setText("");
				txtCustomerContact.setText("");
				txtCustomerEmail.setText("");
				txtCustomerAddress.setText("");
			}}
			
					catch(SQLException ex) {
			}
				}
					
				});
		txtCid.setBounds(191, 26, 184, 17);
		txtCid.setColumns(10);
		panel_3.add(txtCid);
		
		JButton UpdateButton = new JButton("Update");
		UpdateButton.setBackground(Color.ORANGE);
		UpdateButton.setBounds(307, 293, 87, 30);
		UpdateButton.setForeground(Color.BLUE);
		UpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name,contactNo,email,address,Cid ;
				
				name = txtCustomerName.getText();
				contactNo = txtCustomerContact.getText();
				email = txtCustomerEmail.getText();
				address = txtCustomerAddress.getText();
				Cid = txtCid.getText();
				
				try {
					pst = con.prepareStatement("update customer set name=?, contactNo=?, email=?, address=? where id=? ");
					pst.setString(1, name);
					pst.setString(2,contactNo);
					pst.setString(3,email);
					pst.setString(4,address);
					pst.setString(5, Cid);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated");
					
			        
					
					table_load();
					
					txtCustomerName.setText("");
					txtCustomerContact.setText("");
					txtCustomerEmail.setText("");
					txtCustomerAddress.setText("");
					txtCustomerName.requestFocus();
					
				}
				catch(SQLException e1) 
				{
					e1.printStackTrace();
				}
				}	
		});
		UpdateButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(UpdateButton);
		
		JButton DeleteButton = new JButton("Delete");
		DeleteButton.setBackground(Color.ORANGE);
		DeleteButton.setBounds(417, 292, 87, 30);
		DeleteButton.setForeground(Color.RED);
		DeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String Cid ;
			
				Cid = txtCid.getText();
				try 
				{
					pst = con.prepareStatement(" delete from customer where id=? ");
					
					pst.setString(1, Cid);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted");
					
					table_load();
					
					txtCustomerName.setText("");
					txtCustomerContact.setText("");
					txtCustomerEmail.setText("");	
					txtCustomerAddress.setText("");
					txtCustomerName.requestFocus();
					
				}
				
				catch(SQLException e1) 
				{
					
					e1.printStackTrace();
				}
				}	
		});
		DeleteButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(DeleteButton);
		
		table = new JTable();
		table.setBounds(1, 1, 638, 0);
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().add(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(55, 0, 65, 46);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(417, 51, 657, 210);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(514, 292, 87, 30);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h= new Home();
				h.setTitle("Home page");
				frame.dispose();
			}
		});
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(btnNewButton);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
