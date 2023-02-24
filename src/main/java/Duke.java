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

    //Need to uncomment whatever is written down here, ordinarily you can use this but since main is static cannot run this
    //private Ui ui;

//    public Duke() {
//        ui = new Ui();
//    }



    //This goes in the TaskList Class
    public static void deleteTask(String userInput, TaskList taskList, FileHandler fileObject) throws IndexOutOfBoundsException, NumberFormatException, IOException{
        Task item = new Task();
        try {
             item = taskList.getTask(Integer.parseInt(userInput.split(" ")[1]) - 1);
             taskList.removeTask(Integer.parseInt(userInput.split(" ")[1]) - 1);
             fileObject.populateFile(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        } catch (IOException e) {
            throw new IOException();
        }
        //TODO: Throw the exception, this way the flow of control is properly handled --done
        ui.printLine();
        System.out.println("\tNoted! I've removed this task!");
        System.out.println("\t\t" + item.getStatusAndDescription());
        ui.printNoTasks(taskList.getSize());
        ui.printLine();

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


    public static void deadlineExceptionHandler(String userInput, TaskList taskList) {
        try {
            ui.printDeadline(userInput, taskList);
            fileObject.addToFile(taskList.getTask(taskList.getSize()-1).enCode() + System.lineSeparator());
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

    public static void eventExceptionHandler(String userInput, TaskList taskList) {
        try {
            ui.printEvent(userInput, taskList);
            fileObject.addToFile(taskList.getTask(taskList.getSize()-1).enCode() + System.lineSeparator());
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

    public static void todoExceptionHandler(String userInput, TaskList taskList) {
        try {
            ui.printTodo(userInput, taskList);
            fileObject.addToFile(taskList.getTask(taskList.getSize()-1).enCode() + System.lineSeparator());
        }  catch (EmptyTodo e) {
            ui.printLine();
            System.out.println("\tPlease ensure that the todo has a description!");
            ui.printLine();
        } catch (IOException e) {
            System.out.println("unable to write");
        }
    }

    //TODO: Update the parameters to new TaskList Object
    public static void deleteExceptionHandler(String userInput, TaskList taskList) {
        try {
            deleteTask(userInput, taskList, fileObject);
        } catch (IndexOutOfBoundsException e) {
            ui.printLine();
            System.out.println("\tEnter a valid index to delete");
            ui.printLine();
        } catch (NumberFormatException e) {
            ui.printLine();
            System.out.println("\tEnter a valid number to delete");
            ui.printLine();
        } catch (IOException e) {
            ui.printLine();
            System.out.println("\tUnable to delete from the file");
            ui.printLine();
        }
    }





    //public static  ArrayList<Task> taskList = new ArrayList<Task>();
    public static Scanner in = new Scanner(System.in);
    //public static String userInput;
    //public static exceptions.DukeException exceptionHandler;
    public static FileHandler fileObject = new FileHandler(System.getProperty("user.dir") + "/dukeData.txt");
    public static Ui ui = new Ui();
    public static TaskList taskList = new TaskList(fileObject);
    public static Parser parser = new Parser();

    public static Boolean isExit = false;


    public static void main(String[] args) {
        try {
            fileObject.createFile();
            //taskList = fileObject.readFile();
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
        //Ui ui = new Ui(); cannot do this here yet
        ui.showWelcome();
        //ui.readCommand();     //Make user Input a member of the UI Class also
        while (!isExit) { // ensure that the loop can stay on forever if needed.
            ui.readCommand();
            ui.nullChecker();
            if(ui.getUserInput().equals("bye")) { // exit command
                isExit=true;
            } else if(ui.getUserInput().equals("list")) { //displays the list if needed
                ui.listTasks(taskList);
            } else if (parser.isTheSame(ui.getUserInput(), "mark")) { //mark the task in
                ui.markQualityChecker(taskList, fileObject);
            } else if (parser.isTheSame(ui.getUserInput(), "unmark")) {//unmark the task
                ui.unMarkQualityChecker(taskList, fileObject);
            } else if(parser.isTheSame(ui.getUserInput(), "todo")) {
                todoExceptionHandler(ui.getUserInput(), taskList);
            } else if(parser.isTheSame(ui.getUserInput(), "deadline")) {
                deadlineExceptionHandler(ui.getUserInput(), taskList);
            } else if(parser.isTheSame(ui.getUserInput(), "event")) {
                eventExceptionHandler(ui.getUserInput(), taskList);
            } else if(parser.isTheSame(ui.getUserInput(), "delete")) {
                deleteExceptionHandler(ui.getUserInput(), taskList);
            }  else if (parser.isTheSame(ui.getUserInput(),"help")) {
                ui.displayHelper();
            } else { // tells the user that we have added the task in
                //printTask(ui.getUserInput()); // could remove this and ensure that only specific tasks can be entered!
                ui.validCommand();
            }
            //ui.readCommand(); moved to the top
        }
      ui.sayBye();
    }
}
