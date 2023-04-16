package org.example.taskfirst;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.example.taskfirst.ListSubwStream.createSubwayStation;


public class Program {
    static Logger logger = LogManager.getLogger(Program.class);

    public static void writeToFileTxt(ListSubwStream station, String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            logger.info("Write to text file");
            writer.write(station.toString());
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
    }

    public static ListSubwStream readFromFileTxt(String filename) throws IOException {
        ListSubwStream station = createSubwayStation();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            logger.info("Read from the text file");
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split(" ");
                if (parts.length == 3) {
                    String name = parts[1].substring(parts[1].indexOf("'") + 1, parts[1].lastIndexOf("'"));
                    int year = Integer.parseInt(parts[2].substring(parts[2].indexOf("=") + 1, parts[2].lastIndexOf(",")));
                    HourWithDates[] hours = parseHours(reader);
                    station = new ListSubwStream(name, year);
                    station.setHours(hours);
                }
            }
        }
        return station;
    }
    private static HourWithDates[] parseHours(BufferedReader reader) throws IOException {
        List<HourWithDates> hours = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null && line.startsWith("{") && line.endsWith("}")) {
            String[] parts = line.split(", ");
            if (parts.length == 3) {
                int passengers = Integer.parseInt(parts[0].substring(parts[0].indexOf(":") + 1, parts[0].lastIndexOf(",")));
                String comment = parts[1].substring(parts[1].indexOf("'") + 1, parts[1].lastIndexOf("'"));
                ZonedDateTime time = ZonedDateTime.parse(parts[2].substring(parts[2].indexOf(":") + 1), DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy 'ã.'"));
                hours.add(new HourWithDates(passengers, comment, time));
            }
        }
        return hours.toArray(new HourWithDates[0]);
    }

    public static void check_write_read_txt() throws IOException {
        ListSubwStream stationWrite =
                createSubwayStation();
        writeToFileTxt(stationWrite,"station.txt");
        System.out.println("\nWrite_to_file....\n");
        System.out.printf("%s\t%s",stationWrite.getName(),stationWrite.getOpeningYear());
        System.out.println(stationWrite.toStringHours(stationWrite.getHours()));

        System.out.println("Read_from_file....\n");
        ListSubwStream station = readFromFileTxt("station.txt");
        System.out.printf("%s\t%s",station.getName(),station.getOpeningYear());
        System.out.println(station.toStringHours(station.getHours()));
    }

    public static void main(String[] args) {
        try {
            check_write_read_txt();
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
    }
}
