import java.util.*;
public class NonRepeating{
    static char findFirstNonRepeatingChar(String text){
        char[] txt = new char[text.length()];
        HashSet<Character> repeated = new HashSet<>();
        txt=text.toCharArray();
        for (int i = 0; i < txt.length; i++) {

            for (int j = i + 1; j < txt.length; j++) {

                if (txt[i] == txt[j]) {
                    repeated.add(txt[i]);
                    break;
                }
            }
        }
        // Find first character that is NOT repeated
        for (int i = 0; i < txt.length; i++) {

            if (!repeated.contains(txt[i])) {
                return txt[i];
            }
        }
        return '\0';

    }
    public static void main(String[] args) {
        String Duplicate;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a String: ");
        Duplicate=sc.nextLine();
        char dup=findFirstNonRepeatingChar(Duplicate);
        if(dup=='\0'){
            System.out.println("No Non-Repeating Character Found");
        }
        else{
            System.out.println("First Non-Repeating Character: "+dup);
        }
    }
}