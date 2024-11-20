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

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields for Individual Customer.");
                    return;
                }

                IndividualCustomer ic = new IndividualCustomer(id, name, email, phone);
                crm.addCustomer(ic);
                tableModel.addRow(ic.toRow());

                idField.setText("");
                nameField.setText("");
                emailField.setText("");
                companyField.setText("");
                phoneField.setText("");

                JOptionPane.showMessageDialog(this, "Individual Customer added successfully!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Customer ID. Please enter a number.");
            }
        });

        panel.add(addBusinessButton);
        panel.add(addIndividualButton);
        return panel;
    }

    // Load customers from a CSV file
    private void loadCSV() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    int id = Integer.parseInt(values[0]);
                    String name = values[1];
                    String email = values[2];
                    if (values.length == 4) {
                        String company = values[3];
                        BusinessCustomer bc = new BusinessCustomer(id, name, email, company);
                        crm.addCustomer(bc);
                        tableModel.addRow(bc.toRow());
                    } else if (values.length == 5) {
                        String phone = values[4];
                        IndividualCustomer ic = new IndividualCustomer(id, name, email, phone);
                        crm.addCustomer(ic);
                        tableModel.addRow(ic.toRow());
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error loading CSV: " + ex.getMessage());
            }
        }
    }

    // Save customers to a CSV file
    private void saveToCSV() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (FileWriter writer = new FileWriter(file)) {
                for (Customer customer : crm.getCustomers()) {
                    if (customer instanceof BusinessCustomer) {
                        writer.write(customer.getCustomerID() + "," + customer.getName() + "," + customer.getEmail() + "," +
                                ((BusinessCustomer) customer).toRow()[3] + "\n");
                    } else if (customer instanceof IndividualCustomer) {
                        writer.write(customer.getCustomerID() + "," + customer.getName() + "," + customer.getEmail() + ",," +
                                ((IndividualCustomer) customer).toRow()[4] + "\n");
                    }
                }
                JOptionPane.showMessageDialog(this, "Saved to CSV successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving CSV: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String username = JOptionPane.showInputDialog("Enter username to log in:");
        if (username != null && !username.trim().isEmpty()) {
            SwingUtilities.invokeLater(() -> new CRMGUI(username));
        } else {
            JOptionPane.showMessageDialog(null, "Username cannot be empty!");
            System.exit(0);
        }
    }
}
