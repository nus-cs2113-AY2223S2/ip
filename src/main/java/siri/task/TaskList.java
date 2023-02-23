package siri.task;
import java.io.IOException;
import java.util.ArrayList;

import static siri.Duke.indexOfTask;

public class TaskList {
    protected static ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> oldTaskList){
        this.taskList = new ArrayList<>(oldTaskList);
    }
    public ArrayList<Task> getTaskList(){
        return taskList;
    }
    public void printTaskList() {
        System.out.println("Below is your task list");
        for (int i = 0; i < indexOfTask; ++i) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
    }

    public void addTask(String taskCommand, String taskD) throws IOException {
        switch (taskCommand) {
        case "todo":
            taskList.add(new ToDo(taskD.trim()));
            break;
        case "deadline":
            String[] deadlineTaskD = taskD.split("/by ", 2);
            taskList.add(new Deadline(deadlineTaskD[0].trim(), deadlineTaskD[1].trim()));
            break;
        case "event":
            String[] eventName = taskD.split("/from ", 2);
            String[] eventDate = eventName[1].split("/to ", 2);
            taskList.add(new Event(eventName[0].trim(), eventDate[0].trim(), eventDate[1].trim()));
            break;
        }
    }

    public void printNewTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList.get(indexOfTask).toString());
        System.out.println("Now you have " + (indexOfTask + 1) + " tasks in the list.");
    }

    public void deleteTask(String taskNumberString) {
        int taskNumber = Integer.parseInt(taskNumberString);
        printDeletedTask(taskNumber);
        taskList.remove(taskNumber - 1);
        indexOfTask--;
        //IndexOutOfBoundsException
    }

    public void printDeletedTask(int taskNumber) {
        String deleteString = taskList.get(taskNumber - 1).toString();
        System.out.println("Noted! I've deleted this task:");
        System.out.println(deleteString);
        System.out.println("Now you have " + (indexOfTask - 1) + " tasks in the list.");
    }
    public void markTask(String marker, String taskNumberString) {
        int taskNumber = Integer.parseInt(taskNumberString);

        if (marker.equals("mark")) {
            taskList.get(taskNumber - 1).setDone(true);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(taskList.get(taskNumber - 1).toString());
        } else {
            taskList.get(taskNumber - 1).setDone(false);
            System.out.println("Ok! I've marked this task as not done yet: ");
            System.out.println(taskList.get(taskNumber - 1).toString());
        }

        //Null Pointer Exception (taskNumber > indexOfTask)
    }
}
