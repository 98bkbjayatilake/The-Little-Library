package org.bimsara.SpringBookShopApp.service;
import org.bimsara.SpringBookShopApp.Model.Book;
import org.bimsara.SpringBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookService {
    private BookRepository bookRepository;

    //Constructor-based  dependency injection
    @Autowired
    public BookService(BookRepository bookRepository1){
        this.bookRepository=bookRepository1;
    }

    /**
     * save a single book to the database
     * @param book The Book object to save
     */
    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    /*Save multiple books to the database
     *@param books Set of Book objects to save
     * @return Set of saved Book objects.
     * saveALL works with Iterable,so Set can be passed directly.
     * The method returns a List of saved entities, not a Set
     * if you need a Set as the return type ,wrap the result with new HashSet<>
     */
    public Set<Book> saveBooks(Set<Book> books){
        return new HashSet<>(bookRepository.saveAll(books));
    }
}
