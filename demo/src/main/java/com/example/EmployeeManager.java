package divyya.demo.src.main.java.com.example;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeManager {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(int id) {
        employees.removeIf(employee -> employee.getId() == id);
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employees.stream()
                .filter(employee -> employee.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    public double calculateTotalSalary() {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }
}
