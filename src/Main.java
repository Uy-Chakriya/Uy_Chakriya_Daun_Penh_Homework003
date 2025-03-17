import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";
        String RED = "\u001B[31m";

        Scanner sc = new Scanner(System.in);
        DisplayAllMethod.ChooseShowTable();
        DisplayAllMethod display = new DisplayAllMethod();
        int option;
        do {
            System.out.print("\n"+  GREEN+ ">> Choose an option() : " +RESET);
            option = sc.nextInt();
            switch (option) {
                case 1:
                    display.InsertEmployee();
                    break;
                case 2:
                    display.UpdateEmployee();
                    break;
                case 3:
                    display.DisplayEmployee();
                    display.Pagination();
                    break;
                case 4:
                    display.RemoveEmployee();
                    break;
                case 5:
                    System.out.println(GREEN+ "Exiting..." +RESET);
                    break;
                default:
                    System.out.println(RED+ "Invalid Input try again!" +RESET);
            }
        } while (option != 5);
    }
}