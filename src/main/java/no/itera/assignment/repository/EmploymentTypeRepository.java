package no.itera.assignment.repository;

import no.itera.assignment.entity.EmploymentTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentTypeRepository extends CrudRepository<EmploymentTypeEntity, Integer> {
}
