package no.itera.assignment.service;

import no.itera.assignment.dto.EmployeeDto;
import no.itera.assignment.entity.DepartmentEntity;
import no.itera.assignment.entity.EmployeeEntity;
import no.itera.assignment.entity.EmploymentTypeEntity;
import no.itera.assignment.entity.PersonEntity;
import no.itera.assignment.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeServiceTest {

    private EmployeeService sut;

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeEntity employee1, employee2, employee3, employeeInactive;
    private PersonEntity person1, person2, person3;
    private DepartmentEntity department1, department2;
    private EmploymentTypeEntity employmentType;

    @BeforeEach
    void setUp() {
        sut = new EmployeeService(employeeRepository);

        prepareData();
    }

    private void prepareData() {
        person1 = new PersonEntity(1, "person_name1", 1);
        person2 = new PersonEntity(2, "person_name2", 1);
        person3 = new PersonEntity(3, "person_name3", 1);

        department1 = new DepartmentEntity(1, "department_name1");
        department2 = new DepartmentEntity(2, "department_name2");

        employmentType = new EmploymentTypeEntity(1, "employment_type");

        employee1 = new EmployeeEntity(person1, department1, employmentType, Instant.now(), null);
        employee2 = new EmployeeEntity(person2, department1, employmentType, Instant.now(), null);
        employee3 = new EmployeeEntity(person3, department2, employmentType, Instant.now(), null);

        // inactive employee, same person but different department
        employeeInactive = new EmployeeEntity(person1, department2, employmentType, Instant.now(), Instant.now().plus(24, ChronoUnit.HOURS));
    }

    @DisplayName("should retrieve single employee by ID and map to DTO")
    @Test
    void testFetchingEmployeeByPersonId() {
        when(employeeRepository.findByIdPersonId(1)).thenReturn(Optional.ofNullable(employee1));

        EmployeeDto result = sut.fetchEmployeeByPersonId(1);

        verify(employeeRepository).findByIdPersonId(1);

        assertTrue(result.isActive());
        assertEquals(person1.getName(), result.getName());
        assertEquals(person1.getAge(), result.getAge());
        assertEquals(department1.getName(), result.getDepartmentName());
        assertEquals(employee1.getStartDate(), result.getStartDate());
    }

    @DisplayName("given no person should retrieve NoSuchElementException")
    @Test
    void testGivenNoPersonWhenFetchingEmployeeByPersonId() {
        when(employeeRepository.findByIdPersonId(1)).thenReturn(Optional.empty());

        try {
            EmployeeDto result = sut.fetchEmployeeByPersonId(1);
            fail("Expected NoSuchElementException");
        } catch (Exception e) {
            assertEquals(NoSuchElementException.class, e.getClass());
            assertEquals("Employee with person ID=1 doesn't exist.", e.getMessage());
        }

        verify(employeeRepository).findByIdPersonId(1);
    }

    @DisplayName("should retrieve all active employees")
    @Test
    void testFetchingAllActiveEmployees() {
        when(employeeRepository.findByEndDateIsNull()).thenReturn(List.of(employee1, employee2, employee3));

        List<EmployeeDto> result = sut.fetchAllActiveEmployees();

        verify(employeeRepository).findByEndDateIsNull();

        assertEquals(3, result.size());
        assertFalse(result.stream().anyMatch(item -> item.getEndDate() == employeeInactive.getEndDate()));
    }

    @DisplayName("should retrieve all active employees grouped by department")
    @Test
    void testFetchingActiveEmployeesByDepartment() {
        when(employeeRepository.findByEndDateIsNull()).thenReturn(List.of(employee1, employee2, employee3));

        Map<String, List<EmployeeDto>> result = sut.fetchActiveEmployeesByDepartment();

        verify(employeeRepository).findByEndDateIsNull();

        assertEquals(2, result.size());
        assertEquals(2, result.get(department1.getName()).size());
        assertEquals(1, result.get(department2.getName()).size());
    }
}
