package ie.itcarlow;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Invoice extends JFrame {

				private JFrame frame;
				private JTextField txtCID;
				private JTextField txtCName;
				private JTextField txtContact;
				private JTextField txtEmail;
				private JTextField txtAddress;
				private JTextField txtPID;
				private JTextField txtPName;
				private JTextField txtPType;
				private JTextField txtPrice;
				private JTextField txtQuantity;
				private JLabel lblNewLabel_8;
				private JLabel lblNewLabel_9;
				private JLabel lblNewLabel_10;
				private JLabel lblNewLabel_11;
				private JLabel lblNewLabel_12;
				private JButton btnNewButton;
				private JTable table;
				private JButton btnNewButton_1;
				private JTextField txtTotal;
				private JButton btnNewButton_2;
				private JTextField txtPAmount;
				private JButton btnNewButton_3;
				private JTextField txtRAmount;
				private JButton btnNewButton_4;
				private JButton btnNewButton_5;
				private JButton btnNewButton_6;
				private JButton btnNewButton_7;
				public int finalTotal=0;

			/**
			 * Launch the application.
			 */
			public static void main(String[] args) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Invoice frame = new Invoice();
							//frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		
			/**
			 * Create the application.
			 */
			public Invoice() {
				initialize();
				Connect();
				table_load();
			}
			Connection con;
			PreparedStatement pst;
			ResultSet rs;
			
			public void Connect ()
			{
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost/customerdatabase","root",  ""); 	
				}
				catch (ClassNotFoundException ex)
				{
					
				}
				catch (SQLException ex)
				{
					
				}
				
			}
			
			public void table_load()
			{
				try
				{
					pst= con.prepareStatement("select invoice.invoiceID,invoice.customerID,customer.name,"
							+ "invoice.productID,product.name,invoice.Quantity,invoice.total from invoice inner join"
							+ " customer on invoice.customerID = customer.id inner join product on invoice.productID"
							+ " = product.id");
							
					rs = pst.executeQuery ();
					table.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Name", "Type", "Price", "Quantity", "Total"
						}
					) {
						Class[] columnTypes = new Class[] {
							String.class, String.class, Integer.class, Integer.class, Double.class
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
					});
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
				frame.setSize(970, 500);
				frame.setVisible(true);
				frame.setResizable(false);
				frame.getContentPane().setBackground(Color.ORANGE);
				frame.getContentPane().setForeground(Color.BLACK);
				frame.setBounds(100, 100, 909, 541);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Customer Invoice");
				lblNewLabel.setForeground(Color.BLUE);
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
				lblNewLabel.setBounds(10, 11, 198, 29);
				frame.getContentPane().add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("Customer Details");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
				lblNewLabel_1.setForeground(new Color(255, 0, 0));
				lblNewLabel_1.setBounds(10, 44, 160, 14);
				frame.getContentPane().add(lblNewLabel_1);
				
				JLabel lblNewLabel_2 = new JLabel("Search By ID");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_2.setBounds(20, 65, 110, 14);
				frame.getContentPane().add(lblNewLabel_2);
				
				txtCID = new JTextField();
				txtCID.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String id= new String(txtCID.getText());
									
						try {
							Connection dbcon=DBConnection.connectDB();	
							Statement st=dbcon.createStatement();
							ResultSet rs=st.executeQuery("select * from customer where id like '"+id+"%'");
							if(rs.next()) {
								txtCName.setText(rs.getString(2));
								txtContact.setText(rs.getString(3));
								txtEmail.setText(rs.getString(4));
								txtAddress.setText(rs.getString(5));
							}
							else {
								txtCName.setText("");
								txtContact.setText("");
								txtEmail.setText("");
								txtAddress.setText("");
							}
						}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);
					}
				}
				});
				txtCID.setBounds(140, 62, 86, 20);
				frame.getContentPane().add(txtCID);
				txtCID.setColumns(10);
				
				txtCName = new JTextField();
				txtCName.setBounds(236, 62, 102, 20);
				frame.getContentPane().add(txtCName);
				txtCName.setColumns(10);
				
				txtContact = new JTextField();
				txtContact.setBounds(348, 63, 127, 20);
				frame.getContentPane().add(txtContact);
				txtContact.setColumns(10);
				
				txtEmail = new JTextField();
				txtEmail.setBounds(485, 63, 150, 20);
				frame.getContentPane().add(txtEmail);
				txtEmail.setColumns(10);
				
				txtAddress = new JTextField();
				txtAddress.setBounds(645, 63, 212, 20);
				frame.getContentPane().add(txtAddress);
				txtAddress.setColumns(10);
				
				txtPID = new JTextField();
				txtPID.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String id= new String(txtPID.getText());
						
						try {
							Connection dbcon=DBConnection.connectDB();	
							Statement st=dbcon.createStatement();
							ResultSet rs=st.executeQuery("select * from product where id like '"+id+"%'");
							if(rs.next()) {
								txtPName.setText(rs.getString(2));
								txtPType.setText(rs.getString(3));
								txtPrice.setText(rs.getString(4));
								txtQuantity.setText(rs.getString(5));
							}
							else {
								txtPName.setText("");
								txtPType.setText("");
								txtPrice.setText("");
								txtQuantity.setText("");
								
							}
						}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);
						}
					}
				});
				txtPID.setBounds(140, 130, 86, 20);
				frame.getContentPane().add(txtPID);
				txtPID.setColumns(10);
				
				txtPName = new JTextField();
				txtPName.setBounds(236, 130, 102, 20);
				frame.getContentPane().add(txtPName);
				txtPName.setColumns(10);
				
				txtPType = new JTextField();
				txtPType.setBounds(348, 130, 127, 20);
				frame.getContentPane().add(txtPType);
				txtPType.setColumns(10);
				
				txtPrice = new JTextField();
				txtPrice.setBounds(485, 130, 118, 20);
				frame.getContentPane().add(txtPrice);
				txtPrice.setColumns(10);
				
				txtQuantity = new JTextField();
				txtQuantity.setBounds(613, 130, 127, 20);
				frame.getContentPane().add(txtQuantity);
				txtQuantity.setColumns(10);
				
				JLabel lblNewLabel_3 = new JLabel("name");
				lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_3.setBounds(237, 45, 46, 14);
				frame.getContentPane().add(lblNewLabel_3);
				
				JLabel lblNewLabel_4 = new JLabel("contactNo");
				lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_4.setBounds(348, 45, 86, 14);
				frame.getContentPane().add(lblNewLabel_4);
				
				JLabel lblNewLabel_5 = new JLabel("email");
				lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_5.setBounds(485, 45, 46, 14);
				frame.getContentPane().add(lblNewLabel_5);
				
				JLabel lblNewLabel_6 = new JLabel("address");
				lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_6.setBounds(646, 45, 86, 14);
				frame.getContentPane().add(lblNewLabel_6);
				
				JLabel lblNewLabel_7 = new JLabel("Product Detail");
				lblNewLabel_7.setForeground(Color.RED);
				lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
				lblNewLabel_7.setBounds(10, 90, 138, 29);
				frame.getContentPane().add(lblNewLabel_7);
				
				lblNewLabel_8 = new JLabel("Serch By ID");
				lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_8.setBounds(20, 129, 91, 14);
				frame.getContentPane().add(lblNewLabel_8);
				
				lblNewLabel_9 = new JLabel("Product Name");
				lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_9.setBounds(237, 112, 101, 14);
				frame.getContentPane().add(lblNewLabel_9);
				
				lblNewLabel_10 = new JLabel("Type");
				lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_10.setBounds(353, 112, 46, 14);
				frame.getContentPane().add(lblNewLabel_10);
				
				lblNewLabel_11 = new JLabel("Price");
				lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_11.setBounds(485, 112, 60, 14);
				frame.getContentPane().add(lblNewLabel_11);
				
				lblNewLabel_12 = new JLabel("Quantity ");
				lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_12.setBounds(613, 112, 119, 14);
				frame.getContentPane().add(lblNewLabel_12);
				
				btnNewButton = new JButton("ADD");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int price=Integer.parseInt(txtPrice.getText());
						int quantity=Integer.parseInt(txtQuantity.getText());
						int total=price*quantity;
						DefaultTableModel model=(DefaultTableModel)table.getModel();
						model.addRow(new Object[] {txtPName.getText(),txtPType.getText(),price,quantity,total});
						finalTotal= finalTotal+total;
						String finalTotall= String.valueOf(finalTotal);
						txtTotal.setText(finalTotall);
					}
				});
				btnNewButton.setBackground(Color.ORANGE);
				btnNewButton.setForeground(Color.BLUE);
				btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnNewButton.setBounds(754, 129, 89, 23);
				frame.getContentPane().add(btnNewButton);
				
				table = new JTable();
				table.setBounds(29, 168, 814, 154);
				frame.getContentPane().add(table);
				
				btnNewButton_1 = new JButton("Total");
				btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnNewButton_1.setBounds(94, 333, 89, 23);
				frame.getContentPane().add(btnNewButton_1);
				
				txtTotal = new JTextField();
				txtTotal.setBounds(236, 334, 163, 20);
				frame.getContentPane().add(txtTotal);
				txtTotal.setColumns(10);
				
				btnNewButton_2 = new JButton("Paid Amount");
				btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnNewButton_2.setBounds(94, 367, 132, 23);
				frame.getContentPane().add(btnNewButton_2);
				
				txtPAmount = new JTextField();
				txtPAmount.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String paidAmount=txtPAmount.getText();
						int z=Integer.parseInt(paidAmount);
						finalTotal=z-finalTotal;
						String finalTotall=String.valueOf(finalTotal);
						txtRAmount.setText(finalTotall);
						txtRAmount.setEditable(false);
					}
				});
				txtPAmount.setBounds(236, 365, 163, 20);
				frame.getContentPane().add(txtPAmount);
				txtPAmount.setColumns(10);
				
				btnNewButton_3 = new JButton("Return Amount");
				btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnNewButton_3.setBounds(94, 401, 132, 23);
				frame.getContentPane().add(btnNewButton_3);
				
				txtRAmount = new JTextField();
				txtRAmount.setBounds(236, 402, 163, 20);
				frame.getContentPane().add(txtRAmount);
				txtRAmount.setColumns(10);
				
				btnNewButton_4 = new JButton("Print");
				btnNewButton_4.setBackground(Color.ORANGE);
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name=txtCName.getText();
						String contactNo=txtContact.getText();
						String email=txtEmail.getText();
						String address=txtAddress.getText();
						String ProductTotal=txtTotal.getText();
						String ProductAmount=txtPAmount.getText();
						String ReturnAmount=txtRAmount.getText();
										
						String path="D:\\InvoicePDF\\customerInvoice.pdf";// Apply path.
						com.itextpdf.text.Document doc=new com.itextpdf.text.Document();// creating pdf document.
						try {
							PdfWriter.getInstance(doc, new FileOutputStream(path));
							doc.open(); //open the document
							
							//creating paragraph
							Paragraph paragraph1=new Paragraph("				Customer Invoice Management System\n				Contact Number +353896543325\n\n");
							doc.add(paragraph1);//add the content
							Paragraph paragraph2=new Paragraph("\nCustomer Details:\nName:"+name+"\ncontact NO:"+contactNo+"\nEmail:"+email+"\nAddress:"+address+"\n\n");
							doc.add(paragraph2);//add the content
							
							PdfPTable tb1=new PdfPTable(5);
							tb1.addCell("PName");
							tb1.addCell("Type");
							tb1.addCell("Price");
							tb1.addCell("Quantity");
							tb1.addCell("Total");
							for(int i=0;i<table.getRowCount();i++) {
								String PName=table.getValueAt(i, 0).toString();
								String Type=table.getValueAt(i, 1).toString();
								String Price=table.getValueAt(i, 2).toString();
								String Quantity=table.getValueAt(i, 3).toString();
								String Total=table.getValueAt(i, 4).toString();
								tb1.addCell(PName);// add product name in the cell
								tb1.addCell(Type);
								tb1.addCell(Price);
								tb1.addCell(Quantity);
								tb1.addCell(Total);
							}
							doc.add(tb1);
							Paragraph paragraph3=new Paragraph("\n*******Sub Total*******:\nTotal:"+ProductTotal+"\nPaid-Amount:"+ProductAmount+"\nReturn-Amount:"+ReturnAmount+"\n\n");
							doc.add(paragraph3);
							JOptionPane.showMessageDialog(null, "Record Added");
							doc.close();//close the document
						}
					catch(Exception e1) 
						{
						JOptionPane.showMessageDialog(null, e1);
						}		
				}
				});
				btnNewButton_4.setForeground(Color.BLUE);
				btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnNewButton_4.setBounds(422, 336, 89, 23);
				frame.getContentPane().add(btnNewButton_4);
				
				btnNewButton_5 = new JButton("Reset");
				btnNewButton_5.setBackground(Color.ORANGE);
				btnNewButton_5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						txtCName.setText("");
						txtContact.setText("");
						txtCID.setText("");
						txtEmail.setText("");
						txtAddress.setText("");
						txtPID.setText("");
						txtPName.setText("");
						txtPType.setText("");
						txtPrice.setText("");
						txtQuantity.setText("");
						txtTotal.setText("");
						txtPAmount.setText("");
						txtRAmount.setText("");
					}
				});
				btnNewButton_5.setForeground(Color.BLUE);
				btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnNewButton_5.setBounds(738, 367, 89, 23);
				frame.getContentPane().add(btnNewButton_5);
				
				btnNewButton_6 = new JButton("Close");
				btnNewButton_6.setBackground(Color.ORANGE);
				btnNewButton_6.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				btnNewButton_6.setForeground(Color.BLUE);
				btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnNewButton_6.setBounds(738, 334, 89, 23);
				frame.getContentPane().add(btnNewButton_6);
				
				btnNewButton_7 = new JButton("Save");
				btnNewButton_7.setBackground(Color.ORANGE);
				btnNewButton_7.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
				String CustomerID=txtCID.getText();
				String ProductID=txtPID.getText();
				String Quantity=txtQuantity.getText();
				String ProductTotal=txtTotal.getText();
				String ProductAmount=txtPAmount.getText();
				String ReturnAmount=txtRAmount.getText();
			 {
				try {
					pst = con.prepareStatement("insert into invoice(customerID,productID,Quantity,total,paidAmount,returnAmount) values(?,?,?,?,?,?)");
					
					pst.setString(1,CustomerID);
					pst.setString(2,ProductID);
					pst.setString(3,Quantity);
					pst.setString(4,ProductTotal);
					pst.setString(5,ProductAmount);
					pst.setString(6,ReturnAmount);
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Record Added");
					table_load();
									
					txtCID.setText("");
					txtCName.setText("");
					txtPID.setText("");
					txtPName.setText("");
					txtPrice.setText("");
					txtTotal.setText("");
					
					txtPName.requestFocus();
				}	
				catch (SQLException e1)
				{
					JOptionPane.showMessageDialog(null, "Failed");
				}
					}
				
				}
						});
						btnNewButton_7.setForeground(Color.BLUE);
						btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 12));
						btnNewButton_7.setBounds(422, 368, 89, 23);
						frame.getContentPane().add(btnNewButton_7);
						
						JButton btnNewButton_8 = new JButton("Home");
						btnNewButton_8.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Home h= new Home();
								h.setTitle("Home page");
								frame.dispose();
							}
						});
						btnNewButton_8.setForeground(Color.BLUE);
						btnNewButton_8.setBackground(Color.ORANGE);
						btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 12));
						btnNewButton_8.setBounds(583, 423, 89, 23);
						frame.getContentPane().add(btnNewButton_8);
					}
				}
