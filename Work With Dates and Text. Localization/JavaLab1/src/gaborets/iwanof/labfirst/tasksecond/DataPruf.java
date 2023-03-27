package gaborets.iwanof.labfirst.tasksecond;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.*;

public class DataPruf {
    public static void main(String[] args) {
        String[] dateString = {"06.02.2022", "1207.2001", ".04.2015", "29.02.2023"};
        Pattern pattern = Pattern.compile("^(\\d{2})\\.(\\d{2})\\.(\\d{4})$");
        for (int i = 0; i < dateString.length; i++) {
            Matcher matcher = pattern.matcher(dateString[i]);
            System.out.println("(" + dateString[i] + "):");
            if (matcher.matches()) {
                int day = Integer.parseInt(matcher.group(1));
                int month = Integer.parseInt(matcher.group(2));
                int year = Integer.parseInt(matcher.group(3));
                try {
                    Calendar calendar = new GregorianCalendar(year, month - 1, day);
                    Date date = new Date(year - 1900, month - 1, day);
                    LocalDate localDate = LocalDate.of(year, month, day);
                    System.out.println("Date: " + date);
                    System.out.println("GregorianCalendar: " + calendar.getTime());
                    System.out.println("LocalDate: " + localDate + "\n");
                } catch (Exception e){
                    System.out.println(e + "\n");
                }
            } else {System.out.println("Data format falsch\n");}
        }
    }
}
