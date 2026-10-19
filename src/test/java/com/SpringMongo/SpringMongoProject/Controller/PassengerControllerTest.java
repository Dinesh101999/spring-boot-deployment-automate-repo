package com.SpringMongo.SpringMongoProject.Controller;

import com.SpringMongo.SpringMongoProject.Model.Passenger;
import com.SpringMongo.SpringMongoProject.Repository.PassengerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PassengerController.class)
public class PassengerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PassengerRepository passengerRepository;

    // Test for POST /addPassenger
    @Test
    public void testAddPassenger() throws Exception {
        var passenger = new Passenger("1", "John", "Doe", "Male", 30);

        Mockito.when(passengerRepository.save(Mockito.any(Passenger.class))).thenReturn(passenger);

        mockMvc.perform(post("/addPassenger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                         {
                           "passengerID": "1",
                           "firstName": "John",
                           "lastName": "Doe",
                           "gender": "Male",
                           "age": 30
                         }
                         """))
                .andExpect(status().isOk());

        verify(passengerRepository, times(1)).save(Mockito.any(Passenger.class));
    }

    // Test for GET /getPassenger/{PassengerID}
    @Test
    public void testGetAllPassengers() throws Exception {
        var passengers = List.of(
                new Passenger("1", "John", "Doe", "Male", 30),
                new Passenger("2", "Jane", "Smith", "Female", 25)
        );

        Mockito.when(passengerRepository.findAll()).thenReturn(passengers);

        mockMvc.perform(get("/getPassenger/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));

        verify(passengerRepository, times(1)).findAll();
    }

    // Test for GET /getPassengerByFirstName/{firstName}
    @Test
    public void testGetPassengerByFirstName() throws Exception {
        var passengers = List.of(new Passenger("1", "John", "Doe", "Male", 30));

        Mockito.when(passengerRepository.findByFirstName("John")).thenReturn(passengers);

        mockMvc.perform(get("/getPassengerByFirstName/John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName").value("John"));

        verify(passengerRepository, times(1)).findByFirstName("John");
    }

    // Test for PUT /updatePassenger/{PassengerID}
    @Test
    public void testUpdatePassenger() throws Exception {
        var updatedPassenger = new Passenger("1", "Johnny", "Doe", "Male", 35);

        Mockito.when(passengerRepository.save(Mockito.any(Passenger.class))).thenReturn(updatedPassenger);

        mockMvc.perform(put("/updatePassenger/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                         {
                           "passengerID": "1",
                           "firstName": "Johnny",
                           "lastName": "Doe",
                           "gender": "Male",
                           "age": 35
                         }
                         """))
                .andExpect(status().isOk());

        verify(passengerRepository, times(1)).save(Mockito.any(Passenger.class));
    }

    // Test for DELETE /deletePassenger/{PassengerID}
    @Test
    public void testDeletePassenger() throws Exception {
        Mockito.doNothing().when(passengerRepository).deleteById("1");

        mockMvc.perform(delete("/deletePassenger/1"))
                .andExpect(status().isOk());

        verify(passengerRepository, times(1)).deleteById("1");
    }
}
