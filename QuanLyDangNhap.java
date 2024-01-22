package benhan;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuanLyDangNhap extends JFrame implements ActionListener {

    private Container c;
    private JLabel title;
    private JLabel email;
    private JTextField temail;
    private JLabel password;
    private JPasswordField tpass;
    private JLabel specialization;
    private JComboBox<String> tspecial;
    private JLabel term;
    private JCheckBox tterm;
    private JButton submit;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;

    private String chuyenNganh[] = {"Khoa Nội", "Khoa Ngoại", "Khoa Răng Hàm Mặt", "Khoa Ung Bứu", "Khoa Thần Kinh"};

    public QuanLyDangNhap() {
        setTitle("Quản Lý Đăng Nhập");
        setBounds(300, 90, 500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Quản Lý Hồ Sơ");
        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(150, 30);
        c.add(title);

        email = new JLabel("Email");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(100, 20);
        email.setLocation(100, 150);
        c.add(email);

        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 15));
        temail.setSize(190, 20);
        temail.setLocation(200, 150);
        c.add(temail);

        password = new JLabel("Mật khẩu");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(100, 200);
        c.add(password);

        tpass = new JPasswordField();
        tpass.setFont(new Font("Arial", Font.PLAIN, 15));
        tpass.setSize(190, 20);
        tpass.setLocation(200, 200);
        c.add(tpass);

        specialization = new JLabel("Chuyên ngành");
        specialization.setFont(new Font("Arial", Font.PLAIN, 20));
        specialization.setSize(150, 20);
        specialization.setLocation(100, 300);
        c.add(specialization);

        tspecial = new JComboBox<>(chuyenNganh);
        tspecial.setFont(new Font("Arial", Font.PLAIN, 15));
        tspecial.setSize(150, 20);
        tspecial.setLocation(250, 300);
        c.add(tspecial);

        term = new JLabel("Đồng ý với điều kiện và điều khoản.");
        term.setFont(new Font("Arial", Font.PLAIN, 15));
        term.setSize(250, 20);
        term.setLocation(150, 400);
        c.add(term);

        tterm = new JCheckBox();
        tterm.setFont(new Font("Arial", Font.PLAIN, 15));
        tterm.setSize(20, 20);
        tterm.setLocation(400, 400);
        c.add(tterm);

        submit = new JButton("Đăng nhập");
        submit.setFont(new Font("Arial", Font.PLAIN, 15));
        submit.setSize(100, 20);
        submit.setLocation(150, 450);
        submit.addActionListener(this);
        c.add(submit);

        reset = new JButton("Nhập lại");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 450);
        reset.addActionListener(this);
        c.add(reset);

        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300, 400);
        tout.setLocation(500, 100);
        tout.setLineWrap(true);
        tout.setEditable(false);
        c.add(tout);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if (tterm.isSelected()) {
                String email = temail.getText();
                String password = String.valueOf(tpass.getPassword());

                if (email.isEmpty() || password.isEmpty()) {
                    res.setText("Điền đầy đủ vào ô trống.");
                } else {
                    if (validateLogin(email, password)) {
                        res.setText("Đăng nhập thành công.");

                       
                        this.setVisible(false);
                        new view(); 
                    } else {
                        res.setText("Email hoặc mật khẩu không đúng.");
                    }
                }
            } else {
                res.setText("Đồng ý với điều kiện và điều khoản.");
            }
        } else if (e.getSource() == reset) {
            temail.setText("");
            tpass.setText("");
            tspecial.setSelectedIndex(0);
            tterm.setSelected(false);
            tout.setText("");
            res.setText("");
        }
    }

    private boolean validateLogin(String email, String password) {
        String url = "jdbc:mysql://localhost:3306/QLDANGNHAP";
        String userName = "anhlonx";
        String dbPassword = "Hnk180705@";

        try (Connection connection = DriverManager.getConnection(url, userName, dbPassword)) {
            String query = "SELECT * FROM TKBACSI WHERE email=? AND password=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    
                    return resultSet.next();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        new QuanLyDangNhap();
    }
}

