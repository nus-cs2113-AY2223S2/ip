import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


public class Storage {
    private final String filePath;


    public Storage(String filePath){
        this.filePath = filePath;
    }

    public void createFile() {
        File newFolder = new File(filePath);
        File newFile = new File(filePath + "/Buddy.txt");

        try {
            if(!newFolder.exists()){   // if folder does not exist
                newFolder.mkdirs();    // creates new directory

            }

            if(!newFile.exists()){        // if file does not exist
                newFile.createNewFile();  // creates new file
            }
        } catch (IOException e){
            System.out.println("An error occurred when creating the file :(");
            e.printStackTrace();
        }
    }

    public void loadData(){



    }





}
