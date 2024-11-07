// VetController.java
package com.tecsup.petclinic.controllers;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.repositories.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vets")
public class VetController {

    @Autowired
    private VetRepository vetRepository;



    // crea
    @PostMapping
    public Vet createVet(@RequestBody Vet vet) {
        return vetRepository.save(vet);
    }



}