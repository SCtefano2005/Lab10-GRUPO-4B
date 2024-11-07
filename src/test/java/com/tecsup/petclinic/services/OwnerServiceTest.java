package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OwnerServiceTest {

	@Autowired
	private OwnerService ownerService;

	private Owner savedOwner;

	@BeforeEach
	public void setup() {
		// Crea un Owner para las pruebas que requieran datos en la base de datosaaa
		String FIRST_NAME = "George";
		String LAST_NAME = "Smith";
		String ADDRESS = "456 Oak St";
		String CITY = "Springfield";
		String TELEPHONE = "1234567890";

		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		savedOwner = ownerService.create(owner);  // Guardamos este owner para usarlo en las pruebas
	}

	@Test
	public void testCreateOwner() {
		String FIRST_NAME = "Alice";
		String LAST_NAME = "Johnson";
		String ADDRESS = "123 Main St";
		String CITY = "Springfield";
		String TELEPHONE = "1234567890";

		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		Owner createdOwner = ownerService.create(owner);

		assertNotNull(createdOwner.getId());
		assertEquals(FIRST_NAME, createdOwner.getFirstName());
		assertEquals(LAST_NAME, createdOwner.getLastName());
		assertEquals(ADDRESS, createdOwner.getAddress());
		assertEquals(CITY, createdOwner.getCity());
		assertEquals(TELEPHONE, createdOwner.getTelephone());
	}

	@Test
	public void testFindOwnerById() {
		try {
			Owner owner = ownerService.findById(savedOwner.getId());
			assertEquals(savedOwner.getFirstName(), owner.getFirstName());
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testUpdateOwner() {
		String UPDATED_FIRST_NAME = "Tommy";
		String UPDATED_CITY = "Shelbyville";

		savedOwner.setFirstName(UPDATED_FIRST_NAME);
		savedOwner.setCity(UPDATED_CITY);

		Owner updatedOwner = ownerService.update(savedOwner);

		assertEquals(UPDATED_FIRST_NAME, updatedOwner.getFirstName());
		assertEquals(UPDATED_CITY, updatedOwner.getCity());
		assertEquals(savedOwner.getLastName(), updatedOwner.getLastName());
		assertEquals(savedOwner.getAddress(), updatedOwner.getAddress());
		assertEquals(savedOwner.getTelephone(), updatedOwner.getTelephone());
	}

	@Test
	public void testDeleteOwner() {
		try {
			ownerService.delete(savedOwner.getId());
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}

		try {
			ownerService.findById(savedOwner.getId());
			fail("Owner should have been deleted");
		} catch (OwnerNotFoundException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testFindOwnerByFirstName() {
		String FIRST_NAME = savedOwner.getFirstName();

		List<Owner> owners = ownerService.findByFirstName(FIRST_NAME);
		assertTrue(owners.size() > 0, "Se esperaba al menos un Owner con el nombre: " + FIRST_NAME);
	}
}
