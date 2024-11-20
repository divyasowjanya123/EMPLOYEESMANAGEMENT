package divyya.demo.src.main.java.com.example;


import java.util.List;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();

        // Create a new employee
        Employee employee = new Employee(1, "John Doe", "Software Engineer", "IT", 75000);
        try {
            employeeDAO.addEmployee(employee);
            System.out.println("Employee added successfully");

            // Retrieve all employees
            List<Employee> employees = employeeDAO.getAllEmployees();
            for (Employee emp : employees) {
                System.out.println(emp);
            }

            // Update employee
            employee.setName("John Smith");
            employeeDAO.updateEmployee(employee);
            System.out.println("Employee updated successfully");

            //Delete employee
            employeeDAO.deleteEmployee(employee.getId());
            System.out.println("Employee deleted successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



