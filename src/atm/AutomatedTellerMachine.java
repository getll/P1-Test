package atm;

import java.util.Scanner;

/**
 *
 * @author Denmar Ermitano
 */
public class AutomatedTellerMachine {
    private String atmID;
    private User user;
    private Account account;
    private static int nextID= 1;
    
    public AutomatedTellerMachine(){
        this.atmID = String.format("%06d", nextID++);
        this.user = null;
        this.account = null;
    }
    
    public AutomatedTellerMachine(String atmID){
        this.atmID = atmID;
        this.user = null;
        this.account = null;
    }
    
    public AutomatedTellerMachine(AutomatedTellerMachine automatedTellerMachine) {
        this.atmID = automatedTellerMachine.atmID;
        this.user = automatedTellerMachine.user;
        this.account = null;
    }
    
    /**
     * The pipeline of the ATM if someone wants to use it
     */
    public void pipeline() {
        printWelcome();
        readUserId();
        if (!inputPassword())
            System.exit(2);
        
        chooseAccount();
        
        do {
            int oper = chooseOperation();
            switch (oper) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                default:
                    displayBalance();
            }
        } while (doesContinue());
        
        printGoodbye();
    }
    
    public void printWelcome() {
        System.out.println("Welcome to use our ATM");
    }
    
    /**
     * To ask the user to input the user ID, if the id exists, then return
     * the user object, else it will shut down the process.
     * @return the user object that matches the user id
     */
    public void readUserId() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Please enter your user ID");
        String inputId = console.next();
        
        for (int i = 0; i <Bank.getUsers().size(); i++) {
            if (inputId.equals(Bank.getUsers().get(i).getUserId()))
                user = Bank.getUsers().get(i);
                return;
        }
        user = null;
                
//        System.out.println("Your ID is invalid. Goodbye.");
//        System.exit(1);
//        return null;
    }
    
    /**
     * To ask the user to input the password, the user has three tries to give 
     * the right password, if the user has failed 3 times, then the process
     * will be shut down.
     * @param user the user object of the user
     * @return true if the password is correct.
     */
    public boolean inputPassword() {
        Scanner console = new Scanner(System.in);
        
        int maxTry = 3;
        for (int i = 0; i < maxTry; i++) {
            System.out.println("Please enter your user password");
            String password = console.next();
            if (user.getPassword().equals(password))
                return true;
            System.out.println("Your password is wrong.");
        }
        System.out.println("You input password wrong for 3 times.");
        System.exit(2);
        return false;
    }
    
    /**
     * To ask the user to choose the account, could be
     * 1. checking account
     * 2. saving account
     * @param user the user object of the user
     * @return the selected account
     */
    public void chooseAccount() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Please enter the account you want to work with"
                + "\n1. Checking account"
                + "\n2. Saving account" );
        
        
        int accountChoice = console.nextInt();
        account = (accountChoice == 1 ? user.getCheckingAccount() : user.getSavingAccount());
    }
    
    /**
     * To ask the user to choose an operation, could be
     * 1. withdraw
     * 2. deposit
     * 3. display balance
     * @return the operation the user selects
     */
    public int chooseOperation() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Please enter the operation"
                + "\n1. Withdraw"
                + "\n2. Deposit"
                + "\n3. Display balance" );
        int operation = console.nextInt();
        
        return operation;
    }
    
    /**
     * To withdraw from the ATM
     * @param account the account you want to withdraw from
     * @param user 
     * @return true if successful
     */
    public boolean withdraw() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("How much do you want to withdraw? ");
        double amount = console.nextDouble();
        
        if (account.getBalance() < amount) {
            System.out.println("Sorry, you don't have enough balance.");
            return false;
        }
        account.setBalance(account.getBalance() - amount);
        System.out.println("Withdraw successful.");
        user.getHistory().add(new Record());   //BRO YOU GOTTA FIX THIS
        return true;
    }
    
    /**
     * To deposit from the ATM
     * @param account the account you want to withdraw from
     * @return true if successful
     */
    public boolean deposit() {
        Scanner console = new Scanner(System.in);
        
        System.out.println("How much do you want to deposit? ");
        double amount = console.nextDouble();
        
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposit successful.");
        user.getHistory().bbbbbbbbbbbbbbbbb(new Record("withdraw", amount, atmID));
        return true;
    }
    
    /**
     * To display the balance of the account
     * @param account 
     */
    public void displayBalance() {
        System.out.printf("Your current balance is $%.2f.\n", account.getBalance());
    }
    
    /**
     * Ask the user if they want to do another operation
     * @return true if they want to do another operation, false if they want to quit
     */
    public boolean doesContinue () {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Do you want to do another operation? ");
        System.out.println("0. No");
        System.out.println("1. Yes");
        int answer = console.nextInt();                 //0, 1
        
        return answer == 1;
    }
    
    /**
     * To print a goodbye message
     */
    public void printGoodbye() {
        System.out.println("Thank you for using our ATM. Goodbye.");
    }
    
    public boolean equals(AutomatedTellerMachine automatedTellerMachine) {
        return this.atmID.equals(automatedTellerMachine.atmID);
    }
    
    @Override
    public String toString() {
        return String.format("%-10s: %s", "ATM ID", atmID);
    }

    public String getAtmID() {
        return atmID;
    }

    public void setAtmID(String atmID) {
        this.atmID = atmID;
    }

    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        AutomatedTellerMachine.nextID = nextID;
    }
}
