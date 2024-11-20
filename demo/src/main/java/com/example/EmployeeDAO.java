/*package divyya.demo.src.main.java.com.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class EmployeeDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/divya";
    private static final String USER = "postgres";
    private static final String PASSWORD = "ssdpm";
    

    

        
        

    



    public void addEmployee(Employee employee) throws SQLException {
        String query = "INSERT INTO employees (id, name, designation, department, salary) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getDesignation());
            statement.setString(4, employee.getDepartment());
            statement.setDouble(5, employee.getSalary());
            statement.executeUpdate();
            System.out.println("Employee with ID " + employee.getId() +"name" +employee.getName()+"Designation:"+employee.getDesignation()+"Salary "+employee.getSalary());

        }
        catch (SQLException e) {
            if (e.getSQLState().equals("23505")) { // Check for duplicate key violation error
                System.out.println("Employee with ID " + employee.getId() + " already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        String query = "SELECT * FROM employees";
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("designation"),
                        resultSet.getString("department"),
                        resultSet.getDouble("salary")
                );
                employees.add(employee);
            }
        }
        return employees;
    }


    /*public void updateEmployee(Employee employee) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE employees SET name=?, designation=?, department=?, salary=? WHERE id=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, employee.getName());
                stmt.setString(2, employee.getDesignation());
                stmt.setString(3, employee.getDepartment());
                stmt.setDouble(4, employee.getSalary());
                stmt.setInt(5, employee.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    
/* 
    public void updateEmployee(Employee employee) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE employees SET name=?, designation=?, department=?, salary=? WHERE id=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, employee.getName());
                stmt.setString(2, employee.getDesignation());
                stmt.setString(3, employee.getDepartment());
                stmt.setDouble(4, employee.getSalary());
                stmt.setInt(5, employee.getId());
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Employee with ID " + employee.getId() + " updated successfully.");
                } else {
                    System.out.println("Failed to update employee with ID " + employee.getId() + ".");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM employees WHERE id=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    // Implement updateEmployee and deleteEmployee similarly
}
    */
    package divyya.demo.src.main.java.com.example;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;
    

        

        
    
    public class EmployeeDAO {
        private Connection connect() throws SQLException {
            // Update the database URL, username, and password as needed
            String url = "jdbc:postgresql://localhost:5432/divya";
            String user = "postgres";
            String password = "ssdpm";
            return DriverManager.getConnection(url, user, password);
        }
    
        public void addEmployee(Employee employee) throws SQLException {
            String sql = "INSERT INTO employees (id, name, designation, department, salary) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, employee.getId());
                pstmt.setString(2, employee.getName());
                pstmt.setString(3, employee.getDesignation());
                pstmt.setString(4, employee.getDepartment());
                pstmt.setDouble(5, employee.getSalary());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                if (e.getSQLState().equals("23505")) {
                    throw new SQLException("ID already exists", e);
                } else {
                    throw e;
                }
            }
        }
    
        
        public void deleteEmployee(int id) throws SQLException {
            String sql = "DELETE FROM employees WHERE id = ?";
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("Employee not found");
                }
            }
        }
    
        /*public void updateEmployee(Employee employee) throws SQLException {
            String sql = "UPDATE employees SET name = ?, designation = ?, department = ?, salary = ? WHERE id = ?";
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, employee.getName());
                pstmt.setString(2, employee.getDesignation());
                pstmt.setString(3, employee.getDepartment());
                pstmt.setDouble(4, employee.getSalary());
                pstmt.setInt(5, employee.getId());
                pstmt.executeUpdate();
            }
        }*/
        public void updateEmployee(Employee employee) throws SQLException {
            if (!employeeExists(employee.getId())) {
                throw new SQLException("Employee does not exist");
            }
    
            String sql = "UPDATE employees SET name = ?, designation = ?, department = ?, salary = ? WHERE id = ?";
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, employee.getName());
                pstmt.setString(2, employee.getDesignation());
                pstmt.setString(3, employee.getDepartment());
                pstmt.setDouble(4, employee.getSalary());
                pstmt.setInt(5, employee.getId());
                pstmt.executeUpdate();
            }
        }
        public boolean employeeExists(int id) throws SQLException {
            String sql = "SELECT id FROM employees WHERE id = ?";
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                try (ResultSet rs = pstmt.executeQuery()) {
                    return rs.next();
                }
            }
        }
        public List<Employee> getAllEmployees() throws SQLException {
            List<Employee> employees = new ArrayList<>();
            String sql = "SELECT * FROM employees";
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String designation = rs.getString("designation");
                    String department = rs.getString("department");
                    double salary = rs.getDouble("salary");
                    employees.add(new Employee(id, name, designation, department, salary));
                }
            }
            return employees;
        }
    }
    


