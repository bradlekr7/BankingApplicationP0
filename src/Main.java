import AllUsers.Users;
import AllUsers.UsersDao;
import AllUsers.UsersDaoFactory;
import CustomerAccounts.CustomerAccountsDao;
import CustomerAccounts.CustomerAccountsDaoFactory;
import MySqlConnection.ConnectionFactory;
import Transactions.TransactionsDao;
import Transactions.TransactionsDaoFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        UsersDao dao = UsersDaoFactory.getUsersDao();
        CustomerAccountsDao customerAccountsDao = CustomerAccountsDaoFactory.getCustomerAccountsDao();
        TransactionsDao transactionsDao = TransactionsDaoFactory.getTransactionsDao();
        Scanner scanner = new Scanner(System.in);
        Connection connection;
        String sql = "";

        connection = ConnectionFactory.getConnection();

        boolean flag = true;

        while (flag) {
            System.out.println("Welcome to First Bank!");
            System.out.println("********************************");
            System.out.println("Please select from the options below:");
            System.out.println("********************************");
            System.out.println("Press 1 to Register as a new user");
            System.out.println("Press 2 to Login to an existing account");
            System.out.println("Press 3 to Exit");

            int input = scanner.nextInt();

            switch (input) {
                case 1: { //Register for new account
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

                    dao.addUser(users);
                    break;
                }
                case 2: { //Login
                    System.out.println("Enter Email Address: ");
                    String email = scanner.next();
                    System.out.println("Enter Password: ");
                    String password = scanner.next();

                    sql = "select * from users where email='"+ email
                            + "' and password=" + password;
                try {

                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery(sql);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                    if (resultSet.next()) {
                        System.out.println("Login Successful!  Welcome to First Bank!");
                    }

                    }catch (SQLException ex) {

                        System.out.println("**Unsuccessful Login; please try again");


                    } finally {
                    connection.close();
                }

                    break;
                }

                    case 3: {
                    System.out.println("Exiting...Thank you for using our services!");
                    flag = false;
                    break;
                }
                default:
                    System.out.println("Please choose an option between 1 - 3");
            }
        }
    }
}
