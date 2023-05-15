public class UserStorage {
    private static User loggedUser;

    public static void setLoggedUser(User user) {
        loggedUser = user;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void clearLoggedUser() {
        loggedUser = null;
    }
}
