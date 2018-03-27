package com.graphql.example.graphqlexample.respository;

import com.graphql.example.graphqlexample.model.MongoBook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepo extends MongoRepository<MongoBook,String> {


}
