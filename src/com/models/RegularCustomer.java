package com.models;

public  class RegularCustomer extends Customer{
        public RegularCustomer(String name,int age, String contact, String address){
            super(name,age,contact,address);
        }
        @Override
        Customer displayCustomerDetails(){
            return this;
        }

        @Override
        String getCustomerType(){
            return "regular";
        }


    }