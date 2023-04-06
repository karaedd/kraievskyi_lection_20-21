package com.kraievskyi.task.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode
@Document
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
}
