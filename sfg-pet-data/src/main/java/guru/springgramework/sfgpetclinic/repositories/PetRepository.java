package guru.springgramework.sfgpetclinic.repositories;

import guru.springgramework.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
