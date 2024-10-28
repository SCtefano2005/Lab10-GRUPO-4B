package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OwnerServiceTest {

	@Autowired
	private OwnerService ownerService;

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
		Integer ID = 1;
		String EXPECTED_FIRST_NAME = "George";

		try {
			Owner owner = ownerService.findById(ID);
			assertEquals(EXPECTED_FIRST_NAME, owner.getFirstName());
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testUpdateOwner() {
		String FIRST_NAME = "Tom";
		String LAST_NAME = "Smith";
		String ADDRESS = "456 Oak St";
		String CITY = "Springfield";
		String TELEPHONE = "0987654321";

		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		Owner createdOwner = ownerService.create(owner);

		String UPDATED_FIRST_NAME = "Tommy";
		String UPDATED_CITY = "Shelbyville";

		createdOwner.setFirstName(UPDATED_FIRST_NAME);
		createdOwner.setCity(UPDATED_CITY);

		Owner updatedOwner = ownerService.update(createdOwner);

		assertEquals(UPDATED_FIRST_NAME, updatedOwner.getFirstName());
		assertEquals(UPDATED_CITY, updatedOwner.getCity());
		assertEquals(LAST_NAME, updatedOwner.getLastName());
		assertEquals(ADDRESS, updatedOwner.getAddress());
		assertEquals(TELEPHONE, updatedOwner.getTelephone());
	}

	@Test
	public void testDeleteOwner() {
		String FIRST_NAME = "Jake";
		String LAST_NAME = "Doe";
		String ADDRESS = "789 Pine St";
		String CITY = "Springfield";
		String TELEPHONE = "1122334455";

		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		Owner createdOwner = ownerService.create(owner);

		try {
			ownerService.delete(createdOwner.getId());
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}

		try {
			ownerService.findById(createdOwner.getId());
			fail("Owner should have been deleted");
		} catch (OwnerNotFoundException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testFindOwnerByFirstName() {
		String FIRST_NAME = "Jeff";
		int EXPECTED_SIZE = 1;

		List<Owner> owners = ownerService.findByFirstName(FIRST_NAME);
		assertEquals(EXPECTED_SIZE, owners.size());
	}
}
