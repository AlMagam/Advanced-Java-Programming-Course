package gaborets.lab3_fx.taskone;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.*;

public class FileStuff {
    public static void serializeToXML(SubwayStream subwayStream, String fileName) throws IOException {
        XStream xstream = new XStream();
        FileWriter writer = new FileWriter(fileName);
        xstream.autodetectAnnotations(true);
        xstream.toXML(subwayStream, writer);
        writer.close();
    }
    public static SubwayStream deserializeFromXML(String fileName) throws Exception {
        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);
        xstream.processAnnotations(SubwayStream.class);
        try {
            FileReader reader = new FileReader(fileName);
            return (SubwayStream) xstream.fromXML(reader);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
        SubwayStream station = SubwayStream.createSubwayStation();
        String f = "C:\\Users\\Alexo\\IdeaProjects\\lab3_fx\\chooseme.xml";
        serializeToXML(station, f);
        SubwayStream deserializedStation = deserializeFromXML(f);
        assert deserializedStation != null;//assert - проверка утверждения, если нет то ошибка AssertionError
        System.out.printf("%s\t%s", deserializedStation.getName(), deserializedStation.getOpeningYear());
        System.out.println("\n" + station.toStringHours(deserializedStation.getHours()));

        serializeToXML(deserializedStation, f);
    }
}

