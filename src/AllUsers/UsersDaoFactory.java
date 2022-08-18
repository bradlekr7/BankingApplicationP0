package AllUsers;

public class UsersDaoFactory {
    private static UsersDao usersDao;

    private UsersDaoFactory(){

    }

    public static UsersDao getUsersDao(){
        if(usersDao == null) {
            usersDao = new UsersDaoImpl();
        }
        return usersDao;
    }
}
