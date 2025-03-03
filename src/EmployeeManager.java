import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
    String reset = "\u001B[0m";
    String cyanBold = "\u001B[1;36m";
    String green = "\u001B[32m"; // Green
    String red = "\033[31m";   // Red color code;

    private final List<StaffMember> employees = new ArrayList<>();

    // Add an employee to the list
    public void addEmployee(StaffMember employee) {
        employees.add(employee);
        System.out.println("Employee added successfully.");
    }

    // Display all employees in a formatted table
    public void displayEmployee() {
        // Employee Manager
        System.out.println("========================Display all information of employee=========================");
        CellStyle cellStyle1 = new CellStyle(CellStyle.HorizontalAlign.CENTER);
        Table table1 = new Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

        // Add table header
        table1.addCell(green + "Type" + reset, cellStyle1);
        table1.addCell(green + "ID" + reset, cellStyle1);
        table1.addCell(green + "Name" + reset, cellStyle1);
        table1.addCell(green + "Address" + reset, cellStyle1);
        table1.addCell(green + "Salary" + reset, cellStyle1);
        table1.addCell(green + "Bonus" + reset, cellStyle1);
        table1.addCell(green + "Hour" + reset, cellStyle1);
        table1.addCell(green + "Rate" + reset, cellStyle1);
        table1.addCell(green + "Pay" + reset, cellStyle1);

        // Combine static and dynamic employee data
        String[][] employeeData = getCombinedEmployeeData();

        // Add employee data to the table
        for (String[] employee : employeeData) {
            for (String data : employee) {
                table1.addCell(data, cellStyle1);
            }
        }

        table1.setColumnWidth(0, 30, 30);
        table1.setColumnWidth(1, 20, 25);
        table1.setColumnWidth(2, 20, 25);
        table1.setColumnWidth(3, 20, 20);
        table1.setColumnWidth(4, 25, 20);
        table1.setColumnWidth(5, 20, 20);
        table1.setColumnWidth(6, 20, 20);
        table1.setColumnWidth(7, 20, 20);
        table1.setColumnWidth(8, 20, 20);

        // Pagination
        int page = 1;
        int rowsPerPage = 3;
        int totalPages = (int) Math.ceil((double) employeeData.length / rowsPerPage);

        int choice;
        do {
            displayPage(employeeData, page, rowsPerPage);

            System.out.println("Page " + page + " / " + totalPages);
            System.out.println("1. First Page\t2. Next Page\t3. Previous Page\t4. Last Page\t5. Exit");
            System.out.print("Choose option in pagination (1->5): ");

            choice = getUserChoice();

            switch (choice) {
                case 1:
                    page = 1;
                    break;
                case 2:
                    if (page < totalPages) {
                        page++;
                    }
                    break;
                case 3:
                    if (page > 1) {
                        page--;
                    }
                    break;
                case 4:
                    page = totalPages;
                    break;
                case 5:
                    System.out.println("=======================Exit from pagination =======================");
                    System.out.println("Thank you!");
                    return; // Exit pagination and return to the main menu
            }
        } while (choice != 5);
    }

    // Combine static and dynamic employee data
    private String[][] getCombinedEmployeeData() {
        String[][] staticEmployeeData = {
                {"Volunteer", "1", "Chan Narith", "123 Sen Sok", "$888.50", "---", "---", "---", "$888.50"},
                {"Salaried Employee", "2", "Chan Naroth", "234 K-mall", "$999.50", "$10.50", "---", "---", "$1010.00"},
                {"Hourly Salary Employee", "3", "Chan Naroth", "Toul Pongrou", "---", "---", "40", "$10.50", "$420.00"},
                {"Volunteer", "4", "Sok Kunthea", "Phnom Penh", "$800.00", "---", "---", "---", "$800.00"},
                {"Salaried Employee", "5", "Vann Dara", "City Mall", "$1100.00", "$15.00", "---", "---", "$1115.00"},
                {"Hourly Salary Employee", "6", "Srey Mony", "Koh Pich", "---", "---", "45", "$12.00", "$540.00"},
                {"Salaried Employee", "7", "Nang", "KP", "$900.00", "$10.00", "---", "---", "$910.00"}
        };

        // Convert dynamic employees to String array
        String[][] dynamicEmployeeData = new String[employees.size()][9];
        for (int i = 0; i < employees.size(); i++) {
            StaffMember employee = employees.get(i);
            dynamicEmployeeData[i][0] = employee.getClass().getSimpleName();
            dynamicEmployeeData[i][1] = String.valueOf(employee.getId());
            dynamicEmployeeData[i][2] = employee.getName();
            dynamicEmployeeData[i][3] = employee.getAddress();

            // Format salary, bonus, and rate
            if (employee instanceof SalariedEmployee) {
                SalariedEmployee salariedEmployee = (SalariedEmployee) employee;
                dynamicEmployeeData[i][4] = "$" + String.format("%.2f", salariedEmployee.getSalary());
                dynamicEmployeeData[i][5] = "$" + String.format("%.2f", salariedEmployee.getBonus());
                dynamicEmployeeData[i][6] = "---";
                dynamicEmployeeData[i][7] = "---";
            } else if (employee instanceof HourlySalaryEmployee) {
                HourlySalaryEmployee hourlyEmployee = (HourlySalaryEmployee) employee;
                dynamicEmployeeData[i][4] = "---";
                dynamicEmployeeData[i][5] = "---";
                dynamicEmployeeData[i][6] = String.valueOf(hourlyEmployee.getHoursWorked());
                dynamicEmployeeData[i][7] = "$" + String.format("%.2f", hourlyEmployee.getRate());
            } else if (employee instanceof Volunteer) {
                Volunteer volunteer = (Volunteer) employee;
                dynamicEmployeeData[i][4] = "$" + String.format("%.2f", volunteer.pay());
                dynamicEmployeeData[i][5] = "---";
                dynamicEmployeeData[i][6] = "---";
                dynamicEmployeeData[i][7] = "---";
            }

            // Calculate and format pay
            dynamicEmployeeData[i][8] = "$" + String.format("%.2f", employee.pay());
        }

        // Combine static and dynamic data
        String[][] combinedData = new String[staticEmployeeData.length + dynamicEmployeeData.length][9];
        System.arraycopy(staticEmployeeData, 0, combinedData, 0, staticEmployeeData.length);
        System.arraycopy(dynamicEmployeeData, 0, combinedData, staticEmployeeData.length, dynamicEmployeeData.length);

        return combinedData;
    }

    private void displayPage(String[][] employeeData, int page, int rowsPerPage) {
        int startIndex = (page - 1) * rowsPerPage;
        int endIndex = Math.min(startIndex + rowsPerPage, employeeData.length);

        CellStyle cellStyle1 = new CellStyle(CellStyle.HorizontalAlign.CENTER);
        Table table1 = new Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

        // Add table header
        table1.addCell(green + "Type" + reset, cellStyle1);
        table1.addCell(green + "ID" + reset, cellStyle1);
        table1.addCell(green + "Name" + reset, cellStyle1);
        table1.addCell(green + "Address" + reset, cellStyle1);
        table1.addCell(green + "Salary" + reset, cellStyle1);
        table1.addCell(green + "Bonus" + reset, cellStyle1);
        table1.addCell(green + "Hour" + reset, cellStyle1);
        table1.addCell(green + "Rate" + reset, cellStyle1);
        table1.addCell(green + "Pay" + reset, cellStyle1);

        // Display data for current page
        for (int i = startIndex; i < endIndex; i++) {
            String[] employee = employeeData[i];
            for (String data : employee) {
                table1.addCell(data, cellStyle1);
            }
        }

        System.out.println(table1.render());
    }

    private int getUserChoice() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choice = 0;
        boolean valid = false;

        while (!valid) {
            try {
                choice = Integer.parseInt(reader.readLine());
                if (choice < 1 || choice > 5) {
                    System.out.println("Invalid choice, please choose between 1 and 5.");
                } else {
                    valid = true;
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        }
        return choice;
    }

    // Update employee details by ID
    public void updateEmployee(int id, String name, String address) {
        employees.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst()
                .ifPresentOrElse(emp -> {
                    if (name != null) emp.setName(name);
                    if (address != null) emp.setAddress(address);
                }, () -> System.out.println("Employee not found."));
    }

    // Remove an employee by ID
    public void removeEmployee(int id) {
        boolean removed = employees.removeIf(emp -> emp.getId() == id);
        if (removed) {
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void updateEmployeeSalary(int id, double salary) {
        employees.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst()
                .ifPresentOrElse(emp -> {
                    if (emp instanceof SalariedEmployee) {
                        ((SalariedEmployee) emp).setSalary(salary);
                    } else if (emp instanceof HourlySalaryEmployee) {
                        ((HourlySalaryEmployee) emp).setRate(salary);
                    }
                    System.out.println("Employee salary updated successfully.");
                }, () -> System.out.println("Employee not found."));
    }

    // Display employee by ID
    public void displayEmployeeById(int id) {
        StaffMember employee = employees.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst()
                .orElse(null);

        if (employee == null) {
            System.out.println("Employee with ID " + id + " not found.");
            return;
        }

        // Create a table to display employee details
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
        Table table = new Table(6, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
        table.addCell("TYPE", cellStyle);
        table.addCell("ID", cellStyle);
        table.addCell("NAME", cellStyle);
        table.addCell("ADDRESS", cellStyle);
        table.addCell("SALARY", cellStyle);
        table.addCell("PAY", cellStyle);

        // Add employee data to the table
        table.addCell(employee.getClass().getSimpleName(), cellStyle);
        table.addCell(String.valueOf(employee.getId()), cellStyle);
        table.addCell(employee.getName(), cellStyle);
        table.addCell(employee.getAddress(), cellStyle);

        if (employee instanceof SalariedEmployee) {
            SalariedEmployee salariedEmployee = (SalariedEmployee) employee;
            table.addCell("$" + String.format("%.2f", salariedEmployee.getSalary()), cellStyle);
            table.addCell("$" + String.format("%.2f", salariedEmployee.pay()), cellStyle);
        } else if (employee instanceof HourlySalaryEmployee) {
            HourlySalaryEmployee hourlyEmployee = (HourlySalaryEmployee) employee;
            table.addCell("---", cellStyle);
            table.addCell("$" + String.format("%.2f", hourlyEmployee.pay()), cellStyle);
        } else if (employee instanceof Volunteer) {
            Volunteer volunteer = (Volunteer) employee;
            table.addCell("$" + String.format("%.2f", volunteer.pay()), cellStyle);
            table.addCell("$" + String.format("%.2f", volunteer.pay()), cellStyle);
        }

        // Set column widths
        table.setColumnWidth(0, 20, 20);
        table.setColumnWidth(1, 10, 10);
        table.setColumnWidth(2, 20, 20);
        table.setColumnWidth(3, 20, 20);
        table.setColumnWidth(4, 15, 15);
        table.setColumnWidth(5, 15, 15);

        System.out.println(table.render());
    }

    public boolean employeeExists(int id) {
        return false;
    }

    public StaffMember getEmployeeById(int id) {
        return null;
    }
}