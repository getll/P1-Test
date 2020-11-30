package atm;

import java.util.ArrayList;

/**
 *
 * @author Denmar Ermitano
 */
public class Bank {
    private static ArrayList<User> users = new ArrayList<>();
    
    public static void addUser(String userName, String password) {
        users.add(new User(userName, password));
    }
    
    public static void addATM() {
        atms.add(new AutomatedTellerMachine());
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
    
}
