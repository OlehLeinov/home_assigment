package no.itera.assignment.controller;

import no.itera.assignment.dto.EmployeeDto;
import no.itera.assignment.entity.EmployeeEntity;
import no.itera.assignment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * REST API controller for {@link EmployeeEntity}
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Finds one employee by specified person ID
     *
     * @param personId id of the person (unique)
     * @return {@link EmployeeDto}
     */
    @GetMapping("/{personId}")
    public EmployeeDto fetchEmployeeByPersonId(@PathVariable Integer personId) {
        try {
            return employeeService.fetchEmployeeByPersonId(personId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Finds all active employees
     * <p>
     * (active employee has empty end date)
     *
     * @return List of {@link EmployeeDto}
     */
    @GetMapping("/active")
    public List<EmployeeDto> fetchAllActiveEmployees() {
        return employeeService.fetchAllActiveEmployees();
    }

    /**
     * Finds all active employees and group them by department name
     * <p>
     * (active employee has empty end date)
     *
     * @return Map of {@link EmployeeDto} grouped by department name
     */
    @GetMapping("/active/by-department")
    public Map<String, List<EmployeeDto>> fetchActiveEmployeesByDepartment() {
        return employeeService.fetchActiveEmployeesByDepartment();
    }
}
