package no.itera.assignment.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {
    private static final Logger log = LoggerFactory.getLogger(EmployeeRepository.class);

    public void fetchEmployeeByPersonId(Integer personId) {
        log.trace("retrieving employee with personId={}", personId);
    }
}
