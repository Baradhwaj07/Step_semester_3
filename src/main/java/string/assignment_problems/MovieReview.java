public class MovieReview {
    static void classifyWordLengths(String review){
        String[] rev = review.split(" ");
        int sht = 0;
        int mid = 0;
        int lng = 0;
        for(int i=0;i<rev.length;i++){
            if(rev[i].length()<=4){
                sht++;
            }
            else if(rev[i].length()<=8){
                mid++;
            }
            else{
                lng++;
            }
        }
        System.out.println("Short: "+sht+" | Medium: "+mid+" | Long: "+lng);
    }
    public static void main(String[] args) {
        MovieReview.classifyWordLengths("This movie was absolutely fantastic and thrilling");
    }
}
