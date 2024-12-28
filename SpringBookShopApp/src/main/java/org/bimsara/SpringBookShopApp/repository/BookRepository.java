package org.bimsara.SpringBookShopApp.repository;

import org.bimsara.SpringBookShopApp.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository <Book,String>{
}
