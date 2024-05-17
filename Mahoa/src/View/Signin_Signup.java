package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Controller.DBController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Container;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Signin_Signup extends JFrame {
	private JTextField tfuser1;
	private JPasswordField tfpass1;
	private JTextField tfuser;
	private JPasswordField tfpass;
	private JPasswordField tfconfirm;
	public Signin_Signup() {
		getContentPane().setBackground(new Color(255, 240, 245));
		Controller.DBController dbController = new Controller.DBController();
		Container con = getContentPane();
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 46, 626, 277);
		getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(175, 238, 238));
		panel.add(panel_1, "t1");
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(125, 73, 83, 25);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(125, 118, 83, 28);
		panel_1.add(lblNewLabel_1);
		
		tfuser1 = new JTextField();
		tfuser1.setBounds(235, 67, 268, 37);
		panel_1.add(tfuser1);
		tfuser1.setColumns(10);
		
		tfpass1 = new JPasswordField();
		tfpass1.setBounds(235, 114, 268, 37);
		panel_1.add(tfpass1);
		tfpass1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("SIGN IN");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(253, 10, 132, 31);
		panel_1.add(lblNewLabel_2);
		
		JButton jblogin = new JButton("SIGN IN");
		
		
		jblogin.setBounds(276, 200, 85, 21);
		panel_1.add(jblogin);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 250, 205));
		panel.add(panel_2, "t2");
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("SIGN UP");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(251, 10, 132, 31);
		panel_2.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("Username");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(104, 70, 107, 25);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(104, 115, 107, 28);
		panel_2.add(lblNewLabel_1_1);
		
		tfuser = new JTextField();
		tfuser.setColumns(10);
		tfuser.setBounds(238, 64, 268, 37);
		panel_2.add(tfuser);
		
		tfpass = new JPasswordField();
		tfpass.setColumns(10);
		tfpass.setBounds(238, 111, 268, 37);
		panel_2.add(tfpass);
		
		JButton jbregis = new JButton("SIGN UP");
		
		jbregis.setBounds(275, 230, 85, 21);
		panel_2.add(jbregis);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Confirm password");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(104, 164, 124, 28);
		panel_2.add(lblNewLabel_1_1_1);
		
		tfconfirm = new JPasswordField();
		tfconfirm.setColumns(10);
		tfconfirm.setBounds(238, 160, 268, 37);
		panel_2.add(tfconfirm);
		
		JButton btnsignin = new JButton("SIGN IN");
		btnsignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)(panel.getLayout());
				c1.show(panel, "t1");
				tfuser.setText("");
				tfpass.setText("");
				tfconfirm.setText("");
			}
		});
		btnsignin.setBounds(189, 10, 85, 21);
		getContentPane().add(btnsignin);
		
		JButton btnsignup = new JButton("SIGN UP");
		btnsignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout)(panel.getLayout());
				c1.show(panel, "t2");
				tfuser1.setText("");
				tfpass1.setText("");
			}
		});
		btnsignup.setBounds(360, 10, 85, 21);
		getContentPane().add(btnsignup);
		
		jblogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfuser1.getText().isEmpty() || tfpass1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
					return; 
				}
				if (dbController.checkUser(tfuser1.getText(), new String(tfpass1.getPassword()))) {
					JOptionPane.showMessageDialog(null, "Welcome!");
					setVisible(false);
					new Homepage();
				}
				else {
					JOptionPane.showMessageDialog(null, "Tài khoản và mật khẩu không chính xác");
					return;
				}
			}
		});
		
		jbregis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tfuser.getText().isEmpty() || tfpass.getText().isEmpty() || tfconfirm.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
					return; 
				}
				else if (tfpass.getText().equals(tfconfirm.getText())) {
					dbController.addUser(tfuser.getText(), new String(tfpass.getPassword()));
	                JOptionPane.showMessageDialog(null, "Đăng ký thành công");
				} 
				else {
					JOptionPane.showMessageDialog(null, "Hai mật khẩu không giống nhau");
				}
			}
		});
        
        setSize(640, 360);
        setTitle("Đăng nhập/Đăng ký");
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signin_Signup frame = new Signin_Signup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
