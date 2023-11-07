package Model;

import java.sql.Timestamp;
import java.util.Objects;

public class Company {
    private int id;
    private String name;
    private String description;
    private boolean isActive;

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id && isActive == company.isActive && Objects.equals(name, company.name) && Objects.equals(description, company.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, isActive);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    public void setDeletedAt(Timestamp deletedAt) {
    }
}
