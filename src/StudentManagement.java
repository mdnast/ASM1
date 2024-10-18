import java.util.Scanner;

public class StudentManagement {
    private StudentStack studentStack;
    private Scanner scanner;

    public StudentManagement(int capacity) {
        studentStack = new StudentStack(capacity);
        scanner = new Scanner(System.in);
    }

    public void addStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student score: ");
        double score = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Student newStudent = new Student(id, name, score);
        studentStack.push(newStudent);
    }

    public void editStudent() {
        System.out.print("Enter student ID to edit: ");
        String editId = scanner.nextLine();
        for (int i = 0; i <= studentStack.peek().getStudentID().length(); i++) {
            if (studentStack.peek().getStudentID().equals(editId)) {
                System.out.print("Enter new name (or press Enter to skip): ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) {
                    studentStack.peek().setName(newName);
                }
                System.out.print("Enter new score (or -1 to skip): ");
                double newScore = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                if (newScore != -1) {
                    studentStack.peek().setScore(newScore);
                }
                break;
            }
        }
    }

    public void deleteStudent() {
        if (!studentStack.isEmpty()) {
            studentStack.pop();
            System.out.println("Student removed from the stack.");
        } else {
            System.out.println("No students to delete.");
        }
    }

    public void searchStudent() {
        System.out.print("Enter student ID to search: ");
        String searchId = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i <= studentStack.peek().getStudentID().length(); i++) {
            if (studentStack.peek().getStudentID().equals(searchId)) {
                studentStack.peek().displayInfo();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public void displayAllStudents() {
        studentStack.displayStack();
    }
}
