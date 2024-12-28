package org.bimsara.SpringBookShopApp.Model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "customer_order")
public class Order extends BaseEntity<String> {

    private LocalDateTime orderDate;
    private LocalDateTime orderDeliveredDate;
    private LocalDateTime expectedDeliveredDate;
    private String orderStatus;

    public Order(){

    }

    public Order(LocalDateTime orderDate, LocalDateTime orderDeliveredDate,LocalDateTime expectedDeliveredDate,String orderStatus){
        this.orderDate=orderDate;
        this.orderDeliveredDate=orderDeliveredDate;
        this.expectedDeliveredDate=expectedDeliveredDate;
        this.orderStatus=orderStatus;
    }
    //Many Orders belong to One Customer
    @ManyToOne
    @JoinColumn(name="customer_id",referencedColumnName = "Id")
    private Customer customer;
    @ManyToMany
    @JoinTable(
            name="order_book",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name="book_id")
    )
    Set<Book> books=new HashSet<>();
    /**
     * Generates a time-based unique ID before persisting the entity.
     * Format: ORD_yyyyMMddHHmmssSSS
     */
    @PrePersist
    private void generateIdForOrder(){
     DateTimeFormatter formtter=DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
     String timeStamp=LocalDateTime.now().format(formtter);
     this.id="ORD_"+timeStamp;
     this.orderDate=LocalDateTime.now();//Automatically set order date
    }





    /*Getters and Setters for books field or parameter*/
    public Set<Book> getBooks(){
        return books;
    }

    public void setBooks(Set<Book> newBooks){
        this.books=newBooks;
    }

    /*Getters and Setters for customer field name or property*/
    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer newCustomer){
        this.customer=newCustomer;
    }

    public String getId(){
        return id;
    }
}
