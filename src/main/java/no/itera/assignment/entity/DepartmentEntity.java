package no.itera.assignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class DepartmentEntity extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String name;

    public DepartmentEntity() {
    }

    public DepartmentEntity(String name) {
        this.name = name;
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
        if (!(o instanceof DepartmentEntity)) return false;
        if (!super.equals(o)) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
