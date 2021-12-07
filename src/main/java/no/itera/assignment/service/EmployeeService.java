package no.itera.assignment.service;

import no.itera.assignment.dto.EmployeeDto;
import no.itera.assignment.entity.EmployeeEntity;
import no.itera.assignment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto fetchEmployeeByPersonId(Integer personId) {
        return employeeRepository.findByIdPersonId(personId).map(EmployeeEntity::map).orElse(null);
    }

    public List<EmployeeDto> fetchAllActiveEmployees() {
        return employeeRepository.findByEndDateIsNull().stream().map(EmployeeEntity::map).collect(Collectors.toList());
    }

    public Map<String, List<EmployeeDto>> fetchActiveEmployeesByDepartment() {
        return employeeRepository.findByEndDateIsNull().stream().map(EmployeeEntity::map).collect(Collectors.groupingBy(EmployeeDto::getDepartmentName));
    }
}
