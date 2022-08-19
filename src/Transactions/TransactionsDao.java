package Transactions;

import java.sql.SQLException;
import java.util.List;

public interface TransactionsDao {
    void deposit(double amount) throws SQLException ;

    void withdrawal(double amount) throws SQLException;

    void getBalance() throws SQLException;

    static List<Transactions> getTransactions() throws SQLException {
        return getTransactions();
    }

    static Transactions getTransactionsbyId(int id) throws SQLException {
        return getTransactionsbyId(id);
    }
}





