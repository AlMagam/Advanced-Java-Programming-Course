package gaborets.lab3_fx.taskone;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Objects;

@XStreamAlias("Hour")//Аннотации, как будут именоваться эти поля в XML-файле при сериализации.
public class Hour implements Comparable<Hour> {

    @XStreamAlias("passengersNumber")
    private int passengersNumber;
    @XStreamAlias("comment")
    private String comment;
    public boolean containsSubstring(String substring) {
        return getComment().toUpperCase().contains(substring.toUpperCase());
    }
    public Hour() {
    }

    public Hour(int passengersNumber, String comment) {
        if (passengersNumber < 0)
            throw new IllegalArgumentException("keine negativ");
        this.passengersNumber = passengersNumber;
        this.comment = comment;
    }
    public int getPassengersNumber() {
        return passengersNumber;
    }
    public void setPassengersNumber(int passengersNumber) {
        this.passengersNumber = passengersNumber;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString() {

        return "passengersNumber=" + passengersNumber +
                ", comment='" + comment + "'";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hour hour = (Hour) o;

        if (passengersNumber != hour.passengersNumber) return false;
        return Objects.equals(comment, hour.comment);
    }
    @Override
    public int compareTo(Hour o) {
        return Integer.compare(passengersNumber, o.passengersNumber);
    }
    public static void main(String[] args) {
        Hour hour1 = new Hour(2000, "Raz raz dva");
        Hour hour2 = new Hour(1000, "Ne raz raz dva.");

        System.out.println("hour1.toString() ==> " + hour1);
        System.out.println("hour2.toString() ==> " + hour2);

        System.out.println("\nhour1.equals(hour2) ==> " + hour1.equals(hour2));

        System.out.println("invoke hour2.setPassengersNumber(hour1.getPassengersNumber())");
        System.out.println("invoke hour2.setComment(hour1.getComment())");
        hour2.setPassengersNumber(hour1.getPassengersNumber());
        hour2.setComment(hour1.getComment());

        System.out.println("\nhour1.equals(hour2) ==> " + hour1.equals(hour2));
    }

}
