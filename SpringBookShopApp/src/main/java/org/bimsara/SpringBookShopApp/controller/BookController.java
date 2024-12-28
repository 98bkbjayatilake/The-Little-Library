package org.bimsara.SpringBookShopApp.controller;
import org.bimsara.SpringBookShopApp.Model.Book;
import org.bimsara.SpringBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * REST Controller for managing Book resources.
 * Handles HTTP requests related to Book entity.
 * The annotation maps all incoming HTTP requests with the base path /api/books to the methods in controller
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private  BookService bookService;
    @Autowired
    public BookController(BookService bookService1){
        this.bookService=bookService1;
    }



}
