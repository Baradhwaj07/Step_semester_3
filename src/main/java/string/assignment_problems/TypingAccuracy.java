public class TypingAccuracy {
    static void checkTypingAccuracy(String original, String typed){
        int count = 0;
        int i;
        float accuracy;
        String type = "";
        String ori = "";
        int j=0;
        for(i=0;i<typed.length();i++){
            if(original.charAt(i)==typed.charAt(i)){
                count++;
            }
            else{
                ori=String.valueOf(original.charAt(i));
                type=String.valueOf(typed.charAt(i));
                j=i;

            }
        }
        float stringlen = typed.length();
        accuracy=((float)count/stringlen)*100;
        if(count<typed.length()){
            System.out.println("Matched: "+count+"/"+stringlen+" | Accuracy: "+accuracy+" | First Mismatch at position "+j+" ( "+ori+" vs "+type);
        }
        else{
            System.out.println("Matched: "+count+"/"+stringlen+" | Accuracy: "+accuracy+" | No Mismatch");
        }
    }
    public static void main(String[] args) {
        TypingAccuracy.checkTypingAccuracy("hello world", "hello wortd");
        TypingAccuracy.checkTypingAccuracy("coding", "coding");
    }
}