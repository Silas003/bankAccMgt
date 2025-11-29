import com.service.*;
import com.models.*;
import com.utilities.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import com.handlers.AppHandler;

/**
 * Main entry point for the Bank Account Management System.
 * 
 * <p>This application provides a console-based interface for managing bank accounts,
 * including account creation, transaction processing, and transaction history viewing.
 * The system supports both Savings and Checking accounts for Regular and Premium customers.</p>
 * 
 * <p>Features:
 * <ul>
 *   <li>Account creation with customer information</li>
 *   <li>Account listing and viewing</li>
 *   <li>Deposit and withdrawal transactions</li>
 *   <li>Transaction history tracking</li>
 * </ul>
 * </p>
 * 
 * @author Bank Account Management Team
 * @version 1.0
 * @since 1.0
 */
public class Main {

    /**
     * Main method that initializes and starts the Bank Account Management application.
     * 
     * <p>Displays the welcome banner and creates an AppHandler instance to manage
     * the application's main menu and user interactions.</p>
     * 
     * @param args Command-line arguments (not currently used)
     */
    public static void main(String[] args){
        System.out.println("||====================================||");
        System.out.println("  BANK ACCOUNT MANAGEMENT - MAIN MENU");
        System.out.println("||====================================||");
        Scanner scanner = new Scanner(System.in);
        AppHandler app = new AppHandler(scanner);
        app.start();

    }
   
}
