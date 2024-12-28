package org.bimsara.SpringBookShopApp.Model;
import  jakarta.persistence.*;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Inventory extends BaseEntity<String> {
    private int stockQuantity;
    private int reorderLevel;
    private int reorderQuantity;
    private LocalDateTime LastRestockedDate;
    private boolean available;

    public Inventory(){
        //
    }

    public  Inventory(int stockQuantity,int reorderLevel, int reorderQuantity, LocalDateTime LastRestockedDate, Boolean available ){
        this.stockQuantity=stockQuantity;
        this.reorderLevel=reorderLevel;
        this.reorderQuantity=reorderQuantity;
        this.LastRestockedDate=LastRestockedDate;
        this.available=available;
    }

    private static final Set<Integer> generatedNumbers=new HashSet<>();
    private static final SecureRandom random=new SecureRandom();

    /*
    *  In a one-to-one relationship, the use of a single Book object in the Inventory class signifies that
    * each Inventory instance is associated with only one Book instance(Inventory->Book:one to one relationship)
     */
    @OneToOne
    @JoinColumn(name="book_id",referencedColumnName = "id")
    private Book book;
@PrePersist
    private void generateIdForInventory(){
        int randomNumber=generateUniqueRandomNumber();
        this.id="INV_"+randomNumber;
    }
    private int generateUniqueRandomNumber(){
        int randomNumber;
        do{
        randomNumber=0+random.nextInt(50000);// Generate a number in the range 0-50000
        }while (generatedNumbers.contains(randomNumber));
        generatedNumbers.add(randomNumber);
        return randomNumber;
    }



    /*Getters and Setters for book property*/
    public Book getBook(){
         return book;
    }

    public void setBook(Book newBook){
     this.book=newBook;
    }

   public String getId(){
    return  id;
   }
}
