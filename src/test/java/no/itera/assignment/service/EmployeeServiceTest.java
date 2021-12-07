package no.itera.assignment.service;

import no.itera.assignment.entity.EmployeeEntity;
import no.itera.assignment.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {
    private EmployeeService sut;
    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        sut = new EmployeeService(employeeRepository);
    }

    @Test
    void fetchEmployeeByPersonId() {
        EmployeeEntity employee = new EmployeeEntity();
        when(employeeRepository.findByIdPersonId(1)).thenReturn(Optional.ofNullable(employee));
        sut.fetchEmployeeByPersonId(1);
        verify(employeeRepository).findByIdPersonId(1);
    }

    @Test
    void fetchAllActiveEmployees() {
    }

    @Test
    void fetchActiveEmployeesByDepartment() {
    }
}
