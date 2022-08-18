package Transactions;

import AllUsers.Users;

import java.sql.SQLException;
import java.util.List;

public interface TransactionsDao {
    public boolean deposit(double amount) throws SQLException;

    public boolean withdrawal(double amount) throws SQLException;

    void getBalance(double amount) throws SQLException;

    List<Transactions> getTransactions() throws SQLException;

    Transactions getTransactionsbyId(int id) throws SQLException;
}





