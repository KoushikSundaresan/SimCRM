import java.util.Map;

class Complaint {
    private int customerID; // You might want to add this if needed
    private String name, email, company, dateReceived, product, subProduct, issue, subIssue;
    private String narrative, companyResponse, state, zipcode, tags, consent, submittedVia;
    private String dateSent, companyResponseToConsumer;
    private boolean timelyResponse, disputed;
    private String complaintID;

    // Constructor that accepts a Map
    public Complaint(Map<String, String> complaintData) {
        // Assuming you have a way to set customer ID, or you might want to remove it.
        this.customerID = Integer.parseInt(complaintData.get("customer_id")); // Optional
        this.name = complaintData.get("name");
        this.email = complaintData.get("email");
        this.company = complaintData.get("company");
        this.dateReceived = complaintData.get("date_received");
        this.product = complaintData.get("product");
        this.subProduct = complaintData.get("sub_product");
        this.issue = complaintData.get("issue");
        this.subIssue = complaintData.get("sub_issue");
        this.narrative = complaintData.get("consumer_complaint_narrative");
        this.companyResponse = complaintData.get("company_public_response");
        this.state = complaintData.get("state");
        this.zipcode = complaintData.get("zipcode");
        this.tags = complaintData.get("tags");
        this.consent = complaintData.get("consumer_consent_provided");
        this.submittedVia = complaintData.get("submitted_via");
        this.dateSent = complaintData.get("date_sent_to_company");
        this.companyResponseToConsumer = complaintData.get("company_response_to_consumer");
        this.timelyResponse = Boolean.parseBoolean(complaintData.get("timely_response"));
        this.disputed = Boolean.parseBoolean(complaintData.get("consumer_disputed?"));
        this.complaintID = complaintData.get("complaint_id");
    }

    public String getComplaintID() {
        return complaintID;
    }

    public Object[] toRow() {
        return new Object[]{
                customerID, name, email, company, dateReceived, product, subProduct,
                issue, subIssue, narrative, companyResponse, state, zipcode, tags,
                consent, submittedVia, dateSent, companyResponseToConsumer,
                timelyResponse, disputed, complaintID
        };
    }

    public String[] toCSV() {
        return new String[]{
                String.valueOf(customerID), name, email, company, dateReceived, product,
                subProduct, issue, subIssue, narrative, companyResponse, state,
                zipcode, tags, consent, submittedVia, dateSent,
                companyResponseToConsumer, String.valueOf(timelyResponse),
                String.valueOf(disputed), complaintID
        };
    }
}
