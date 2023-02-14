package database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Task.Task;
import IO.Input;

public class DatabaseManager {

    public static void FIleWrite(ArrayList<Task> Tasks) throws IOException {
        FileWriter fw = new FileWriter("data.txt");
        BufferedWriter info = new BufferedWriter(fw);
        for (int i = 0; i < Tasks.size(); i++) {
            String AddLine = Input.parseTaskToWrite(Tasks.get(i));
            info.write(AddLine);
            info.newLine();
        }
        info.close();
    }

    public static ArrayList<Task> FileRead() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Task task;
        try {
            File f = new File("data.txt");
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                task = Input.parseFileContent(sc.nextLine());
                tasks.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            File newFile = new File("data.txt");
            newFile.createNewFile();
        }
        return tasks;

    }


}
