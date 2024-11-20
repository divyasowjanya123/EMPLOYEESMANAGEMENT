package divyya.demo.src.test.java.com.example;

import divyya.demo.src.main.java.com.example.Employee;

/*import org.junit.Test;
import org.junit.Assert;

public class testcases {
@Test{

    public void testgetting()
    {
        Employee ep=new Employee(10,"swapna","manager", "HR",100000);
        Assert.assertEquals(10,getId());
    }
    
}*/

    
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class EmployeeDAOTest {
    private EmployeeDAO employeeDAO;

    @BeforeEach
    public void setUp() {
        // Initialize EmployeeDAO with test database configuration
        employeeDAO = new EmployeeDAO("jdbc:postgresql://localhost:5432/testdb", "testuser", "testpass");
    }

    @Test
    public void testAddEmployee() {
        // Create a new employee
        Employee employee = new Employee(1, "John Doe", "Engineer", "IT", 60000);

        try {
            // Add the employee to the database
            employeeDAO.addEmployee(employee);

            // Retrieve the employee from the database
            Employee retrievedEmployee = employeeDAO.getEmployeeById(1);

            // Assert that the retrieved employee matches the added employee
            Assertions.assertEquals(employee.getId(), retrievedEmployee.getId());
            Assertions.assertEquals(employee.getName(), retrievedEmployee.getName());
            Assertions.assertEquals(employee.getDesignation(), retrievedEmployee.getDesignation());
            Assertions.assertEquals(employee.getDepartment(), retrievedEmployee.getDepartment());
            Assertions.assertEquals(employee.getSalary(), retrievedEmployee.getSalary());

        } catch (SQLException e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred while adding or retrieving employee: " + e.getMessage());
        }
    }
}

