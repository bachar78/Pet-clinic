package guru.springgramework.sfgpetclinic.services;

import guru.springgramework.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLstName(String lastName);
}
