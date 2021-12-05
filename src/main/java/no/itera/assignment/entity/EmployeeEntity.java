package no.itera.assignment.entity;

import javax.persistence.Entity;
import java.time.Instant;
import java.util.Objects;

@Entity
public class EmployeeEntity extends BaseEntity {

    private String name;
    private Integer age;
    private String departmentName;
    private Instant startDate;
    private Instant endDate;

    public EmployeeEntity() {
    }

    public EmployeeEntity(String name, int age, String departmentName, Instant startDate, Instant endDate) {
        this.name = name;
        this.age = age;
        this.departmentName = departmentName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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
        return age == that.age && Objects.equals(name, that.name) && Objects.equals(departmentName, that.departmentName) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, departmentName, startDate, endDate);
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", departmentName='" + departmentName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
