import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement(100000); // Tăng dung lượng stack để chứa nhiều sinh viên
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add student");
            System.out.println("2. Edit student");
            System.out.println("3. Delete student");
            System.out.println("4. Search student");
            System.out.println("5. Display all students");
            System.out.println("6. Add random students");
            System.out.println("7. Sort students by Bubble Sort");
            System.out.println("8. Sort students by Merge Sort");
            System.out.println("9. Compare Bubble Sort and Merge Sort");
            System.out.println("10. Exit");

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
                    System.out.print("Enter the number of random students to add (e.g., 100, 1000, 10000): ");
                    int count = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    management.addRandomStudents(count);
                    break;
                case 7:
                    management.sortStudentsByScore();
                    break;
                case 8:
                    management.mergeSortStudentsByScore(); // Merge Sort
                    break;
                case 9:
                    management.compareSortingAlgorithms();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
