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
        boolean isReturn = false;
        try {
            isReturn = Integer.parseInt(userInput.split(" ")[1])>0 && Integer.parseInt(userInput.split(" ")[1])<currentIndex+1;
        } catch (NumberFormatException e) {
            System.out.println("\tWhoops, need to ensure that your inputs are numbers! BUT a");
        }
        return (isReturn);
    }

    public static void printMarkedTask(String userInput, ArrayList<Task> taskList) {
//        try {
//            taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1).markTask();
//        } catch (NumberFormatException e) {
//            printLine();
//            System.out.println("\tWhoops inputs must be numbers");
//            printLine();
//        } catch (IndexOutOfBoundsException e) {
//            printLine();
//            System.out.println("\tWhoops inputs must be valid");
//            printLine();
//        }
        System.out.println("\tNice! I've marked this task as done:");
        taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1).markTask();
        try {
            fileObject.populateFile(taskList);
        } catch (IOException e) {
            System.out.println("WHoops");
        }
        System.out.println("\t\t" + taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1).getStatusAndDescription());
    }

    public static void deleteTask() throws IndexOutOfBoundsException, NumberFormatException{
        Task item = new Task();
        try {
             item = taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        printLine();
        System.out.println("\tNoted! I've removed this task!");
        System.out.println("\t\t" + taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1).getStatusAndDescription());
        taskList.remove(Integer.parseInt(userInput.split(" ")[1]) - 1);
        currentIndex-=1;
        printNoTasks(currentIndex);
        printLine();
        try {
            fileObject.populateFile(taskList);
        } catch (IOException e) {
            System.out.println("Unable to delete");
        }

    }

    public static void printUnmarkedTask(String userInput, ArrayList<Task> taskList) {
        System.out.println("\tNice! I've marked this task as not done:");
        taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1).unMarkTask();
        try {
            fileObject.populateFile(taskList);
        } catch (IOException e) {
            System.out.println("WHoops");
        }
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
//    public static void printTask(String userInput) {
//        printLine();
//        Task temp = new Task(userInput); // set the description
//        taskList.add(temp);
//        currentIndex+=1;
//        System.out.println("\tadded: " + userInput);
//        printLine();
//    }
    public static void sayBye() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    public static void displayHelper() {
        printLine();
        System.out.println("\tHi! These are the commands which duke understands!");
        printLine();
        System.out.println("\ttodo - Creates a todo, use it by adding 'todo' and some description. An example is listed below:");
        System.out.println("\t\t'todo get milk'");
        printLine();
        System.out.println("\tdeadline - Creates a deadline, use it by adding 'deadline' followed by some description and a deadline which follows '/by'");
        System.out.println("\t\t'deadline get milk /by tomorrow'");
        printLine();
        System.out.println("\tevent - Creates an event, use it by adding 'event' ,some description, a start date followed by '/from' and an end date followed by '/to'");
        System.out.println("\t\t'event get some milk /from today /to tomorrow");
        printLine();
        System.out.println("\tbye - to exit the program!");
        printLine();
    }

    public static void deadlineExceptionHandler() {
        try {
            printDeadline(userInput);
            fileObject.addToFile(taskList.get(taskList.size()-1).enCode() + System.lineSeparator());
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
            fileObject.addToFile(taskList.get(taskList.size()-1).enCode() + System.lineSeparator());
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
            fileObject.addToFile(taskList.get(taskList.size()-1).enCode() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("unable to write");
        }
    }

    public static void deleteExceptionHandler() {
        try {
            deleteTask();
        } catch (IndexOutOfBoundsException e) {
            printLine();
            System.out.println("\tEnter a valid index to delete");
            printLine();
        } catch (NumberFormatException e) {
            printLine();
            System.out.println("\tEnter a valid number to delete");
            printLine();
        }
    }




//    final static int MAXTASKS = 100;
//    public static Task[] taskList = new Task[MAXTASKS];
    public static  ArrayList<Task> taskList = new ArrayList<>();

    public static int currentIndex = 0;
    public static Scanner in = new Scanner(System.in);
    public static String userInput;
    //public static exceptions.DukeException exceptionHandler;
    public static FileHandler fileObject = new FileHandler(System.getProperty("user.dir") + "/dukeData.txt");
    public static void main(String[] args) {
        try {
            fileObject.createFile();
            //fileObject.clearFile();
            taskList = fileObject.readFile();
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
        currentIndex = taskList.size();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("If you are unsure of the commands, type 'help'");
        System.out.println("---------------------------------------------------------------------------------");
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
                if(isInRange(userInput, currentIndex)==false) {
                    printLine();
                    System.out.println("\tNice try, enter a valid index to mark:");
                    printLine();
                } else {
                    printLine();
                    printMarkedTask(userInput, taskList);
                    printLine();
                }
                //printMarkedTask(userInput, taskList);
            } else if (isTheSame(userInput, "unmark")) {//unmark the task
                if(isInRange(userInput, currentIndex)==false) {
                    printLine();
                    System.out.println("\tNice try, enter a valid index to unmark:");
                    printLine();
                } else {
                    printLine();
                    printUnmarkedTask(userInput, taskList);
                    printLine();
                }
            } else if(isTheSame(userInput, "todo")) {
                todoExceptionHandler();
                //leave this for the final refactoring
            } else if(isTheSame(userInput, "deadline")) {
                deadlineExceptionHandler();
            } else if(isTheSame(userInput, "event")) {
                eventExceptionHandler();
            } else if(isTheSame(userInput, "delete")) {
                deleteExceptionHandler();
            }  else if (isTheSame(userInput,"help")) {
                displayHelper();
            } else { // tells the user that we have added the task in
                //printTask(userInput); // could remove this and ensure that only specific tasks can be entered!
                printLine();
                System.out.println("\tPlease enter a valid input");
                printLine();
            }
            userInput = in.nextLine();
        }
       sayBye();
    }
}
