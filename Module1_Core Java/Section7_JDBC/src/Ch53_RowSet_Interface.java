import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.RowSetProvider;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;

public class Ch53_RowSet_Interface {

    private Connection connection;

    public static void main(String[] args) {
    	Ch53_RowSet_Interface system = new Ch53_RowSet_Interface();
        system.initializeConnection();  // Initialize the connection

        // Run the JdbcRowSet example
        System.out.println("Running JdbcRowSet Example:");
        system.runJdbcRowSetExample();

        // Run the CachedRowSet example
        System.out.println("\nRunning CachedRowSet Example:");
      system.runCachedRowSetExample();

        // Run the WebRowSet example
        System.out.println("\nRunning WebRowSet Example:");
        system.runWebRowSetExample();

       system.closeConnection();  // Close the connection at the end
    }

    // Method to initialize the connection
    public void initializeConnection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/bankdatabase";
            String username = "postgres";
            String password = "lazycoffee";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to close the connection
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method for JdbcRowSet Example
    public void runJdbcRowSetExample() {
        JdbcRowSet rowSet = null;

        try {
        	// Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            
            // Create a JdbcRowSet instance
            rowSet = RowSetProvider.newFactory().createJdbcRowSet();
            
            // Set the database connection details for PostgreSQL
            rowSet.setUrl("jdbc:postgresql://localhost:5432/bankdatabase");
            rowSet.setUsername("postgres");
            rowSet.setPassword("lazycoffee");
            // Use the existing connection
            rowSet.setCommand("SELECT * FROM customers WHERE customer_id < 5");
            
            //rowSet.execute(connection); rowset doesnt take connection object hence URL config is needed. 

            // Execute the query
            rowSet.execute(); // Make sure the command is set before calling execute()
            // Scroll through the rows and display customer details
            while (rowSet.next()) {
                int customerId = rowSet.getInt("customer_id");
                String firstName = rowSet.getString("first_name");
                String lastName = rowSet.getString("last_name");
                String email = rowSet.getString("email");

                System.out.println("Customer ID: " + customerId);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Email: " + email);
                System.out.println("------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rowSet != null) rowSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Method for CachedRowSet Example
    public void runCachedRowSetExample() {
        CachedRowSet cachedRowSet = null;

        try {
        	connection.setAutoCommit(false);
            // Create a CachedRowSet instance
            cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();

            // Set the SQL command to execute
            cachedRowSet.setCommand("SELECT * FROM accounts WHERE balance > 1000");

            // Execute the query and populate the CachedRowSet
            cachedRowSet.execute(connection);

            // Manipulate the data offline
            while (cachedRowSet.next()) {
                int accountId = cachedRowSet.getInt("account_id");
                double balance = cachedRowSet.getDouble("balance");

                // Example of updating the balance offline
                if (balance > 5000) {
                    cachedRowSet.updateDouble("balance", balance - 500);
                    cachedRowSet.updateRow();
                }

                System.out.println("Account ID: " + accountId + ", Updated Balance: " + cachedRowSet.getDouble("balance"));
            }

            // Reconnect to the database and sync changes
            cachedRowSet.acceptChanges(connection);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cachedRowSet != null) cachedRowSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Method for WebRowSet Example
    public void runWebRowSetExample() {
        WebRowSet webRowSet = null;

        try {
            // Create a WebRowSet instance
            webRowSet = RowSetProvider.newFactory().createWebRowSet();

            // Set the SQL command to execute
            webRowSet.setCommand("SELECT * FROM transactions WHERE account_id = 1");

            // Execute the query and populate the WebRowSet
            webRowSet.execute(connection);


            // Display the deserialized data
            while (webRowSet.next()) {
                int transactionId = webRowSet.getInt("transaction_id");
                double amount = webRowSet.getDouble("amount");
                String transactionDate = webRowSet.getString("transaction_date");

                System.out.println("Transaction ID: " + transactionId + ", Amount: $" + amount + ", Date: " + transactionDate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (webRowSet != null) webRowSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
