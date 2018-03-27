package com.graphql.example.graphqlexample.service.datafetcher;

import com.graphql.example.graphqlexample.model.MongoBook;
import com.graphql.example.graphqlexample.respository.MongoRepo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoAllBooksDataFetcher implements DataFetcher<List<MongoBook>> {
    private static final org.slf4j.Logger logger =  LoggerFactory.getLogger(MongoAllBooksDataFetcher.class);
    @Autowired
    MongoRepo mongoRepo;

    @Override
    public List<MongoBook> get(DataFetchingEnvironment dataFetchingEnvironment) {
        logger.info(mongoRepo.findAll().toString());
        return mongoRepo.findAll();
    }
}
