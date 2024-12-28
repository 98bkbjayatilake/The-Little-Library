package org.bimsara.SpringBookShopApp.Model;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;
@Entity
public class Book extends BaseEntity<String>{
    private int ISBN;
    private String title;
    private float unitPrice;

    /*
    * authors-Field mean to hold unique objects of type author
    */
    @ManyToMany(mappedBy = "books")
    Set<Author> authors=new HashSet<>();
    @ManyToMany(mappedBy = "books")
    Set<Order> orders=new HashSet<>();

    @OneToOne(mappedBy = "book")
    private Inventory inventory;

    public Book(){

    }
    public Book(int ISBN,String title){
    this.ISBN=ISBN;
    this.title=title;
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
    this.id=ISBN+"_"+title.replaceAll("\\s+","_").substring(0,10);
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
