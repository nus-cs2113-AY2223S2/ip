package duke;

import java.io.FileWriter;
import java.io.IOException;

public class TaskManager {
    private static Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void addTask(String name, int id) {
        tasks[taskCount] = new Task(name, false, id);
        taskCount++;
    }

    public int getTaskCount() {
        return this.taskCount;
    }

    public void addDeadline(String name, String deadline, int id) {
        tasks[taskCount] = new Deadline(name, false, id, deadline);
        taskCount++;
    }

    public void addEvent(String eventName, String startTime, String finishTime, int id) {
        tasks[taskCount] = new Event(eventName, false, id, startTime, finishTime);
        taskCount++;
    }

    public void markTask(int id) {
        tasks[id].setIsDone(true);
        System.out.println("The task has been marked as done!");
        System.out.println("[X] " + tasks[id].getName());
    }

    public void unmarkTask(int id) {
        tasks[id].setIsDone(false);
        System.out.println("The task has been marked as NOT done!");
        System.out.println("[ ] " + tasks[id].getName());
    }

    public void listTask() {
        for (int i = 0; i < taskCount; i++) {
            tasks[i].print();
        }
    }

    public void saveFile() throws IOException {
        String filePath = "C:/repos/IP/src/main/java/duke/load.txt";
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < taskCount; i++) {
            try {
                fw.write(tasks[i].toString() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        fw.close();
    }

}
