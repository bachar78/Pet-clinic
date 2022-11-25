package guru.springgramework.sfgpetclinic.services.springdatajpa;

import guru.springgramework.sfgpetclinic.model.Owner;
import guru.springgramework.sfgpetclinic.repositories.OwnerRepository;
import guru.springgramework.sfgpetclinic.repositories.PetRepository;
import guru.springgramework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDjpaServiceTest {
    public static final String LAST_NAME = "Smith";
    public static final long ID = 3L;
    Owner owner;

    @InjectMocks
    OwnerSDjpaService service;

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;


    @BeforeEach
    void setUp() {

    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());
        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> ownersService = service.findAll();
        assertNotNull(ownersService);
        assertEquals(2, ownersService.size());
    }

    @Test
    void findById() {
        Owner owner = Owner.builder().id(ID).lastName(LAST_NAME).build();
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));
        Owner findOwner = service.findById(ID);
        assertEquals(owner.getId(), findOwner.getId());
        assertEquals(owner.getLastName(), findOwner.getLastName());
        assertNotNull(findOwner);
        verify(ownerRepository, times(1)).findById(any());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner ownerService = service.findById(anyLong());
        assertNull(ownerService);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).lastName("bachar").build();
        when(ownerRepository.save(ownerToSave)).thenReturn(ownerToSave);
        Owner ownerService = service.save(ownerToSave);
        assertNotNull(ownerService);
        assertEquals(ownerService.getLastName(), ownerToSave.getLastName());
        verify(ownerRepository, times(1)).save(any());
    }

    @Test
    void delete() {
        Owner ownerToDelete = Owner.builder().id(3L).lastName("Daowd").build();
        service.delete(ownerToDelete);
        assertNull(service.findById(3L));
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        Owner ownerToDeleteById = Owner.builder().id(3L).lastName("Daowd").build();
        service.deleteById(ownerToDeleteById.getId());
        assertNull(service.findById(ownerToDeleteById.getId()));
        verify(ownerRepository, times(1)).deleteById(any());

    }

    @Test
    void findByLstName() {
        Owner returnOwner = Owner.builder().lastName(LAST_NAME).build();
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
        Owner smith = service.findByLstName(LAST_NAME);
        assertEquals(LAST_NAME, smith.getLastName());
        verify(ownerRepository, times(1)).findByLastName(any());
    }
}