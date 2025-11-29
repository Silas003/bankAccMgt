package com.models;

/**
 * Interface defining the contract for objects that can process financial transactions.
 * 
 * <p>This interface provides a standardized way for account types to handle deposits
 * and withdrawals. Implementing classes must provide logic for processing transactions
 * based on their specific business rules (e.g., minimum balance requirements,
 * overdraft limits, etc.).</p>
 * 
 * @author Bank Account Management Team
 * @version 1.0
 * @since 1.0
 */
public interface Transactable{
    
    /**
     * Processes a financial transaction (deposit or withdrawal) on the implementing object.
     * 
     * <p>The implementation should validate the transaction based on account-specific
     * rules before executing. For withdrawals, this may include checking minimum balance
     * requirements, overdraft limits, or other account-specific constraints.</p>
     * 
     * @param amount The transaction amount. Must be positive for both deposits and withdrawals.
     * @param type The transaction type, case-insensitive. Expected values: "Deposit" or "Withdrawal"
     * @return {@code true} if the transaction was processed successfully, {@code false} otherwise
     */
    boolean processTransactions(double amount, String type);
}
