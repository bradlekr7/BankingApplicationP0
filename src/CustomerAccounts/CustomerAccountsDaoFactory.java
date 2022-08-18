package CustomerAccounts;


public class CustomerAccountsDaoFactory {
    public static CustomerAccountsDao customerAccountsDao;

    private CustomerAccountsDaoFactory(){

    }

    public static CustomerAccountsDao getCustomerAccountsDao(){
        if(customerAccountsDao == null) {
            customerAccountsDao = new CustomerAccountsDaoImpl();
        }
        return customerAccountsDao;
    }
}


