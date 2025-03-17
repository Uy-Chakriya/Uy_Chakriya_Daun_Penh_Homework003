import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Volunter extends StaffMember {
    String RESET = "\u001B[0m";
    String GREEN = "\u001B[32m";
    String RED = "\u001B[31m";
    Scanner sc = new Scanner(System.in);
    private double salary;

//    generic type
    private ArrayList<StaffMember> employees;

    public Volunter(ArrayList<StaffMember> employees, int nextId) {
        this.employees = employees;
    }

    public Volunter(int id, String name, String address, double salary) {
        super(id, name, address);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public double setSalary() {
        return salary;
    }

    public String toString() {
        return "";
    }

    @Override
    public double pay() {
        return salary;
    }

// ===>> Here is the validate blog
    public void InsertVolunteer(int id) {
    boolean inputCheck = false;
    String name = "";
    String address = "";
    double salary = 0.0;
    System.out.println("ID: " + id);

    try {
        // Validate ឈ្មោះ
        while (true) {
            System.out.print("=> Enter Name: ");
            name = sc.nextLine();
            if (Pattern.matches("^[A-Za-z ]+$", name)) {
                break;
            } else {
                System.out.println(RED + "Invalid name! Please enter only letters and spaces." + RESET);
            }
        }

        // Validate អាស័យដ្ឋាន
        while (true) {
            System.out.print("=> Enter Address: ");
            address = sc.nextLine();
            if (Pattern.matches("^[A-Za-z0-9 ,.]+$", address)) {
                break;
            } else {
                System.out.println(RED + "Invalid address! Please Input Again." + RESET);
            }
        }

        // Validate ប្រាក់ខែ ([+] ជានិច្ច)
        while (true) {
            System.out.print("=> Enter Salary: ");
            try {
                salary = sc.nextDouble();
                if (salary >= 0) {
                    break; // [+]
                } else {
                    System.out.println(RED + "\nInvalid salary! Please enter a valid positive number." + RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(RED + "\nInvalid salary! Please enter a valid number." + RESET);
                sc.next();
            }
        }

        employees.add(new Volunter(id, name, address, salary));
        inputCheck = true;

        if (inputCheck) {
            System.out.println(GREEN + "[+] You added " + name + " as a Volunteer successfully! *\n\n" + RESET);
        }
    } catch (NullPointerException e) {
        System.out.println(RED + "Error: Employee list or scanner is not initialized." + RESET);
    } catch (Exception e) {
        System.out.println(RED + "An unexpected error occurred: " + e.getMessage() + RESET);
    } finally {
        sc.nextLine();
    }
}
}