package managers;

import errors.InvalidDeadlineException;
import errors.InvalidEventException;
import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveManager {

    public static final String PATHNAME = "data.txt";

    //Storing format:
    //T0 name
    //D0 name /by time
    //E0 name /from time /to time
    public static ArrayList<Task> initialiseData() {
        File f = new File(PATHNAME);
        ArrayList<Task> storedTasks = new ArrayList<>();
        try {
            if (f.createNewFile()) {
                return storedTasks;
            }
        } catch (IOException e) {
            System.out.println("Error occurred when creating new file.");
        }
        try {
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                processStoredTask(storedTasks, line);
            }
        } catch (FileNotFoundException e) {
            return storedTasks;
        } catch (InvalidDeadlineException | InvalidEventException e) {
            System.out.println("Stored data is corrupted.");
        }

        return storedTasks;
    }

    public static void saveCurrentState (ArrayList<Task> todoList) {
        try {
            FileWriter fileWriter = new FileWriter(PATHNAME, true);
            FileWriter fileClearer = new FileWriter(PATHNAME);
            fileClearer.write("");
            fileClearer.close();
            String taskState = "";
            for (Task item: todoList) {
                String toWrite = "";
                if (item.isDone()) {
                    taskState = "1";
                } else {
                    taskState = "0";
                }
                switch (item.getClassType()) {
                case "T":
                    toWrite = "T" + taskState + item.getToStore();
                    break;
                case "D" :
                    toWrite = "D" + taskState + item.getToStore();
                    break;
                case "E":
                    toWrite = "E" + taskState + item.getToStore();
                    break;
                }
                fileWriter.write(toWrite + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("/(TAT)/  There seems to be some error when saving data...");
        }
    }
    private static void processStoredTask(ArrayList<Task> storedTasks, String line) throws InvalidDeadlineException, InvalidEventException {
        switch (line.substring(0,1)) {
        case "T":
            ToDos toDo = new ToDos(line.substring(3));
            setMarkState(line, toDo);
            storedTasks.add(toDo);
            break;
        case "D":
            Deadlines deadline = new Deadlines(line.substring(3));
            setMarkState(line, deadline);
            storedTasks.add(deadline);
            break;
        case "E":
            Events event = new Events(line.substring(3));
            setMarkState(line, event);
            storedTasks.add(event);
            break;
        }
    }

    private static void setMarkState(String line, Task task) {
        if (Integer.parseInt(line.substring(1,2)) == 1) {
            task.markAsState(true);
        } else {
            task.markAsState(false);
        }
    }
}
