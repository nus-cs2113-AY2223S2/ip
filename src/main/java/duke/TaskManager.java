package duke;

import java.util.*;


import java.io.FileWriter;
import java.io.IOException;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String name) {
        tasks.add(new Task(name, false));
    }

    public void addDeadline(String name, String deadline) {
        tasks.add(new Deadline(name, false,deadline));
    }

    public void addEvent(String eventName, String startTime, String finishTime) {
        tasks.add(new Event(eventName, false, startTime, finishTime));
    }

    public void markTask(int id) {
        tasks.get(id).setIsDone(true);
        System.out.println("This item has been marked as done!");
        System.out.println("[X] " + tasks.get(id).getName());
    }

    public void unmarkTask(int id) {
        tasks.get(id).setIsDone(false);
        System.out.println("The item has been marked as NOT done!");
        System.out.println("[ ] " + tasks.get(id).getName());
    }

    public void listTask() {
        if(tasks.size() == 0) {
            System.out.println("The list is currently empty!");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1);
            tasks.get(i).print();
        }
    }

    public void deleteTask(int id) {
        System.out.println("This item has been removed!");
        System.out.println("[ ] " + tasks.get(id).getName());
        tasks.remove(id);
    }

    public int getSize() {
        return tasks.size();
    }

    public void saveFile() throws IOException {
        String filePath = "C:/repos/IP/src/main/java/duke/load.txt";
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            try {
                fw.write(tasks.get(i).toString() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        fw.close();
    }

}
