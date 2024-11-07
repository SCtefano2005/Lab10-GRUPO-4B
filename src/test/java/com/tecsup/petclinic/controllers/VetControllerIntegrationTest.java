package com.tecsup.petclinic.controllers;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.repositories.VetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VetControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VetRepository vetRepository;

    @Test
    public void shouldReturnVetById() throws Exception {

        Vet vet = new Vet();
        vet.setFirstName("Dr. Smith");
        vet.setLastName("Doe");
        vet = vetRepository.save(vet);

        mockMvc.perform(get("/api/vets/" + vet.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Dr. Smith"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void shouldCreateVet() throws Exception {
        String newVetJson = "{ \"firstName\": \"Dr. Jane\", \"lastName\": \"Doe\" }";

        mockMvc.perform(post("/api/vets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newVetJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Dr. Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void shouldDeleteVet() throws Exception {
        Vet vet = new Vet();
        vet.setFirstName("Dr. Delete");
        vet.setLastName("Doe");
        vet = vetRepository.save(vet);


        mockMvc.perform(delete("/api/vets/" + vet.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


}