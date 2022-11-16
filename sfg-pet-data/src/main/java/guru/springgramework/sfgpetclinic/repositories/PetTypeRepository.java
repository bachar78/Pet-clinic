package guru.springgramework.sfgpetclinic.repositories;

import guru.springgramework.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
