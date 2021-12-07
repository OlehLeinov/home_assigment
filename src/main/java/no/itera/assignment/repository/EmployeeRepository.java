package no.itera.assignment.repository;

import no.itera.assignment.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for {@link EmployeeEntity}
 *
 * @implNote entity has composite primary key (personId and departmentId)
 */
@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {

    /**
     * Find one {@link EmployeeEntity} by specified person id
     *
     * @param personId id of the person
     * @return Optional entity
     * @implNote personId is unique
     */
    Optional<EmployeeEntity> findByIdPersonId(Integer personId);

    /**
     * Find all active {@link EmployeeEntity}'s
     *
     * @return List of entities
     * @implNote active employee doesn't have end date set
     */
    List<EmployeeEntity> findByEndDateIsNull();
}
