package com.hammy.journalApp.repository;

import com.hammy.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRespositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA() {
        Query query = new Query();
        Criteria criteria = new Criteria();

        query.addCriteria(criteria.andOperator(Criteria.where("email").regex("[A-Za-z0-9\\._%+\\-]+@[A-Za-z0-9\\.\\-]+\\.[A-Za-z]{2,}"),
                Criteria.where("sentimentAnalysis").is(true))

        );

        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
