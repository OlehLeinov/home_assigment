package no.itera.assignment.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class EmploymentTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String name;

    public EmploymentTypeEntity() {
    }

    public EmploymentTypeEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmploymentTypeEntity)) return false;
        EmploymentTypeEntity that = (EmploymentTypeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "EmploymentTypeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
