public class BusinessCustomer extends Customer {
    private String companyName;

    public BusinessCustomer(int customerID, String name, String email, String companyName) {
        super(customerID, name, email);
        this.companyName = companyName;
    }

    @Override
    public Object[] toRow() {
        return new Object[]{getCustomerID(), getName(), getEmail(), companyName, ""};
    }
}
