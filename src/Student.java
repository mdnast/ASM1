public class Student {
    private String studentID;
    private String name;
    private double score;
    private String rank;

    public Student(String studentID, String name, double score) {
        this.studentID = studentID;
        this.name = name;
        this.score = score;
        this.rank = calculateRank();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
        this.rank = calculateRank();
    }

    public String getRank() {
        return rank;
    }

    private String calculateRank() {
        if (score < 5.0) {
            return "Fail";
        } else if (score >= 5.0 && score < 6.5) {
            return "Medium";
        } else if (score >= 6.5 && score < 7.5) {
            return "Good";
        } else if (score >= 7.5 && score < 9.0) {
            return "Very Good";
        } else {
            return "Excellent";
        }
    }

    public void displayInfo() {
        System.out.println("ID: " + studentID + ", Name: " + name + ", Score: " + score + ", Rank: " + rank);
    }
}
