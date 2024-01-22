package benhan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class GiaoDienQuanLy {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchTextField;
    private JTextField addIdField, addNameField, addAgeField, addGenderField, addPhoneNumberField, addHealthStatusField;
    private JTextField editIdField, editNameField, editAgeField, editGenderField, editPhoneNumberField, editHealthStatusField;
  
    public static void main(String[] args) {
    	String serverName = "3306";
        String databaseName = "QLBENHAN";
        String userName = "anhlonx";
        String password = "Hnk180705@";

        
        String url = "jdbc:mysql://localhost:3306/QLBENHAN";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Không thể tìm thấy driver MySQL.");
            return;
        }
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            System.out.println("Kết nối đến cơ sở dữ liệu thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Không thể kết nối đến cơ sở dữ liệu!");
        }
        SwingUtilities.invokeLater(GiaoDienQuanLy::new);
    }
    
    private void luutruhoso() {
        
        String connectionUrl = "jdbc:mysql://localhost:3306/QLBENHAN";
        String userName = "anhlonx";
        String password = "Hnk180705@";

        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password)) {            
            String sql = "SELECT * FROM Patients";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {                
                var resultSet = preparedStatement.executeQuery();           
                model.setRowCount(0);               
                while (resultSet.next()) {
                    String patientID = resultSet.getString("PatientID");
                    String name = resultSet.getString("Name");
                    String age = resultSet.getString("Age");
                    String gender = resultSet.getString("Gender");
                    String phoneNumber = resultSet.getString("PhoneNumber");
                    String healthStatus = resultSet.getString("HealthStatus");

                    model.addRow(new Object[]{patientID, name, age, gender, phoneNumber, healthStatus});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Lỗi khi lấy dữ liệu từ cơ sở dữ liệu.");
        }
    }
    public GiaoDienQuanLy() {
        initialize();    
        model = (DefaultTableModel) table.getModel();
       
        luutruhoso();
    }

    private void initialize() {
        frame = new JFrame();

        frame.setBounds(100, 100, 800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel functionPanel = new JPanel(new GridBagLayout());
        functionPanel.setBackground(new Color(0x00FF7F));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton addButton = new JButton("thêm bệnh nhân");
        JButton removeButton = new JButton("xóa bệnh nhân");
        JButton searchButton = new JButton("tìm kiếm");
        JButton editButton = new JButton("sửa thông tin bệnh nhân");

        searchTextField = new JTextField(15);
        addIdField = new JTextField(10);
        addNameField = new JTextField(10);
        addAgeField = new JTextField(10);
        addGenderField = new JTextField(10);
        addPhoneNumberField = new JTextField(10);
        addHealthStatusField = new JTextField(10);

        editIdField = new JTextField(10);
        editNameField = new JTextField(10);
        editAgeField = new JTextField(10);
        editGenderField = new JTextField(10);
        editPhoneNumberField = new JTextField(10);
        editHealthStatusField = new JTextField(10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        functionPanel.add(addButton, gbc);

        gbc.gridy = 1;
        functionPanel.add(removeButton, gbc);

        gbc.gridy = 10;
        functionPanel.add(searchButton, gbc);

        gbc.gridy =11;
        functionPanel.add(searchTextField, gbc);

        gbc.gridy = 4;
        functionPanel.add(new JLabel("Số ID bệnh nhân:"), gbc);
        gbc.gridx = 1;
        functionPanel.add(addIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        functionPanel.add(new JLabel("Họ tên:"), gbc);
        gbc.gridx = 1;
        functionPanel.add(addNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        functionPanel.add(new JLabel("Tuổi:"), gbc);
        gbc.gridx = 1;
        functionPanel.add(addAgeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        functionPanel.add(new JLabel("Giới tính:"), gbc);
        gbc.gridx = 1;
        functionPanel.add(addGenderField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        functionPanel.add(new JLabel("SĐT:"), gbc);
        gbc.gridx = 1;
        functionPanel.add(addPhoneNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        functionPanel.add(new JLabel("Tình trạng sức khỏe:"), gbc);
        gbc.gridx = 1;
        functionPanel.add(addHealthStatusField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        functionPanel.add(editButton, gbc);
        
        GridBagConstraints gbcAddButton = new GridBagConstraints();
        gbcAddButton.fill = GridBagConstraints.HORIZONTAL;
        gbcAddButton.insets = new Insets(5, 5, 5, 5);

        gbcAddButton.gridx = 0;
        gbcAddButton.gridy = 0;
        functionPanel.add(addButton, gbcAddButton);

        GridBagConstraints gbcRemoveButton = new GridBagConstraints();
        gbcRemoveButton.fill = GridBagConstraints.HORIZONTAL;
        gbcRemoveButton.insets = new Insets(5, 5, 5, 5);

        gbcRemoveButton.gridx = 0;
        gbcRemoveButton.gridy = 1;
        functionPanel.add(removeButton, gbcRemoveButton);

        frame.getContentPane().add(functionPanel, BorderLayout.WEST);

        JPanel tablePanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane();

        table = new JTable();
        table.setBackground(new Color(1.0f, 0.75f, 0.80f));
        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Số ID bệnh nhân", "Họ tên", "Tuổi", "Giới tính", "SĐT", "Tình trạng sức khỏe"}
        ));
        scrollPane.setViewportView(table);

        tablePanel.add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(tablePanel, BorderLayout.CENTER);

        model = (DefaultTableModel) table.getModel();

        addButton.addActionListener(e -> addPatient());
        removeButton.addActionListener(e -> removePatient());
        searchButton.addActionListener(e -> searchPatient());
        editButton.addActionListener(e -> editPatient());

        frame.setVisible(true);
    }

    	private void addPatient() {
    	    String patientID = addIdField.getText();
    	    String name = addNameField.getText();
    	    String age = addAgeField.getText();
    	    String gender = addGenderField.getText();
    	    String phoneNumber = addPhoneNumberField.getText();
    	    String healthStatus = addHealthStatusField.getText();

    	    if (!patientID.isEmpty() && !name.isEmpty() && !age.isEmpty() &&
    	        !gender.isEmpty() && !phoneNumber.isEmpty() && !healthStatus.isEmpty()) {
    	        model.addRow(new Object[]{patientID, name, age, gender, phoneNumber, healthStatus});
    	        
    	        insertpatient(patientID, name, age, gender, phoneNumber, healthStatus);

    	        addIdField.setText("");
    	        addNameField.setText("");
    	        addAgeField.setText("");
    	        addGenderField.setText("");
    	        addPhoneNumberField.setText("");
    	        addHealthStatusField.setText("");
    	    } else {
    	        JOptionPane.showMessageDialog(frame, "Vui lòng điền đầy đủ thông tin bệnh nhân.");
    	    }
    	}
    	private void deletePatientFromDatabase(String patientID) {   	  
    	    String connectionUrl = "jdbc:mysql://localhost:3306/QLBENHAN";
    	    String userName = "anhlonx";
    	    String password = "Hnk180705@";

    	    try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password)) {
    	        String sql = "DELETE FROM Patients WHERE PatientID = ?";

    	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    	            preparedStatement.setString(1, patientID);

    	            int rows = preparedStatement.executeUpdate();

    	            if (rows > 0) {
    	                System.out.println("Đã xóa bệnh nhân khỏi cơ sở dữ liệu.");
    	            } else {
    	                System.out.println("Không có bệnh nhân nào được xóa.");
    	            }
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	        System.out.println("Lỗi khi xóa bệnh nhân khỏi cơ sở dữ liệu!");
    	        System.out.println("SQL State: " + e.getSQLState());
    	        System.out.println("Error Code: " + e.getErrorCode());
    	        System.out.println("Error Message: " + e.getMessage());
    	    }
    	}

    	private void removePatient() {
    	int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String patientID = model.getValueAt(selectedRow, 0).toString();

            deletePatientFromDatabase(patientID);

            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(frame, "Vui lòng chọn bệnh nhân cần xóa.");
        }
    	}

    	private void searchPatient() {
        String timkiem = searchTextField.getText().trim().toLowerCase();
        DefaultTableModel searchModel = new DefaultTableModel(
                new Object[][] {},
                new String[] {"Số ID bệnh nhân", "Họ tên", "Tuổi", "Giới tính", "SĐT", "Tình trạng sức khỏe"}
        );

        for (int i = 0; i < model.getRowCount(); i++) {
            String patientID = model.getValueAt(i, 0).toString().toLowerCase();
            String name = model.getValueAt(i, 1).toString().toLowerCase();
            String age = model.getValueAt(i, 2).toString().toLowerCase();
            String gender = model.getValueAt(i, 3).toString().toLowerCase();
            String phoneNumber = model.getValueAt(i, 4).toString().toLowerCase();
            String healthStatus = model.getValueAt(i, 5).toString().toLowerCase();

            if (patientID.contains(timkiem) || name.contains(timkiem) || age.contains(timkiem) ||
                gender.contains(timkiem) || phoneNumber.contains(timkiem) || healthStatus.contains(timkiem)) {
                searchModel.addRow(new Object[]{patientID, name, age, gender, phoneNumber, healthStatus});
            }
        }

        table.setModel(searchModel);
    }
    	private void insertpatient(String patientID, String name, String age, String gender, String phoneNumber, String healthStatus) {
        String connectionUrl = "jdbc:mysql://localhost:3306/QLBENHAN";
        String userName = "anhlonx";
        String password = "Hnk180705@";

        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password)) {
            String sql = "INSERT INTO Patients (PatientID, Name, Age, Gender, PhoneNumber, HealthStatus) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, patientID);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, age);
                preparedStatement.setString(4, gender);
                preparedStatement.setString(5, phoneNumber);
                preparedStatement.setString(6, healthStatus);

                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(frame, "Đã thêm bệnh nhân vào cơ sở dữ liệu.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Lỗi khi thêm bệnh nhân vào cơ sở dữ liệu.");
        }
    }
    	private void editPatient() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String patientID = model.getValueAt(selectedRow, 0).toString();
            String name = model.getValueAt(selectedRow, 1).toString();
            String age = model.getValueAt(selectedRow, 2).toString();
            String gender = model.getValueAt(selectedRow, 3).toString();
            String phoneNumber = model.getValueAt(selectedRow, 4).toString();
            String healthStatus = model.getValueAt(selectedRow, 5).toString();

            editIdField.setText(patientID);
            editNameField.setText(name);
            editAgeField.setText(age);
            editGenderField.setText(gender);
            editPhoneNumberField.setText(phoneNumber);
            editHealthStatusField.setText(healthStatus);

            JPanel panel = new JPanel(new GridLayout(7, 2));
            panel.add(new JLabel("Số ID bệnh nhân:"));
            panel.add(editIdField);
            panel.add(new JLabel("Họ tên:"));
            panel.add(editNameField);
            panel.add(new JLabel("Tuổi:"));
            panel.add(editAgeField);
            panel.add(new JLabel("Giới tính:"));
            panel.add(editGenderField);
            panel.add(new JLabel("SĐT:"));
            panel.add(editPhoneNumberField);
            panel.add(new JLabel("Tình trạng sức khỏe:"));
            panel.add(editHealthStatusField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Sửa thông tin bệnh nhân",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String newPatientID = editIdField.getText();
                String newName = editNameField.getText();
                String newAge = editAgeField.getText();
                String newGender = editGenderField.getText();
                String newPhoneNumber = editPhoneNumberField.getText();
                String newHealthStatus = editHealthStatusField.getText();

                if (!newPatientID.isEmpty() && !newName.isEmpty() && !newAge.isEmpty() &&
                        !newGender.isEmpty() && !newPhoneNumber.isEmpty() && !newHealthStatus.isEmpty()) {
                    model.setValueAt(newPatientID, selectedRow, 0);
                    model.setValueAt(newName, selectedRow, 1);
                    model.setValueAt(newAge, selectedRow, 2);
                    model.setValueAt(newGender, selectedRow, 3);
                    model.setValueAt(newPhoneNumber, selectedRow, 4);
                    model.setValueAt(newHealthStatus, selectedRow, 5);

                    updatepatient(patientID, newPatientID, newName, newAge, newGender, newPhoneNumber, newHealthStatus);
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Vui lòng chọn bệnh nhân cần sửa thông tin.");
        }
    }
    	private void updatepatient(String oldPatientID, String newPatientID, String newName, String newAge, String newGender, String newPhoneNumber, String newHealthStatus) {
        String connectionUrl = "jdbc:mysql://localhost:3306/QLBENHAN";
        String userName = "anhlonx";
        String password = "Hnk180705@";

        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password)) {
            String query = "UPDATE Patients SET PatientID=?, Name=?, Age=?, Gender=?, PhoneNumber=?, HealthStatus=? WHERE PatientID=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newPatientID);
                preparedStatement.setString(2, newName);
                preparedStatement.setString(3, newAge);
                preparedStatement.setString(4, newGender);
                preparedStatement.setString(5, newPhoneNumber);
                preparedStatement.setString(6, newHealthStatus);
                preparedStatement.setString(7, oldPatientID);
                int rows = preparedStatement.executeUpdate();

                if (rows > 0) {
                    System.out.println("Đã cập nhật thông tin bệnh nhân trong cơ sở dữ liệu.");
                } else {
                    System.out.println("Không có bệnh nhân nào được cập nhật.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi cập nhật thông tin bệnh nhân trong cơ sở dữ liệu!");
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getMessage());
        }
    }
}
