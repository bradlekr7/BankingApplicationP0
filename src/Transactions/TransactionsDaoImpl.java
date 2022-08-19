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
    public void deposit(double amount) throws SQLException {

        String sql = "insert into customer_transactions (deposits) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, amount);

        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Sorry, transaction could not be completed at this time. Please try again.");

        }
    }

    @Override
    public void withdrawal(double amount) throws SQLException {
        String sql = "insert into customer_transactions (withdrawals) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, amount);

        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Successfully withdrew: " + amount);

        } else {
            System.out.println("Sorry, insufficient funds. Transaction could not be completed at this time. Please try again.");
        }

    }

    @Override
    public void getBalance() throws SQLException {

        String sql = "select from customer_transactions (current_balance) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        double amount = 0;
        preparedStatement.setDouble(1,amount);

        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Your current balance is: " + amount);
        } else {
            System.out.println("Sorry, something went wrong. Please try your request again.");
        }
    }


    public List<Transactions> getTransactions() throws SQLException {
        List<Transactions> transactions = new ArrayList<>();
        String sql = "select * from customer_transactions";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {

        }
        return transactions;
    }


    public Transactions getTransactionsbyId(int id) throws SQLException {
        Transactions transaction = new Transactions();
        String sql = "select * from customer_transactions where id = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        int userId = resultSet.getInt(1);
        Double deposits = resultSet.getDouble(2);
        Double withdrawals = resultSet.getDouble(3);
        Double balance = resultSet.getDouble(4);

        transaction = new Transactions(1, 100.00, 50.00, 20.00);
        return transaction;
    }
}
