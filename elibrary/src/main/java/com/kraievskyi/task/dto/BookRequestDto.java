package com.kraievskyi.task.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode
@Document
public class BookRequestDto {
    private String title;
    private String author;
    private String text;
}
