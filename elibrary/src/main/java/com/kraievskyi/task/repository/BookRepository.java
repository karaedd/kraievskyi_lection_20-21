package com.kraievskyi.task.repository;

import com.kraievskyi.task.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}
