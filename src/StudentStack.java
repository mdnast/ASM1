public class StudentStack {
    private Student[] stack;
    private int top;
    private int capacity;

    // Constructor to initialize the stack
    public StudentStack(int capacity) {
        this.capacity = capacity;
        stack = new Student[capacity];
        top = -1;
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Method to check if the stack is full
    public boolean isFull() {
        return top == capacity - 1;
    }

    // Method to add a student to the stack (push)
    public void push(Student student) {
        if (!isFull()) {
            stack[++top] = student;
        } else {
            System.out.println("Stack is full, cannot add more students.");
        }
    }

    // Method to remove and return the top student (pop)
    public Student pop() {
        if (!isEmpty()) {
            return stack[top--];
        } else {
            System.out.println("Stack is empty, cannot pop.");
            return null;
        }
    }

    // Method to return the top student without removing (peek)
    public Student peek() {
        if (!isEmpty()) {
            return stack[top];
        } else {
            System.out.println("Stack is empty.");
            return null;
        }
    }

    // Method to display all students in the stack
    public void displayStack() {
        if (!isEmpty()) {
            for (int i = 0; i <= top; i++) {
                stack[i].displayInfo();
            }
        } else {
            System.out.println("Stack is empty.");
        }
    }
}
