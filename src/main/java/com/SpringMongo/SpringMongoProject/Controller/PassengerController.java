package com.SpringMongo.SpringMongoProject.Controller;

import com.SpringMongo.SpringMongoProject.Model.Passenger;
import com.SpringMongo.SpringMongoProject.Repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PassengerController {
    @Autowired
    PassengerRepository passengerRepository;

    @PostMapping("/addPassenger")
    public void addPassenger(@RequestBody Passenger passenger) {
        passengerRepository.save(passenger);
    }

    @GetMapping("/getPassenger/{PassengerID}")
    public List<Passenger> getPassenger() {
        return passengerRepository.findAll();
    }

    @GetMapping("/getPassengerByFirstName/{firstName}")
    public List<Passenger> getPassengerFirstName(@PathVariable("firstName") String firstName) {
        return passengerRepository.findByFirstName(firstName);
    }

    @PutMapping("/updatePassenger/{PassengerID}")
    public void updatePassenger(@RequestBody Passenger passenger, @PathVariable("PassengerID") String PassengerID) {
        passenger.setPassengerID(PassengerID);
        passengerRepository.save(passenger);
    }

    @DeleteMapping("/deletePassenger/{PassengerID}")
    public void deletePassenger(@PathVariable("PassengerID") String PassengerID) {
        passengerRepository.deleteById(PassengerID);
    }
}
