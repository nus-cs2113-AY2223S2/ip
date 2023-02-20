package Duke.DukeFunction;

import Duke.DukeCommandLine.DukeTaskInputException;
import Duke.DukeTask.DukeDeadline;
import Duke.DukeTask.DukeEvent;
import Duke.DukeTask.DukeTask;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeList {
    private static final ArrayList<DukeTask> taskList = new ArrayList<>();
    private static final String FILE_PATH = "data/list.txt";
    public void addTask(DukeTask task) throws DukeTaskInputException {
        try {
            taskList.add(task);
            System.out.println("Got it. I've added this task:");
            task.printTask();
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new DukeTaskInputException(e.getMessage());
        }
    }
    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            taskList.get(i).printTask(i);
        }
    }
    public static void isValidID(int id) throws DukeTaskInputException {
        boolean isIDInValid = id < 0 || id >= taskList.size();
        if(isIDInValid) {
            throw new DukeTaskInputException("Sorry, the id is invalid!");
        }
    }

    public void markDone(int id) throws DukeTaskInputException {
        try {
            isValidID(id);
            taskList.get(id).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            taskList.get(id).printTask();
        } catch (DukeTaskInputException e) {
            throw new DukeTaskInputException("[mark] " + e.getMessage());
        }
    }
    public void unmarkDone(int id) throws DukeTaskInputException {
        try {
            isValidID(id);
            taskList.get(id).unmarkAsDone();
            System.out.println("Nice! I've unmarked this task as done:");
            taskList.get(id).printTask();
        } catch (DukeTaskInputException e) {
            throw new DukeTaskInputException("[unmark] " + e.getMessage());
        }
    }
    public void deleteTask(int id) throws DukeTaskInputException {
        try {
            isValidID(id);
            System.out.println("Noted. I've removed this task:");
            taskList.get(id).printTask();
            taskList.remove(id);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (DukeTaskInputException e) {
            throw new DukeTaskInputException("[delete] " + e.getMessage());
        }
    }

    public void storeTask(FileWriter fileWriter) throws IOException {
        try {
            for (int i = 0; i < taskList.size(); i++) {
                fileWriter.write(taskList.get(i).saveTask());
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
