package gaborets.iwanof.labfirst.taskfive;

import java.util.Arrays;

public class GetArSubstr {
    public static void main(String[] args) {
        String inputString = "asd12abc345def67gh89ijds2s";
        if(inputString.length() >= 20) {
            String[] substr = inputString.split("\\d+");
            for(String str : substr) {
//                if(!str.matches("\\d+")){
                    System.out.println(str);
                //}
            }
        }
    }
}
