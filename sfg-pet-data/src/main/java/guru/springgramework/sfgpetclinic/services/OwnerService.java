package guru.springgramework.sfgpetclinic.services;

import guru.springgramework.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLstName(String lastName);
    List<Owner> findAllByLastNameLike(String lastName);
}
