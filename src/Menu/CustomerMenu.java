package Menu;

import AllUsers.Users;
import AllUsers.UsersDao;
import AllUsers.UsersDaoFactory;
import CustomerAccounts.CustomerAccountsDao;
import CustomerAccounts.CustomerAccountsDaoFactory;
import MySqlConnection.ConnectionFactory;
import Transactions.Transactions;
import Transactions.TransactionsDao;
import Transactions.TransactionsDaoFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerMenu {


    UsersDao dao = UsersDaoFactory.getUsersDao();

    CustomerAccountsDao customerAccountsDao = CustomerAccountsDaoFactory.getCustomerAccountsDao();
    TransactionsDao transactionsDao = TransactionsDaoFactory.getTransactionsDao();
    Scanner scanner = new Scanner(System.in);
    String sql = "";
    Connection connection= ConnectionFactory.getConnection();

        boolean flag = true;

        while (flag){
            System.out.println("Welcome to First Bank!");
            System.out.println("********************************");
            System.out.println("Please select from the options below:");
            System.out.println("********************************");
            System.out.println("Press 1 to Register for a new account");
            System.out.println("Press 2 to Update existing account");
            System.out.println("Press 3 to Remove an existing account");
            System.out.println("Press 4 to Get all transactions");
            System.out.println("Press 5 to Get transaction by id");
            System.out.println("Press 6 to Logout");

            int input = scanner.nextInt();

            switch (input) {
                case 1: { //Add new account
                    System.out.println("Enter First Name: ");
                    String fname = scanner.next();
                    System.out.println("Enter Last Name: ");
                    String lname = scanner.next();
                    System.out.println("Enter Email: ");
                    String email = scanner.next();
                    System.out.println("Enter Password: ");
                    String password = scanner.next();
                    System.out.println("Please select 'customer' or 'employee'");
                    String userType = scanner.next();
                    Users users = new Users();
                    users.setfName(fname);
                    users.setlName(lname);
                    users.setEmail(email);
                    users.setPassword(password);
                    users.setUserType(userType);

                    try {
                        dao.addUser(users);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 2: { //update account info
                    System.out.println("Enter Id: ");
                    int id = scanner.nextInt();
                    System.out.println("Enter First Name: ");
                    String fname = scanner.next();
                    System.out.println("Enter Last Name: ");
                    String lname = scanner.next();
                    System.out.println("Enter Email: ");
                    String email = scanner.next();
                    System.out.println("Enter New Password: ");
                    String password = scanner.next();
                    System.out.println("Are you a 'customer' or 'administrator'? ");
                    String userType = scanner.next();
                    Users users = new Users(id, fname, lname, email, password, userType);
                    try {
                        dao.updateUser(users);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }

                case 3: { //delete
                    System.out.println("Enter Id: ");
                    int id = scanner.nextInt();
                    try {
                        dao.deleteUser(id);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
            case 4: { //get all Transactions
                List<Transactions> transactions = null;
                try {
                    transactions = transactionsDao.getTransactions();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                for (Transactions transactions1 : transactions) {
                   System.out.println(transactions1);
                }
                break;
            }
            case 5: { //get by id
                System.out.print("Enter Id: ");
                int id = scanner.nextInt();
                List<Transactions> transactions = null;
                try {
                    transactions = transactionsDao.getTransactions();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(transactions);
               break;
            }
           case 6: {
                System.out.println("Thank you for using our services!");
                System.out.println("Logging out...");
                flag = false;
                break;
            }
            default:
               System.out.println("Please choose an option between 1 - 6");
        }
    }
}
