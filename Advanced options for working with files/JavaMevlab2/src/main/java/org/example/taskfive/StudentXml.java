package org.example.taskfive;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.example.AcademGroup;
import org.example.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;


public class StudentXml {
    public static void studentXmlSerialization() {
        XStream xStream = new XStream();
        List<Student> students = Arrays.asList(
                new Student("Azian Mafia", "KN-221A"),
                new Student("Karinochka Gaborets", "KN-221A"),
                new Student("Dukarschka Dunaj", "KN-221A")
        );
        AcademGroup academicGroup = new AcademGroup();
        for (Student student : students) {
            academicGroup.addStudent(student);
        }

        xStream.alias("academicGroup", AcademGroup.class);
        xStream.alias("student", Student.class);

        String xml = xStream.toXML(academicGroup);
        try (FileWriter fw = new FileWriter("AcademicGroup.xml");
             PrintWriter out = new PrintWriter(fw)) {
            out.println(xml);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void studentXmlDeserialization() {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);

        xStream.alias("academicGroup", AcademGroup.class);
        xStream.alias("student", Student.class);

        AcademGroup newAcademicGroup = (AcademGroup) xStream.fromXML(new File("AcademicGroup.xml"));
        for (Student student : newAcademicGroup.getStudents()) {
            System.out.println(student.getName() + " " + student.getGroup());
        }

    }
}

