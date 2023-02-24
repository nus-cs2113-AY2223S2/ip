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

    //Need to uncomment whatever is written down here, ordinally you can use this but since main is static cannot run this
    //private Ui ui;

//    public Duke() {
//        ui = new Ui();
//    }

    //This can go in the UI Class
//    public static void ui.printLine() {
//        System.out.println("\t---------------------------------------------------------------------------------");
//    }

    //This can go in the UI Class


    //This goes in the parser class
    public static boolean isTheSame(String userInput, String toCompare) {
        return userInput.split(" ")[0].equals(toCompare);
    }
    

    //This goes in the parser class
    public static boolean isInRange(String userInput, ArrayList<Task> taskList) {
        boolean isReturn = false;
        try {
            isReturn = Integer.parseInt(userInput.split(" ")[1])>0 && Integer.parseInt(userInput.split(" ")[1])<taskList.size()+1;
        } catch (NumberFormatException e) {
            System.out.println("\tWhoops, need to ensure that your inputs are numbers! BUT a");
        }
        return (isReturn);
    }

    //This goes in the UI Class


    //This goes in the TaskList Class
    public static void deleteTask(String userInput, ArrayList<Task> taskList) throws IndexOutOfBoundsException, NumberFormatException{
        Task item = new Task();
        try {
             item = taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        ui.printLine();
        System.out.println("\tNoted! I've removed this task!");
        System.out.println("\t\t" + taskList.get(Integer.parseInt(userInput.split(" ")[1]) - 1).getStatusAndDescription());
        taskList.remove(Integer.parseInt(userInput.split(" ")[1]) - 1);
        ui.printNoTasks(taskList.size());
        ui.printLine();
        try {
            fileObject.populateFile(taskList);
        } catch (IOException e) {
            System.out.println("Unable to delete");
        }

    }

    //This goes in the UI Class


    //This goes in the parser class


    //This goes in the parser class




    // This goes into the UI Class
    

    //This goes into the UI Class


    //this goes into the UI class


    //this goes into the UI Class

//    public static void printTask(String userInput) {
//        ui.printLine();
//        Task temp = new Task(userInput); // set the description
//        taskList.add(temp);
//        System.out.println("\tadded: " + userInput);
//        ui.printLine();
//    }

    //This goes into the UI Class


    public static void deadlineExceptionHandler(String userInput, ArrayList<Task> taskList) {
        try {
            ui.printDeadline(userInput, taskList);
            fileObject.addToFile(taskList.get(taskList.size()-1).enCode() + System.lineSeparator());
        } catch (EmptyDeadline e) {
            ui.printLine();
            System.out.println("\tPlease ensure that the deadline isn't empty!");
            ui.printLine();
        } catch (DeadlineMissingPhrase e) {
            ui.printLine();
            System.out.println("\tPlease ensure that you include the '/by' phrase to indicate the deadline!");
            ui.printLine();
        } catch (DeadlineIsBlank e) {
            ui.printLine();
            System.out.println("\tPlease ensure that the deadline is not composed of solely white spaces!");
            ui.printLine();
        } catch (IOException e) {
            System.out.println("unable to write");
        }
    }

    public static void eventExceptionHandler(String userInput, ArrayList<Task> taskList) {
        try {
            ui.printEvent(userInput, taskList);
            fileObject.addToFile(taskList.get(taskList.size()-1).enCode() + System.lineSeparator());
        } catch (EmptyEvent e) {
            ui.printLine();
            System.out.println("\tPlease ensure that the event isn't empty!");
            ui.printLine();
        } catch (EventMissingFromPhrase e) {
            ui.printLine();
            System.out.println("\tPlease ensure that you include the '/from' phrase to indicate the start of the event!");
            ui.printLine();
        } catch (EventMissingToPhrase e) {
            ui.printLine();
            System.out.println("\tPlease ensure that you include the '/to' phrase to indicate the end of the event!");
            ui.printLine();
        } catch (EventMissingBothPhrases e) {
            ui.printLine();
            System.out.println("\tPlease ensure that you include the '/from' and '/to' phrase to indicate the start and end of the event!");
            ui.printLine();
        } catch (EventFromIsBlank e) {
            ui.printLine();
            System.out.println("\tPlease ensure that the event has a valid start time period");
            ui.printLine();
        } catch (EventToIsBlank e) {
            ui.printLine();
            System.out.println("\tPlease ensure that the event has a valid end time period");
            ui.printLine();
        } catch (IOException e) {
            System.out.println("unable to write");
        }
    }

    public static void todoExceptionHandler(String userInput, ArrayList<Task> arrayList) {
        try {
            ui.printTodo(userInput, taskList);
        }
        catch (EmptyTodo e) {
            ui.printLine();
            System.out.println("\tPlease ensure that the todo has a description!");
            ui.printLine();
        }
        try {
            fileObject.addToFile(taskList.get(taskList.size()-1).enCode() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("unable to write");
        }
    }

    public static void deleteExceptionHandler(String userInput, ArrayList<Task> arrayList) {
        try {
            deleteTask(userInput, arrayList);
        } catch (IndexOutOfBoundsException e) {
            ui.printLine();
            System.out.println("\tEnter a valid index to delete");
            ui.printLine();
        } catch (NumberFormatException e) {
            ui.printLine();
            System.out.println("\tEnter a valid number to delete");
            ui.printLine();
        }
    }




//    final static int MAXTASKS = 100;
//    public static Task[] taskList = new Task[MAXTASKS];
    public static  ArrayList<Task> taskList = new ArrayList<Task>();
    public static Ui ui = new Ui();
    public static Scanner in = new Scanner(System.in);
    //public static String userInput;
    //public static exceptions.DukeException exceptionHandler;
    public static FileHandler fileObject = new FileHandler(System.getProperty("user.dir") + "/dukeData.txt");

    public static Boolean isExit = false;


    public static void main(String[] args) {
        try {
            fileObject.createFile();
            //fileObject.clearFile();
            taskList = fileObject.readFile();
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
        ui.showWelcome();
        //ui.readCommand();     //Make user Input a member of the UI Class also
        while (!isExit) { // ensure that the loop can stay on forever if needed.
            ui.readCommand();

            while(ui.getUserInput().equals("") || ui.getUserInput().equals(" ")) {
                ui.printLine();
                System.out.println("\tSorry please enter a valid input ");
                ui.printLine();
                ui.readCommand();
            }

            if(ui.getUserInput().equals("bye")) { // exit command
                isExit=true;
            } else if(ui.getUserInput().equals("list")) { //displays the list if needed
                ui.printLine();
                System.out.println("\tHere are the tasks in your list:");
                ui.listTasks(taskList);
                ui.printLine();
            } else if (isTheSame(ui.getUserInput(), "mark")) { //mark the task in
                if(isInRange(ui.getUserInput(), taskList)==false) {
                    ui.printLine();
                    System.out.println("\tNice try, enter a valid index to mark:");
                    ui.printLine();
                } else {
                    ui.printMarkedTask(ui.getUserInput(), taskList, fileObject);
                }
                //printMarkedTask(ui.getUserInput(), taskList);
            } else if (isTheSame(ui.getUserInput(), "unmark")) {//unmark the task
                if(isInRange(ui.getUserInput(), taskList)==false) {
                    ui.printLine();
                    System.out.println("\tNice try, enter a valid index to unmark:");
                    ui.printLine();
                } else {
                    ui.printUnmarkedTask(ui.getUserInput(), taskList, fileObject);
                }
            } else if(isTheSame(ui.getUserInput(), "todo")) {
                todoExceptionHandler(ui.getUserInput(), taskList);
                //leave this for the final refactoring
            } else if(isTheSame(ui.getUserInput(), "deadline")) {
                deadlineExceptionHandler(ui.getUserInput(), taskList);
            } else if(isTheSame(ui.getUserInput(), "event")) {
                eventExceptionHandler(ui.getUserInput(), taskList);
            } else if(isTheSame(ui.getUserInput(), "delete")) {
                deleteExceptionHandler(ui.getUserInput(), taskList);
            }  else if (isTheSame(ui.getUserInput(),"help")) {
                ui.displayHelper();
            } else { // tells the user that we have added the task in
                //printTask(ui.getUserInput()); // could remove this and ensure that only specific tasks can be entered!
                ui.printLine();
                System.out.println("\tPlease enter a valid input");
                ui.printLine();
            }
            //ui.readCommand(); moved to the top
        }
      ui.sayBye();
    }
}
