package commands;

import java.io.File;
import java.io.IOException;

public class CreateFile {
    public static void createFile(File f){
        try {
            if (f.createNewFile()){
                System.out.println("File created");
            }
        } catch (IOException e) {
            System.out.println("File already exists.");
        }
    }
}
