package guru.springgramework.sfgpetclinic.repositories;

import guru.springgramework.sfgpetclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;


public interface SpecialtyRepository extends CrudRepository<Speciality, Long> {
}
