import java.util.Scanner;
public class NameReversal{
    static String reverseCustomerName(String customerName){
        char[] arr=new char[customerName.length()];
        arr=customerName.toCharArray();
        String newName="";
        for(int i=arr.length-1;i>=0;i--){
            newName+=arr[i];
        }
        return newName;
    }
    public static void main(String[] args) {
        String customerName;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Customer Name: ");
        customerName=sc.nextLine();
        String newName;
        newName=reverseCustomerName(customerName);
        System.out.println(newName);
    }
}