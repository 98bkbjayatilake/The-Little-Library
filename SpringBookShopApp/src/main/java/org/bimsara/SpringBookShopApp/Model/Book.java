package org.bimsara.SpringBookShopApp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
public class Book extends BaseEntity<String>{
    @JsonProperty("isbn")
    @Column(name = "\"isbn\"")
    private String isbn;

    @JsonProperty("bookTitle")  // Map JSON field "bookTitle" to this field
    @Column(name = "\"bookTitle\"")
    private String bookTitle;
    @JsonProperty("unitPrice")
    @Column(name = "\"unitPrice\"")
    private float unitPrice;

    /*
    * authors-Field mean to hold unique objects of type author
    */
    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    Set<Author> authors=new HashSet<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    Set<Order> orders=new HashSet<>();
    @JsonIgnore
    @OneToOne(mappedBy = "book")
    private Inventory inventory;

    public Book(){

    }
    public Book(String ISBN,String bookTitle,float unitPrice){
    this.isbn=ISBN;
    this.bookTitle=bookTitle;
    this.unitPrice=unitPrice;
    }

/*
  *this method is executed automatically by the JPA providerbefore the entity is persisted for the first time or
  updated in the database
 */
   @PrePersist
   private void generateIdForBook(){
    /*
    *title.replaceAll("\\s+","_")
    *\\s-This is a regular expression that matches one or more whitespace characters(spces,tabs,newlines, etc.)
    * .replaceAll-This method replaces all occurrences of the matched pattern(whitespace) with an underscore character(_)
    * The result is that all whitespace in the title is replaced with underscores.
     */
    this.id=isbn+"_"+bookTitle.replaceAll("\\s+","_").substring(0,10);
   }

    /*
    *getter and setter for Authors property
    */
   public Set<Author> getAuthors(){
    return  authors;
   }

   public void setAuthors(Set<Author> newAuthors){
       this.authors=newAuthors;
   }
   /*
   *getter and setter for orders property
   */
   public Set<Order> getOrders(){
       return orders;
   }

   public void setOrders(Set<Order> newOrders){
       this.orders=newOrders;
   }

   /*
   *getters and setters for inventory property
   */
    public Inventory getInventory(){
        return inventory;
    }

    public void setInventory(Inventory newInventory){
        this.inventory=newInventory;
    }

   public String getId(){
       return id;
   }
}
