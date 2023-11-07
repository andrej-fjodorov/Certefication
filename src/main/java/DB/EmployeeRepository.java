package DB;

import Model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAll() throws SQLException;

    Employee getById(int id);

    int create(Employee employee);

    int update(Employee e);

    void deleteById(int id) throws SQLException;
}
