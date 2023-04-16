package org.example.taskfirst;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class HourWithDates extends Hour {
    private ZonedDateTime timeOfEditing;

    public HourWithDates(int passengersNumber, String comment, ZonedDateTime timeOfEditing) {
        super(passengersNumber, comment);
        this.timeOfEditing = timeOfEditing;
    }

    public ZonedDateTime getTimeOfEditing() {
        return timeOfEditing;
    }

    public void setTimeOfEditing(ZonedDateTime timeOfEditing) {
        this.timeOfEditing = timeOfEditing;
    }

    @Override
    public String getComment() {
        return super.getComment();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL);
        return String.format(
                "{%s: %d, %s: '%s', %s: %s}",
                "Pass_num", getPassengersNumber(),
                "Comment", getComment(),
                "Time_of_editing", getTimeOfEditing().format(formatter));
    }
}
