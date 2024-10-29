package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    /** Es para buscar por id */
    @Test
    public void testFindVetById() {

        String FIRST_NAME_EXPECTED = "James";

        Integer ID = 1;

        Vet vet = null;

        try {
            vet = this.vetService.findById(ID);
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }
        assertEquals(FIRST_NAME_EXPECTED, vet.getFirstName());
    }

    /** Es para crear una vet */
    @Test
    public void testCreateVet() {

        String FIRST_NAME = "Sarah";
        String LAST_NAME = "Connor";

        Vet vet = new Vet(FIRST_NAME, LAST_NAME);

        Vet vetCreated = this.vetService.create(vet);

        log.info("VET CREATED :" + vetCreated.toString());

        assertNotNull(vetCreated.getId());
        assertEquals(FIRST_NAME, vetCreated.getFirstName());
        assertEquals(LAST_NAME, vetCreated.getLastName());
    }

    /** Es para actualizar un vet */
    @Test
    public void testUpdateVet() {

        String FIRST_NAME = "Michael";
        String LAST_NAME = "Smith";

        String UP_FIRST_NAME = "MichaelUpdated";
        String UP_LAST_NAME = "SmithUpdated";

        Vet vet = new Vet(FIRST_NAME, LAST_NAME);

        log.info(">" + vet);
        Vet vetCreated = this.vetService.create(vet);
        log.info(">>" + vetCreated);

        vetCreated.setFirstName(UP_FIRST_NAME);
        vetCreated.setLastName(UP_LAST_NAME);

        Vet upgradedVet = this.vetService.update(vetCreated);
        log.info(">>>>" + upgradedVet);

        assertEquals(UP_FIRST_NAME, upgradedVet.getFirstName());
        assertEquals(UP_LAST_NAME, upgradedVet.getLastName());
    }

}

