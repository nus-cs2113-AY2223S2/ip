package Duke;
import Duke.*;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    public static ArrayList<Task> tasks = Duke.tasks;

    public static void deleteTask(String command) {
        try {
            int val = Integer.parseInt(command.substring(7));
            System.out.println("Noted. I've removed this task:");
            Task task = tasks.get(val-1);
            System.out.print(val);
            task.printTask();
            tasks.remove(val-1);
        } catch (NumberFormatException e){
            System.out.println("ERROR The task number is invalid!");
        }

    }

    public static void findTask(String command){
        String keyword = command.substring(5);
        System.out.println(keyword);
        ArrayList<Task> refinedTasks = new ArrayList<>();
        for (Task task : tasks){
            if ((task.getTaskName()).contains(keyword) ){
                refinedTasks.add(task);
            }
        }
        int numberOfFoundTasks = refinedTasks.size();
        int i = 1;
        System.out.println("There are " + numberOfFoundTasks + " matching tasks in your list");
        for (Task foundTask : refinedTasks){
            if (foundTask != null) {
                System.out.print(i);
                foundTask.printTask();
            }
            i++;
        }



    }


    public static void createTodo(String command) {
        command = command.substring(5);
        Task task = new Todo(command);
        task.isCompleted = false;
        task.taskName = command;
        System.out.println("added:  " + task.taskName);
        tasks.add(task);
    }

    public static void createEvent(String command) {
        String content = command.substring(5);
        String[] contents = content.split("/");
        String title = contents[0];
        String startTime = contents[1];
        String endTime = contents[2];
        Event newEvent = new Event(title);
        newEvent.setStartTime(startTime);
        newEvent.setEndTime(endTime);
        newEvent.setAsEvent();
        tasks.add(newEvent);
        newEvent.newEventResponse();
    }

    public static void createDeadline(String command) {
        String content = command.substring(8);
        String[] contents = content.split("/");
        String title = contents[0];
        String dueDate = contents[1];
        Deadline newDeadline = new Deadline(title);
        newDeadline.setEndTime(dueDate);
        newDeadline.setAsDeadline();
        tasks.add(newDeadline);
        newDeadline.newDeadlineResponse();
    }

    public static void unmarkTask(String command, int numberOfTasks) {
        String[] result = command.split(" ");
        int taskNum = Integer.parseInt(result[1]);
        if (taskNum > numberOfTasks || taskNum <= 0) {
            System.out.println("Error! No such task exists!");
        } else {
            Task target = tasks.get(taskNum - 1);
            target.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(target.getStatusIcon() + "   " + target.taskName);
        }
    }

    public static void markTask(String command, int numberOfTasks) {
        String[] result = command.split(" ");
        int taskNum = Integer.parseInt(result[1]);
        if (taskNum > numberOfTasks || taskNum <= 0) {
            System.out.println("Error! No such task exists!");
        } else {
            Task target = tasks.get(taskNum - 1);
            target.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(target.getStatusIcon() + "   " + target.taskName);
        }
    }

    public static void printTaskList() {
        int i = 1;
        for (Task a : Duke.tasks) {
            if (a != null) {
                System.out.print(i);
                a.printTask();
                i++;
            }
        }
    }

    public static String readTask() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;

    }

    public static String checkForAdditionalTask() {

        System.out.println("Do you have any other task for me?  ");
        return readTask();

    }




}
