package com.graphql.example.graphqlexample.service.datafetcher;

import com.graphql.example.graphqlexample.model.Book;
import com.graphql.example.graphqlexample.respository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDataFetcher implements DataFetcher<Book> {
    private static final org.slf4j.Logger logger =  LoggerFactory.getLogger(BookDataFetcher.class);
    @Autowired
    BookRepository bookRepository;

    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
        logger.info("Here");
        String isn = dataFetchingEnvironment.getArgument("id");
        logger.info("ISN:"+isn);
        return bookRepository.getOne(isn);
    }
}
