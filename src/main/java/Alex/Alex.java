package Alex;

import Alex.exception.DukeException;
import Alex.manager.TaskManager;
import Alex.task.Deadline;
import Alex.task.Event;
import Alex.task.Task;
import Alex.task.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Alex {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner myObj = new Scanner(System.in);
        greeting();
        /** Constantly asks for user input until bye is received */
        while(!taskManager.getExit()) {
            String userInput = myObj.nextLine();
            try {
                handleInput(userInput, taskManager);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a " + e.getMessage() + " cannot be empty.");
                printLine();
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printLine();
            }

        }

    }
    public static void echoResponse(Task task, int number) {
        System.out.println("Got it. I've added this task:" + "\n " + task + "\n" +
                "Now you have " + number + " tasks in the list" );
        printLine();
    }
    public static void printLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }
    public static void greeting() {
        printLine();
        String greet = "Hello! I'm Alex\nWhat can I do for you today?";
        System.out.println(greet);
        printLine();
    }
    public static void exit() {
        String exit = "Bye. Hope to chat with you again soon!";
        System.out.println(exit);
        printLine();
    }
    /** Method that utilises a taskManager to handle different user inputs accordingly */
    public static void handleInput(String userInput, TaskManager taskManager) throws DukeException {
        String[] words = userInput.split(" ");
        String[] allCommands = new String[]{"todo" , "bye", "event", "list" , "unmark" , "mark", "deadline", "delete"};
        List<String> commandList = new ArrayList<>(Arrays.asList(allCommands));
        String command = words[0].toLowerCase();
        if(!commandList.contains(command)) {
            throw new DukeException();
        }
        if(words.length < 2 && !command.equals("bye") && !command.equals("list")) {
            throw new IndexOutOfBoundsException(command);
        }
        if(command.equals("list")) {
            int taskNo = 1;
            for(Task t : taskManager.getAllTasks()) {
                System.out.println(taskNo + "." + t);
                taskNo += 1;
            }
            printLine();

        }
        else if(command.equals("bye")) {
            exit();
            taskManager.setExit();
        }
        else if(command.equals("mark")) {
            printLine();
            System.out.println("Good job! I have marked this task as completed:");
            int number = Integer.parseInt(words[1]);
            taskManager.getAllTasks().get(number - 1).markAsDone();
            System.out.println(taskManager.getAllTasks().get(number - 1));
            printLine();

        }
        else if(command.equals("unmark")) {
            printLine();
            System.out.println("Got it! I have marked this task as not yet completed:");
            int number = Integer.parseInt(words[1]);
            taskManager.getAllTasks().get(number - 1).unmark();
            System.out.println(taskManager.getAllTasks().get(number - 1));
            printLine();
        }

        else if(command.equals("delete")) {
            printLine();
            System.out.println("Noted. I've removed this task:");
            int number = Integer.parseInt(words[1]);
            Task t =  taskManager.getAllTasks().get(number - 1);
            taskManager.deleteTask(number - 1);
            System.out.println(t);
            System.out.println("Now you have " + taskManager.getNumberOfTasks() + " tasks in the list.");
            printLine();
        }

        else {
            String activity = "";
            for (int i = 1; i < words.length; i++) {
                if(words[i].charAt(0) == '/' ) {
                    break;
                }
                else {
                    activity += " " + words[i];
                }

            }
            if(command.equals("todo")) {
                Task t = new Todo(activity);
                taskManager.setTask(t);
                printLine();
                echoResponse(t, taskManager.getNumberOfTasks());
            }
            else if(command.equals("deadline")) {
                String by = "";
                int byIndex = 0;
                for(int i = 0; i < words.length; i++) {
                    if(words[i].charAt(0) == '/') {
                        byIndex = i;
                    }
                }
                for(int i = byIndex + 1; i < words.length; i++) {
                    by += " " + words[i];
                }

                Task deadline = new Deadline(activity, by);
                taskManager.setTask(deadline);
                printLine();
                echoResponse(deadline, taskManager.getNumberOfTasks());
            }
            else if(command.equals("event")) {
                String from = "";
                String to = "";
                int fromIndex = 0;
                int toIndex = 0;
                for(int i = 0; i < words.length; i++) {
                    if(words[i].toLowerCase().equals("/from")) {
                        fromIndex = i;
                    }
                    if(words[i].toLowerCase().equals("/to")) {
                        toIndex = i;
                    }
                }
                for(int i = fromIndex + 1; i < toIndex; i++) {
                    from += " " + words[i];
                }
                for(int i = toIndex + 1; i < words.length; i++) {
                    to += " " + words[i];
                }
                Task event = new Event(activity, from, to);
                taskManager.setTask(event);
                printLine();
                echoResponse(event, taskManager.getNumberOfTasks());
            }

        }

    }
}
