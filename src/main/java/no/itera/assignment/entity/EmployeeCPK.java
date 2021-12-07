package no.itera.assignment.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeCPK implements Serializable {

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;
    @OneToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    public EmployeeCPK() {
    }

    public EmployeeCPK(PersonEntity person, DepartmentEntity department) {
        this.person = person;
        this.department = department;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeCPK)) return false;
        EmployeeCPK that = (EmployeeCPK) o;
        return Objects.equals(person, that.person) && Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, department);
    }

    @Override
    public String toString() {
        return "EmployeeCPK{" +
                "person=" + person +
                ", department=" + department +
                '}';
    }
}
