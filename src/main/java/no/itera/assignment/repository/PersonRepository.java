package no.itera.assignment.repository;

import no.itera.assignment.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {
}
