import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class CRMGUI extends JFrame {
    private CRM crm;
    private DefaultTableModel tableModel;
    private JTable customerTable;
    private String currentUsername;

    public CRMGUI(String username) {
        this.currentUsername = username;
        crm = new CRM();

        // Set title and icon
        setTitle("SimCRM - Logged in as: " + username);
        setIconImage(new ImageIcon("src/simcrm_icon.png").getImage()); // Load icon from the project folder

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up layout
        setLayout(new BorderLayout());
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Table for displaying customers
        String[] columns = {"Customer ID", "Name", "Email", "Company Name", "Phone Number"};
        tableModel = new DefaultTableModel(columns, 0);
        customerTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(customerTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load and Save CSV Buttons
        JPanel buttonPanel = new JPanel();
        JButton loadCSVButton = new JButton("Load from CSV");
        loadCSVButton.addActionListener(e -> loadCSV());
        JButton saveCSVButton = new JButton("Save to CSV");
        saveCSVButton.addActionListener(e -> saveToCSV());
        buttonPanel.add(loadCSVButton);
        buttonPanel.add(saveCSVButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Panel for manual customer entry
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 4));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField companyField = new JTextField();
        JTextField phoneField = new JTextField();

        panel.add(new JLabel("Customer ID"));
        panel.add(idField);
        panel.add(new JLabel("Name"));
        panel.add(nameField);
        panel.add(new JLabel("Email"));
        panel.add(emailField);
        panel.add(new JLabel("Company Name"));
        panel.add(companyField);
        panel.add(new JLabel("Phone Number"));
        panel.add(phoneField);

        // Buttons for adding Business and Individual Customers
        JButton addBusinessButton = new JButton("Add Business Customer");
        addBusinessButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String email = emailField.getText();
                String companyName = companyField.getText();

                if (name.isEmpty() || email.isEmpty() || companyName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields for Business Customer.");
                    return;
                }

                BusinessCustomer bc = new BusinessCustomer(id, name, email, companyName);
                crm.addCustomer(bc);
                tableModel.addRow(bc.toRow());

                idField.setText("");
                nameField.setText("");
                emailField.setText("");
                companyField.setText("");
                phoneField.setText("");

                JOptionPane.showMessageDialog(this, "Business Customer added successfully!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Customer ID. Please enter a number.");
            }
        });

        JButton addIndividualButton = new JButton("Add Individual Customer");
        addIndividualButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();

                if (name.isEmpty() || email
