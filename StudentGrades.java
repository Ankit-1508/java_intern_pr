import java.util.*;
public class StudentGrades {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> grades = new ArrayList<>();
        System.out.println("Enter student grades (type -1 to finish):");
        while (true) {
            System.out.print("Enter grade: ");
            double grade = scanner.nextDouble();
            if (grade == -1) {
                break;
            }
            if (grade<0||grade>100) {
                System.out.println("Invalid grade. Please enter a value between 0 and 100.");
                continue;
            }
            grades.add(grade);
        }
        if(grades.isEmpty()) {
            System.out.println("No grades entered.");
        } else {
            double sum = 0;
            double max = grades.get(0);
            double min = grades.get(0);

            for (double g : grades) {
                sum+=g;
                if(g>max)max=g;
                if (g<min)min=g;
            }
            double average=sum/grades.size();
            System.out.printf("Number of students: %d\n", grades.size());
            System.out.printf("Average grade: %.2f\n", average);
            System.out.printf("Highest grade: %.2f\n", max);
            System.out.printf("Lowest grade: %.2f\n", min);
        }
    }
}
