package ie.itcarlow;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.EventQueue;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.ImageIcon;


public class Home extends JFrame {

	private JPanel contentPane;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(537, 250);
		frame.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 250);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setForeground(Color.GRAY);
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String C1[] ={"Invoice", "Product", "Customer"};
		JComboBox comboBox = new JComboBox(C1);
		comboBox.setBackground(Color.ORANGE);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 25));
		comboBox.setBounds(29, 84, 219, 63);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton) {
					String chosen = (String)comboBox.getSelectedItem();
					if(chosen == "Invoice") {
						JOptionPane.showMessageDialog(null, "Invoice Table");
						frame.dispose();
						Invoice frame1 = new Invoice();
					}
					
					if(chosen == "Product") {
						JOptionPane.showMessageDialog(null, "Product Table");
						frame.dispose();
						Product frame2 = new Product();
					}
					
					if(chosen == "Customer") {
						JOptionPane.showMessageDialog(null, "Customer Table");
						frame.dispose();
						Customer frame3 = new Customer();
					}
					
					
				}
			}
		});
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(258, 84, 87, 22);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(258, 127, 87, 22);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Log Out");
		btnNewButton_2.setBackground(Color.ORANGE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a=JOptionPane.showConfirmDialog(null, "Do you really want to close","Select", JOptionPane.YES_NO_OPTION);
				if(a==0)
				{
					
					setVisible(false);
					new Login().setVisible(true);
					frame.dispose();
				}
			}
		});
		btnNewButton_2.setForeground(Color.RED);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(397, 169, 102, 22);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Customer Admin Invoice Management System");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(10, 11, 497, 77);
		contentPane.add(lblNewLabel);
	}
}
