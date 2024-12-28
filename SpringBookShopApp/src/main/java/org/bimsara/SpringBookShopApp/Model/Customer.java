package org.bimsara.SpringBookShopApp.Model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer extends Person {
    public Customer(){

    }
    public Customer(String NIC, String firstName,String lastName, String phoneNumber,String email){
        super(NIC,firstName,lastName,phoneNumber,email);
    }
    /*inverse side-orders-check the line which is below  after the @mappedBy annotation*/
    //One Customer can have many Orders
    @OneToMany(mappedBy="customer")
    private  Set<Order> orders=new HashSet<>();
    @PrePersist
    private void generateIdForCustomer(){
       generateIdForPerson("CUS");
    }

    /*Getters and Setters for orders field name or property*/

    public Set<Order> getOrders(){
        return orders;
    }

    public void setOrders(Set<Order> newOrders){
      this.orders=newOrders;
    }
    public String getId(){
        return id;
    }
}
