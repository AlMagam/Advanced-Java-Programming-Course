package org.example.taskfirst;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class Add {

    public static ZonedDateTime getCurrentDateTimeMinusDays(long minusDays) {
        return ZonedDateTime.now(ZoneId.of("Europe/Kiev")).minusDays(minusDays);
    }

}