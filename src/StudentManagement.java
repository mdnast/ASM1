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
        long startTime = System.nanoTime(); // Bắt đầu đo thời gian

        for (int i = 0; i < count; i++) {
            String id = "S" + (studentStack.size() + 1); // ID tuần tự
            String name = NAMES[random.nextInt(NAMES.length)]; // Tên ngẫu nhiên
            double score = 5 + random.nextDouble() * 5; // Điểm từ 5 đến 10
            Student newStudent = new Student(id, name, score);
            studentStack.push(newStudent); // Thêm vào stack
        }

        long endTime = System.nanoTime(); // Kết thúc đo thời gian
        long elapsedTime = endTime - startTime; // Thời gian chạy (nano giây)

        // Hiển thị danh sách sinh viên đã thêm
        System.out.println("\n" + count + " random students added successfully:");
        studentStack.displayStack();

        // Hiển thị thời gian chạy
        System.out.println("\nTime taken to add " + count + " students: " + elapsedTime + " ns");
    }
    public void editStudent() {
        // Mã chỉnh sửa không thay đổi
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

        // Duyệt toàn bộ stack để tìm sinh viên
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

        // Chuyển stack sang mảng để dễ sắp xếp
        int n = studentStack.size();
        Student[] studentsArray = new Student[n];
        for (int i = 0; i < n; i++) {
            studentsArray[i] = studentStack.pop(); // Lấy từng phần tử từ stack
        }

        // Đo thời gian bắt đầu
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

        // Đo thời gian kết thúc
        long endTime = System.nanoTime();

        // Tính toán thời gian chạy (nano giây)
        long elapsedTime = endTime - startTime;

        // Đưa dữ liệu đã sắp xếp trở lại stack
        for (int i = 0; i < n; i++) {
            studentStack.push(studentsArray[i]);
        }

        // Hiển thị kết quả sắp xếp
        System.out.println("Students sorted by score in ascending order:");
        for (Student student : studentsArray) {
            student.displayInfo(); // Hiển thị từng sinh viên
        }

        // Hiển thị thời gian chạy
        System.out.println("Time taken to sort: " + elapsedTime + " ns");
    }
    public void mergeSortStudentsByScore() {
        if (studentStack.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }

        // Chuyển stack sang mảng để dễ sắp xếp
        int n = studentStack.size();
        Student[] studentsArray = new Student[n];
        for (int i = 0; i < n; i++) {
            studentsArray[i] = studentStack.pop();
        }

        // Đo thời gian bắt đầu
        long startTime = System.nanoTime();

        // Merge Sort
        mergeSort(studentsArray, 0, n - 1);

        // Đo thời gian kết thúc
        long endTime = System.nanoTime();

        // Tính toán thời gian chạy (nano giây)
        long elapsedTime = endTime - startTime;

        // Đưa dữ liệu đã sắp xếp trở lại stack
        for (int i = 0; i < n; i++) {
            studentStack.push(studentsArray[i]);
        }

        // Hiển thị kết quả sắp xếp
        System.out.println("Students sorted by score using Merge Sort in ascending order:");
        for (Student student : studentsArray) {
            student.displayInfo();
        }

        // Hiển thị thời gian chạy
        System.out.println("Time taken to sort with Merge Sort: " + elapsedTime + " ns");
    }

    // Hàm Merge Sort
    private void mergeSort(Student[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Chia nhỏ
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            // Trộn lại
            merge(array, left, mid, right);
        }
    }

    // Hàm Trộn
    private void merge(Student[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Tạo mảng tạm thời
        Student[] leftArray = new Student[n1];
        Student[] rightArray = new Student[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

        // Trộn hai mảng tạm lại với nhau
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

        // Sao chép các phần tử còn lại
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

        // Tạo bản sao Stack để đảm bảo không ảnh hưởng đến dữ liệu gốc
        int n = studentStack.size();
        Student[] originalArray = new Student[n];
        for (int i = 0; i < n; i++) {
            originalArray[i] = studentStack.pop();
        }

        // Clone dữ liệu cho từng thuật toán
        Student[] bubbleSortArray = originalArray.clone();
        Student[] mergeSortArray = originalArray.clone();

        // Đo thời gian Bubble Sort
        long startBubble = System.nanoTime();
        bubbleSort(bubbleSortArray);
        long endBubble = System.nanoTime();
        long bubbleTime = endBubble - startBubble;

        // Đo thời gian Merge Sort
        long startMerge = System.nanoTime();
        mergeSort(mergeSortArray, 0, mergeSortArray.length - 1);
        long endMerge = System.nanoTime();
        long mergeTime = endMerge - startMerge;

        // Hiển thị kết quả so sánh
        System.out.println("\nComparison of Sorting Algorithms:");
        System.out.println("Bubble Sort time: " + bubbleTime + " ns");
        System.out.println("Merge Sort time: " + mergeTime + " ns");

        // Hiển thị danh sách đã sắp xếp bằng cả hai thuật toán (tùy chọn)
        System.out.println("\nStudents sorted by Bubble Sort:");
        for (Student student : bubbleSortArray) {
            student.displayInfo();
        }

        System.out.println("\nStudents sorted by Merge Sort:");
        for (Student student : mergeSortArray) {
            student.displayInfo();
        }

        // Đưa lại dữ liệu gốc vào Stack
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
