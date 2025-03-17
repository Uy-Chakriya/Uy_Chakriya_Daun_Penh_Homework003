import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class DisplayAllMethod {

    String RESET = "\u001B[0m";
    String GREEN = "\u001B[32m";
    String RED = "\u001B[31m";

    static String RESETS = "\u001B[0m";
    static String GREENS = "\u001B[32m";
    static String REDS = "\u001B[31m";

    private int nextId = 11;
    Scanner scanner = new Scanner(System.in);
    ArrayList<StaffMember> employees = new ArrayList<>();
    {
        // Default 7 members
        employees.add(new Volunter(1, "Dara", "PP", 0));
        employees.add(new SalariedEmployee(2, "Anna", "SR", 3000, 500));
        employees.add(new HourlySalaryEmployee(3, "Bopha", "BTB", 20, 50));
        employees.add(new Volunter(4, "Sara", "PP", 0));
        employees.add(new SalariedEmployee(5, "Sora", "SR", 3500, 500));
        employees.add(new HourlySalaryEmployee(6, "Markara", "BTB", 20, 50));
        employees.add(new Volunter(7, "Meta", "PP", 0));
        employees.add(new Volunter(8, "Cheata", "PP", 0));
        employees.add(new Volunter(9, "Soda", "PP", 0));
        employees.add(new Volunter(10, "Vita", "PP", 0));
    }

    Volunter insertVolunteer = new Volunter(employees, nextId);
    SalariedEmployee insertSalariedEmployee = new SalariedEmployee(employees, nextId);
    HourlySalaryEmployee insertHourlySalaryEmployee = new HourlySalaryEmployee(employees, nextId);

    public static void ChooseShowTable() {
        CellStyle centerAlign = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.SURROUND_HEADER_AND_COLUMNS);
        table.addCell(GREENS + "    STAFF MANAGEMENT SYSTEM    " + RESETS, centerAlign);
        table.addCell("1. Insert Employee", centerAlign);
        table.addCell("2. Update Employee", centerAlign);
        table.addCell("3. Display Employee", centerAlign);
        table.addCell("4. Remove Employee", centerAlign);
        table.addCell("5. Exit", centerAlign);
        System.out.println(table.render());
    }

    public void InsertEmployee() {
        System.out.println("\n" + "=".repeat(20) + " [+] Insert Employee [+] " + "=".repeat(20));
        while (true) {
            System.out.println(GREEN + ">> Choose one Type: " + RESET);
            CellStyle centerAlign = new CellStyle(CellStyle.HorizontalAlign.center);
            Table table = new Table(4, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
            table.addCell(" 1. Volunteer ", centerAlign);
            table.addCell(" 2. Salaried Employee ", centerAlign);
            table.addCell(" 3. Hourly Employee ", centerAlign);
            table.addCell(" 4. Back ", centerAlign);
            System.out.println(table.render());

            System.out.print("\n => Enter Type Number: ");
            int ChooseTypeOption = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    ChooseTypeOption = scanner.nextInt();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println(RED + "Error: Please enter a valid number (1-4)." + RESET);
                    System.out.print(" => Enter Type Number: ");
                    scanner.next();
                }
            }
            switch (ChooseTypeOption) {
                case 1:
                    try {
                        insertVolunteer.InsertVolunteer(nextId++);
                    } catch (Exception e) {
                        System.out.println(RED + "Error inserting Volunteer: " + e.getMessage() + RESET);
                    }
                    break;
                case 2:
                    try {
                        insertSalariedEmployee.InsertSalariedEmployee(nextId++);
                    } catch (Exception e) {
                        System.out.println(RED + "Error inserting Salaried Employee: " + e.getMessage() + RESET);
                    }
                    break;
                case 3:
                    try {
                        insertHourlySalaryEmployee.InsertHourlySalaryEmployee(nextId++);
                    } catch (Exception e) {
                        System.out.println(RED + "Error inserting Hourly Employee: " + e.getMessage() + RESET);
                    }
                    break;
                case 4:
                    System.out.println("\n\n");
                    return;
                default:
                    System.out.println(RED + "Invalid option! Please choose 1-4." + RESET);
            }
        }
    }

    public void DisplayEmployee() {
        System.out.println("\n" + "=".repeat(20) + "* Display Employee *" + "=".repeat(20));
        CellStyle centerAlign = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(9, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        table.addCell(GREEN + "Type" + RESET, centerAlign);
        table.addCell(GREEN + "ID" + " ".repeat(5) + RESET, centerAlign);
        table.addCell(GREEN + "Name" + " ".repeat(5) + RESET, centerAlign);
        table.addCell(GREEN + "Address" + " ".repeat(5) + RESET, centerAlign);
        table.addCell(GREEN + "Salary" + " ".repeat(5) + RESET, centerAlign);
        table.addCell(GREEN + "Bonus" + " ".repeat(5) + RESET, centerAlign);
        table.addCell(GREEN + "Hour" + " ".repeat(5) + RESET, centerAlign);
        table.addCell(GREEN + "Rate" + " ".repeat(5) + RESET, centerAlign);
        table.addCell(GREEN + "Pay" + " ".repeat(5) + RESET, centerAlign);
        System.out.println("\n");

        for (StaffMember employee : employees) {
            if (employee instanceof Volunter) {
                table.addCell("Volunteer");
                table.addCell(String.valueOf(employee.id), centerAlign);
                table.addCell(employee.name, centerAlign);
                table.addCell(employee.address, centerAlign);
                table.addCell(String.valueOf(((Volunter) employee).getSalary()), centerAlign);
                table.addCell("...", centerAlign);
                table.addCell("...", centerAlign);
                table.addCell("...", centerAlign);
                table.addCell(String.valueOf(employee.pay()), centerAlign);
            } else if (employee instanceof SalariedEmployee) {
                table.addCell("Salaried Employee");
                table.addCell(String.valueOf(employee.id), centerAlign);
                table.addCell(employee.name, centerAlign);
                table.addCell(employee.address, centerAlign);
                table.addCell(String.valueOf(((SalariedEmployee) employee).getSalary()), centerAlign);
                table.addCell(String.valueOf(((SalariedEmployee) employee).getBunus()), centerAlign);
                table.addCell("...", centerAlign);
                table.addCell("...", centerAlign);
                table.addCell(String.valueOf(employee.pay()), centerAlign);
            } else if (employee instanceof HourlySalaryEmployee) {
                table.addCell("Hourly Salary Employee");
                table.addCell(String.valueOf(employee.id), centerAlign);
                table.addCell(employee.name, centerAlign);
                table.addCell(employee.address, centerAlign);
                table.addCell("...", centerAlign);
                table.addCell("...", centerAlign);
                table.addCell(String.valueOf(((HourlySalaryEmployee) employee).getHourWorked()), centerAlign);
                table.addCell(String.valueOf(((HourlySalaryEmployee) employee).getRate()), centerAlign);
                table.addCell(String.valueOf(employee.pay()), centerAlign);
            }
        }
        System.out.println(table.render());
    }

    public void Pagination() {
        System.out.println("\n" + "_".repeat(30) + " [+] Pagination [+] " + "_".repeat(30) + "\n");
        System.out.print("1. First Page" + " ".repeat(5));
        System.out.print("2. Next Page" + " ".repeat(5));
        System.out.print("3. Previous Page" + " ".repeat(5));
        System.out.print("4. Last Page" + " ".repeat(5));
        System.out.print("5. Exit" + " ".repeat(5) + "\n");
        System.out.println("_".repeat(80) + "\n");

        int currentPage = 1;
        int pageSize = 5;
        int totalPages = (int) Math.ceil((double) employees.size() / pageSize);

        while (true) {
            System.out.print("\n => Enter your choice of pagination: ");
            int pagination = scanner.nextInt();
            switch (pagination) {

                // First Page
                case 1:
                    currentPage = 1;
                    displayPage(currentPage, pageSize);
                    break;

                // Next Page
                case 2:
                    if (currentPage < totalPages) {
                        currentPage++;
                        displayPage(currentPage, pageSize);
                    } else {
                        System.out.println(RED + "Already on the last page!" + RESET);
                    }
                    break;

                // Previous Page
                case 3:
                    if (currentPage > 1) {
                        currentPage--;
                        displayPage(currentPage, pageSize);
                    } else {
                        System.out.println(RED + "Already on the first page!" + RESET);
                    }
                    break;

                // Last Page
                case 4:
                    currentPage = totalPages;
                    displayPage(currentPage, pageSize);
                    break;

                // Exit
                case 5:
                    return;
                default:
                    System.out.println(RED + "Invalid choice. Please try again." + RESET);
            }
            // បង្ហាញ current page status
            System.out.println(GREEN + "Page " + currentPage + " of " + totalPages + RESET);
        }
    }

    private void displayPage(int page, int pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, employees.size());

        if (startIndex >= employees.size()) {
            System.out.println("\n"+RED + "No employees to display!" + RESET +"\n");
            return;
        }
        CellStyle centerAlign = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(9, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        table.addCell(GREEN + "Type" + RESET, centerAlign);
        table.addCell(GREEN + "ID" + " ".repeat(5) + RESET, centerAlign);
        table.addCell(GREEN + "Name" + " ".repeat(5) + RESET, centerAlign);
        table.addCell(GREEN + "Address" + " ".repeat(5) + RESET, centerAlign);
        table.addCell(GREEN + "Salary" + " ".repeat(5) + RESET, centerAlign);
        table.addCell(GREEN + "Bonus" + " ".repeat(5) + RESET, centerAlign);
        table.addCell(GREEN + "Hour" + " ".repeat(5) + RESET, centerAlign);
        table.addCell(GREEN + "Rate" + " ".repeat(5) + RESET, centerAlign);
        table.addCell(GREEN + "Pay" + " ".repeat(5) + RESET, centerAlign);


        for (int i = startIndex; i < endIndex; i++) {
            StaffMember employee = employees.get(i);
            if (employee instanceof Volunter) {
                table.addCell("Volunteer");
                table.addCell(String.valueOf(employee.id), centerAlign);
                table.addCell(employee.name, centerAlign);
                table.addCell(employee.address, centerAlign);
                table.addCell(String.valueOf(((Volunter) employee).getSalary()), centerAlign);
                table.addCell("...", centerAlign);
                table.addCell("...", centerAlign);
                table.addCell("...", centerAlign);
                table.addCell(String.valueOf(employee.pay()), centerAlign);
            } else if (employee instanceof SalariedEmployee) {
                table.addCell("Salaried Employee");
                table.addCell(String.valueOf(employee.id), centerAlign);
                table.addCell(employee.name, centerAlign);
                table.addCell(employee.address, centerAlign);
                table.addCell(String.valueOf(((SalariedEmployee) employee).getSalary()), centerAlign);
                table.addCell(String.valueOf(((SalariedEmployee) employee).getBunus()), centerAlign);
                table.addCell("...", centerAlign);
                table.addCell("...", centerAlign);
                table.addCell(String.valueOf(employee.pay()), centerAlign);
            } else if (employee instanceof HourlySalaryEmployee) {
                table.addCell("Hourly Salary Employee");
                table.addCell(String.valueOf(employee.id), centerAlign);
                table.addCell(employee.name, centerAlign);
                table.addCell(employee.address, centerAlign);
                table.addCell("...", centerAlign);
                table.addCell("...", centerAlign);
                table.addCell(String.valueOf(((HourlySalaryEmployee) employee).getHourWorked()), centerAlign);
                table.addCell(String.valueOf(((HourlySalaryEmployee) employee).getRate()), centerAlign);
                table.addCell(String.valueOf(employee.pay()), centerAlign);
            }
        }
        System.out.println(table.render());
    }

    public void UpdateEmployee() {
        try {
            System.out.print("\n => Enter or Search ID to update: ");
            int searchId = scanner.nextInt();
            scanner.nextLine();

            StaffMember selectedEmployee = null;
            for (StaffMember employee : employees) {
                if (employee.id == searchId) {
                    selectedEmployee = employee;
                    break;
                }
            }
            if (selectedEmployee == null) {
                System.out.println(RED + "Employee with ID " + searchId + " not found." + RESET);
                return;
            }

            CellStyle centerAlign = new CellStyle(CellStyle.HorizontalAlign.center);
            Table table = null;

            if (selectedEmployee instanceof Volunter) {
                table = new Table(5, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
                table.addCell(GREEN + "Type" + RESET, centerAlign);
                table.addCell(GREEN + "ID" + RESET, centerAlign);
                table.addCell(GREEN + "Name" + RESET, centerAlign);
                table.addCell(GREEN + "Address" + RESET, centerAlign);
                table.addCell(GREEN + "Salary" + RESET, centerAlign);
                table.addCell("Volunteer", centerAlign);
                table.addCell(String.valueOf(selectedEmployee.id), centerAlign);
                table.addCell(selectedEmployee.name, centerAlign);
                table.addCell(selectedEmployee.address, centerAlign);
                table.addCell(String.valueOf(((Volunter) selectedEmployee).getSalary()), centerAlign);
            } else if (selectedEmployee instanceof SalariedEmployee) {
                table = new Table(6, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
                table.addCell(GREEN + "Type" + RESET, centerAlign);
                table.addCell(GREEN + "ID" + RESET, centerAlign);
                table.addCell(GREEN + "Name" + RESET, centerAlign);
                table.addCell(GREEN + "Address" + RESET, centerAlign);
                table.addCell(GREEN + "Salary" + RESET, centerAlign);
                table.addCell(GREEN + "Bonus" + RESET, centerAlign);
                table.addCell("Salaried Employee", centerAlign);
                table.addCell(String.valueOf(selectedEmployee.id), centerAlign);
                table.addCell(selectedEmployee.name, centerAlign);
                table.addCell(selectedEmployee.address, centerAlign);
                table.addCell(String.valueOf(((SalariedEmployee) selectedEmployee).getSalary()), centerAlign);
                table.addCell(String.valueOf(((SalariedEmployee) selectedEmployee).getBunus()), centerAlign);
            } else if (selectedEmployee instanceof HourlySalaryEmployee) {
                table = new Table(7, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
                table.addCell(GREEN + "Type" + RESET, centerAlign);
                table.addCell(GREEN + "ID" + RESET, centerAlign);
                table.addCell(GREEN + "Name" + RESET, centerAlign);
                table.addCell(GREEN + "Address" + RESET, centerAlign);
                table.addCell(GREEN + "Hours" + RESET, centerAlign);
                table.addCell(GREEN + "Rate" + RESET, centerAlign);
                table.addCell(GREEN + "Salary" + RESET, centerAlign);
                table.addCell("Hourly Salary Employee", centerAlign);
                table.addCell(String.valueOf(selectedEmployee.id), centerAlign);
                table.addCell(selectedEmployee.name, centerAlign);
                table.addCell(selectedEmployee.address, centerAlign);
                table.addCell(String.valueOf(((HourlySalaryEmployee) selectedEmployee).getHourWorked()), centerAlign);
                table.addCell(String.valueOf(((HourlySalaryEmployee) selectedEmployee).getRate()), centerAlign);
                table.addCell(String.valueOf(((HourlySalaryEmployee) selectedEmployee).getSalary()), centerAlign);
            }

            System.out.println(table.render());

// ជ្រើស column ណាមួយដើម្បីធ្វើការកែប្រែ (3 types)
            while (true) {
                System.out.println("\n" + "=".repeat(20) + "* Choose one column to update *" + "=".repeat(20));
                if (selectedEmployee instanceof Volunter) {
                    System.out.print("1. Name  2. Address  3. Salary  0. Cancel\n");
                } else if (selectedEmployee instanceof SalariedEmployee) {
                    System.out.print("1. Name  2. Address  3. Salary  4. Bonus  0. Cancel\n");
                } else if (selectedEmployee instanceof HourlySalaryEmployee) {
                    System.out.print("1. Name  2. Address  3. Hour Worked  4. Rate  5. Salary  0. Cancel\n");
                }

                try {
                    System.out.print("\n => Select Column Number: ");
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1:
                            System.out.print("\n => Change Name To : ");
                            selectedEmployee.name = scanner.nextLine();
                            break;

                        case 2:
                            System.out.print("=> Change Address To: ");
                            selectedEmployee.address = scanner.nextLine();
                            break;

                        case 3:
                            if (selectedEmployee instanceof Volunter) {
                                System.out.print("=> Change Salary To : ");
                                ((Volunter) selectedEmployee).setSalary();
                            } else if (selectedEmployee instanceof HourlySalaryEmployee) {
                                System.out.print("=> Change Hour Worked To : ");
                                ((HourlySalaryEmployee) selectedEmployee).setHourWorked();
                            } else {
                                System.out.print("=> Change Salary To : ");
                                ((SalariedEmployee) selectedEmployee).setSalary();
                            }
                            scanner.nextLine();
                            break;

                        case 4:
                            if (selectedEmployee instanceof SalariedEmployee) {
                                System.out.print("=> Change Bonus To : ");
                                ((SalariedEmployee) selectedEmployee).setBonus(scanner.nextDouble());
                            } else if (selectedEmployee instanceof HourlySalaryEmployee) {
                                System.out.print("=> Change Rate To : ");
                                ((HourlySalaryEmployee) selectedEmployee).setRate();
                            }
                            scanner.nextLine();
                            break;

                        case 5:
                            if (selectedEmployee instanceof HourlySalaryEmployee) {
                                System.out.print("=> Change Salary To : ");
                                ((HourlySalaryEmployee) selectedEmployee).setSalary(scanner.nextDouble());
                                scanner.nextLine();
                            }
                            break;

                        case 0:
                            return;

                        default:
                            System.out.println("\n" + RED + "Invalid choice. Please try again." + RESET + "\n");
                    }
                } catch (InputMismatchException e) {
                    System.out.println(RED + "Invalid input. Please enter a valid number." + RESET);
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println(RED + "An error occurred: " + e.getMessage() + RESET);
                    scanner.nextLine();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println(RED + "Invalid ID input. Please enter a valid number." + RESET);
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(RED + "An unexpected error occurred: " + e.getMessage() + RESET);
        }
    }

    public void RemoveEmployee() {
        System.out.println("\n" + "=".repeat(20) + "* Remove Employee *" + "=".repeat(20));

        try {
            System.out.print("=> Enter ID to remove: ");
            int removeId = scanner.nextInt();
            scanner.nextLine();

            boolean removed = employees.removeIf(employee -> employee.id == removeId);
            if (removed) {
                System.out.println(GREEN + "\n" + "* Employee with ID: " + removeId + " has been removed successfully! *" + RESET + "\n");
            } else {
                System.out.println("\n" + RED + "Employee with ID " + removeId + " not found." + RESET + "\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("\n" + RED + "Invalid input. Please enter a valid numeric ID." + RESET + "\n");
            scanner.nextLine(); // Clear invalid input
        } catch (NullPointerException e) {
            System.out.println("\n" + RED + "Error: Employee list is not initialized." + RESET + "\n");
        } catch (Exception e) {
            System.out.println("\n" + RED + "An unexpected error occurred: " + e.getMessage() + RESET + "\n");
        }
    }

}



