package CustomerAccounts;

import MySqlConnection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerAccountsDaoImpl implements CustomerAccountsDao {
    Connection connection;

    public CustomerAccountsDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addCustomerAccount(CustomerAccounts customerAccounts) throws SQLException {
        String sql = "insert into customer_accounts (fName, lName, currentBalance) values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, customerAccounts.getfName());
        preparedStatement.setString(2, customerAccounts.getlName());
        preparedStatement.setDouble(3, customerAccounts.getCurrentBalance());

        int count = preparedStatement.executeUpdate();

        if (count > 0) {
            System.out.println("New Account Created!");
        } else {
            System.out.println("Something went wrong; please try again.");
        }
    }

    @Override
    public void updateCustomerAccount(CustomerAccounts customerAccounts) throws SQLException {
        String sql = "Update customer_accounts set fname = ?, lname = ?, current_balance = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, customerAccounts.getfName());
        preparedStatement.setString(2, customerAccounts.getlName());
        preparedStatement.setDouble(3, customerAccounts.getCurrentBalance());
        preparedStatement.setInt(4, customerAccounts.getCustomerId());
        preparedStatement.setInt(5, customerAccounts.getAccountId());

        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Account Info Updated!");
        } else {
            System.out.println("Something went wrong; please try again");
        }
    }

    @Override
    public void deleteCustomerAccount(int id) throws SQLException {
        String sql = "delete from users where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("User deleted");
        } else {
            System.out.println("Something went wrong; please try again");
        }
    }

    @Override
    public List<CustomerAccounts> getCustomerAccounts() throws SQLException {
        List<CustomerAccounts> users = new ArrayList<>();
        String sql = "select * from customer_accounts";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int customerId = resultSet.getInt(1);
            int accountId = resultSet.getInt(2);
            String fname = resultSet.getString(3);
            String lname = resultSet.getString(4);
            Double currentBalance = resultSet.getDouble(5);
            CustomerAccounts customerAccounts = new CustomerAccounts(customerId, accountId, fname, lname, currentBalance);
            users.add(customerAccounts);
        }
        return users;
    }

    @Override
    public CustomerAccounts getCustomerAccountsbyId(int id) throws SQLException {
        CustomerAccounts customerAccounts = new CustomerAccounts();
        String sql = "select * from customer_accounts where account_id = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        if (resultSet != null) {
            int customerId = resultSet.getInt(1);
            int accountId= resultSet.getInt(2);
            String fname = resultSet.getString(3);
            String lname = resultSet.getString(4);
            Double currentBalance = resultSet.getDouble(5);


            customerAccounts = new CustomerAccounts(customerId, accountId, fname, lname, currentBalance);
        } else {
            System.out.println("no record found");
        }
        return customerAccounts;
    }
}



