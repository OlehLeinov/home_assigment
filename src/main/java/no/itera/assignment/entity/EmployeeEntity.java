package no.itera.assignment.entity;

import no.itera.assignment.dto.EmployeeDto;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.Instant;
import java.util.Objects;

@Entity
public class EmployeeEntity extends BaseEntity {

    @OneToOne
    private DepartmentEntity department;

    @OneToOne
    private EmploymentTypeEntity employmentType;

    @OneToOne
    private PersonEntity person;

    private Instant startDate;
    private Instant endDate;

    public EmployeeEntity() {
    }

    public EmployeeEntity(DepartmentEntity department, EmploymentTypeEntity employmentType, PersonEntity person, Instant startDate, Instant endDate) {
        this.department = department;
        this.employmentType = employmentType;
        this.person = person;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public EmployeeDto map() {
        return new EmployeeDto(person.getName(), person.getAge(), department.getName(), startDate, endDate);
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public EmploymentTypeEntity getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentTypeEntity employmentType) {
        this.employmentType = employmentType;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeEntity)) return false;
        if (!super.equals(o)) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(department, that.department) && Objects.equals(employmentType, that.employmentType) && Objects.equals(person, that.person) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department, employmentType, person, startDate, endDate);
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "department=" + department +
                ", employmentType=" + employmentType +
                ", person=" + person +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
