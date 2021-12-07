package no.itera.assignment.repository;

import no.itera.assignment.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {

    List<EmployeeEntity> findByEndDateIsNull();
}
