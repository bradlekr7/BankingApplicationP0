package CustomerAccounts;

public class CustomerAccounts {

    private int customerId;
    private int accountId;
    private String fName;
    private String lName;
    private double currentBalance;

    public CustomerAccounts() {
    }

    public CustomerAccounts(int customerId, int accountId, String fName, String lName, double currentBalance) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.fName = fName;
        this.lName = lName;
        this.currentBalance = currentBalance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }


    @Override

    public String toString() {
        return "AllCustomerAccounts.CustomerAccounts{" +
                "CustomerId= " + customerId +
                "AccountId= " + accountId +
                ", first name=' " + fName + '\'' +
                ", last name=' " + lName + '\'' +
                ", current balance" + currentBalance + '\'' +
                '}';
    }
}

