package AllUsers;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao {

    void addUser(Users users) throws SQLException;
    void updateUser(Users users) throws SQLException;
    void deleteUser(int id) throws SQLException;

    List<Users> getUsers() throws SQLException;
    Users getUserbyId(int id) throws SQLException;
}

