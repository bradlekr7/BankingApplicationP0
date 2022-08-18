package Transactions;

import MySqlConnection.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionsDaoImpl implements TransactionsDao {
    Connection connection;

    public TransactionsDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public boolean deposit(double amount) throws SQLException {

        String sql = "insert into customer_transactions (deposits) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, amount);

        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Successfully deposited: " + amount);
            return true;
        } else {
            System.out.println("Sorry, transaction could not be completed at this time. Please try again.");
            return false;
        }
    }

    @Override
    public boolean withdrawal(double amount) throws SQLException {
        String sql = "insert into customer_transactions (withdrawals) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, amount);

        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Successfully withdrew: " + amount);
            return true;
        } else {
            System.out.println("Sorry, transaction could not be completed at this time. Please try again.");
        }
        return false;
    }

    @Override
    public void getBalance(double amount) throws SQLException {

        String sql = "insert into customer_transactions (balance) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, amount);

        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Your current balance is: " + amount);
        } else {
            System.out.println("Sorry, something went wrong. Please try your request again.");
        }
    }

    @Override
    public List<Transactions> getTransactions() throws SQLException {
        List<Transactions> transactions = new ArrayList<>();
        String sql = "select * from customer_transactions";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {

        }
        return transactions;
    }

    @Override
    public Transactions getTransactionsbyId(int id) throws SQLException {
        Transactions transaction = new Transactions();
        String sql = "select * from customer_transactions where id = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        if (resultSet != null) {
            int userId = resultSet.getInt(1);
            Double deposits = resultSet.getDouble(2);
            Double withdrawals = resultSet.getDouble(3);
            Double balance = resultSet.getDouble(4);

            transaction = new Transactions(1, 100.00, 50.00, 20.00);
        } else {
            System.out.println("no record found");
        }
        return transaction;
    }
}
