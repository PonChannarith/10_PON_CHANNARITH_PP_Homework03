import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        EmployeeManager manager = new EmployeeManager();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int option;

        do {
            CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
            Table table = new Table(1, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
            table.addCell("STAFF MANAGEMENT", cellStyle);
            table.addCell("1. Insert Employee", cellStyle);
            table.addCell("2. Update Employee", cellStyle);
            table.addCell("3. Display Employees", cellStyle);
            table.addCell("4. Remove Employee", cellStyle);
            table.addCell("5. Exit", cellStyle);

            table.setColumnWidth(0, 70, 70);
            System.out.println(table.render());
            System.out.print("=> Choose an option: ");
            option = Integer.parseInt(reader.readLine());

            switch (option) {
                case 1:
                    insertEmployee(manager, reader);
                    break;
                case 2:
                    updateEmployee(manager, reader);
                    break;
                case 3:
                    manager.displayEmployee();
                    break;
                case 4:
                    removeEmployee(manager, reader);
                    break;
                case 5:
                    System.out.println("Exiting Employee Management System. Thank you!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option (1-5).");
            }
        } while (option != 5);
    }

    private static void insertEmployee(EmployeeManager manager, BufferedReader reader) throws IOException {
        int typeOption;
        do {
            System.out.println("Choose type:");
            System.out.println("1. Volunteer");
            System.out.println("2. Salaried Employee");
            System.out.println("3. Hourly Employee");
            System.out.println("4. Back");
            System.out.print("=> Enter type of number: ");
            typeOption = Integer.parseInt(reader.readLine());

            switch (typeOption) {
                case 1:
                    System.out.println("ID: " + Volunteer.idCounter);
                    System.out.print("Enter Name: ");
                    String name = reader.readLine();
                    System.out.print("Enter Address: ");
                    String address = reader.readLine();
                    System.out.print("Enter Salary: ");
                    double salary = Double.parseDouble(reader.readLine());
                    manager.addEmployee(new Volunteer(name, address, salary));
                    break;
                case 2:
                    System.out.println("ID: " + SalariedEmployee.idCounter);
                    System.out.print("Enter Name: ");
                    name = reader.readLine();
                    System.out.print("Enter Address: ");
                    address = reader.readLine();
                    System.out.print("Enter Salary: ");
                    salary = Double.parseDouble(reader.readLine());
                    System.out.print("Enter Bonus: ");
                    double bonus = Double.parseDouble(reader.readLine());
                    manager.addEmployee(new SalariedEmployee(name, address, salary, bonus));
                    break;
                case 3:
                    System.out.println("ID: " + StaffMember.idCounter);
                    System.out.print("Enter Name: ");
                    name = reader.readLine();
                    System.out.print("Enter Address: ");
                    address = reader.readLine();
                    System.out.print("Enter Hours Worked: ");
                    int hoursWorked = Integer.parseInt(reader.readLine());
                    System.out.print("Enter Rate: ");
                    double rate = Double.parseDouble(reader.readLine());

                    HourlySalaryEmployee employee = new HourlySalaryEmployee(name, address, hoursWorked, rate);
                    manager.addEmployee(employee);
                    break;

                case 4:
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option (1-4).");
            }
        } while (typeOption != 4);
    }

    private static void updateEmployee(EmployeeManager manager, BufferedReader reader) throws IOException {
        System.out.print("Enter or Search ID to update: ");
        int id = Integer.parseInt(reader.readLine());
        System.out.println("Updated Employee Data:");
        manager.displayEmployeeById(id);
//        System.out.println("Search by id to update successfully ");
        // Check if the employee exists


        StaffMember employee = manager.getEmployeeById(id);

        // Display the employee's current data in a formatted table
        System.out.println("================= Update Employee =================");
        Table table = new Table(6, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);


        if (employee instanceof Volunteer) {
            Volunteer volunteer = (Volunteer) employee;
            table.addCell("Volunteer");
            table.addCell(String.valueOf(volunteer.getId()));
            table.addCell(volunteer.getName());
            table.addCell(volunteer.getAddress());
            table.addCell("$" + String.format("%.2f", volunteer.getSalary()));
            table.addCell("$" + String.format("%.2f", volunteer.getSalary()));
        } else if (employee instanceof SalariedEmployee) {
            SalariedEmployee salariedEmployee = (SalariedEmployee) employee;
            table.addCell("Salaried Employee");
            table.addCell(String.valueOf(salariedEmployee.getId()));
            table.addCell(salariedEmployee.getName());
            table.addCell(salariedEmployee.getAddress());
            table.addCell("$" + String.format("%.2f", salariedEmployee.getSalary()));
            table.addCell("$" + String.format("%.2f", salariedEmployee.getSalary() + salariedEmployee.getBonus()));
        } else if (employee instanceof HourlySalaryEmployee) {
            HourlySalaryEmployee hourlyEmployee = (HourlySalaryEmployee) employee;
            table.addCell("Hourly Salary Employee");
            table.addCell(String.valueOf(hourlyEmployee.getId()));
            table.addCell(hourlyEmployee.getName());
            table.addCell(hourlyEmployee.getAddress());
            table.addCell("N/A");
            table.addCell("$" + String.format("%.2f", hourlyEmployee.getSalary()));
        }

        System.out.println(table.render());

        // Update options
        System.out.println("Choose one column to update:");
        System.out.println("1. Name");
        System.out.println("2. Address");
        if (employee instanceof HourlySalaryEmployee) {
            System.out.println("3. Hours Worked");
            System.out.println("4. Rate");
        } else {
            System.out.println("3. Salary");
        }
        System.out.println("0. Cancel");
        System.out.print("=> Select column number: ");
        int columnOption = Integer.parseInt(reader.readLine());

        switch (columnOption) {
            case 1:
                System.out.print("Change name to: ");
                String name = reader.readLine();
                manager.updateEmployee(id, name, null);
                System.out.println("Name updated successfully.");
                break;
            case 2:
                System.out.print("Change address to: ");
                String address = reader.readLine();
                manager.updateEmployee(id, null, address);
                System.out.println("Address updated successfully.");
                break;
            case 3:
                if (employee instanceof HourlySalaryEmployee) {
                    System.out.print("Change hours worked to: ");
                    int hoursWorked = Integer.parseInt(reader.readLine());
                    ((HourlySalaryEmployee) employee).setHoursWorked(hoursWorked);
                    System.out.println("Hours worked updated successfully.");
                } else {
                    System.out.print("Change salary to: ");
                    double salary = Double.parseDouble(reader.readLine());
                    manager.updateEmployeeSalary(id, salary);
                    System.out.println("Salary updated successfully.");
                }
                break;
            case 4:
                if (employee instanceof HourlySalaryEmployee) {
                    System.out.print("Change rate to: ");
                    double rate = Double.parseDouble(reader.readLine());
                    ((HourlySalaryEmployee) employee).setRate(rate);
                    System.out.println("Rate updated successfully.");
                } else {
                    System.out.println("Invalid option for this employee type.");
                }
                break;
            case 0:
                System.out.println("Update canceled.");
                break;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
                return;
        }

        // Display the updated employee data
        System.out.println("Updated Employee Data:");
        manager.displayEmployeeById(id);
    }

    private static void removeEmployee(EmployeeManager manager, BufferedReader reader) throws IOException {
        System.out.print("Enter ID to remove: ");
        int id = Integer.parseInt(reader.readLine());
        manager.removeEmployee(id);
    }
}