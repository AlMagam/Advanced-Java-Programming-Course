package org.example;

public class Student {
    private String name;
    private String group;

    public Student(String name, String group) {
        this.name = name;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return name + " " + group;
    }

}