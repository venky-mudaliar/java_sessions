package application;

import accounts.BankAccount;
import accounts.CheckingAccount;
import accounts.PremiumBankAccount;
import accounts.SavingsAccount;
import accounts.SavingsAccountPrint;
import customer.CustomerDetails;
import exceptions.InsufficientFundsException;
import services.CreditCardPayment;
import services.EmailNotification;
import services.LightBillPayment;
import services.NotificationServiceAbstractClass;
import services.PaymentServiceInterface;
import services.SMSNotification;
import utilities.BankTransactionHistory;
import utilities.BankUtils;
import utilities.TransactionType;

public class BankSystem {
	public static void main(String[] args) throws InsufficientFundsException
	{
		// CHAPTER - OBJECTS
		// Creating objects (Bank Accounts) from the BankAccount class
        BankAccount simpleBankObject = new BankAccount();
        simpleBankObject.accountHolderName = "LazyCoffee"; // Setting the account holder name
        simpleBankObject.accountNumber = "24";             // Setting the account number
        simpleBankObject.balance = 1500;                   // Setting an initial balance

        // CHAPTER - METHODS
        // Accessing the behavior (methods) of the objects
        // Instance Method: Checking the balance of the account
        simpleBankObject.checkBalance();

        // Static Method: Getting the bank name without creating an object
        System.out.println("Bank Name: " + BankAccount.getBankName());

        // Void Method: Depositing an amount of money
        simpleBankObject.deposit(2500);  // Depositing 2500

        // Parameterized Method: Withdrawing an amount of money
        simpleBankObject.withdraw(500);  // Withdrawing 500

        // Check balance again after transactions
        simpleBankObject.checkBalance();
        
        
        /// CHAPTER - METHOD OVERLOADING
        // Calling different overloaded versions of the deposit method
        simpleBankObject.deposit(100.0);  // Simple deposit
        simpleBankObject.deposit(200.0, "Savings");  // Deposit with a message
        simpleBankObject.deposit("CHK123456", 500.0);  // Deposit via check
        simpleBankObject.checkBalance();
        
        
        // CHAPTERS - CONSTRUCTORS
        // Creating objects (Bank Accounts) from the BankAccount class using constructors
        // Using default constructor
        BankAccount defaultAccount = new BankAccount();
        defaultAccount.checkBalance(); // Should show default values

        // Using parameterized constructor with all details
        BankAccount customAccount = new BankAccount("LazyCoffee", "24", 1500);
        customAccount.checkBalance(); // Should show initialized values

        // Using constructor with only the account holder name (constructor chaining will apply)
        BankAccount simpleAccount = new BankAccount("SimpleUser");
        simpleAccount.checkBalance(); // Should show default balance and account number
        
        // Accessing the behavior (methods) of the objects
        // Instance Method: Checking the balance of the account
        customAccount.checkBalance();

        // Static Method: Getting the bank name without creating an object
        System.out.println("Bank Name: " + BankAccount.getBankName());

        // Void Method: Depositing an amount of money
        customAccount.deposit(2500);  // Depositing 2500

        // Parameterized Method: Withdrawing an amount of money
        customAccount.withdraw(500);  // Withdrawing 500

        // Check balance again after transactions
        customAccount.checkBalance();

        // Calling different overloaded versions of the deposit method
        customAccount.deposit(100.0);  // Simple deposit
        customAccount.deposit(200.0, "Monthly Salary Deposit");  // Deposit with a message
        customAccount.deposit("CHK123456", 500.0);  // Deposit via check

        // Check final balance after multiple transactions
        customAccount.checkBalance();
        
        // CHAPTER - INHERITANCE
        // SavingsAccount object with interest rate
        SavingsAccount savingsAccount = new SavingsAccount("John Doe", "789456", 2000.0, 5.0);
        savingsAccount.checkBalance();
        savingsAccount.applyInterest();  // Apply interest and check balance again
        savingsAccount.checkBalance();
        
        // SavingsAccount object with interest rate
        SavingsAccountPrint savingsAccountPrint = new SavingsAccountPrint("John Doe", "789456", 2000.0, 5.0, 43367);
        savingsAccountPrint.checkBalance();
        savingsAccountPrint.applyInterest();  // Apply interest and check balance again
        savingsAccountPrint.checkBalance();
        savingsAccountPrint.printSavingsAccount();
        
        // CheckingAccount object with overdraft limit
        CheckingAccount checkingAccount = new CheckingAccount("Jane Smith", "654321", 1000.0, 500.0);
        checkingAccount.checkBalance();
        checkingAccount.withdraw(1200);  // Withdraw within overdraft limit
        checkingAccount.checkBalance();
        checkingAccount.withdraw(500);  // Exceed overdraft limit
        checkingAccount.checkBalance();
        
        // CHAPTER - FINAL CLASS AND FINAL METHODS
        // Final Class: PremiumBankAccount
        PremiumBankAccount premiumAccount = new PremiumBankAccount("RichUser", "987654", 10000.0);
        premiumAccount.deposit(2000);
        premiumAccount.printStatement(); // Final method from PremiumBankAccount class
        premiumAccount.withdraw(5000);
        premiumAccount.printStatement();
        
        //calling final method from BankAcccount class
        simpleBankObject.printAccountDetails();
        customAccount.printAccountDetails();
        savingsAccount.printAccountDetails();
        checkingAccount.printAccountDetails();
        
        // CHAPTER PACKAGE
        CustomerDetails firstCustomer = new CustomerDetails("lazyCoffeePack", "India", "LazycoffeeNominee");
        System.out.println(firstCustomer.toString());
        
        //calling method from different package
        BankUtils bankUtil = new BankUtils();
        bankUtil.printAccountSummary(simpleBankObject);
        bankUtil.printAccountSummary(customAccount);
        bankUtil.printAccountSummary(savingsAccount);
        bankUtil.printAccountSummary(checkingAccount);
        
        //CHAPTER - INTERFACES
        PaymentServiceInterface paymentOne = new CreditCardPayment();
        paymentOne.processPayment(900);
        PaymentServiceInterface paymentTwo = new LightBillPayment();
        paymentTwo.processPayment(500);
        
        // CHAPTER - ABSTRACT CLASSES
        // Sending notifications using NotificationService
        NotificationServiceAbstractClass emailNotification = new EmailNotification("john@example.com", "Deposit of $500 successful.");
        emailNotification.sendNotification();

        NotificationServiceAbstractClass smsNotification = new SMSNotification("123-456-7890", "Withdrawal of $600 processed.");
        smsNotification.sendNotification();
        
        SavingsAccount exceptionSavingAccount = new SavingsAccount("LazyCoffeeException", "17765883", 450, 3.5);
        //exceptionSavingAccount.withdraw(600); 
        try {
            // Attempt to withdraw more than the balance in SavingsAccount
        	exceptionSavingAccount.withdraw(600);
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // CHAPTER - ENUM
        // Creating a bank account with initial balance
        BankAccount account = new BankAccount("LazyCoffeeEnumDemo", "13365883", 500);

        try {
            // Perform a deposit transaction
            account.performTransaction(200, TransactionType.DEPOSIT);

            // Perform a withdrawal transaction with a fee
            account.performTransaction(100, TransactionType.WITHDRAWAL);

            // Attempt to withdraw more than the balance (this will trigger the custom exception)
            account.performTransaction(600, TransactionType.WITHDRAWAL);

        } catch (InsufficientFundsException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }
        
        // Create a BankTransactionHistory object
        BankTransactionHistory transactionHistory = new BankTransactionHistory();

        // Add some transactions using autoboxing
        transactionHistory.addTransaction(50.0);  // Autoboxing double -> Double
        transactionHistory.addTransaction(150.0);
        transactionHistory.addTransaction(200.0);

        // Print the transaction history
        transactionHistory.printTransactionHistory();

        // Calculate and display the total of all transactions
        double total = transactionHistory.calculateTotalTransactions();  // Unboxing Double -> double
        System.out.println("Total transactions: $" + total);
        
 
	}
}