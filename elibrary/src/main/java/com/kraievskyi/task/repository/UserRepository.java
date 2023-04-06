package com.kraievskyi.task.repository;

import com.kraievskyi.task.model.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserData, String> {
    UserData findUserByEmail(String email);
}
