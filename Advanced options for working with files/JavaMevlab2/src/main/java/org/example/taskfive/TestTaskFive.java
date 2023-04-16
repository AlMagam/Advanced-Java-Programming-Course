package org.example.taskfive;


import java.io.IOException;

import static org.example.AcademGroup.testStudentStream;
import static org.example.taskfive.StudentXml.studentXmlDeserialization;
import static org.example.taskfive.StudentXml.studentXmlSerialization;
import static org.example.taskfive.StudentJson.studentJsonDeserialization;
import static org.example.taskfive.StudentJson.studentJsonSerialization;

public class TestTaskFive {
    public static void main(String[] args) throws IOException {
        try {
            //test_mit_streams(StreamAPI)
            System.out.println("StreamAPI");
            testStudentStream();

            //xml serialization and deserialization to file AcademicGroup.xml
            System.out.println("\nXML Serialization and Deserialization");
            studentXmlSerialization();
            studentXmlDeserialization();

            //json serialization and deserialization to file AcademicGroup.json
            System.out.println("\nJSON Serialization and Deserialization");
            studentJsonSerialization();
            studentJsonDeserialization();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
