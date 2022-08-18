package Menu;
import AllUsers.UsersDao;
import AllUsers.UsersDaoFactory;
import CustomerAccounts.CustomerAccounts;
import CustomerAccounts.CustomerAccountsDao;
import CustomerAccounts.CustomerAccountsDaoFactory;
import MySqlConnection.ConnectionFactory;
import Transactions.Transactions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class AdminMenu {

    UsersDao dao = UsersDaoFactory.getUsersDao();
    CustomerAccountsDao customerAccountsDao = CustomerAccountsDaoFactory.getCustomerAccountsDao();

    TransactionsDao transactionsDao = TransactionsDaoFactory.getTransactionsDao();
    Scanner scanner = new Scanner(System.in);
    String sql = "";

    Connection connection = ConnectionFactory.getConnection();


       boolean flag = true;

        while (flag) {
            System.out.println("Welcome to First Bank!");
            System.out.println("********************************");
            System.out.println("Please select from the options below:");
            System.out.println("********************************");
            System.out.println("Press 1 to View all customer accounts");
            System.out.println("Press 2 to View all customer transactions");
            System.out.println("Press 3 to Search customers by id");
            System.out.println("Press 4 to Search transactions by id");
            System.out.println("Press 5 to Remove an existing customer account");
            System.out.println("Press 6 to Logout");


        Scanner scanner = null;
        int input = scanner.nextInt();

        switch (input) {
            case 1 -> { //View all customer accounts
                List<CustomerAccounts> customerAccounts = null;
                try {
                    customerAccounts = CustomerAccountsDao.getCustomerAccounts();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                for (CustomerAccounts customerAccount : customerAccounts) {
                    System.out.println(customerAccount);
                }
                break;

            }
            case 2 -> { //View all customer transactions
                List<Transactions> transactions = TransactionsDao.getTransactions();
                for (Transactions transactions1 : transactions) {
                    System.out.println(transactions1);
                }

                break;
            }
            case 3 -> { //Search customer accounts by id
                System.out.print("Enter Id: ");
                int id = scanner.nextInt();
                CustomerAccounts customerAccounts = CustomerAccountsDao.getCustomerAccountsbyId(id);
                System.out.println(customerAccounts);
                break;

            }
            case 4 -> { //Search transactions by id

                System.out.print("Enter Id: ");
                int id = scanner.nextInt();
                Transactions transactions = TransactionsDao.getTransactionsbyId(id);
                System.out.println(transactions);
                break;

            }
            case 5 -> { //Delete an existing account

                System.out.println("Enter Id: ");
                int id = scanner.nextInt();
                try {
                    customerAccountsDao.deleteCustomerAccount(id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            }
            case 6 -> {
                System.out.println("Thank you for using our services!");
                System.out.println("Logging out...");
                flag = false;
                break;
            }
            default -> System.out.println("Please choose an option between 1 - 6");
        }
        }
    }
}

