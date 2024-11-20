package divyya.demo.src.main.java.com.example;

public class PayrollReportTask implements Runnable {
    private EmployeeManager employeeManager;

    public PayrollReportTask(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    @Override
    public void run() {
        double totalSalary = employeeManager.getEmployees().stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("Total Payroll: " + totalSalary);
    }
}