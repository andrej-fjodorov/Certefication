package DB;

import Model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class EmployeeRepositoryImpl implements  EmployeeRepository {
    private final static String GET_ALL_EMPLOYEES="select*from employee";
    private final static String ADD_NEW_EMPLOYEE="insert into employee (company_id, first_name, last_name, phone) values (?,?,?,?)";
    private final static String GET_LAST_ADDED_EMPLOYEE="select*from employee order by \"id\" desc limit 1";
    private final static String CHANGE_IS_ACTIVE_EMPLOYEE_INFO="update employee set is_active='false' where \"id\"=?";
    private final static String DELETE_EMPLOYEE_BY_NAME="delete from employee where \"first_name\"=?";
    @Override
    public List<Employee> getAll() throws SQLException {
        String dburl = "jdbc:postgresql://dpg-cj94hf0eba7s73bdki80-a.oregon-postgres.render.com/x_clients_db_r06g";
        String user = "x_clients_db_r06g_user";
        String password = "0R1RNWXMepS7mrvcKRThRi82GtJ2Ob58";
        Connection connection = getConnection(dburl, user, password);
        ResultSet resultSet = connection.createStatement().executeQuery(GET_ALL_EMPLOYEES);
        List<Employee> list = new ArrayList<>();
        while (resultSet.next()) {
            Employee employee = new Employee(id, "first_name","last_name","last_name","company_id","phone");
            employee.setId(resultSet.getInt("Id"));
            employee.setFirstName(resultSet.getString("first_name"));
            employee.setLastName(resultSet.getString("last_name"));
            employee.setLastName(resultSet.getString("middle_name"));
            employee.setCompanyId(resultSet.getInt("company_id"));
            employee.setPhone(resultSet.getString("phone"));
            list.add(employee);
        }
        return list;
    }

    @Override
    public Employee getById(int id) {
        return null;
    }


    public int create(int company_id,String first_name, String last_name,String phone) throws SQLException {
        String dburl = "jdbc:postgresql://dpg-cj94hf0eba7s73bdki80-a.oregon-postgres.render.com/x_clients_db_r06g";
        String user = "x_clients_db_r06g_user";
        String password = "0R1RNWXMepS7mrvcKRThRi82GtJ2Ob58";
        Connection connection = getConnection(dburl, user, password);
        PreparedStatement statement=connection.prepareStatement(ADD_NEW_EMPLOYEE);
        statement.setInt(1,company_id);
        statement.setString(2,first_name);
        statement.setString(3,last_name);
        statement.setString(4,phone);
        statement.executeUpdate();
        ResultSet set=connection.createStatement().executeQuery(GET_LAST_ADDED_EMPLOYEE);
        set.next();
        Employee employee=new Employee(id, "first_name","last_name","last_name","company_id","phone");
        employee.setId(set.getInt("id"));
        return employee.getId();

    }


    public int update() {
        return 0;
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String dburl = "jdbc:postgresql://dpg-cj94hf0eba7s73bdki80-a.oregon-postgres.render.com/x_clients_db_r06g";
        String user = "x_clients_db_r06g_user";
        String password = "0R1RNWXMepS7mrvcKRThRi82GtJ2Ob58";
        Connection connection = getConnection(dburl, user, password);
        PreparedStatement statement=connection.prepareStatement(DELETE_EMPLOYEE_BY_NAME);
        statement.setString(1,"first_name");
        int i = statement.executeUpdate();
        System.out.println("deleted employees from employee table:"+i);

    }
}
