package com.SpringMongo.SpringMongoProject.Repository;

import com.SpringMongo.SpringMongoProject.Model.Passenger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PassengerRepository extends MongoRepository<Passenger,String> {
    List<Passenger> findByFirstName(String firstName);
}
