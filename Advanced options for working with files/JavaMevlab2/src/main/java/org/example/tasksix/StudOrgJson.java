package org.example.tasksix;

import org.example.AcademGroup;
import org.example.Student;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class StudOrgJson {
    public static void studentOrgJsonSerialization() throws IOException {
        List<Student> students = Arrays.asList(
                new Student("Pypok Deda", "KN-221C"),
                new Student("Dykan Kolobkovich", "KN-221D")
        );
        AcademGroup academicGroup = new AcademGroup();
        for (Student student : students) {
            academicGroup.addStudent(student);
        }

        JSONObject academicGroupJson = new JSONObject();
        JSONArray studentsJson = new JSONArray();
        for (Student student : academicGroup.getStudents()) {
            JSONObject studentJson = new JSONObject();
            studentJson.put("name", student.getName());
            studentJson.put("group", student.getGroup());
            studentsJson.put(studentJson);
        }
        academicGroupJson.put("students", studentsJson);

        try (FileWriter file = new FileWriter("AcademicGroupOrg.json")) {
            JSONObject academicGroupWrapper = new JSONObject();
            academicGroupWrapper.put("academicGroup", academicGroupJson);
            file.write(academicGroupWrapper.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void studentOrgJsonDeserialization() {

        try {
            String json = new String(Files.readAllBytes(Paths.get("AcademicGroupOrg.json")));
            JSONObject jsonObject = new JSONObject(json);
            AcademGroup academicGroup = new AcademGroup();

            JSONArray jsonArray = jsonObject.getJSONObject("academicGroup").getJSONArray("students");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject studentJson = jsonArray.getJSONObject(i);
                Student student = new Student(studentJson.getString("name"), studentJson.getString("group"));
                academicGroup.addStudent(student);
            }

            for (Student student : academicGroup.getStudents()) {
                System.out.println(student.getName() + " " + student.getGroup());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        try{
            //json.org serialization and deserialization to file AcademicGroupOrg.json
            System.out.println("\njson.org Serialization and Deserialization");
            studentOrgJsonSerialization();
            studentOrgJsonDeserialization();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}