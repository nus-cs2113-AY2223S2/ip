package duke;

import duke.task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class Duke {
    public static void printGreeting(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void printFarewell(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void addNewTask(ArrayList<Task> tasks, String[] taskParameters, TaskType taskType) {
        Task newTask;
        switch (taskType) {
        case TODO:
            newTask = new ToDo(taskParameters[0]);
            break;
        case DEADLINE:
            newTask = new Deadline(taskParameters[0], taskParameters[1]);
            break;
        case EVENT:
            newTask = new Event(taskParameters[0], taskParameters[1], taskParameters[2]);
            break;
        default:
            // No task type specified, there is some bug in the code if instruction reaches this point,
            // simply do not add anything to the tasks
            System.out.println("Error in addNewTask method, no task type specified");
            return;
        }
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void printTasks(ArrayList<Task> tasks){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); ++i) {
            System.out.print(i + 1 + ".");
            System.out.println(tasks.get(i).toString());
        }
    }

    public static int getTaskIndex(String[] taskNumbers, int totalTasks) throws DukeException{
        // From parser, in the current implementation, taskNumbers.length is always equal to 1
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskNumbers[0]);
        } catch (Exception e){
            throw new TaskNumberException();
        }
        if(taskIndex <= 0 || taskIndex > totalTasks){
            throw new TaskNumberException();
        }

        // Minus one from task index since the array tasks is 0-based
        taskIndex -= 1;
        return taskIndex;
    }
    public static void changeTaskStatus(ArrayList<Task> tasks, String[] taskNumbers, boolean isDone) throws DukeException{
        // From parser, taskNumbers.length is always equal to 1
        int taskIndex = getTaskIndex(taskNumbers, tasks.size());

        if(isDone){
            if(tasks.get(taskIndex).isDone()){
                System.out.println("This task is already marked done:");
                System.out.println(tasks.get(taskIndex).toString());
            } else{
                tasks.get(taskIndex).setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(taskIndex).toString());
            }
        }else{
            if(!tasks.get(taskIndex).isDone()){
                System.out.println("This task is already marked as not done:");
                System.out.println(tasks.get(taskIndex).toString());
            } else{
                tasks.get(taskIndex).setDone(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(taskIndex).toString());
            }
        }
    }

    public static void deleteTask(ArrayList<Task> tasks, String[] taskNumbers) throws DukeException{
        int taskIndex = getTaskIndex(taskNumbers, tasks.size());

        Task taskToRemove = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + taskToRemove);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        final File taskDataDirectory = new File("data");
        final File taskDataFile = new File("data/task-data.txt");
        if(!taskDataDirectory.exists()){
            try{
                taskDataDirectory.mkdir();
            }catch (SecurityException e){
                System.out.println("An error occurred while creating the data directory: " + e.getMessage());
                return;
            }
        }
        Scanner in = new Scanner(System.in);
        printGreeting();
        boolean isProgramRunning = true;
        while(isProgramRunning)
        {
            String userInput = in.nextLine();
            Command command;
            try{
                command = Parser.parseCommand(userInput);
                switch(command.getCommandType()){
                case ADD_TODO_COMMAND:
                    addNewTask(tasks, command.getAdditionalParameters(), TaskType.TODO);
                    break;
                case ADD_DEADLINE_COMMAND:
                    addNewTask(tasks, command.getAdditionalParameters(), TaskType.DEADLINE);
                    break;
                case ADD_EVENT_COMMAND:
                    addNewTask(tasks, command.getAdditionalParameters(), TaskType.EVENT);
                    break;
                case LIST_TASKS_COMMAND:
                    printTasks(tasks);
                    break;
                case MARK_TASK_COMMAND:
                    changeTaskStatus(tasks, command.getAdditionalParameters(), true);
                    break;
                case UNMARK_TASK_COMMAND:
                    changeTaskStatus(tasks, command.getAdditionalParameters(), false);
                    break;
                case DELETE_TASK_COMMAND:
                    deleteTask(tasks, command.getAdditionalParameters());
                    break;
                case END_PROGRAM_COMMAND:
                    printFarewell();
                    isProgramRunning = false;
                    break;
                case UNKNOWN_COMMAND:
                default:
                    System.out.println("Unknown task or task parameters received. Please try again.");
                    break;
                }
            }catch (DukeException e){
                System.out.println(e.getMessage());
            }
        }
/*        try {
            TaskData.storeTaskData(taskDataFile, tasks);
        }catch(IOException e){
            System.out.println("An error occured while storing your task data: " + e.getMessage());
        }*/
    }
}
