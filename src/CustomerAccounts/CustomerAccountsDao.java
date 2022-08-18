package CustomerAccounts;


import java.sql.SQLException;
import java.util.List;

public interface CustomerAccountsDao {
    void addCustomerAccount(CustomerAccounts customerAccounts) throws SQLException;
    void updateCustomerAccount(CustomerAccounts customerAccounts) throws SQLException;
    void deleteCustomerAccount(int customerId) throws SQLException;

    static List<CustomerAccounts> getCustomerAccounts() throws SQLException;
    static CustomerAccounts getCustomerAccountsbyId(int id) throws SQLException;
}


