package org.example.tasksecond;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;



public class FindByNioFile {

    public static void main(String[] args) {
        Path folderPath = Paths.get("testDir");
        if (!Files.exists(folderPath) || !Files.isDirectory(folderPath)) {
            System.out.println("Error,folder existiert nicht\n");
        }else{
            System.out.println("Suchen mit dem java.nio.file:\n");
            try (Stream<Path> paths = Files.walk(folderPath)) {
                paths.filter(Files::isRegularFile).forEach(p -> System.out.println(p.getFileName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

