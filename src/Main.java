import AllUsers.Users;
import AllUsers.UsersDao;
import AllUsers.UsersDaoFactory;
import CustomerAccounts.CustomerAccounts;
import CustomerAccounts.CustomerAccountsDao;
import CustomerAccounts.CustomerAccountsDaoFactory;
import MySqlConnection.ConnectionFactory;
import Transactions.Transactions;
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

    public static void displayCustomerMenu() throws SQLException {

        UsersDao dao = UsersDaoFactory.getUsersDao();
        CustomerAccountsDao customerAccountsDao = CustomerAccountsDaoFactory.getCustomerAccountsDao();
        TransactionsDao transactionsDao = TransactionsDaoFactory.getTransactionsDao();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("Welcome to First Bank!");
            System.out.println("********************************");
            System.out.println("Please select from the options below:");
            System.out.println("********************************");
            System.out.println("Press 1 to Make a deposit");
            System.out.println("Press 2 to Make a withdrawal");
            System.out.println("Press 3 to Get current balance");
            System.out.println("Press 4 to Update account info");
            System.out.println("Press 5 to Get all transactions");
            System.out.println("Press 6 to Logout");

            int input = scanner.nextInt();


            switch (input) {
                case 1: { //Deposit
                    System.out.println("Please enter deposit amount: ");

                    double deposit = scanner.nextDouble();
                    System.out.println("$" + deposit + " was deposited into your account.");
                    transactionsDao.deposit(deposit);
                    break;
                }
                case 2: { //withdrawal
                    System.out.println("How much would you like to withdraw? ");

                    double withdrawal = scanner.nextDouble();
                    System.out.println("$" + withdrawal + " was withdrawn from your account.");
                    transactionsDao.withdrawal(withdrawal);

                    break;
                }

                case 3: { //current Balance
                    transactionsDao.getBalance();
                    System.out.println("Your current balance is: ");

                    break;
                }
                case 4: { //update account info
                    System.out.println("Update First Name: ");
                    String fname = scanner.next();
                    System.out.println("Update Last Name: ");
                    String lname = scanner.next();
                    System.out.println("Update Email Address: ");
                    String email = scanner.next();
                    System.out.println("Enter New Password: ");
                    String password = scanner.next();
                    System.out.println("Are you a 'customer' or 'employee'?");
                    String userType = scanner.next();
                    Users users = new Users();
                    users.setfName(fname);
                    users.setlName(lname);
                    users.setEmail(email);
                    users.setPassword(password);
                    users.setUserType(userType);

                    dao.updateUser(users);
                    System.out.println("Account Info Updated!");

                    break;

                }
                case 5: { //get by id
                    System.out.print("Enter Id: ");
                    int id = scanner.nextInt();
                    List<Transactions> transactions = null;
                    try {
                        transactions = TransactionsDao.getTransactions();
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

        public static void displayAdminMenu() throws SQLException {

            UsersDao dao = UsersDaoFactory.getUsersDao();
            CustomerAccountsDao customerAccountsDao = CustomerAccountsDaoFactory.getCustomerAccountsDao();
            TransactionsDao transactionsDao = TransactionsDaoFactory.getTransactionsDao();
            Scanner scanner = new Scanner(System.in);

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


                int input = scanner.nextInt();

                switch (input) {
                    case 1 -> { //View all customer accounts
                        List<CustomerAccounts> customerAccounts = CustomerAccountsDao.getCustomerAccounts();
                        try {
                            customerAccounts = CustomerAccountsDao.getCustomerAccounts();
                        } catch (SQLException e) {
                            //throw new RuntimeException(e);
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
        public static void main(String[]args) throws SQLException {

            UsersDao dao = UsersDaoFactory.getUsersDao();
            CustomerAccountsDao customerAccountsDao = CustomerAccountsDaoFactory.getCustomerAccountsDao();
            TransactionsDao transactionsDao = TransactionsDaoFactory.getTransactionsDao();
            Scanner scanner = new Scanner(System.in);

            Connection connection;
            String sql = "";

            connection =ConnectionFactory.getConnection();

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
                            System.out.println("New user created!");

                        if (userType.equals("customer")) {
                            displayCustomerMenu();
                        }else if(userType.equals("employee")){
                            displayAdminMenu();
                        }else
                            break;

                        }
                    case 2: { //Login
                        try {
                            String userType = new String();
                            System.out.println("Enter Email Address: ");
                            String email = scanner.next();
                            System.out.println("Enter Password: ");
                            String password = scanner.next();
                            // System.out.println("Specify user type: 'Customer' or 'Employee'");
                            ///String userType = scanner.next();


                            sql = "select * from users where email='" + email
                                    + "' and password=" + password;

                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                            ResultSet resultSet = preparedStatement.executeQuery(sql);
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                            if (resultSet.next()) {
                                System.out.println("Login Successful!");
                                //System.out.println("DEBUG: The current userType is " + userType);
                            } if (userType.equals("employee")) {
                                    displayAdminMenu();
                                } else if (userType.equals("customer")) {
                                    displayCustomerMenu();
                                } else
                                    // System.out.println("**Unsuccessful Login; please try again");
                                    break;

                            connection.close();

                            break;
                        } catch (SQLException exception) {
                            System.out.println("Oops, something went wrong; please try again.");
                        }

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

