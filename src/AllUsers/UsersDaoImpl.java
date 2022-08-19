package AllUsers;

import MySqlConnection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements UsersDao {
    Connection connection;

    public UsersDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addUser(Users users) throws SQLException {
        String sql = "insert into users (fName, lName, email, password, user_type) values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, users.getfName());
        preparedStatement.setString(2, users.getlName());
        preparedStatement.setString(3, users.getEmail());
        preparedStatement.setString(4, users.getPassword());
        preparedStatement.setString(5, users.getUserType());
        int count = preparedStatement.executeUpdate();

        if (count > 0) {
            System.out.println("New user created!");
        } else {
            System.out.println("Something went wrong; please try again.");
        }
    }

    @Override
    public void updateUser(Users users) throws SQLException {
        String sql = "Update users set fname = ?, lname = ?, email = ?, password = ?, user_type = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, users.getfName());
        preparedStatement.setString(2, users.getlName());
        preparedStatement.setString(3, users.getEmail());
        preparedStatement.setString(4, users.getPassword());
        preparedStatement.setString(5, users.getUserType());
        //preparedStatement.setInt(6, users.getId());

        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("User updated");
        } else {
            System.out.println("Something went wrong; please try again");
        }
    }

    @Override
    public void deleteUser(int id) throws SQLException {
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
    public List<Users> getUsers() throws SQLException {
        List<Users> users = new ArrayList<>();
        String sql = "select * from users";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String fname = resultSet.getString(2);
            String lname = resultSet.getString(3);
            String email = resultSet.getString(4);
            String password = resultSet.getString(5);
            String userType = resultSet.getString(6);
            Users user = new Users(id, fname, lname, email, password, userType);
            users.add(user);
        }
        return users;
    }

    @Override
    public Users getUserbyId(int userId) throws SQLException {
        Users user = new Users();
        String sql = "select * from users where id = " + userId;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        if (resultSet != null) {
            int id = resultSet.getInt(1);
            String fname = resultSet.getString(2);
            String lname = resultSet.getString(3);
            String email = resultSet.getString(4);
            String password = resultSet.getString(5);
            String userType = resultSet.getString(6);

            user = new Users(id, fname, lname, email, password, userType);
        } else {
            System.out.println("no record found");
        }
        return user;
    }
}

