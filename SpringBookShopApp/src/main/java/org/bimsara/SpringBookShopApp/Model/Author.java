package org.bimsara.SpringBookShopApp.Model;
import  jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Author extends Person {
    public Author(){
    }
    public Author(String NIC, String firstName,String lastName, String phoneNumber,String email){
     super(NIC,firstName,lastName,phoneNumber,email);
    }
@ManyToMany
@JoinTable(
        name = "author_book",
        joinColumns = @JoinColumn(name = "author_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
        )
    private Set<Book> books=new HashSet<>();

    @PrePersist
    private void generateIdForAuthor(){
        super.generateIdForPerson("AUT");
    }

    public Set<Book> getBooks(){
        return books;
    }

    public void setBooks(Set<Book> newBooks){
        this.books=newBooks;
    }

    public String getId(){
        return id;
    }
}


