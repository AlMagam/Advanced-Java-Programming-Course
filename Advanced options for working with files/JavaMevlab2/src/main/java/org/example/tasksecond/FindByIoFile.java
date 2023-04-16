package org.example.tasksecond;
import java.io.File;


public class FindByIoFile {
    public static void main(String[] args) {
        File folder = new File("target");
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Error,folder existiert nicht\n");
        }else{
            System.out.println("Suchen mit dem java.io.file: :\n");
            listFilesRecursively(folder);
        }
    }
    private static void listFilesRecursively(File folder) {
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                listFilesRecursively(file);
            } else {
                System.out.println(file.getAbsolutePath());
            }
        }
    }

}

