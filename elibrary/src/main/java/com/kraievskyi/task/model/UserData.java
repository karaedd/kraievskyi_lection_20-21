package com.kraievskyi.task.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserData {
    @Id
    @Indexed(unique = true)
    private String id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private boolean enabled;
    private List<Book> book;
}
