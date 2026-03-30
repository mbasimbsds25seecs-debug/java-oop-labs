import java.util.Scanner;

class Student {
    protected String name;
    protected int rollNumber;
    protected String department;

    public Student(String name, int rollNumber, String department) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.department = department;
    }

    public double calculateGrade() {
        return 0.0;
    }

    public String getGradeCategory(double marks) {
        if (marks >= 85) return "Excellent";
        else if (marks >= 60) return "Good";
        else return "Needs Improvement";
    }

    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public String getDepartment() { return department; }
}


class ExamStudent extends Student {
    private double examMarks;

    public ExamStudent(String name, int rollNumber, String department, double examMarks) {
        super(name, rollNumber, department);
        this.examMarks = examMarks;
    }

    @Override
    public double calculateGrade() {
        return examMarks; 
    }

    @Override
    public String toString() {
        return "[EXAM]        " + name + " | Roll: " + rollNumber + " | Marks: " + String.format("%.2f", calculateGrade());
    }
}


class ProjectStudent extends Student {
    private double quizMarks;
    private double projectMarks;
    private double presentationMarks;

    public ProjectStudent(String name, int rollNumber, String department,
                          double quizMarks, double projectMarks, double presentationMarks) {
        super(name, rollNumber, department);
        this.quizMarks = quizMarks;
        this.projectMarks = projectMarks;
        this.presentationMarks = presentationMarks;
    }

    @Override
    public double calculateGrade() {
    
        return (quizMarks * 0.20) + (projectMarks * 0.50) + (presentationMarks * 0.30);
    }

    @Override
    public String toString() {
        return "[PROJECT]     " + name + " | Roll: " + rollNumber + " | Marks: " + String.format("%.2f", calculateGrade());
    }
}


class AttendanceStudent extends Student {
    private double baseMarks;
    private double attendancePercentage;

    public AttendanceStudent(String name, int rollNumber, String department,
                             double baseMarks, double attendancePercentage) {
        super(name, rollNumber, department);
        this.baseMarks = baseMarks;
        this.attendancePercentage = attendancePercentage;
    }

    @Override
    public double calculateGrade() {
        // attendance above 90% gives 5 bonus marks, capped at 100
        double bonus = (attendancePercentage >= 90) ? 5 : 0;
        return Math.min(100, baseMarks + bonus);
    }

    @Override
    public String toString() {
        return "[ATTENDANCE]  " + name + " | Roll: " + rollNumber + " | Marks: " + String.format("%.2f", calculateGrade());
    }
}

public class lab8 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("========== TASK 3: UNIVERSITY GRADING ECOSYSTEM ==========\n");

        System.out.print("How many students do you want to enter? ");
        int n = sc.nextInt();
        sc.nextLine();

        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Student " + (i + 1) + " ---");
            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter roll number: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter department: ");
            String dept = sc.nextLine();

            System.out.println("Evaluation type:");
            System.out.println("  1 = Exam only");
            System.out.println("  2 = Quiz + Project + Presentation");
            System.out.println("  3 = Base marks + Attendance");
            System.out.print("Choose (1/2/3): ");
            int type = sc.nextInt();
            sc.nextLine();

            if (type == 1) {
                System.out.print("Enter exam marks (out of 100): ");
                double exam = sc.nextDouble();
                sc.nextLine();
                students[i] = new ExamStudent(name, roll, dept, exam);

            } else if (type == 2) {
                System.out.print("Enter quiz marks (out of 100): ");
                double quiz = sc.nextDouble();
                System.out.print("Enter project marks (out of 100): ");
                double project = sc.nextDouble();
                System.out.print("Enter presentation marks (out of 100): ");
                double presentation = sc.nextDouble();
                sc.nextLine();
                students[i] = new ProjectStudent(name, roll, dept, quiz, project, presentation);

            } else {
                System.out.print("Enter base marks (out of 100): ");
                double base = sc.nextDouble();
                System.out.print("Enter attendance percentage (e.g. 95): ");
                double attendance = sc.nextDouble();
                sc.nextLine();
                students[i] = new AttendanceStudent(name, roll, dept, base, attendance);
            }
        }

        // Process all students together
        System.out.println("\n       GRADE REPORT       ");
        Student topStudent = students[0];

        for (Student s : students) {
            double grade = s.calculateGrade();
            System.out.println(s + " | Category: " + s.getGradeCategory(grade));
            if (grade > topStudent.calculateGrade()) {
                topStudent = s;
            }
        }

        System.out.println("\nTop Performing Student: " + topStudent.getName()
                + " (Roll: " + topStudent.getRollNumber() + ")"
                + " with " + String.format("%.2f", topStudent.calculateGrade()) + " marks");

        // Evaluate a single student independently
        System.out.println("\n       SINGLE STUDENT EVALUATION       ");
        System.out.println("\nEnter details for one more student:");
        System.out.print("Enter name: ");
        String sName = sc.nextLine();

        System.out.print("Enter roll number: ");
        int sRoll = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter department: ");
        String sDept = sc.nextLine();

        System.out.print("Enter exam marks (out of 100): ");
        double sMarks = sc.nextDouble();
        sc.nextLine();

        Student single = new ExamStudent(sName, sRoll, sDept, sMarks);
        double sGrade = single.calculateGrade();
        System.out.println("\n" + single + " | Category: " + single.getGradeCategory(sGrade));

        sc.close();
    }
}