package com.models;

/**
 * Represents a premium customer in the bank account management system.
 * 
 * <p>Premium customers receive enhanced benefits including fee waivers and
 * higher minimum balance requirements. They must maintain a minimum balance
 * of $10,000 to qualify for premium status.</p>
 * 
 * <p>Premium customer benefits:
 * <ul>
 *   <li>Monthly fees are waived on checking accounts</li>
 *   <li>Higher minimum balance requirement ($10,000)</li>
 *   <li>Access to premium account features</li>
 * </ul>
 * </p>
 * 
 * <p>Premium customers are required to make an initial deposit of at least
 * $10,000 when opening an account to maintain their premium status.</p>
 * 
 * @author Bank Account Management Team
 * @version 1.0
 * @since 1.0
 * @see Customer
 * @see RegularCustomer
 */
public  class PremiumCustomer extends Customer{

        /** Minimum balance required to maintain premium customer status */
        private double minimumBalance = 10000;

        /**
         * Constructs a premium customer with the provided information.
         * 
         * <p>Initializes the customer with premium status and sets the minimum
         * balance requirement to $10,000.</p>
         * 
         * @param name Customer's full name
         * @param age Customer's age in years
         * @param contact Customer's contact phone number
         * @param address Customer's physical address
         */
        public PremiumCustomer(String name,int age, String contact, String address){
            super(name,age,contact,address);
        }

        /**
         * Retrieves the minimum balance requirement for premium customers.
         * 
         * @return The minimum balance amount required for premium status
         */
        public double getMinimumBalance(){
            return this.minimumBalance;
        }

        /**
         * Sets the minimum balance requirement for premium customers.
         * 
         * <p>This allows for flexibility in adjusting premium customer requirements
         * if business rules change.</p>
         * 
         * @param minimumBalance The new minimum balance requirement
         */
        public void setMinimumBalance(double minimumBalance){
            this.minimumBalance = minimumBalance;
        }
        
        /**
         * Returns this customer object for display purposes.
         * 
         * @return This customer instance
         */
        @Override
        Customer displayCustomerDetails(){
            return this;
        }

        /**
         * Returns the customer type identifier.
         * 
         * @return "Premium" as the customer type
         */
        @Override
        String getCustomerType(){
            return "Premium";
        }

        /**
         * Indicates whether fees are waived for this premium customer.
         * 
         * <p>Premium customers always have fees waived as a benefit of their status.</p>
         * 
         * @return Always returns {@code true} for premium customers
         */
        public boolean hasWaivedFees(){
            return true;
        }
}