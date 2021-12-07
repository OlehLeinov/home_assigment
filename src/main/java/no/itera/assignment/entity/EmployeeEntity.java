package no.itera.assignment.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @EmbeddedId
    private EmployeeCPK id;

    @OneToOne
    private EmploymentTypeEntity employmentType;

    @Column(name = "start_date", nullable = false)
    private Instant startDate;
    @Column(name = "end_date")
    private Instant endDate;

    public EmployeeEntity() {
    }

    public EmployeeEntity(PersonEntity person, DepartmentEntity department, EmploymentTypeEntity employmentType, Instant startDate, Instant endDate) {
        this.id = new EmployeeCPK(person, department);
        this.employmentType = employmentType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public EmployeeCPK getId() {
        return id;
    }

    public void setId(EmployeeCPK id) {
        this.id = id;
    }

    public EmploymentTypeEntity getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentTypeEntity employmentType) {
        this.employmentType = employmentType;
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
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(employmentType, that.employmentType) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employmentType, startDate, endDate);
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", employmentType=" + employmentType +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
