
<%@ page import="java.util.List" %>
<%@ page import="com.example.Employee" %>
<%@ page import="com.example.EmployeeDAO" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    EmployeeDAO employeeDAO = new EmployeeDAO();
    try {
        employeeDAO.deleteEmployee(id);
        out.println("Employee deleted successfully.");
    } catch (Exception e) {
        out.println("Error deleting employee: " + e.getMessage());
    }
%>
