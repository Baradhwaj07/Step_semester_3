import java.util.Scanner;
public class BMI{
    static String getBmiStatus(double bmi){
        if(bmi<18.5){
            return "UnderWeight";
        }
        else if(bmi>=30){
            return "Obese";
        }
        else{
            return "Normal";
        }
    }
    static void printWellnessReport(double[] heights, double[] weights){
        double bmi;
        for(int i = 0;i<n;i++){
            bmi=weights[i]/(heights[i]*heights[i]);
            bmi=Math.round(bmi*100)/100.00;
            System.out.println("BMI: "+bmi+" | Status: "+getBmiStatus(bmi));
        }
    }
    static int n=2;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] heights = new double[n];
        double[] weights = new double[n];
        for(int i=0;i<n;i++){
            System.out.print("Person "+(i+1)+"\nHeight: ");
            heights[i]=sc.nextDouble();
            System.out.print("Weight: ");
            weights[i]=sc.nextDouble();
            System.out.print("\n");
        }
        printWellnessReport(heights, weights);
    }
}