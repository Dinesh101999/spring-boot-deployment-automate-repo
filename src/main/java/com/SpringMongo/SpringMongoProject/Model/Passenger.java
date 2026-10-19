package com.SpringMongo.SpringMongoProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
    @Id
    private String passengerID;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;

}
