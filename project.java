import java.util.Scanner;
public class project{
     public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        final int MAX_STUDENTS = 100;
        final int MAX_SUBJECTS = 10;
        float[][] studentMarks = new float[MAX_STUDENTS][MAX_SUBJECTS];
        String[] studentNames = new String[MAX_STUDENTS];
        int[] studentIDs = new int[MAX_STUDENTS];
        float[] totalMarks = new float[MAX_STUDENTS];
        float[] averageMarks = new float[MAX_STUDENTS];
        int[] subjectCounts = new int[MAX_STUDENTS];
        int studentCount = 0;
        String quit;
        while (true) {
            inputStudentDetails(scan, studentCount, studentNames, studentIDs, studentMarks, subjectCounts);
            totalMarks[studentCount] = calculateTotalMarks(studentMarks[studentCount], subjectCounts[studentCount]);
            averageMarks[studentCount] = calculateAverage(totalMarks[studentCount], subjectCounts[studentCount]);
            displayStudentDetails(studentCount, studentNames, studentIDs, studentMarks, totalMarks, averageMarks, subjectCounts);
            studentCount++;
            System.out.print("Do you want to enter details for another student? (Type 'QUIT' to stop): ");
            quit = scan.next();
            if (quit.equalsIgnoreCase("QUIT")) {
                break;
            }
        }
        displaySummaryReport(studentCount, studentNames, studentIDs, totalMarks, averageMarks);
    }
    public static void inputStudentDetails(Scanner scan, int studentIndex, String[] studentNames, int[] studentIDs, float[][] studentMarks, int[] subjectCounts) {
        System.out.print("Enter student name: ");
        studentNames[studentIndex] = scan.next();
        System.out.print("Enter student ID: ");
        studentIDs[studentIndex] = scan.nextInt();

        System.out.print("Enter the number of subjects: ");
        int numSubjects = scan.nextInt();
        subjectCounts[studentIndex] = numSubjects;

        for (int i = 0; i < numSubjects; i++) {
            float marks;
            do {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                marks = scan.nextFloat();
                if (marks < 0 || marks > 100) {
                    System.out.println("Invalid marks! Please enter a value between 0 and 100.");
                }
            } while (marks < 0 || marks > 100);

            studentMarks[studentIndex][i] = marks;
        }
    }
    public static float calculateTotalMarks(float[] marks, int numSubjects) {
        float total = 0;
        for (int i = 0; i < numSubjects; i++) {
            total += marks[i];
        }
        return total;
    }
    public static float calculateAverage(float totalMarks, int numSubjects) {
        float average =totalMarks / (float) numSubjects;
    	return average;
    }
    public static void displayStudentDetails(int studentIndex, String[] studentNames, int[] studentIDs, float[][] studentMarks, float[] totalMarks, float[] averageMarks, int[] subjectCounts) {
        System.out.println("\nStudent Details:");
        System.out.println("-------------------------");
        System.out.println("Name: " + studentNames[studentIndex]);
        System.out.println("ID: " + studentIDs[studentIndex]);

        for (int j = 0; j < subjectCounts[studentIndex]; j++) {
            String grade = calculateGrade(studentMarks[studentIndex][j]);
            System.out.println("Subject " + (j + 1) + ": Marks = " + studentMarks[studentIndex][j] + ", Grade = " + grade);
        }

        System.out.printf("Total Marks: %.2f\n", totalMarks[studentIndex]);
        System.out.printf("Average Marks: %.2f\n", averageMarks[studentIndex]);
        System.out.println("-------------------------");
    }
    public static void displaySummaryReport(int studentCount, String[] studentNames, int[] studentIDs, float[] totalMarks, float[] averageMarks) {
        System.out.println("\nSummary Report:");
        System.out.println("------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-12s | %-8s |\n", "Student ID", "Name", "Total Marks", "Average");
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < studentCount; i++) {
            System.out.printf("| %-10d | %-10s | %-12.2f | %-8.2f |\n", studentIDs[i], studentNames[i], totalMarks[i], averageMarks[i]);
        }
        System.out.println("------------------------------------------------------");
    }
    public static String calculateGrade(float marks) {
        if (marks >= 90) {
            return "A";
        } else if (marks >= 80) {
            return "B";
        } else if (marks >= 70) {
            return "C";
        } else if (marks >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
    }
