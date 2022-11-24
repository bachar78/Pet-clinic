package guru.springgramework.sfgpetclinic.services.map;

import guru.springgramework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {
    OwnerServiceMap ownerServiceMap;
    Long ownerId = 1L;
    String lastName = "Bachar";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);
        assertEquals(owner.getId(), ownerId);
    }

    @Test
    void saveExistingId() {
        final Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        ownerServiceMap.save(owner2);
        assertEquals(2L, owner2.getId());
    }

    @Test
    void saveNotExistingId() {
        Owner ownerSaved = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(ownerSaved);
        assertNotNull(ownerSaved.getId());
    }

    @Test
    void save() {
        Owner owner2 = Owner.builder().id(2L).build();
        ownerServiceMap.save(owner2);
        assertEquals(2, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertNull(ownerServiceMap.findById(ownerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertNull(ownerServiceMap.findById(ownerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLstName() {
        Owner findOwner = ownerServiceMap.findById(ownerId);
        Owner bachar = ownerServiceMap.findByLstName(lastName);
        assertNotNull(bachar);
        assertEquals(findOwner, bachar);
    }

    @Test
    void findByLstNameNotFound() {
        Owner smith = ownerServiceMap.findByLstName("smith");
        assertNull(smith);
    }
}