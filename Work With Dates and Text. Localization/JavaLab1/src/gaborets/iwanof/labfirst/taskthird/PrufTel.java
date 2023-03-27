package gaborets.iwanof.labfirst.taskthird;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//phoneKod
//https://www.prostobank.ua/spravochniki/kompanii/id/1027#:~:text=%D0%9A%D0%BE%D0%B4%D0%B0%D0%BC%D0%B8%20%D0%B4%D0%BB%D1%8F%20%D0%B7%D0%B2%D0%BE%D0%BD%D0%BA%D0%BE%D0%B2%20%D0%BD%D0%B0%20%D1%82%D0%B5%D0%BB%D0%B5%D1%84%D0%BE%D0%BD%D1%8B,%2C%20%D0%9C%D0%BE%D0%B1%D0%B8%D0%BB%D1%8B%D1%87%20(%D0%A2%D0%B0%D1%80%D0%B8%D1%84%D0%BD%D1%8B%D0%B9%20%D0%BF%D0%BB%D0%B0%D0%BD).
public class PrufTel {
    public static void main(String[] args) {
        String[] telNumbers = {"38(068) 355-12-32",
                "+38(097)354 23 12",
                "+38(099) 355 12 41",
                "+38(045)-354-23-12"
        };
        String regexPhone = "^\\+?38\\(0(68|98|97|96|67)\\)(-|\\s)?\\d{3}(-|\\s)\\d{2}(-|\\s)\\d{2}$";
        for(String el : telNumbers){
            boolean is_kor = el.matches(regexPhone);
            if(is_kor){
                System.out.println(el + " is korekt\n");
            }else{
                System.out.println(el + " is falsch\n");
            }
        }
    }
}
