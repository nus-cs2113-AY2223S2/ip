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



    //public static  ArrayList<Task> taskList = new ArrayList<Task>();
    public static Scanner in = new Scanner(System.in);
    //public static String userInput;
    //public static exceptions.DukeException exceptionHandler;
    public static FileHandler fileObject = new FileHandler(System.getProperty("user.dir") + "/dukeData.txt");
    public static Ui ui = new Ui();
    public static TaskList taskList = new TaskList(fileObject);
    public static Parser parser = new Parser();

    public static Boolean isExit = false;
    public static Command command = new Command();
    public static ExceptionHandler exceptionHandler = new ExceptionHandler();


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
                command.commandlistTasks(taskList);
            } else if (parser.isTheSame(ui.getUserInput(), "mark")) { //mark the task in
                exceptionHandler.markExceptionHandler(ui.userInput, taskList, fileObject);
            } else if (parser.isTheSame(ui.getUserInput(), "unmark")) {//unmark the task
                exceptionHandler.unMarkExceptionHandler(ui.userInput, taskList, fileObject);
            } else if(parser.isTheSame(ui.getUserInput(), "todo")) {
                exceptionHandler.todoExceptionHandler(ui.getUserInput(), taskList, fileObject);
            } else if(parser.isTheSame(ui.getUserInput(), "deadline")) {
                exceptionHandler.deadlineExceptionHandler(ui.getUserInput(), taskList, fileObject);
            } else if(parser.isTheSame(ui.getUserInput(), "event")) {
                exceptionHandler.eventExceptionHandler(ui.getUserInput(), taskList, fileObject);
            } else if(parser.isTheSame(ui.getUserInput(), "delete")) {
                exceptionHandler.deleteExceptionHandler(ui.getUserInput(), taskList, fileObject);
            }  else if (parser.isTheSame(ui.getUserInput(),"help")) {
                command.commandHelp();
            } else { // tells the user that we have added the task in
                //printTask(ui.getUserInput()); // could remove this and ensure that only specific tasks can be entered!
                ui.validCommand();
            }
            //ui.readCommand(); moved to the top
        }
      ui.sayBye();
    }
}
