import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
public class SalariedEmployee extends StaffMember{
    String RESET = "\u001B[0m";
    String GREEN = "\u001B[32m";
    String RED = "\u001B[31m";

    Scanner sc = new Scanner(System.in);
    private double salary;
    private double bunus;
    private ArrayList<StaffMember> employees;
    private int nextId;

    public SalariedEmployee(ArrayList<StaffMember> employees, int nextId) {
        this.employees = employees;
        this.nextId = nextId;
    }
    public SalariedEmployee(int id,
                            String name,
                            String address,
                            double salary,
                            double bunus){
        super(id, name, address);
        this.salary = salary;
        this.bunus = bunus;
    }

    public double getSalary(){
        return salary;
    }

    public double setSalary(){
        return salary;
    }

    public double getBunus(){
        return bunus;
      }
    public void setBonus(double v) {}

    public String toString(){
        return "";
    }
    @Override
    //  bunus
    public double pay(){
        return salary * bunus;
    }
    public void InsertSalariedEmployee(int id){
        boolean inputCheck = false;
        String name;
        String address;
        double salary;
        double bonus;
        System.out.println("ID: " + id);

        // Validate ឈ្មោះ
        while (true) {
            System.out.print("=> Enter Name: ");
            name = sc.nextLine();
            if (Pattern.matches("^[A-Za-z ]+$", name)) {
                break;
            } else {
                System.out.println(RED+ "Invalid name! Please enter only letters and spaces." +RESET );
            }
        }

        // Validate អាស័យដ្ឋាន
        while (true) {
            System.out.print("=> Enter Address: ");
            address = sc.nextLine();
            if (Pattern.matches("^[A-Za-z0-9 ,.]+$", address)) {
                break;
            } else {
                System.out.println(RED+ "Invalid address! Please use only letters, numbers, spaces, commas, and periods."  +RESET );
            }
        }

        // Validate ប្រាក់ខែត្រូវតែវិជ្ជមាន
        while (true) {
            System.out.print("=> Enter Salary: ");
            if (sc.hasNextDouble()) {
                salary = sc.nextDouble();
                if (salary >= 0) break;
            } else {
                sc.next();
            }
            System.out.println(RED+ "Invalid salary! Please enter a valid positive number."  +RESET);
        }

        // Validate Bonus ត្រូវតែវិជ្ជមាន
        while (true) {
            System.out.print("=> Enter Bonus: ");
            if (sc.hasNextDouble()) {
                bonus = sc.nextDouble();
                if (bonus >= 0) break;
            } else {
                sc.next();
            }
            System.out.println(RED+ "Invalid bonus! Please enter a valid positive number."  +RESET);
        }
        sc.nextLine();
        employees.add(new SalariedEmployee(id, name, address, salary, bonus));
        inputCheck = true;
        if (inputCheck) {
            System.out.println(GREEN+ "[+] ---> You added " + name + " as a SalariedEmployee successfully! [+] \n\n"  +RESET);
        }
    }
}