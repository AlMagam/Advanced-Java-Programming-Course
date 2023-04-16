package org.example.taskfirst;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class XStreamJSONSerializer {
    static Logger logger = LogManager.getLogger(Program.class);

    public static void main(String[] args) throws IOException {
        ListSubwStream station = ListSubwStream.createSubwayStation();
        serialize(station, "station.json");
        ListSubwStream deserializedStation = deserialize("station.json");
        System.out.println(deserializedStation.toString());
    }

    public static void serialize(ListSubwStream obj, String filename) throws IOException {
        logger.info("Write to json_file");
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        String json = xstream.toXML(obj);
        Files.writeString(new File(filename).toPath(), json);
    }


    public static ListSubwStream deserialize(String filename) throws IOException {
        logger.info("Read from json_file");
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        String json = Files.readString(new File(filename).toPath());
        return (ListSubwStream) xstream.fromXML(json);
    }
}


