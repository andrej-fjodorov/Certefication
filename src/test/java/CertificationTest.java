import API.CompanyService;
import API.CompanyServiceImpl;
import API.EmployeeService;
import API.EmployeeServiceImpl;
import DB.CompanyRepository;
import DB.EmployeeRepository;
import Model.Company;
import Model.Employee;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CertificationTest {
    private CompanyRepository companyService;
    private EmployeeRepository employeeService;
    private CompanyService companyApiService;
    private EmployeeService employeeAPIService;

    static Faker faker = new Faker();


    @Test
    @DisplayName("1. Проверить, что список компаний фильтруется по параметру active")
    public void filterByIsActive() throws SQLException {
        step("Получить список компаний");
        List<Company> companiesA = companyApiService.getAll(true);
        step("Получить список из компаний БД");
        List<Company> companiesB = companyService.getAll(true);
        step("Сравнить результаты");
            Assertions.assertEquals(companiesA.size(), companiesB.size());

        step("Получить cписок компаний isActive");
        List<Company> companiesIsActiveA = companyApiService.getAll(true);
        step("Получить cписок компаний isActive из бд");
        List<Company>companiesIsActiveB = companyService.getAll(true);
        step("Сравнить результаты");
        Assertions.assertEquals(companiesIsActiveA.size(), companiesIsActiveB.size());
        ;
    }

    @Test
    @DisplayName("2. Проверить, что неактивный сотрудник не отображается в списке")
    public void addEmployeeOfNotExistingCompany() throws SQLException {
        step("Создаем компанию в БД");
        step("Создаем неактивного сотрудника в БД");
        Company companiesIsActiveB = companyService.getById(2);
        Employee employeE = employeeService.getById(2);
    }
    @Test
    @DisplayName("3. Проверить, что у удаленной компании проставляется в БД поле deletedAt")
    public void shouldFillDeletedAtToDeletedCompany() throws SQLException {

    }

}
