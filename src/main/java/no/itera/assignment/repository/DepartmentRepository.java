package no.itera.assignment.repository;

import no.itera.assignment.entity.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Integer> {
}
