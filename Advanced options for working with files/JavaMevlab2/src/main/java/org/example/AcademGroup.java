package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AcademGroup  {

    private List<Student> students;

    public AcademGroup() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }


    //streams_realisation//StreamAPI
    public void saveToFile(String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            students.stream()
                    .map(student -> student.getName() + "," + student.getGroup())
                    .forEach(writer::println);
        }
    }

    public void readFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            students = reader.lines()
                    .map(line -> line.split(","))
                    .map(parts -> new Student(parts[0], parts[1]))
                    .collect(Collectors.toList());
        }
        for (Student student : students) {
            System.out.println(student.getName() + ", " + student.getGroup());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Student student : students) {
            sb.append(student.toString()).append("\n");
        }
        return sb.toString();
    }
    public static void testStudentStream() throws IOException {
        List<Student> students = Arrays.asList(
                new Student("Azian Mafia", "KN-221A"),
                new Student("Karinochka Gaborets", "KN-221A"),
                new Student("Dukarschka Dunaj", "KN-221A")
        );
        AcademGroup academicGroup = new AcademGroup();
        for (Student student : students) {
            academicGroup.addStudent(student);
        }
        academicGroup.saveToFile("group.txt");
        academicGroup.readFromFile("group.txt");

    }
}