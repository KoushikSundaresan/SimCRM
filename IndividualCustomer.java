public class IndividualCustomer extends Customer {
    private String phoneNumber;

    public IndividualCustomer(int customerID, String name, String email, String phoneNumber) {
        super(customerID, name, email);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Object[] toRow() {
        return new Object[]{getCustomerID(), getName(), getEmail(), "", phoneNumber};
    }
}
