package org.bimsara.SpringBookShopApp.service;
import org.bimsara.SpringBookShopApp.Model.Book;
import org.bimsara.SpringBookShopApp.controller.BookController;
import org.bimsara.SpringBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    private static final Logger log = LoggerFactory.getLogger(BookService.class);
    /**
     * save a single book to the database
     * @param book The Book object to save
     */
    public Book saveBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book object cannot be null");
        }
        log.info("Saving book: {}", book);
        return bookRepository.save(book);
    }


    /*Save multiple books to the database
     *@param books Set of Book objects to save
     * @return Set of saved Book objects.
     * saveALL works with Iterable,so Set can be passed directly.
     * The method returns a List of saved entities, not a Set
     * if you need a Set as the return type ,wrap the result with new HashSet<>
     */
    public List<Book> saveBooks(List<Book> books){
        return bookRepository.saveAll(books);
    }

    public Optional<List<Book>> getBookByIsbn(String isbn){
        List<Book> resultIsbn=bookRepository.findByIsbn(isbn);
        return Optional.ofNullable(resultIsbn) ;
    }

    public Optional<List<Book>> getBookByTitle(String title){
        List<Book> resultTitle=bookRepository.findByBookTitle(title);
        return  Optional.ofNullable(resultTitle);
    }

}
