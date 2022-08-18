package Transactions;


public class TransactionsDaoFactory {

    private static TransactionsDao transactionsDao;

    private TransactionsDaoFactory(){

    }

    public static TransactionsDao getTransactionsDao(){
        if(transactionsDao == null) {
            transactionsDao = new TransactionsDaoImpl();
        }
        return transactionsDao;
    }
}
