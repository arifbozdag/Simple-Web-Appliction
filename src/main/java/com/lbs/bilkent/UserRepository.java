package com.lbs.bilkent;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername (String username); //after findBy make first letter capital and rest the same
}
