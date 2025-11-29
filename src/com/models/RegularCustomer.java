package com.models;

/**
 * Represents a regular customer in the bank account management system.
 * 
 * <p>Regular customers are the standard customer tier with no special benefits
 * or fee waivers. They are subject to standard account fees and minimum balance
 * requirements as defined by their account types.</p>
 * 
 * <p>Regular customers:
 * <ul>
 *   <li>Pay standard monthly fees on checking accounts</li>
 *   <li>Must meet standard minimum balance requirements</li>
 *   <li>Do not receive fee waivers or premium benefits</li>
 * </ul>
 * </p>
 * 
 * @author Bank Account Management Team
 * @version 1.0
 * @since 1.0
 * @see Customer
 * @see PremiumCustomer
 */
public  class RegularCustomer extends Customer{
        /**
         * Constructs a regular customer with the provided information.
         * 
         * @param name Customer's full name
         * @param age Customer's age in years
         * @param contact Customer's contact phone number
         * @param address Customer's physical address
         */
        public RegularCustomer(String name,int age, String contact, String address){
            super(name,age,contact,address);
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
         * @return "regular" as the customer type
         */
        @Override
        String getCustomerType(){
            return "regular";
        }


    }