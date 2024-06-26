package no.itera.assignment.service;

import no.itera.assignment.dto.EmployeeDto;
import no.itera.assignment.entity.EmployeeEntity;
import no.itera.assignment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Service for {@link EmployeeEntity}
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Fetch {@link EmployeeEntity} by personId and map it to {@link EmployeeDto}
     *
     * @param personId id of the person
     * @return {@link EmployeeDto}
     * @throws NoSuchElementException employee not found
     * @implNote personId is unique
     */
    public EmployeeDto fetchEmployeeByPersonId(Integer personId) {
        return employeeRepository.findByIdPersonId(personId).map(this::map)
                .orElseThrow(() -> new NoSuchElementException("Employee with person ID=" + personId + " doesn't exist."));
    }

    /**
     * Fetch all active {@link EmployeeEntity}'s and map them to {@link EmployeeDto}
     *
     * @return List of {@link EmployeeDto}
     * @implNote active employee has end date set to null
     */
    public List<EmployeeDto> fetchAllActiveEmployees() {
        return employeeRepository.findByEndDateIsNull().stream().map(this::map).collect(Collectors.toList());
    }

    /**
     * Fetch all active {@link EmployeeEntity}'s, map them to {@link EmployeeDto} and group by department name
     *
     * @return Map of {@link EmployeeDto} grouped by department name
     * @implNote active employee has end date set to null
     */
    public Map<String, List<EmployeeDto>> fetchActiveEmployeesByDepartment() {
        return employeeRepository.findByEndDateIsNull().stream().map(this::map).collect(Collectors.groupingBy(EmployeeDto::getDepartmentName));
    }

    private EmployeeDto map(EmployeeEntity entity) {
        return new EmployeeDto(entity.getId().getPerson().getName(),
                entity.getId().getPerson().getAge(),
                entity.getId().getDepartment().getName(),
                entity.getStartDate(),
                entity.getEndDate());
    }
}
