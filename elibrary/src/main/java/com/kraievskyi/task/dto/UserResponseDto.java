package com.kraievskyi.task.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Document
public class UserResponseDto {
    private String id;
    private String name;
    private String role;
    private List<String> book;
}
