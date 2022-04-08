package ie.itcarlow;

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Product extends JFrame {

	private JFrame frame;
	private JTextField txtpname;
	private JTextField txtprice;
	private JTextField txttype;
	private JTextField txtpid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product frame= new Product();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Product() {
		super("product Admin Window");
		initialize();
		Connect ();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	private JTextField txtquantity;
	
	
	public void Connect() 
	{
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/customerdatabase", "root","");
		}
				
		catch(SQLException ex)
		{
			
		}
	}
	
	public void table_load()
	{
		try
		{
			pst= con.prepareStatement("select * from product");
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
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setSize(719, 448);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setForeground(Color.GRAY);
		frame.setBounds(100, 100, 719, 491);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 452);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Product Table");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(28, 0, 260, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBorder(new TitledBorder(null, "Product Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 47, 401, 189);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Product Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 25, 106, 30);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Product Type");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 66, 106, 30);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Product Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 107, 106, 30);
		panel_1.add(lblNewLabel_1_2);
		
		txtpname = new JTextField();
		txtpname.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpname.setBounds(192, 32, 170, 30);
		panel_1.add(txtpname);
		txtpname.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtprice.setColumns(10);
		txtprice.setBounds(192, 109, 170, 30);
		panel_1.add(txtprice);
		
		txttype = new JTextField();
		txttype.setFont(new Font("Tahoma", Font.BOLD, 12));
		txttype.setColumns(10);
		txttype.setBounds(192, 68, 170, 30);
		panel_1.add(txttype);
		
		JLabel lblNewLabel_2 = new JLabel("Product Quantity");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 148, 134, 14);
		panel_1.add(lblNewLabel_2);
		
		txtquantity = new JTextField();
		txtquantity.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtquantity.setBounds(192, 150, 170, 30);
		panel_1.add(txtquantity);
		txtquantity.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
			String name,type,price,quantity ;
			
			name = txtpname.getText();
			type = txttype.getText();
			price = txtprice.getText();
			quantity = txtprice.getText();
			
			try {
				pst = con.prepareStatement("insert into product(name,type,price,quantity) values(?,?,?,?)");
				pst.setString(1,name);
				pst.setString(2,type);
				pst.setString(3,price);
				pst.setString(4,quantity);
				
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Record Added");
				
				table_load();
				
				txtpname.setText("");
				txttype.setText("");
				txtprice.setText("");
				txtquantity.setText("");
				txtpname.requestFocus();
				
			}
			
			catch(SQLException e1) 
			{
				
				e1.printStackTrace();
			}
			}
	
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(20, 328, 81, 30);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(Color.RED);
		btnClear.setBackground(Color.ORANGE);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtpname.setText("");
				txttype.setText("");
				txtprice.setText("");
				txtquantity.setText("");
				txtpid.setText("");
				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnClear.setBounds(111, 328, 81, 30);
		frame.getContentPane().add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(Color.RED);
		btnExit.setBackground(Color.ORANGE);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExit.setBounds(202, 328, 81, 30);
		frame.getContentPane().add(btnExit);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.ORANGE);
		panel_3.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 233, 401, 84);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(93, 10, 10, 10);
		panel_3.add(panel_3_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("Product ID");
		lblNewLabel_1_3.setBounds(27, 31, 99, 17);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(lblNewLabel_1_3);
		
		txtpid = new JTextField();
		txtpid.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpid.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				
				try 
				{
					String id =txtpid.getText();
					
					pst = con.prepareStatement("select name,type,price from product where id =?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next() == true)
					
				{
				
				String name= rs.getString(1);
				String type = rs.getString(2);
				String price = rs.getString(3);
				
				txtpname.setText(name);
				txttype.setText(type);
				txtprice.setText(price);
				
			}
			
			
			else
			{
				txtpname.setText("");
				txttype.setText("");
				txtprice.setText("");
			}}
			
					catch(SQLException ex) {
						ex.printStackTrace();
			}
				}
					
				});
		txtpid.setBounds(194, 26, 170, 30);
		txtpid.setColumns(10);
		panel_3.add(txtpid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.BLUE);
		btnUpdate.setBackground(Color.ORANGE);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pname,type,price,quantity,pid ;
				
				pname = txtpname.getText();
				type = txttype.getText();
				price = txtprice.getText();
				quantity = txtquantity.getText();
				pid = txtpid.getText();
				
				try {
					pst = con.prepareStatement("update product set name=?, type=?, price=?, quantity=? where id=? ");
					pst.setString(1, pname);
					pst.setString(2,type);
					pst.setString(3,price);
					pst.setString(4, quantity);
					pst.setString(5, pid);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated");
					
					table_load();
					
					txtpname.setText("");
					txttype.setText("");
					txtprice.setText("");	
					txtquantity.setText("");
					txtpname.requestFocus();
					
				}
				
				catch(SQLException e1) 
				{
					
					e1.printStackTrace();
				}
				}
				
		
					
				
				
				
			
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnUpdate.setBounds(293, 328, 90, 30);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.RED);
		btnDelete.setBackground(Color.ORANGE);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String pid ;
				
				
				pid = txtpid.getText();
				
				try {
					pst = con.prepareStatement(" delete from product where id=? ");
					
					pst.setString(1, pid);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted");
					
					table_load();
					
					txtpname.setText("");
					txttype.setText("");
					txtprice.setText("");	
					txtquantity.setText("");
					txtpname.requestFocus();
					
				}
				
				catch(SQLException e1) 
				{
					
					e1.printStackTrace();
				}
				}
			
			
			
			
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDelete.setBounds(421, 328, 81, 30);
		frame.getContentPane().add(btnDelete);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setForeground(Color.BLACK);
		table.setBounds(421, 47, 272, 273);
		frame.getContentPane().add(table);
		
		JButton btnNewButton_1 = new JButton("Home");
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Home h= new Home();
				h.setTitle("Register a Customer");
				h.setVisible(false);	
				frame.dispose();				
			}
		});
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(512, 330, 101, 25);
		frame.getContentPane().add(btnNewButton_1);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
