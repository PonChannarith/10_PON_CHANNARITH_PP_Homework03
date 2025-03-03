import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z\\s]+$");
    private static final Pattern ADDRESS_PATTERN = Pattern.compile("^[\\w\\s,.-]+$");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
    private static final Pattern HOURS_PATTERN = Pattern.compile("^\\d{1,3}$");


    public static void main(String[] args) throws IOException {
        EmployeeManager manager = new EmployeeManager();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int option;
        String reset = "\u001B[0m";
        String cyanBold = "\u001B[1;36m";
        String green = "\u001B[32m"; // Green
        String red = "\033[31m";   // Red color code;
        System.out.println(cyanBold+ "".repeat(30)+" ███████╗███╗   ███╗██████╗ ██╗      ██████╗ ██╗   ██╗███████╗███████╗    ███╗   ███╗ █████╗ ███╗   ██╗ █████╗  ██████╗ ███████╗███╗   ███╗███████╗███╗   ██╗████████╗    ███████╗██╗   ██╗███████╗████████╗███████╗███╗   ███╗"+reset);
        System.out.println(cyanBold+ "".repeat(30)+" ██╔════╝████╗ ████║██╔══██╗██║     ██╔═══██╗╚██╗ ██╔╝██╔════╝██╔════╝    ████╗ ████║██╔══██╗████╗  ██║██╔══██╗██╔════╝ ██╔════╝████╗ ████║██╔════╝████╗  ██║╚══██╔══╝    ██╔════╝╚██╗ ██╔╝██╔════╝╚══██╔══╝██╔════╝████╗ ████║"+reset);
        System.out.println(cyanBold+ "".repeat(30)+" █████╗  ██╔████╔██║██████╔╝██║     ██║   ██║ ╚████╔╝ █████╗  █████╗      ██╔████╔██║███████║██╔██╗ ██║███████║██║  ███╗█████╗  ██╔████╔██║█████╗  ██╔██╗ ██║   ██║       ███████╗ ╚████╔╝ ███████╗   ██║   █████╗  ██╔████╔██║"+reset);
        System.out.println(cyanBold+ "".repeat(30)+" ██╔══╝  ██║╚██╔╝██║██╔═══╝ ██║     ██║   ██║  ╚██╔╝  ██╔══╝  ██╔══╝      ██║╚██╔╝██║██╔══██║██║╚██╗██║██╔══██║██║   ██║██╔══╝  ██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║       ╚════██║  ╚██╔╝  ╚════██║   ██║   ██╔══╝  ██║╚██╔╝██"+reset);
        System.out.println(cyanBold+ "".repeat(30)+" ███████╗██║ ╚═╝ ██║██║     ███████╗╚██████╔╝   ██║   ███████╗███████╗    ██║ ╚═╝ ██║██║  ██║██║ ╚████║██║  ██║╚██████╔╝███████╗██║ ╚═╝ ██║███████╗██║ ╚████║   ██║       ███████║   ██║   ███████║   ██║   ███████╗██║ ╚═╝ ██║"+reset);
        System.out.println(cyanBold+ "".repeat(30)+" ╚══════╝╚═╝     ╚═╝╚═╝     ╚══════╝ ╚═════╝    ╚═╝   ╚══════╝╚══════╝    ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝       ╚══════╝   ╚═╝   ╚══════╝   ╚═╝   ╚══════╝╚═╝     ╚═╝"+reset);
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
            CellStyle cellStyle1 = new CellStyle(CellStyle.HorizontalAlign.CENTER);
            Table table1 = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
            System.out.println("Choose type:");
            table1.addCell("1. Volunteer", cellStyle1);
            table1.addCell("2. Salaried Employee", cellStyle1);
            table1.addCell("3. Hourly Employee", cellStyle1);
            table1.addCell("4. Back", cellStyle1);
            table1.setColumnWidth(0, 20, 20);
            table1.setColumnWidth(1, 30, 30);
            table1.setColumnWidth(2, 20, 20);
            table1.setColumnWidth(3, 20, 20);
            System.out.println(table1.render());
            System.out.print("=> Enter type of number: ");
            typeOption = Integer.parseInt(reader.readLine());

            switch (typeOption) {
                case 1:
                    System.out.println("ID: " + Volunteer.idCounter);
                    String name = validateInput(reader, "Enter Name: ", NAME_PATTERN);
                    String address = validateInput(reader, "Enter Address: ", ADDRESS_PATTERN);
                    double salary = Double.parseDouble(validateInput(reader, "Enter Salary: ", NUMBER_PATTERN));
                    manager.addEmployee(new Volunteer(name, address, salary));
                    break;
                case 2:
                    System.out.println("ID: " + SalariedEmployee.idCounter);
                    name = validateInput(reader, "Enter Name: ", NAME_PATTERN);
                    address = validateInput(reader, "Enter Address: ", ADDRESS_PATTERN);
                    salary = Double.parseDouble(validateInput(reader, "Enter Salary: ", NUMBER_PATTERN));
                    double bonus = Double.parseDouble(validateInput(reader, "Enter Bonus: ", NUMBER_PATTERN));
                    manager.addEmployee(new SalariedEmployee(name, address, salary, bonus));
                    break;
                case 3:
                    System.out.println("ID: " + StaffMember.idCounter);
                    name = validateInput(reader, "Enter Name: ", NAME_PATTERN);
                    address = validateInput(reader, "Enter Address: ", ADDRESS_PATTERN);
                    int hoursWorked = Integer.parseInt(validateInput(reader, "Enter Hours Worked: ", HOURS_PATTERN));
                    double rate = Double.parseDouble(validateInput(reader, "Enter Rate: ", NUMBER_PATTERN));

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

        StaffMember employee = manager.getEmployeeById(id);

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
                String name = validateInput(reader, "Change name to: ", NAME_PATTERN);
                manager.updateEmployee(id, name, null);
                System.out.println("Name updated successfully.");
                break;
            case 2:
                String address = validateInput(reader, "Change address to: ", ADDRESS_PATTERN);
                manager.updateEmployee(id, null, address);
                System.out.println("Address updated successfully.");
                break;
            case 3:
                if (employee instanceof HourlySalaryEmployee) {
                    int hoursWorked = Integer.parseInt(validateInput(reader, "Change hours worked to: ", HOURS_PATTERN));
                    ((HourlySalaryEmployee) employee).setHoursWorked(hoursWorked);
                    System.out.println("Hours worked updated successfully.");
                } else {
                    double salary = Double.parseDouble(validateInput(reader, "Change salary to: ", NUMBER_PATTERN));
                    manager.updateEmployeeSalary(id, salary);
                    System.out.println("Salary updated successfully.");
                }
                break;
            case 4:
                if (employee instanceof HourlySalaryEmployee) {
                    double rate = Double.parseDouble(validateInput(reader, "Change rate to: ", NUMBER_PATTERN));
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

        System.out.println("Updated Employee Data:");
        manager.displayEmployeeById(id);
    }

    private static void removeEmployee(EmployeeManager manager, BufferedReader reader) throws IOException {
        System.out.print("Enter ID to remove: ");
        int id = Integer.parseInt(reader.readLine());
        manager.removeEmployee(id);
    }

    private static String validateInput(BufferedReader reader, String prompt, Pattern pattern) throws IOException {
        String input;
        do {
            System.out.print(prompt);
            input = reader.readLine();
            if (!pattern.matcher(input).matches()) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (!pattern.matcher(input).matches());
        return input;
    }
}