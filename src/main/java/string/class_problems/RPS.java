import java.util.*;
public class RPS{
    static int win=0, lose=0, draw=0;
    static String playRound(String playerMove, String computerMove){
        String result;
        if(playerMove.trim().equalsIgnoreCase(computerMove)){
            draw++;
            result="Draw";
        }
        else if((playerMove.trim().equalsIgnoreCase("rock")&&computerMove.equalsIgnoreCase("scissors"))||(playerMove.trim().equalsIgnoreCase("paper")&&computerMove.equalsIgnoreCase("rock"))||(playerMove.trim().equalsIgnoreCase("scissors")&&computerMove.equalsIgnoreCase("paper"))){
            win++;
            result="Player Wins";
        }
        else{
            lose++;
            result="Computer Wins";
        }
        return result;
    }
    public static void main(String[] args) {
        double percent;
        String playerMove, computerMove = "";
        Random r=new Random();
        Scanner s = new Scanner(System.in);
        int a,n=5;
        for(int i=0;i<n;i++){
            a=r.nextInt(3);
            switch(a){
                case 0:
                    computerMove="rock";
                    break;
                case 1:
                    computerMove="paper";
                    break;
                case 2:
                    computerMove="scissors";
                    break;
                default:
                    System.out.println("Invalid");
            }
            System.out.print("Enter a choice: ");
            playerMove=s.next();
            if((playerMove.equalsIgnoreCase("rock"))||(playerMove.equalsIgnoreCase("Rock"))||(playerMove.equalsIgnoreCase("Scissors"))||(playerMove.equalsIgnoreCase("scissor"))||(playerMove.equalsIgnoreCase("Paper"))||(playerMove.equalsIgnoreCase("paper"))){
                System.out.println(playRound(playerMove,computerMove));
            }
            else{
                System.out.println("Invalid Input");
            }
        }
        percent=((double)win*100)/((double)win+(double)draw+(double)lose);
        System.out.println("Wins: "+win+" Lose: "+lose+" Draw: "+draw+" Win Percent: "+percent);
    }
}