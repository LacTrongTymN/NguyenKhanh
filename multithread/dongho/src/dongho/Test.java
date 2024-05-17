import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Test extends JFrame {
    private Clock clock;

    public Test() {
        setTitle("Simple Clock");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton bt = new JButton("Update Time");
        JTextField jTextField = new JTextField(7);
        jTextField.setPreferredSize(new Dimension(50, 30));

        JPanel jPanelclock = new JPanel();
        JPanel jPanel_duoi = new JPanel();
        jPanel_duoi.add(jTextField, BorderLayout.EAST);
        jPanel_duoi.add(bt);

        clock = new Clock();
        jPanelclock.add(clock);

        jPanel_duoi.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 30));
        this.add(jPanelclock);
        this.add(jPanel_duoi);
        this.setLayout(new GridLayout(2, 1));

        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timezone = jTextField.getText();
                try {
                    int newtimez = Integer.parseInt(timezone);
                    new Test();
                    jTextField.setText("");
                    clock.setTimezoneOffset(newtimez);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer for timezone offset.");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Test();
    }
}