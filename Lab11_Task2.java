 
import java.util.Scanner;

public class Lab11_Task2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numSubjects = 0;

        System.out.println("||||| NUST Result Processing System |||||");

        // Get number of subjects
        while (true) {
            System.out.print("Enter number of subjects: ");
            try {
                numSubjects = Integer.parseInt(sc.nextLine().trim());
                if (numSubjects <= 0) {
                    System.out.println("Number of subjects must be greater than zero.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        double[] marks = new double[numSubjects];
        double total = 0;

        // Get marks for each subject
        for (int i = 0; i < numSubjects; i++) {
            while (true) {
                System.out.print("Enter marks for Subject " + (i + 1) + " (0 - 100): ");
                try {
                    double mark = Double.parseDouble(sc.nextLine().trim());

                    if (mark < 0) {
                        throw new IllegalArgumentException("Marks cannot be negative.");
                    }
                    if (mark > 100) {
                        throw new IllegalArgumentException("Marks cannot exceed 100.");
                    }

                    marks[i] = mark;
                    break;

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

        // Calculate total
        for (double mark : marks) {
            total += mark;
        }

        // Calculate average with division by zero protection
        double average = 0;
        try {
            if (numSubjects == 0) {
                throw new ArithmeticException("Cannot calculate average with zero subjects.");
            }
            average = total / numSubjects;
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Display results
        System.out.println("\n===== Result Summary =====");
        for (int i = 0; i < numSubjects; i++) {
            System.out.printf("Subject %d: %.2f%n", (i + 1), marks[i]);
        }
        System.out.printf("Total Marks : %.2f%n", total);
        System.out.printf("Average     : %.2f%n", average);
        System.out.println("Grade       : " + getGrade(average));

        sc.close();
    }

    static String getGrade(double average) {
        if (average >= 90) return "A+";
        else if (average >= 80) return "A";
        else if (average >= 70) return "B";
        else if (average >= 60) return "C";
        else if (average >= 50) return "D";
        else return "F";
    }
}