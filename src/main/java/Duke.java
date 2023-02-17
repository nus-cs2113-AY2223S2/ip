import exceptions.*;

import java.io.IOException;
import java.util.ArrayList;
// branch level-5 update as merge was not successful
import java.util.Arrays;
import java.util.Scanner;

/**
 * Incorrect Tags have been resolved, all commits have been tagged appropriately
 */
public class Duke {

    public static void printLine() {
        System.out.println("\t---------------------------------------------------------------------------------");
    }
    public static void listTasks(int currentIndex, ArrayList<Task> taskList) {
        for(int i = 0; i<currentIndex;i+=1) {
            System.out.println('\t' + Integer.toString(i+1) + "." + taskList.get(i).getStatusAndDescription());
            // can be further optimized.
        }
    }

    public static boolean isTheSame(String userInput, String toCompare) {
        return userInput.split(" ")[0].equals(toCompare);
    }
    

    public static boolean isInRange(String userInput, int currentIndex) {
        return (Integer.parseInt(userInput.split(" ")[1])>0 && Integer.parseInt(userInput.split(" ")[1])<currentIndex+1);
    }

    public static void printMarkedTask(String userInput, ArrayList<Task> taskList) {
        System.out.println("\tNice! I've marked this task as done:");
        taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1).markTask();
        System.out.println("\t\t" + taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1).getStatusAndDescription());
    }

    public static void deleteTask() {
        printLine();
        System.out.println("\tNoted! I've removed this task!");
        System.out.println("\t\t" + taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1).getStatusAndDescription());
        taskList.remove(Integer.parseInt(userInput.split(" ")[1]) - 1);
        currentIndex-=1;
        printNoTasks(currentIndex);
        printLine();
    }

    public static void printUnmarkedTask(String userInput, ArrayList<Task> taskList) {
        System.out.println("\tNice! I've marked this task as not done:");
        taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1).unMarkTask();
        System.out.println("\t\t" + taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1).getStatusAndDescription());
    }
    public static String[] getDeadline(String userInput) {
        String intermediateStage = userInput.replace("deadline ", "");
        String[] deadlineAndDescription = intermediateStage.split("/by ");
        return deadlineAndDescription;

    }
    public static String[] getEvent(String userInput) {
        String intermediateStage = userInput.replace("event ", "");
        String[] eventDescription = intermediateStage.split("/from | /to");
        return eventDescription;
    }
    public static void printNoTasks(int currentIndex) {
        if(currentIndex==1) {
            System.out.println("\tNow you have " + Integer.toString(currentIndex) + " task in the list");
        } else {
            System.out.println("\tNow you have " + Integer.toString(currentIndex) + " tasks in the list");
        }
    }

    public static void printTodo(String userInput) throws EmptyTodo {
        String[] holder = userInput.split(" ");
        if(holder.length<2)
        {
            throw new EmptyTodo();
        }
        printLine();
        String input = userInput.replace("todo ", "");
        Todos temp = new Todos(input);
        taskList.add(temp);
        currentIndex+=1;
        printNoTasks(currentIndex);
        printLine();
    }
    public static void printDeadline(String userInput) throws EmptyDeadline, DeadlineMissingPhrase, DeadlineIsBlank {
        printLine();
        String[] deadlineAndDescription = getDeadline(userInput);
        if(!userInput.contains("/by ") && userInput.split(" ").length>1) {
            throw new DeadlineMissingPhrase();
        } else if(deadlineAndDescription.length==1) {
            throw new EmptyDeadline();
        } else if(deadlineAndDescription[1].isBlank()) {
            throw new DeadlineIsBlank();
        }
        Deadlines temp = new Deadlines(deadlineAndDescription[0], deadlineAndDescription[1]);
        taskList.add(temp);
        currentIndex+=1;
        printNoTasks(currentIndex);
        printLine();
    }

    public static void printEvent(String userInput) throws EmptyEvent, EventMissingBothPhrases, EventMissingToPhrase, EventMissingFromPhrase, EventFromIsBlank, EventToIsBlank {
        printLine();
        String [] eventDescription = getEvent(userInput);
        if(!userInput.contains("/from") && userInput.split(" ").length>1) {
            throw new EventMissingFromPhrase();
        } else if(!userInput.contains("/to") && userInput.split(" ").length>1) {
            throw new EventMissingToPhrase();
        } else if(!(userInput.contains("/from") || userInput.contains("/to")) && userInput.split(" ").length>1) {
            throw new EventMissingFromPhrase();
        } else if (eventDescription.length==1) {
            throw new EmptyEvent();
        }else if(eventDescription[1].isBlank()) {
            throw new EventFromIsBlank();
        } else if(eventDescription[2].isBlank()) {
            throw new EventToIsBlank();
        }
        Events temp = new Events(eventDescription[0], eventDescription[1], eventDescription[2]);
        taskList.add(temp);
        currentIndex+=1;
        printNoTasks(currentIndex);
        printLine();
    }
    public static void printTask(String userInput) {
        printLine();
        Task temp = new Task(userInput); // set the description
        taskList.add(temp);
        currentIndex+=1;
        System.out.println("\tadded: " + userInput);
        printLine();
    }
    public static void sayBye() {
        try {
            fileObject.clearFile();
        } catch (IOException e) {
            System.out.println("Oh No unable to leave");
        }
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    public static void deadlineExceptionHandler() {
        try {
            printDeadline(userInput);
            fileObject.addToFile(currentIndex +"." + taskList.get(taskList.size()-1).getStatusAndDescription() + System.lineSeparator());
        } catch (EmptyDeadline e) {
            printLine();
            System.out.println("\tPlease ensure that the deadline isn't empty!");
            printLine();
        } catch (DeadlineMissingPhrase e) {
            printLine();
            System.out.println("\tPlease ensure that you include the '/by' phrase to indicate the deadline!");
            printLine();
        } catch (DeadlineIsBlank e) {
            printLine();
            System.out.println("\tPlease ensure that the deadline is not composed of solely white spaces!");
            printLine();
        } catch (IOException e) {
            System.out.println("unable to write");
        }
    }

    public static void eventExceptionHandler() {
        try {
            printEvent(userInput);
            fileObject.addToFile(currentIndex +"." + taskList.get(taskList.size()-1).getStatusAndDescription() + System.lineSeparator());
        } catch (EmptyEvent e) {
            printLine();
            System.out.println("\tPlease ensure that the event isn't empty!");
            printLine();
        } catch (EventMissingFromPhrase e) {
            printLine();
            System.out.println("\tPlease ensure that you include the '/from' phrase to indicate the start of the event!");
            printLine();
        } catch (EventMissingToPhrase e) {
            printLine();
            System.out.println("\tPlease ensure that you include the '/to' phrase to indicate the end of the event!");
            printLine();
        } catch (EventMissingBothPhrases e) {
            printLine();
            System.out.println("\tPlease ensure that you include the '/from' and '/to' phrase to indicate the start and end of the event!");
            printLine();
        } catch (EventFromIsBlank e) {
            printLine();
            System.out.println("\tPlease ensure that the event has a valid start time period");
            printLine();
        } catch (EventToIsBlank e) {
            printLine();
            System.out.println("\tPlease ensure that the event has a valid end time period");
            printLine();
        } catch (IOException e) {
            System.out.println("unable to write");
        }
    }

    public static void todoExceptionHandler() {
        try {
            printTodo(userInput);
        }
        catch (EmptyTodo e) {
            printLine();
            System.out.println("\tPlease ensure that the todo has a description!");
            printLine();
        }
        try {
            fileObject.addToFile(currentIndex +"." + taskList.get(taskList.size()-1).getStatusAndDescription() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("unable to write");
        }
    }




//    final static int MAXTASKS = 100;
//    public static Task[] taskList = new Task[MAXTASKS];
    public static  ArrayList<Task> taskList = new ArrayList<>();

    public static int currentIndex = 0;
    public static Scanner in = new Scanner(System.in);
    public static String userInput;
    //public static exceptions.DukeException exceptionHandler;
    public static FileHandler fileObject = new FileHandler("src/main/java/dukeData.txt");
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------------------------------------------------------");
        try {
            fileObject.createFile();
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
        userInput = in.nextLine();
        while (true) { // ensure that the loop can stay on forever if needed.
            while(userInput.equals("") || userInput.equals(" ")) {
                printLine();
                System.out.println("\tSorry please enter a valid input ");
                printLine();
                userInput = in.nextLine();
            }
            if(userInput.equals("bye")) { // exit command
                break;
            } else if(userInput.equals("list")) { //displays the list if needed
                printLine();
                System.out.println("\tHere are the tasks in your list:");
                listTasks(currentIndex, taskList);
                printLine();
            } else if (isTheSame(userInput, "mark")) { //mark the task in
                while(isInRange(userInput, currentIndex)==false) {
                    printLine();
                    System.out.println("\tNice try, enter a valid index to mark:");
                    printLine();
                    userInput = in.nextLine();
                }
                printLine();
                printMarkedTask(userInput, taskList);
                printLine();
            } else if (isTheSame(userInput, "unmark")) {//unmark the task
                while(isInRange(userInput, currentIndex)==false) {
                    printLine();
                    System.out.println("\tNice try, enter a valid index to unmark:");
                    printLine();
                    userInput = in.nextLine();
                }
                printLine();
                printUnmarkedTask(userInput, taskList);
                printLine();
            } else if(isTheSame(userInput, "todo")) {
                todoExceptionHandler();
                //leave this for the final refactoring
            } else if(isTheSame(userInput, "deadline")) {
                deadlineExceptionHandler();
            } else if(isTheSame(userInput, "event")) {
                eventExceptionHandler();
            }  else { // tells the user that we have added the task in
                printTask(userInput); // could remove this and esnure that only specific tasks can be entered!
            }
            /*
            either way, the process to add to the file could be this way.
            Essentially create file manip:
                1. At the end of this while loop, take the final input
                2. could add at the end of each of the exception handlers, this should work fine
             */
            userInput = in.nextLine();
        }
       sayBye();
    }
}
