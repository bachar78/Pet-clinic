package guru.springgramework.sfgpetclinic.repositories;

import guru.springgramework.sfgpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;


public interface VisitRepository extends CrudRepository<Visit, Long> {
}
