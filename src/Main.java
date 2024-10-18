import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement(10);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add student");
            System.out.println("2. Edit student");
            System.out.println("3. Delete student");
            System.out.println("4. Search student");
            System.out.println("5. Display all students");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    management.addStudent();
                    break;
                case 2:
                    management.editStudent();
                    break;
                case 3:
                    management.deleteStudent();
                    break;
                case 4:
                    management.searchStudent();
                    break;
                case 5:
                    management.displayAllStudents();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
