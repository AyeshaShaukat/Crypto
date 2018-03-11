import java.awt.*;
import java.util.*;
//import java.io.*;

public class Crypto {
    public static void main(String[] args) {
        System.out.println("Methods of encrypting and decrypting text");

        Scanner input = new Scanner(System.in);
        System.out.print("Write text: ");
        String text = input.nextLine();

        //Part 1 - Normalize Text
        System.out.println(text = normalizeText(text));

        //Part 2 - Caesar Cipher
        System.out.print("Enter shift value: ");
        int shiftValue = input.nextInt();
        System.out.println(caesarify(text, shiftValue));

        //Part 3 - Code Groups
        System.out.print("Enter size to make code groups: ");
        int groupSize = input.nextInt();
        String codeGroupedString = groupify(text, groupSize);

        //Part 4 - Putting it all together
        System.out.println("Encrypting string... ");
        String cypherText = encryptString(text,shiftValue,groupSize);
        System.out.println(cypherText);

        //Part 5 - Hacker Problem - Decrypt
        ungroupify(codeGroupedString);
        System.out.println("Decrypting string...");
        String plainText = decryptString(cypherText,shiftValue);
        System.out.println(plainText);
    }

    public static String normalizeText(String t){
        String temp = "";

        //Removes all the spaces from your text
        // & Remove any punctuation (. , : ; ’ ” ! ? ( ) )
        temp = t.replaceAll("[\\s.,:;'!?()\"]", "");

        //Turn all lower-case letters into upper-case letters
        temp = temp.toUpperCase();
        return temp;
    }

    public static String caesarify(String t, int sValue){
        String temp = shiftAlphabet(sValue);
        //Encrypting normalized text to encrypted key
        temp = t.replace(t,temp);
        return temp;
    }

    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;

        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }

    public static String groupify(String t, int n){
        //Break the string according to given number of group size
        String temp = "";
        int count = 0;
        for(int i = 0; i < t.length(); i++) {
            if (i % n == 0 && i != 0) {     //It means if iterator value and number of letters value is same
                temp = temp + " ";
            }
            temp = temp + t.charAt(i);
        }
        //For loop to know how many x's are needed
        //Count from last space till last index
        for(int i = temp.lastIndexOf(" ")+1; i < temp.length(); i++) {
            ++count;
        }
        //Append x's at the last
        for(int i = n - count; i > 0; i--){
            temp += "x";
        }
        return temp;
    }

    public static String encryptString(String t, int sValue, int n)
    {
        String temp = "";
        temp = normalizeText(t);
        temp = caesarify(t,sValue);
        temp = groupify(t,n);

        return temp;
    }

    public static String ungroupify(String groupedStr){
        String temp = groupedStr.replaceAll("[\\sx]", "");
        return temp;
    }

    public static String decryptString(String t, int sValue){
        return ungroupify(t);
    }
}
