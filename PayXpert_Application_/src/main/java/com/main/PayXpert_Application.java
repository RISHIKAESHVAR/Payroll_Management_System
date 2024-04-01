package com.main;

import com.service.EmployeeService;

import com.dao.EmployeeServiceDao;
import com.service.FinancialRecordService;
import com.dao.FinancialRecordServiceDao;
import com.service.PayrollService;
import com.dao.PayrollServiceDao;
import com.service.TaxService;
import com.dao.TaxServiceDao;
import com.model.Employee;
import com.model.FinancialRecord;
import com.model.Payroll;
import com.model.Tax;
import com.util.SqlConnection;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PayXpert_Application {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Establishing a database connection
            Connection connection = SqlConnection.getConnection();

            EmployeeServiceDao employeeServiceDao = new EmployeeServiceDao(connection);
            EmployeeService employeeService = new EmployeeService(employeeServiceDao);
            
            PayrollServiceDao payrollServiceDao = new PayrollServiceDao(connection);
            PayrollService payrollService = new PayrollService(payrollServiceDao);
            
            TaxServiceDao taxServiceDao = new TaxServiceDao(connection);
            TaxService taxService = new TaxService(taxServiceDao);
            
            FinancialRecordServiceDao financialRecordServiceDao = new FinancialRecordServiceDao(connection);
            FinancialRecordService financialRecordService = new FinancialRecordService(financialRecordServiceDao);

            while (true) {
                System.out.println("Choose what you have to access by giving number form 1 to 5");
                System.out.println("1. Access Employee ");
                System.out.println("2. Access Payroll");
                System.out.println("3. Access Financial Record");
                System.out.println("4. Access Tax");
                System.out.println("5. Exit");

                int tableChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (tableChoice) {
                    case 1:
                        handleEmployeeTable(employeeService, scanner);
                        break;
                    case 2:
                    	handlePayrollTable(payrollService, scanner);
                        break;
                    case 3:
                    	handleFinancialRecordTable(financialRecordService, scanner);
                        break;
                    case 4:
                    	handleTaxTable(taxService, scanner);
                        break;
                    case 5:
                        System.out.println("getting out of the Application");
                        SqlConnection.closeConnection(connection);
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	private static void handleEmployeeTable(EmployeeService employeeService, Scanner scanner) {
        while (true) {
            System.out.println("Choose an option for Employee table:");
            System.out.println("1. Add Employee to the Employee Table");
            System.out.println("2. Update Employee in the Employee Table");
            System.out.println("3. Get information by using EmployeeID ");
            System.out.println("4. Get All Employees in the Table");
            System.out.println("5. Back to Accessing table Selection");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addEmployee(employeeService, scanner);
                    break;
                case 2:
                    updateEmployee(employeeService, scanner);
                    break;
                case 3:
                    getEmployeeById(employeeService, scanner);
                    break;
                case 4:
                    getAllEmployees(employeeService);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }

    private static void addEmployee(EmployeeService employeeService, Scanner scanner) {
        try {
            System.out.println("Enter Employee details:");
            System.out.print("Employee ID: ");
            int employeeID = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Date of Birth (YYYY-MM-DD): ");
            Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());

            System.out.print("Gender: ");
            String gender = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine();

            System.out.print("Address: ");
            String address = scanner.nextLine();

            System.out.print("Position: ");
            String position = scanner.nextLine();

            System.out.print("Joining Date (YYYY-MM-DD): ");
            Date joiningDate = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());

            // Creating Employee object
            Employee employee = new Employee(employeeID, firstName, lastName, dateOfBirth, gender, email,
                    phoneNumber, address, position, joiningDate, null);

            // Adding employee
            employeeService.addEmployee(employee);
            System.out.println("Employee added successfully.");
        } catch (ParseException e) {
            System.out.println("Error parsing date. Please enter date in valid format (YYYY-MM-DD).");
        }
    }

    private static void updateEmployee(EmployeeService employeeService, Scanner scanner) {
        System.out.print("Enter Employee ID to update: ");
        int employeeID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Get existing employee
        Employee existingEmployee = employeeService.getEmployeeById(employeeID);
        if (existingEmployee != null) {
            try {
                System.out.println("Enter updated details for Employee ID " + employeeID + ":");

                System.out.print("First Name: ");
                existingEmployee.setFirstName(scanner.nextLine());

                System.out.print("Last Name: ");
                existingEmployee.setLastName(scanner.nextLine());

                System.out.print("Date of Birth (YYYY-MM-DD): ");
                existingEmployee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine()));

                System.out.print("Gender: ");
                existingEmployee.setGender(scanner.nextLine());

                System.out.print("Email: ");
                existingEmployee.setEmail(scanner.nextLine());

                System.out.print("Phone Number: ");
                existingEmployee.setPhoneNumber(scanner.nextLine());

                System.out.print("Address: ");
                existingEmployee.setAddress(scanner.nextLine());

                System.out.print("Position: ");
                existingEmployee.setPosition(scanner.nextLine());

                System.out.print("Joining Date (YYYY-MM-DD): ");
                existingEmployee.setJoiningDate(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine()));

                // Updating employee
                employeeService.updateEmployee(existingEmployee);
                System.out.println("Employee with ID " + employeeID + " updated successfully.");
            } catch (ParseException e) {
                System.out.println("Error parsing date. Please enter date in valid format (YYYY-MM-DD).");
            }
        } else {
            System.out.println("Employee with ID " + employeeID + " not found.");
        }
    }

    private static void getEmployeeById(EmployeeService employeeService, Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int employeeID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Retrieve employee by ID
        Employee employee = employeeService.getEmployeeById(employeeID);
        if (employee != null) {
            System.out.println("Employee details:");
            System.out.println(employee);
        } else {
            System.out.println("Employee with ID " + employeeID + " not found.");
        }
    }

    private static void getAllEmployees(EmployeeService employeeService) {
        List<Employee> employees = employeeService.getAllEmployees();
        if (!employees.isEmpty()) {
            System.out.println("All Employees:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        } else {
            System.out.println("No employees found.");
        }
    }
    private static void handlePayrollTable(PayrollService payrollService, Scanner scanner) {
        while (true) {
            System.out.println("Choose an option for Payroll table:");
            System.out.println("1. Add Payroll to the Table");
            System.out.println("2. Update Payroll in the table");
            System.out.println("3. Get Details of Payroll Table by PayrollID ");
            System.out.println("4. Get All Payrolls in Payroll Table");
            System.out.println("5. Back to Accessing Table Selection");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addPayroll(payrollService, scanner);
                    break;
                case 2:
                    updatePayroll(payrollService, scanner);
                    break;
                case 3:
                    getPayrollById(payrollService, scanner);
                    break;
                case 4:
                    getAllPayrolls(payrollService);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }

    private static void addPayroll(PayrollService payrollService, Scanner scanner) {
        try {
            System.out.println("Enter Payroll details:");
            System.out.print("Payroll ID: ");
            int payrollID = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Employee ID: ");
            int employeeID = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Pay Period Start Date (YYYY-MM-DD): ");
            Date payPeriodStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());

            System.out.print("Pay Period End Date (YYYY-MM-DD): ");
            Date payPeriodEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());

            System.out.print("Basic Salary: ");
            double basicSalary = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.print("Overtime Pay: ");
            double overtimePay = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.print("Deductions: ");
            double deductions = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.print("Net Salary: ");
            double netSalary = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            Payroll payroll = new Payroll(payrollID, employeeID, payPeriodStartDate, payPeriodEndDate,
                    basicSalary, overtimePay, deductions, netSalary);
            payrollService.addPayroll(payroll);
            System.out.println("Payroll added successfully.");
        } catch (ParseException e) {
            System.out.println("Error parsing date. Please enter date in valid format (YYYY-MM-DD).");
        }
    }

    private static void updatePayroll(PayrollService payrollService, Scanner scanner) {
        System.out.print("Enter Payroll ID to update: ");
        int payrollID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Payroll existingPayroll = payrollService.getPayrollById(payrollID);
        if (existingPayroll != null) {
            try {
                System.out.println("Enter updated details for Payroll ID " + payrollID + ":");

                // Similar to adding payroll, take input for each attribute and update existing payroll

                payrollService.updatePayroll(existingPayroll);
                System.out.println("Payroll with ID " + payrollID + " updated successfully.");
            } catch (Exception e) {
                System.out.println("Error updating payroll: " + e.getMessage());
            }
        } else {
            System.out.println("Payroll with ID " + payrollID + " not found.");
        }
    }

    private static void getPayrollById(PayrollService payrollService, Scanner scanner) {
        System.out.print("Enter Payroll ID: ");
        int payrollID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Payroll payroll = payrollService.getPayrollById(payrollID);
        if (payroll != null) {
            System.out.println("Payroll details:");
            System.out.println(payroll);
        } else {
            System.out.println("Payroll with ID " + payrollID + " not found.");
        }
    }

    private static void getAllPayrolls(PayrollService payrollService) {
        List<Payroll> payrolls = payrollService.getAllPayrolls();
        if (!payrolls.isEmpty()) {
            System.out.println("All Payrolls:");
            for (Payroll payroll : payrolls) {
                System.out.println(payroll);
            }
        } else {
            System.out.println("No payrolls found.");
        }
    }
    private static void handleTaxTable(TaxService taxService, Scanner scanner) {
        while (true) {
            System.out.println("Choose an option for Tax table:");
            System.out.println("2. Get Details of Tax Table by TaxID");
            System.out.println("3. Get All Taxes in tax Table");
            System.out.println("4. Back to Accessing Table Selection");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    getTaxById(taxService, scanner);
                    break;
                case 2:
                    getAllTaxes(taxService);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }
        }
    }

    private static void getTaxById(TaxService taxService, Scanner scanner) {
        try {
            System.out.print("Enter Tax ID: ");
            int taxID = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Retrieve tax by ID
            Tax tax = taxService.getTaxById(taxID);
            if (tax != null) {
                System.out.println("Tax details:");
                System.out.println(tax);
            } else {
                System.out.println("Tax with ID " + taxID + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getAllTaxes(TaxService taxService) {
        try {
            List<Tax> taxes = taxService.getAllTaxes();
            if (!taxes.isEmpty()) {
                System.out.println("All Taxes:");
                for (Tax tax : taxes) {
                    System.out.println(tax);
                }
            } else {
                System.out.println("No taxes found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void handleFinancialRecordTable(FinancialRecordService financialRecordService, Scanner scanner) {
        while (true) {
            System.out.println("Choose an option for Financial Record table:");
            System.out.println("1. Get FinancialRecord Details by FinancialRecordID");
            System.out.println("2. Get All Financial Records in FinancialRecordTable");
            System.out.println("3. Back to Table Selection");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    getFinancialRecordById(financialRecordService, scanner);
                    break;
                case 2:
                    getAllFinancialRecords(financialRecordService);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 3.");
            }
        }
    }

    
    private static void getFinancialRecordById(FinancialRecordService financialRecordService, Scanner scanner) {
        try {
            System.out.print("Enter Financial Record ID: ");
            int recordID = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Retrieve financial record by ID
            FinancialRecord financialRecord = financialRecordService.getFinancialRecordById(recordID);
            if (financialRecord != null) {
                System.out.println("Financial Record details:");
                System.out.println(financialRecord);
            } else {
                System.out.println("Financial Record with ID " + recordID + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getAllFinancialRecords(FinancialRecordService financialRecordService) {
        try {
            List<FinancialRecord> records = financialRecordService.getAllFinancialRecords();
            if (!records.isEmpty()) {
                System.out.println("All Financial Records:");
                for (FinancialRecord record : records) {
                    System.out.println(record);
                }
            } else {
                System.out.println("No financial records found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
