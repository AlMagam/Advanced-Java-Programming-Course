package gaborets.lab3_fx.taskone;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XStreamAlias("MetroStationList")
public class SubwayStationList extends SubwayStation {
    @XStreamImplicit(itemFieldName = "hours")
    private List<Hour> hours = new ArrayList<>();

    public SubwayStationList(String name, int openingYear) {
        setName(name);
        setOpeningYear(openingYear);
    }
    public int hoursCount() {
        return hours.size();
    }
    @Override
    public void sortByPassengerNumberDesc() {
        hours.sort(((o1, o2) -> Integer.compare(o2.getPassengersNumber(), o1.getPassengersNumber())));
    }
    @Override
    public void sortByCommentLengthDesc() {
        hours.sort((o1, o2) -> Integer.compare(o2.getComment().length(), o1.getComment().length()));
    }
    @Override
    public Hour[] getHours() {
        return hours.toArray(new Hour[]{});
    }
    @Override
    public Hour getHour(int i) {
        return hours.get(i);
    }
    @Override
    public void setHours(Hour[] hours) {
        this.hours = Arrays.asList(hours);
    }
    public boolean addHour(Hour hour) {
        if (hours.contains(hour)) {
            return false;
        }
        return hours.add(hour);
    }
    public void addHour(String comment, int passNum) {
        addHour(new Hour(passNum, comment));
    }
}
