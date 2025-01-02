package org.bimsara.SpringBookShopApp.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.bimsara.SpringBookShopApp.Model.Book;
import org.bimsara.SpringBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;
import java.util.Optional;

/*
 * REST Controller for managing Book resources.
 * Handles HTTP requests related to Book entity.
 * The annotation maps all incoming HTTP requests with the base path /api/books to the methods in controller
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private  BookService bookService;

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @PostMapping("/createBook")
    public ResponseEntity<?> createBook(@RequestBody Book newBook) {
        log.info("Received book: {}", newBook);
        try {
            if (newBook == null) {
                return ResponseEntity.badRequest().body("Book data is missing"); // More informative response
            }
            Book savedBook = bookService.saveBook(newBook);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        } catch (IllegalArgumentException e) {
            log.error("Invalid book data", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid book data"); // Informative response
        } catch (Exception e) {
            log.error("Error while creating book", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error"); // Informative response
        }
    }

    @PostMapping("/createBooks")
    public ResponseEntity<?> createBooks(@RequestBody List<Book> newBooks){
        try{
            if(newBooks==null){
             return  ResponseEntity.badRequest().body("The collection of books is null");
            }
            List<Book> savedBooks=bookService.saveBooks(newBooks);
            log.info("{} books succesfully saved",savedBooks.size());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBooks);
        }catch(IllegalArgumentException e){
            log.error("Validation failed for one or more books: {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid book data: "+e.getMessage());
        }catch(Exception e){
            log.error("Unexpected error while saving books: {}", e.getMessage());
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error "+e.getMessage());
        }
    }

}


