package org.bimsara.SpringBookShopApp.controller;

import org.bimsara.SpringBookShopApp.Model.Book;
import org.bimsara.SpringBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private BookService bookService;


    @GetMapping("/getBookIdByIsbn")
    public ResponseEntity<?> getByIsbn(@RequestParam String isbn) {
        if(isbn!=null)
        {isbn = isbn.trim();// Removes leading and trailing spaces
        isbn = isbn.replaceAll("[\\n\\r]", "");// Removes any newline or carriage return characters
        }
        Optional<List<Book>> book_result=bookService.getBookByIsbn(isbn);
        /*isPresent()- ensuring that Optional has a non-null value*/
        if(book_result.isPresent() && !book_result.get().isEmpty()) {
            //If books are found,check if there is exactly one book
            if (book_result.get().size()==1) {
                /*book_result.get()-access to the List<Book> inside the Optional object*/
                Book book = book_result.get().get(0);
                return ResponseEntity.ok(book.getId());
            } else {
                //if multiple books are found (due to partial ISBN match), return the list of books
                return ResponseEntity.ok(book_result.get()) ;
            }
        }else {
            //IF no books are found, return 400 status
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No books found for given ISBN");
        }
    }

}
