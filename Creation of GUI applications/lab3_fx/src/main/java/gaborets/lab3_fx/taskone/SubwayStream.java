package gaborets.lab3_fx.taskone;

import com.thoughtworks.xstream.XStream;

import java.text.Collator;
import java.util.*;

public class SubwayStream extends SubwayStationList {
    public SubwayStream(String name, int openingYear) {
        super(name, openingYear);
    }
    public SubwayStream() {
        super("", 0);
    }
    @Override
    public void setName(String name) {
        super.setName(name);
    }
    @Override
    public void setHours(Hour[] hours) {
        super.setHours(hours);
    }
    @Override
    public void setOpeningYear(int openingYear) {
        super.setOpeningYear(openingYear);
    }
    @Override
    public String toString() {
        return String.format("%s='%s', %s=%d, %s:%s",
                "Subway_station_name", getName(),
                "year_of_open", getOpeningYear(),
                "Hours", toStringHours(getHours()));
    }
    public Hour[] findHoursByComment(String text) {  //da
        return Arrays.stream(getHours()).filter((hour -> {
            for (String word : hour.getComment().split(WORDS)) {
                if (word.startsWith(text) || word.endsWith(text)) {
                    return true;
                }
            }
            return false;
        })).toArray(Hour[]::new);
    }
    @Override
    public boolean addHour(Hour hour) {
        if (hour != null) {
            return super.addHour(hour);
        }
        return false;
    }

    public double getAvgPassengers() {
        return Arrays.stream(getHours()).mapToInt(Hour::getPassengersNumber).average().orElse(0);
    }
    @Override
    public void sortByCommentAlphabetically() {
        Hour[] hours = getHours();
        Collator collator = Collator.getInstance(Locale.US);

        hours = Arrays.stream(hours)
                .sorted((o1, o2) -> collator.compare(o1.getComment(), o2.getComment()))
                .toArray(Hour[]::new);

        setHours(hours);
    }

    public void setHour(Hour h) {
        Hour[] hours = getHours();
        for (int i = 0; i < hours.length; i++) {
            if (hours[i].equals(h)) {
                hours[i] = h;
                setHours(hours);
                return;
            }
        }
        Hour[] newHours = Arrays.copyOf(hours, hours.length + 1);
        newHours[newHours.length - 1] = h;
        setHours(newHours);
    }
    static SubwayStream createSubwayStation() {
        SubwayStream station = new SubwayStream("Hauptbahnhof", 1847);
        Hour[] hours = new Hour[]{
                new Hour(973, "was zu viel passangers"),
                new Hour(132, "wenig passangers"),
                new Hour(700, "midle pas"),
                new Hour(990, "was zu viel pas"),
                new Hour(1490, "das ist zu viel ich denke")
        };
        station.setHours(hours);
        return station;
    }

    public void test() {
        System.out.println(this);
        System.out.println(this.getTotalPassengersNumber());
        System.out.println(this.getHourWithMaxWordNumberInComment());
        System.out.println(this.getHourWithLeastPassengersNumber());
        System.out.println(this.getAvgPassengers());
        String comment = "very";
        System.out.printf("\nFind hours by comment heisst -> %s\n%s ", comment, this.toStringHours(this.findHoursByComment(comment)));

        this.sortByCommentLengthDesc();
        System.out.printf("SortedByCommentLengthDesc:\n%s", this);

        this.sortByPassengerNumberDesc();
        System.out.printf("sortedByPassengerNumberDesc:\n%s", this);

        this.sortByCommentAlphabetically();
        System.out.printf("sortedByCommentAlphabetically:\n%s", this);

    }

    public static void main(String[] args) {
        SubwayStream stat =
                SubwayStream.createSubwayStation();
        stat.test();
        SubwayStream subwayStream = new SubwayStream("Hauptbahnhof", 1847);
        Hour[] hours = new Hour[]{
                new Hour(973, "was zu viel passangers"),
                new Hour(132, "wenig passangers"),
                new Hour(700, "midle pas"),
                new Hour(990, "was zu viel pas"),
                new Hour(1490, "das ist zu viel ich denke")
        };
        subwayStream.setHours(hours);
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        String xml = xStream.toXML(subwayStream);
        System.out.println(xml);
    }
}
