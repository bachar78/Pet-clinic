package guru.springgramework.sfgpetclinic.services;

import guru.springgramework.sfgpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService {
    Owner findByLstName(String lastName);

    Owner findById(Long id);
    Owner save(Owner owner);
    Set<Owner> findAll();
}
