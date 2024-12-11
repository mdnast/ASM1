import java.util.Random;
import java.util.Scanner;

public class StudentManagement {
    private StudentStack studentStack;
    private Scanner scanner;
    private static final String[] NAMES = {
            "Alex", "Maria", "John", "Sophia", "James", "Emma", "Michael", "Olivia", "Daniel", "Isabella"
    };

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

    public void addRandomStudents(int count) {
        Random random = new Random();
        long startTime = System.nanoTime(); // Start time measurement

        for (int i = 0; i < count; i++) {
            String id = "S" + (studentStack.size() + 1); // Sequential ID
            String name = NAMES[random.nextInt(NAMES.length)]; // Random name
            double score = 5 + random.nextDouble() * 5; // Score between 5 and 10
            Student newStudent = new Student(id, name, score);
            studentStack.push(newStudent); // Add to stack
        }

        long endTime = System.nanoTime(); // End time measurement
        long elapsedTime = endTime - startTime; // Execution time in nanoseconds

        // Display the list of added students
       // System.out.println("\n" + count + " random students added successfully:");
        //studentStack.displayStack();

        // Display execution time
       // System.out.println("\nTime taken to add " + count + " students: " + elapsedTime + " ns");
    }

    public void editStudent() {
        // Code for editing remains unchanged
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

        // Traverse the stack to find the student
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

    public void sortStudentsByScore() {
        if (studentStack.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }

        // Transfer stack to an array for sorting
        int n = studentStack.size();
        Student[] studentsArray = new Student[n];
        for (int i = 0; i < n; i++) {
            studentsArray[i] = studentStack.pop(); // Extract each element from the stack
        }

        // Start time measurement
        long startTime = System.nanoTime();

        // Bubble Sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (studentsArray[j].getScore() > studentsArray[j + 1].getScore()) {
                    Student temp = studentsArray[j];
                    studentsArray[j] = studentsArray[j + 1];
                    studentsArray[j + 1] = temp;
                }
            }
        }

        // End time measurement
        long endTime = System.nanoTime();

        // Calculate execution time in nanoseconds
        long elapsedTime = endTime - startTime;

        // Push sorted data back to the stack
        for (int i = 0; i < n; i++) {
            studentStack.push(studentsArray[i]);
        }

        // Display sorted results
        System.out.println("Students sorted by score in ascending order:");
        for (Student student : studentsArray) {
            student.displayInfo(); // Display each student
        }

        // Display execution time
        System.out.println("Time taken to sort: " + elapsedTime + " ns");
    }

    public void mergeSortStudentsByScore() {
        if (studentStack.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }

        // Transfer stack to an array for sorting
        int n = studentStack.size();
        Student[] studentsArray = new Student[n];
        for (int i = 0; i < n; i++) {
            studentsArray[i] = studentStack.pop();
        }

        // Start time measurement
        long startTime = System.nanoTime();

        // Merge Sort
        mergeSort(studentsArray, 0, n - 1);

        // End time measurement
        long endTime = System.nanoTime();

        // Calculate execution time in nanoseconds
        long elapsedTime = endTime - startTime;

        // Push sorted data back to the stack
        for (int i = 0; i < n; i++) {
            studentStack.push(studentsArray[i]);
        }

        // Display sorted results
        System.out.println("Students sorted by score using Merge Sort in ascending order:");
        for (Student student : studentsArray) {
            student.displayInfo();
        }

        // Display execution time
        System.out.println("Time taken to sort with Merge Sort: " + elapsedTime + " ns");
    }

    // Merge Sort method
    private void mergeSort(Student[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Divide
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            // Merge
            merge(array, left, mid, right);
        }
    }

    // Merge function
    private void merge(Student[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays
        Student[] leftArray = new Student[n1];
        Student[] rightArray = new Student[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

        // Merge the temporary arrays
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].getScore() <= rightArray[j].getScore()) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public void displayAllStudents() {
        studentStack.displayStack();
    }

    public void compareSortingAlgorithms() {
        if (studentStack.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }

        // Create a backup of the stack to avoid modifying the original data
        int n = studentStack.size();
        Student[] originalArray = new Student[n];
        for (int i = 0; i < n; i++) {
            originalArray[i] = studentStack.pop();
        }

        // Clone data for each algorithm
        Student[] bubbleSortArray = originalArray.clone();
        Student[] mergeSortArray = originalArray.clone();

        // Measure time for Bubble Sort
        long startBubble = System.nanoTime();
        bubbleSort(bubbleSortArray);
        long endBubble = System.nanoTime();
        long bubbleTime = endBubble - startBubble;

        // Measure time for Merge Sort
        long startMerge = System.nanoTime();
        mergeSort(mergeSortArray, 0, mergeSortArray.length - 1);
        long endMerge = System.nanoTime();
        long mergeTime = endMerge - startMerge;

        // Display comparison results
        System.out.println("\nComparison of Sorting Algorithms:");
        System.out.println("Bubble Sort time: " + bubbleTime + " ns");
        System.out.println("Merge Sort time: " + mergeTime + " ns");

        // Optionally display sorted lists from both algorithms
        System.out.println("\nStudents sorted by Bubble Sort:");
        for (Student student : bubbleSortArray) {
          ///  student.displayInfo();
        }

        System.out.println("\nStudents sorted by Merge Sort:");
        for (Student student : mergeSortArray) {
          ///  student.displayInfo();
        }

        // Push the original data back into the stack
        for (Student student : originalArray) {
            studentStack.push(student);
        }
    }

    // Bubble Sort Method
    private void bubbleSort(Student[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].getScore() > array[j + 1].getScore()) {
                    Student temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
