package org.example.taskfirst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListSubwStation extends SubwayStation {
    private List<Hour> hours = new ArrayList<>();
    public ListSubwStation(String name, int openingYear) {
        setName(name);
        setOpeningYear(openingYear);
    }
    @Override
    public void sortByPassengerNumberDesc() { //da
        hours = hours.stream()
                .sorted(Comparator.comparingInt(hour -> -hour.getPassengersNumber()))
                .collect(Collectors.toList());
    }
@Override
public void sortByCommentLengthDesc() { //da
    hours = hours.stream()
            .sorted(Comparator.comparingInt(hour -> -hour.getComment().length()))
            .collect(Collectors.toList());
}
    @Override
    public Hour[] getHours() {
        return hours.toArray(new Hour[]{});
    }
    @Override
    public void setHours(Hour[] hours) {
        this.hours = Arrays.asList(hours);
    }
}
