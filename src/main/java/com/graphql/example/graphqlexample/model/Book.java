package com.graphql.example.graphqlexample.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    private String isn;
    private String title;
    private String publisher;
    private String[] authors;
    private String publishedDate;

}
