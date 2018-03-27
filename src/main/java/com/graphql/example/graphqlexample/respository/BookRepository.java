package com.graphql.example.graphqlexample.respository;

import com.graphql.example.graphqlexample.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
