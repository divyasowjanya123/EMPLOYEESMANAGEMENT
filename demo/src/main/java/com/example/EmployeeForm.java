package divyya.demo.src.main.java.com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeForm {
    private JFrame frame;
    private JTextField idField, nameField, designationField, departmentField, salaryField;
    private EmployeeDAO employeeDAO;

    public EmployeeForm() {
        employeeDAO = new EmployeeDAO();
        frame = new JFrame("Employee Form");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7, 2));

        frame.add(new JLabel("ID:"));
        idField = new JTextField();
        frame.add(idField);

        frame.add(new JLabel("Name:"));
        nameField = new JTextField();
        frame.add(nameField);

        frame.add(new JLabel("Designation:"));
        designationField = new JTextField();
        frame.add(designationField);

        frame.add(new JLabel("Department:"));
        departmentField = new JTextField();
        frame.add(departmentField);

        frame.add(new JLabel("Salary:"));
        salaryField = new JTextField();
        frame.add(salaryField);

        JButton addButton = new JButton("Add Employee");
        frame.add(addButton);

        JButton deleteButton = new JButton("Delete Employee");
        frame.add(deleteButton);

        JButton updateButton = new JButton("Update Employee");
        frame.add(updateButton);

        JButton getAllButton = new JButton("Get All Employees");
        frame.add(getAllButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    String designation = designationField.getText();
                    String department = departmentField.getText();
                    double salary = Double.parseDouble(salaryField.getText());

                    Employee employee = new Employee(id, name, designation, department, salary);
                    employeeDAO.addEmployee(employee);
                    JOptionPane.showMessageDialog(frame, "Employee added successfully");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input format: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    if (ex.getMessage().contains("ID already exists")) {
                        JOptionPane.showMessageDialog(frame, "Error adding employee: ID already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error adding employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    employeeDAO.deleteEmployee(id);
                    JOptionPane.showMessageDialog(frame, "Employee deleted successfully");
                }/*catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID format: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error deleting employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }*/
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID format: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    if (ex.getMessage().contains("Employee not found")) {
                        JOptionPane.showMessageDialog(frame, "Employee not found: Already deleted", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error deleting employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        /*updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    String designation = designationField.getText();
                    String department = departmentField.getText();
                    double salary = Double.parseDouble(salaryField.getText());

                    Employee employee = new Employee(id, name, designation, department, salary);
                    employeeDAO.updateEmployee(employee);
                    JOptionPane.showMessageDialog(frame, "Employee updated successfully");
                } /*catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input format: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error updating employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }*/
                /*catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input format: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    if (ex.getMessage().contains("Employee does not exist")) {
                        JOptionPane.showMessageDialog(frame, "Error updating employee: Employee does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error updating employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });*/
                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int id = Integer.parseInt(idField.getText());
                            String name = nameField.getText();
                            String designation = designationField.getText();
                            String department = departmentField.getText();
                            double salary = Double.parseDouble(salaryField.getText());
        
                            Employee employee = new Employee(id, name, designation, department, salary);
                            employeeDAO.updateEmployee(employee);
                            JOptionPane.showMessageDialog(frame, "Employee updated successfully");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Invalid input format: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (SQLException ex) {
                            if (ex.getMessage().contains("Employee does not exist")) {
                                JOptionPane.showMessageDialog(frame, "Error updating employee: Employee does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Error updating employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });

                getAllButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            List<Employee> employees = employeeDAO.getAllEmployees();
                            displayEmployees(employees);
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(frame, "Error fetching employees: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

        frame.setVisible(true);
    }
    private void displayEmployees(List<Employee> employees) {
        StringBuilder employeeList = new StringBuilder();
        for (Employee employee : employees) {
            employeeList.append(employee).append("\n");
        }
        JTextArea textArea = new JTextArea(employeeList.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JFrame listFrame = new JFrame("List of Employees");
        listFrame.setSize(500, 400);
        listFrame.add(scrollPane);
        listFrame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeForm());
    }
}
