package com.graphql.example.graphqlexample.service;

import com.graphql.example.graphqlexample.model.MongoBook;
import com.graphql.example.graphqlexample.respository.MongoRepo;
import com.graphql.example.graphqlexample.service.datafetcher.MongoAllBooksDataFetcher;
import com.graphql.example.graphqlexample.service.datafetcher.MongoDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class GraphQLService {

    @Value("classpath:books.graphql")
    Resource resource;

    @Autowired
    MongoRepo mongoRepo;

    private GraphQL graphQL;

    @Autowired
    private MongoAllBooksDataFetcher mongoAllBooksDataFetcher;
    @Autowired
    private MongoDataFetcher mongoBookDataFetcher;

    @PostConstruct
    private void loadSchema() throws IOException {
        loadDataIntoMongo();
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry definitionRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(definitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private void loadDataIntoMongo() {
        Stream.of(
                new MongoBook("123", "Book of Clouds", "Kindle Edition",
                        new String[]{
                                "Chloe Aridjis"
                        }, "Nov 2017"),
                new MongoBook("124", "Cloud Arch & Engineering", "Orielly",
                        new String[]{
                                "Peter", "Sam"
                        }, "Jan 2015"),
                new MongoBook("125", "Java 9 Programming", "Orielly",
                        new String[]{
                                "Venkat", "Ram"
                        }, "Dec 2016")
        ).forEach(book -> {
            mongoRepo.insert(book);
        });
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("mongoAllBooks", mongoAllBooksDataFetcher)
                        .dataFetcher("mongoBook", mongoBookDataFetcher)
                )
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }

}
