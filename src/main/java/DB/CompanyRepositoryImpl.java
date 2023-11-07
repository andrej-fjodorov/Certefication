package DB;

import Model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class CompanyRepositoryImpl implements CompanyRepository {
    private final static String GET_ALL_COMPANY = "select*from company where \"deleted_at\" is null";
    private final static String GET_ALL_ACTIVE_COMPANY = "select*from company where \"is_active\"='true' and \"deleted_at\" is null";
    private final static String ADD_NEW_COMPANY = "insert into company (name, description) values (?,?)";
    private final static String GET_LAST_ADDED_COMPANY = "select*from company where \"deleted_at\" is null order by \"id\" desc limit 1";
    private final static String DELETE_COMPANY = "delete from company where \"id\"=?";
    private final static String GET_COMPANY_BY_ID = "select*from company where \"id\"=?";
    @Override
    public List<Company> getAll() throws SQLException {
        String dburl = "jdbc:postgresql://dpg-cj94hf0eba7s73bdki80-a.oregon-postgres.render.com/x_clients_db_r06g";
        String user = "x_clients_db_r06g_user";
        String password = "0R1RNWXMepS7mrvcKRThRi82GtJ2Ob58";
        Connection connection = getConnection(dburl, user, password);

        ResultSet resultSet = connection.createStatement().executeQuery(GET_ALL_COMPANY);
        List<Company> list = new ArrayList<>();
        while (resultSet.next()) {
            Company company = new Company( id,"name", "description",true);
            company.setId(resultSet.getInt("id"));
            company.setName(resultSet.getString("name"));
            company.setDescription(resultSet.getString("description"));
            company.setDeletedAt(resultSet.getTimestamp("deleted_at"));
            list.add(company);
        }
        return list;
    }




    @Override
    public List<Company> getAll(boolean isActive) throws SQLException {
        String dburl = "jdbc:postgresql://dpg-cj94hf0eba7s73bdki80-a.oregon-postgres.render.com/x_clients_db_r06g";
        String user = "x_clients_db_r06g_user";
        String password = "0R1RNWXMepS7mrvcKRThRi82GtJ2Ob58";
        Connection connection = getConnection(dburl, user, password);
        ResultSet resultSet = connection.createStatement().executeQuery(GET_ALL_ACTIVE_COMPANY);
        List<Company> list = new ArrayList<>();
        while (resultSet.next()) {
            Company company = new Company("id","name", "description",true);
            company.setId(resultSet.getInt("id"));
            company.setActive(resultSet.getBoolean("is_active"));
            company.setName(resultSet.getString("name"));
            company.setDescription(resultSet.getString("description"));
            list.add(company);
        }
        return list;
    }



    @Override
    public Company getLast() {
        return null;
    }

    @Override
    public Company getById(int id) throws SQLException {

        String dburl = "jdbc:postgresql://dpg-cj94hf0eba7s73bdki80-a.oregon-postgres.render.com/x_clients_db_r06g";
        String user = "x_clients_db_r06g_user";
        String password = "0R1RNWXMepS7mrvcKRThRi82GtJ2Ob58";
        Connection connection = getConnection(dburl, user, password);
        PreparedStatement statement = connection.prepareStatement(GET_COMPANY_BY_ID);
        statement.setInt(1, id);
        ResultSet set = statement.executeQuery();
        set.next();
        Company company = new Company(id,"name", "description",true);
        company.setDeletedAt(set.getTimestamp("deleted_at"));
        return company;
    }

    @Override
    public int create(String name) throws SQLException {

        String dburl = "jdbc:postgresql://dpg-cj94hf0eba7s73bdki80-a.oregon-postgres.render.com/x_clients_db_r06g";
        String user = "x_clients_db_r06g_user";
        String password = "0R1RNWXMepS7mrvcKRThRi82GtJ2Ob58";
        Connection connection = getConnection(dburl, user, password);
        PreparedStatement statement = connection.prepareStatement(ADD_NEW_COMPANY);
        statement.setString(1, "name");
        statement.executeUpdate();
        ResultSet resultSet = connection.createStatement().executeQuery(GET_LAST_ADDED_COMPANY);
        resultSet.next();
        Company company = new Company("id");
        company.setId(resultSet.getInt("id"));
        return company.getId();
    }

    @Override
    public int create(String name, String description) throws SQLException {
        String dburl = "jdbc:postgresql://dpg-cj94hf0eba7s73bdki80-a.oregon-postgres.render.com/x_clients_db_r06g";
        String user = "x_clients_db_r06g_user";
        String password = "0R1RNWXMepS7mrvcKRThRi82GtJ2Ob58";
        Connection connection = getConnection(dburl, user, password);
        PreparedStatement statement = connection.prepareStatement(ADD_NEW_COMPANY);
        statement.setString(2, "name");
        statement.setString(3, "description");
        statement.executeUpdate();
        ResultSet resultSet = connection.createStatement().executeQuery(GET_LAST_ADDED_COMPANY);
        resultSet.next();
        Company company = new Company("name", "description");
        company.setId(resultSet.getInt("id"));
        return company.getId();
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String dburl = "jdbc:postgresql://dpg-cj94hf0eba7s73bdki80-a.oregon-postgres.render.com/x_clients_db_r06g";
        String user = "x_clients_db_r06g_user";
        String password = "0R1RNWXMepS7mrvcKRThRi82GtJ2Ob58";
        Connection connection = getConnection(dburl, user, password);
        PreparedStatement statement = connection.prepareStatement(DELETE_COMPANY);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
