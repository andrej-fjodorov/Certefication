package API;

import Model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getRandomEmployee(int companyId);

    List<Employee> getAll(int companyId);

    Employee getById(int id);

    int create(Employee employee, String token);

    Employee update(Employee employee, String token);
}
