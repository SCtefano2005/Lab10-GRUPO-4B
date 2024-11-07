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

    // busca
    @GetMapping("/{id}")
    public ResponseEntity<Vet> getVetById(@PathVariable Integer id) {
        Optional<Vet> vet = vetRepository.findById(id);
        return vet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // crea
    @PostMapping
    public Vet createVet(@RequestBody Vet vet) {
        return vetRepository.save(vet);
    }

    // upda
    @PutMapping("/{id}")
    public ResponseEntity<Vet> updateVet(@PathVariable Integer id, @RequestBody Vet vetDetails) {
        Optional<Vet> vet = vetRepository.findById(id);
        if (vet.isPresent()) {
            Vet updatedVet = vet.get();
            updatedVet.setFirstName(vetDetails.getFirstName());
            updatedVet.setLastName(vetDetails.getLastName());
            vetRepository.save(updatedVet);
            return ResponseEntity.ok(updatedVet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
// TO DO
    // dele
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVet(@PathVariable Integer id) {
        if (vetRepository.existsById(id)) {
            vetRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}