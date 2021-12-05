package no.itera.assignment.service;

import no.itera.assignment.dto.EmployeeDto;
import no.itera.assignment.entity.EmployeeEntity;
import no.itera.assignment.repository.DepartmentRepository;
import no.itera.assignment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public EmployeeDto fetchEmployeeByPersonId(Integer personId) {
        return employeeRepository.findById(personId).map(EmployeeEntity::map).orElseThrow();
    }

    public List<EmployeeDto> fetchAllActiveEmployees() {
        return employeeRepository.findAllActive().stream().map(EmployeeEntity::map).collect(Collectors.toList());
    }

    public Map<String, List<EmployeeDto>> fetchActiveEmployeesByDepartment() {
        Map<String, List<EmployeeDto>> activeEmployeesByDepartment = new HashMap<>();

        departmentRepository.findAll().forEach(d -> {
            List<EmployeeDto> employeesByDepartment = employeeRepository.findAllActiveByDepartmentId(d.getId()).stream()
                    .map(EmployeeEntity::map).collect(Collectors.toList());
            activeEmployeesByDepartment.put(d.getName(), employeesByDepartment);
        });

        return activeEmployeesByDepartment;
    }
}
