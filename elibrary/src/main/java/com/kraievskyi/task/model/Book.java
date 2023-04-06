package com.kraievskyi.task.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode
@Document
public class Book {
    @Id
    @Indexed(unique = true)
    private String id;
    private String title;
    private String author;
    private String text;
}
