public class Customer {
    private int customerID;
    private String name;
    private String email;

    public Customer(int customerID, String name, String email) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Object[] toRow() {
        return new Object[]{customerID, name, email};
    }
}
