import java.util.ArrayList;

public class CRM {
    private ArrayList<Customer> customers;

    public CRM() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void removeCustomer(int customerID) {
        customers.removeIf(customer -> customer.getCustomerID() == customerID);
    }

    public void clearCustomers() {
        customers.clear();
    }
}
