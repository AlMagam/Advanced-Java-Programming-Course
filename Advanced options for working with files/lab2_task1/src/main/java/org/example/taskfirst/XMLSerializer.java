package org.example.taskfirst;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.TypePermission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class XMLSerializer {
    static Logger logger = LogManager.getLogger(Program.class);
    private static final XStream xstream = new XStream(new DomDriver());

    static {
        xstream.alias("station", ListSubwStream.class);
        xstream.alias("hour", Hour.class);
        xstream.alias("hour-with-dates", HourWithDates.class);

        TypePermission listSubwStreamPermission = new ListSubwStreamPermission();
        xstream.addPermission(listSubwStreamPermission);
    }

    public static String serialize(ListSubwStream station) {
        logger.info("Write to xml_file");
        return xstream.toXML(station);
    }

    public static ListSubwStream deserialize(String xml) {
        logger.info("Read from xml_file");
        return (ListSubwStream) xstream.fromXML(xml);
    }

    public static void main(String[] args) throws IOException {
        ListSubwStream station = ListSubwStream.createSubwayStation();
        String xml = XMLSerializer.serialize(station);
        ListSubwStream deserializedStation = XMLSerializer.deserialize(xml);

        System.out.printf("%s\t%s",deserializedStation.getName(),deserializedStation.getOpeningYear());
        System.out.println(station.toStringHours(deserializedStation.getHours()));

    }
}
