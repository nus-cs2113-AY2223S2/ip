package duke.database;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageTest {
    protected ArrayList<Task> taskList;
    //public static final String FILE_PATH = "data/duke.txt";
    public void Storage() throws IOException {
        this.taskList = new ArrayList<>();
        File f = new File("data/duke.txt");
        if (f.exists()) {
//            Scanner scan = new Scanner(f);
//            while (scan.hasNextLine()) {
//                String taskString = scan.nextLine();
//                Task currTask = parseToTask(taskString);
//                this.taskList.add(currTask);
//            }
//            //scan.close();
//            System.out.println("nvr create file");
        } else {
            File dir = new File("data");
            dir.mkdirs();
            f.createNewFile();
            System.out.println("Created file");
        }
    }

//    public void Storage() {
//        this.taskList = new ArrayList<>();
//        loadFromDatabase();
//        System.out.println("run");
//    }
//
//    public void loadFromDatabase() {
//        try {
//            initialise();
//        } catch (IOException e) {
//            System.out.println("failed to initialise database");
//        }
//    }
//
//    public void initialise() throws IOException {
//        File f = new File(FILE_PATH);
//        Scanner scan = new Scanner(f);
//        if (f.exists()) {
//            while (scan.hasNextLine()) {
//                String taskString = scan.nextLine();
//                Task currTask = parseToTask(taskString);
//                this.taskList.add(currTask);
//            }
//            //scan.close();
//            System.out.println("nvr create file");
//        } else {
//            File dir = new File("data");
//            dir.mkdirs();
//            f.createNewFile();
//            System.out.println("Created file");
//        }
//    }

    public Task parseToTask(String str) {
        Task task;
        String[] stringArr = str.split("|");
        String typeOfTask = stringArr[2];

        if (typeOfTask.equals("T")) {
            task = new ToDo((stringArr[1]));
        } else if (typeOfTask.equals("D")) {
            task = new Deadline(stringArr[1], stringArr[3]);
        } else if (typeOfTask.equals("E")) {
            task = new Event(stringArr[1], stringArr[3], stringArr[4]);
        } else {
            throw new IllegalStateException();
        }

        if (stringArr[0].equals("X")) {
            task.setDone();
        }
        return task;
    }

//    public void saveToDatabase() { //when need to rewrite whole datafile (for marking and deleting)
//        try {
//            FileWriter fileWrite = new FileWriter(FILE_PATH, true);
//            for (Task taskToAddToDatabaseList: taskList) {
//                String taskInDatabaseFormat = stringToWrite(taskToAddToDatabaseList).toString();
//                fileWrite.write(taskInDatabaseFormat + System.lineSeparator());
//            }
//            fileWrite.close();
//        } catch (IOException e) {
//            System.out.println("failed to save file");
//        }
//    }
}

