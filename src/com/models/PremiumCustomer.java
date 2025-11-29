package com.models;

public  class PremiumCustomer extends Customer{

        private double minimumBalance = 10000;

        public PremiumCustomer(String name,int age, String contact, String address){
            super(name,age,contact,address);
        }

        public double getMinimumBalance(){
            return this.minimumBalance;
        }

        public void setMinimumBalance(double minimumBalance){
            this.minimumBalance = minimumBalance;
        }
        @Override
        Customer displayCustomerDetails(){
            return this;
        }

        @Override
        String getCustomerType(){
            return "Premium";
        }

        public boolean hasWaivedFees(){
            return true;
        }
}