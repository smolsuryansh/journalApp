package com.hammy.journalApp.repository;
import com.hammy.journalApp.entity.JournalEntry;
import com.hammy.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String username);

    void deleteByUserName(String username);
}
