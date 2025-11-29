package com.models;

/**
 * Abstract base class representing a customer in the bank account management system.
 * 
 * <p>This class provides the core structure for all customer types (Regular and Premium).
 * It maintains customer identification, personal information, and contact details.
 * Customer IDs are automatically generated in the format "CUS" followed by a sequential counter.</p>
 * 
 * <p>Customer information includes:
 * <ul>
 *   <li>Unique customer ID</li>
 *   <li>Name</li>
 *   <li>Age</li>
 *   <li>Contact information (phone number)</li>
 *   <li>Address</li>
 * </ul>
 * </p>
 * 
 * <p>Subclasses must implement:
 * <ul>
 *   <li>{@link #displayCustomerDetails()} - Return customer details for display</li>
 *   <li>{@link #getCustomerType()} - Return the customer type identifier</li>
 * </ul>
 * </p>
 * 
 * @author Bank Account Management Team
 * @version 1.0
 * @since 1.0
 * @see RegularCustomer
 * @see PremiumCustomer
 */
public  abstract class Customer{
        /** Unique identifier for the customer in format "CUS" + counter */
        private String customerId;
        
        /** Customer's full name */
        private String name;
        
        /** Customer's age in years */
        private int age;
        
        /** Customer's contact phone number */
        private String contact;
        
        /** Customer's physical address */
        private String address;

        /** Static counter for generating unique customer IDs */
        static int customerCounter;

        /**
         * Default constructor that initializes the customer and generates a unique customer ID.
         * 
         * <p>Increments the static customer counter to ensure each customer receives
         * a unique identifier.</p>
         */
        Customer(){
            setCustomerId();
            customerCounter++;
        }

        /**
         * Returns a string representation of the customer.
         * 
         * <p>Currently returns only the customer's name. This can be overridden
         * by subclasses to provide more detailed information.</p>
         * 
         * @return Customer name as a formatted string
         */
        @Override
        public String toString(){
            return String.format("%s ",this.getName());
        }

        /**
         * Constructs a customer with all required information.
         * 
         * <p>Initializes the customer with a unique ID, then sets all provided
         * customer information fields.</p>
         * 
         * @param name Customer's full name
         * @param age Customer's age in years
         * @param contact Customer's contact phone number
         * @param address Customer's physical address
         */
        Customer(String name,int age, String contact, String address){
            this();
            setName(name);
            setAge(age);
            setContact(contact);
            setAddress(address);
        }

        /**
         * Retrieves the customer's unique identifier.
         * 
         * @return The customer ID as a String
         */
        public String getCustomerId() {
            return this.customerId;
        }

        /**
         * Generates and sets a unique customer ID based on the current customer counter.
         * 
         * <p>Format: "CUS" followed by the customer counter value.
         * This ensures sequential, unique customer IDs.</p>
         */
        public void setCustomerId() {
            this.customerId = "CUS"+customerCounter;
        }
        
        /**
         * Retrieves the customer's name.
         * 
         * @return The customer's name
         */
        public String getName() {
            return this.name;
        }
        
        /**
         * Sets the customer's name.
         * 
         * @param name The customer's full name
         */
        public void setName(String name) {
            this.name = name;
        }
        
        /**
         * Retrieves the customer's age.
         * 
         * @return The customer's age in years
         */
        public int getAge() {
            return this.age;
        }
        
        /**
         * Sets the customer's age.
         * 
         * @param age The customer's age in years. Should be a positive value.
         */
        public void setAge(int age) {
            this.age = age;
        }
        
        /**
         * Retrieves the customer's contact information.
         * 
         * @return The customer's contact phone number
         */
        public String getContact() {
            return this.contact;
        }
        
        /**
         * Sets the customer's contact information.
         * 
         * @param contact The customer's contact phone number
         */
        public void setContact(String contact) {
            this.contact = contact;
        }
        
        /**
         * Retrieves the customer's address.
         * 
         * @return The customer's physical address
         */
        public String getAddress() {
            return this.address;
        }
        
        /**
         * Sets the customer's address.
         * 
         * @param address The customer's physical address
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         * Abstract method to return customer details for display purposes.
         * 
         * <p>Subclasses should implement this to return a formatted representation
         * of all customer information appropriate for their customer type.</p>
         * 
         * @return Customer object (typically {@code this}) containing customer details
         */
        abstract Customer displayCustomerDetails();
        
        /**
         * Abstract method to return the customer type identifier.
         * 
         * <p>Subclasses must return their specific customer type (e.g., "regular", "Premium").</p>
         * 
         * @return String representation of the customer type
         */
        abstract String getCustomerType();
    }




    