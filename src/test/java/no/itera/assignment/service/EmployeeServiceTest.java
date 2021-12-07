package no.itera.assignment.service;

import no.itera.assignment.dto.EmployeeDto;
import no.itera.assignment.entity.DepartmentEntity;
import no.itera.assignment.entity.EmployeeEntity;
import no.itera.assignment.entity.EmploymentTypeEntity;
import no.itera.assignment.entity.PersonEntity;
import no.itera.assignment.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {
    private EmployeeService sut;
    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeEntity employee1, employee2, employee3, employee4;
    private PersonEntity person1, person2, person3, person4;
    private DepartmentEntity department1, department2;
    private EmploymentTypeEntity employmentType;

    @BeforeEach
    void setUp() {
        sut = new EmployeeService(employeeRepository);

        person1 = new PersonEntity(1, "person_name1", 1);
        person2 = new PersonEntity(2, "person_name2", 1);
        department1 = new DepartmentEntity(1, "department_name");
        department2 = new DepartmentEntity(2, "department_name");
        employmentType = new EmploymentTypeEntity(1, "employment_type");
        employee1 = new EmployeeEntity(person1, department1, employmentType, Instant.now(), null);
        employee2 = new EmployeeEntity(person2, department1, employmentType, Instant.now(), null);
        employee3 = new EmployeeEntity(person3, department1, employmentType, Instant.now(), null);
        employee4 = new EmployeeEntity(person4, department2, employmentType, Instant.now(), null);
    }

    @Test
    void fetchEmployeeByPersonId() {
        when(employeeRepository.findByIdPersonId(1)).thenReturn(Optional.ofNullable(employee1));

        EmployeeDto result = sut.fetchEmployeeByPersonId(1);

        verify(employeeRepository).findByIdPersonId(1);

        assertTrue(result.isActive());
        assertEquals(person1.getName(), result.getName());
        assertEquals(person1.getAge(), result.getAge());
        assertEquals(department1.getName(), result.getDepartmentName());
        assertEquals(employee1.getStartDate(), result.getStartDate());
    }

    @Test
    void fetchAllActiveEmployees() {
        when(employeeRepository.findByEndDateIsNull()).thenReturn(List.of(employee1, employee2, employee3, employee4));

        List<EmployeeDto> result = sut.fetchAllActiveEmployees();

        verify(employeeRepository).findByEndDateIsNull();

        assertEquals(4, result.size());
    }

    @Test
    void fetchActiveEmployeesByDepartment() {
    }
}
