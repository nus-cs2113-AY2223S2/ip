package duke.database;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public ArrayList<Task> tasks;
    public static final String FILE_PATH = "data/duke.txt";
    public static final String BORDER = "//";

    public Storage() throws IOException {
        File f = new File(FILE_PATH);
        this.tasks = new ArrayList<>();

        if (f.exists()) {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String taskString = sc.nextLine();
                Task currTask = parseToTask(taskString);
                this.tasks.add(currTask);
            }
        } else {
            File dir = new File("data");
            dir.mkdirs();
            f.createNewFile();
        }
    }

    /** Overwriting the txt file */
    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToAdd);
        fw.close();
    }

    /** Converts string to respective task type */
    public Task parseToTask(String str) {
        Task currTask;
        String[] stringArr = str.split("//");
        String typeOfTask = stringArr[2];

        if (typeOfTask.equals("T")) {
            currTask = new ToDo((stringArr[1]));
        } else if (typeOfTask.equals("D")) {
            currTask = new Deadline(stringArr[1], stringArr[3]);
        } else {
            currTask = new Event(stringArr[1], stringArr[3], stringArr[4]);
        }

        if (stringArr[0].equals("X")) {
            currTask.setDone();
        }
        return currTask;
    }

    /** Updates an individual task to database*/
    public static void addTaskToDatabase(Task taskToAdd) {
        try {
            FileWriter f = new FileWriter(FILE_PATH, true);
            String taskInDatabaseFormat = stringToWrite(taskToAdd).toString();
            f.write(taskInDatabaseFormat + System.lineSeparator());
            f.close();
        } catch (IOException e) {
            System.out.println("Unable to add task to database :(");
        }
    }

    /** Converts Task to StringBuilder to be passed as string */
    public static StringBuilder stringToWrite(Task taskToAddToDatabaseList) {
        StringBuilder sb = new StringBuilder();

        // Format of task in database is: X//description//type of task
        sb.append(taskToAddToDatabaseList.getMarking() + BORDER
                + taskToAddToDatabaseList.getDescription() + BORDER);

        // X//description//T
        if (taskToAddToDatabaseList instanceof ToDo) {
            sb.append("T");
        }
        // X//description//D//by
        else if (taskToAddToDatabaseList instanceof Deadline) {
            sb.append("D" + BORDER + ((Deadline) taskToAddToDatabaseList).getBy());
        }
        // X//description//E//from//to
        else {
            sb.append("E" + BORDER + ((Event) taskToAddToDatabaseList).getFrom()
                    + BORDER + ((Event) taskToAddToDatabaseList).getTo());
        }
        return sb;
    }

}
