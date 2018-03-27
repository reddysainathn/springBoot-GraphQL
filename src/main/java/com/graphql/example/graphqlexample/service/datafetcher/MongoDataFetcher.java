package com.graphql.example.graphqlexample.service.datafetcher;

import com.graphql.example.graphqlexample.model.MongoBook;
import com.graphql.example.graphqlexample.respository.MongoRepo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MongoDataFetcher implements DataFetcher<MongoBook> {

    @Autowired
    MongoRepo mongoRepo;

    @Override
    public MongoBook get(DataFetchingEnvironment dataFetchingEnvironment) {
        String isn = dataFetchingEnvironment.getArgument("id");
        Optional<MongoBook> byId = mongoRepo.findById(isn);
        return byId.get();
    }
}