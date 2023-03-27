package gaborets.iwanof.labfirst.taskfourth;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class passCheck {
    public static void main(String[] args) {
        String[] passwords = {"ad-d5Df", "ac75d-db", "lf-Ads3", "ak-K8"};
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-*_])[a-zA-Z0-9-*_]{4,}$");
        for (int i = 0; i < passwords.length; i++) {
            Matcher matcher = pattern.matcher(passwords[i]);
            System.out.print(passwords[i]);
            if (matcher.matches()) {
                System.out.println(" is gut");
            } else {
                System.out.println(" is nicht gut");
            }
        }
    }
}
