package no.itera.assignment.repository;

import no.itera.assignment.entity.EmploymentTypeEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmploymentTypeRepository extends CrudRepository<EmploymentTypeEntity, Integer> {
}
