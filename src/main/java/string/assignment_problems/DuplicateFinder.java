public class DuplicateFinder{
    static void checkDuplicateSeats(int[] seatNumbers){
        int i,j;
        int count =0;
        int[] dup = new int[seatNumbers.length];
        for(i=0;i<seatNumbers.length;i++){
            for(j=i+1;j<seatNumbers.length;j++){
                if(seatNumbers[i]==seatNumbers[j]){
                    dup[count]=seatNumbers[i];
                    count++;
                }
            }
        }
        if(count>0){
            System.out.print("\nDuplicate Seat Numbers found ");
            for(i=0;i<count;i++){
                System.out.print(dup[i]+"\n");
            }
        }
        else{
            System.out.println("No Duplicate Seats found");
        }
    }
    public static void main(String[] args) {
        int[] seatNumbers1 = {101,102,103,102,105};
        int[] seatNumbers2 = {101,102,103,104,105};
        DuplicateFinder.checkDuplicateSeats(seatNumbers1);
        DuplicateFinder.checkDuplicateSeats(seatNumbers2);
    }
}