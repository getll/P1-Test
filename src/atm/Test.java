package atm;

/**
 *
 * @author Denmar Ermitano
 */
public class Test {
    public static void main(String[] args) {
        Bank.addUser("Yi", "1234");
        Bank.addUser("Wang", "2345");
        Bank.addUser("Mike", "3456");
        
        AutomatedTellerMachine a1 = new AutomatedTellerMachine();
        AutomatedTellerMachine a2 = new AutomatedTellerMachine();
        AutomatedTellerMachine a3 = new AutomatedTellerMachine();

//        Bank.getUsers()get(0)
        a1.pipeline();
        System.out.println(Bank.getUsers().get(0).getHistory());
    }
}
