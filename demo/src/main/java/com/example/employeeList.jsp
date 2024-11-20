<%@ page import="java.util.List" %>
<%@ page import="com.example.Employee" %>
<%@ page import="com.example.EmployeeDAO" %>
<%
    EmployeeDAO employeeDAO = new EmployeeDAO();
    List<Employee> employees = employeeDAO.getAllEmployees();
%>

<html>
<head>
    <title>Employee List</title>
</head>
<body>
    <h1>Employee List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Designation</th>
            <th>Department</th>
            <th>Salary</th>
            
        </tr>
        <%
            for (Employee employee : employees) {
        %>
        <tr>
            <td><%= employee.getId() %></td>
            <td><%= employee.getName() %></td>
            <td><%= employee.getDesignation() %></td>
            <td><%= employee.getDepartment() %></td>
            <td><%= employee.getSalary() %></td>
            
            
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>

