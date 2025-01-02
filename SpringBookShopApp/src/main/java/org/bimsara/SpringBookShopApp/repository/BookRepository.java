package org.bimsara.SpringBookShopApp.repository;
import java.util.List;
import org.bimsara.SpringBookShopApp.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository <Book,String>{

    /*
    *@Param("isbn") annotation is used to link the method argument isbn with the query parameter :isbn.
     This tells Spring that the method argument isbn should be injected into query at the place of :isbn
     *REPLACE(b.isbn, '-', ''= REPLACE(:isbn, '-', '')
     * This will ensure that the hyphens are removed when comparing the ISBN in both the database and the input parameter.
    */
    @Query("SELECT b FROM Book b WHERE REPLACE(b.isbn, '-', '')= REPLACE(:isbn, '-', '')")
    List<Book> findByIsbn(@Param("isbn")String isbn);

    @Query("select b from Book b where b.bookTitle=?1")
    List<Book> findByBookTitle(String title);
}
