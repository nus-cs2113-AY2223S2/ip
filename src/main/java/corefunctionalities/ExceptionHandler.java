package corefunctionalities;

import dataypes.Task;
import exceptions.*;
import helpers.Command;
import helpers.Parser;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class ExceptionHandler {
    protected Ui ui = new Ui();
    protected Command command = new Command();
    protected Parser parser = new Parser();
    protected boolean isExit = false;
    public void deadlineExceptionHandler(String userInput, TaskList taskList, FileHandler fileObject) {
        try {
            command.commandDeadline(userInput, taskList, fileObject);
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
            ui.printLine();
            System.out.println("unable to write");
        } catch (DateTimeParseException e) {
            System.out.println("\tPlease ensure that the deadline follows the following format: yyyy-mm-dd.");
            ui.printLine();
        } catch (WrongChrono e) {
            System.out.println("\tPlease ensure that the deadline isn't before the current date");
            ui.printLine();
        }
    }

    public void eventExceptionHandler(String userInput, TaskList taskList, FileHandler fileObject) {
        try {
            command.commandEvent(userInput, taskList, fileObject);
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
            ui.printLine();
            System.out.println("unable to write");
        } catch (DateTimeParseException e) {
            System.out.println("\tPlease ensure that the from and to dates follow the following format: yyyy-mm-dd.");
            ui.printLine();
        } catch (WrongChrono e) {
            System.out.println("\tPlease ensure that the from and to dates aren't in the past...");
            ui.printLine();
        }  catch (FromAfterTo e) {
            System.out.println("\tPlease ensure that your dates are chronologically appropriate...");
            System.out.println("\tThe from date cannot be after the to date");
            ui.printLine();
        }
    }

    public  void todoExceptionHandler(String userInput, TaskList taskList, FileHandler fileObject) {
        try {
            command.commandTodo(userInput, taskList, fileObject);
        }  catch (EmptyTodo e) {
            ui.printLine();
            System.out.println("\tPlease ensure that the todo has a description!");
            ui.printLine();
        } catch (IOException e) {
            System.out.println("unable to write");
        }
    }

    public  void deleteExceptionHandler(String userInput, TaskList taskList, FileHandler fileObject) {
        try {
            command.commandDeleteTask(userInput, taskList, fileObject);
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

    public  void markExceptionHandler(String userInput, TaskList taskList, FileHandler fileObject) {
        try {
            command.commandMarkTask(userInput, taskList, fileObject);
        } catch (MarkQualityException e) {
            ui.printLine();
            System.out.println("\tNice try, enter a valid index to mark:");
            ui.printLine();
        } catch (IOException e) {
            System.out.println("WHoops");
        } catch (NumberFormatException e) {
            ui.printLine();
            System.out.println("\tNice try, enter a valid Number to mark:");
            ui.printLine();
        } catch (TaskMarked e) {
            ui.printLine();
            System.out.println("\tTask has already been marked");
            ui.printLine();
        }
    }

    public  void unMarkExceptionHandler(String userInput, TaskList taskList, FileHandler fileObject) {
        try {
            command.commandUnMarkTask(userInput, taskList, fileObject);
        } catch (UnmarkQualityException e) {
            ui.printLine();
            System.out.println("\tNice try, enter a valid index to unmark:");
            ui.printLine();
        } catch (IOException e) {
            System.out.println("WHoops");
        } catch (NumberFormatException e) {
            ui.printLine();
            System.out.println("\tNice try, enter a valid Number to unmark:");
            ui.printLine();
        } catch (TaskUnMarked e) {
            ui.printLine();
            System.out.println("\tTask has already been unmarked");
            ui.printLine();
        }
    }
    public void listExceptionHandler(TaskList taskList) {
        try {
            command.commandlistTasks(taskList);
        } catch (EmptyList e) {
            ui.printLine();
            System.out.println("\tThe list is empty!");
            ui.printLine();
        }
    }

    public boolean isExit() {
        return isExit;
    }

    public void execute(String userInput, TaskList taskList, FileHandler fileObject) {
        if(userInput.equals("bye")) { // exit command
            isExit=true;
        } else if(userInput.equals("list")) { //displays the list if needed
            this.listExceptionHandler(taskList);
        } else if (parser.isTheSame(userInput, "mark")) { //mark the task in
            this.markExceptionHandler(userInput, taskList, fileObject);
        } else if (parser.isTheSame(userInput, "unmark")) {//unmark the task
            this.unMarkExceptionHandler(userInput, taskList, fileObject);
        } else if(parser.isTheSame(userInput, "todo")) {
            this.todoExceptionHandler(userInput, taskList, fileObject);
        } else if(parser.isTheSame(userInput, "deadline")) {
            this.deadlineExceptionHandler(userInput, taskList, fileObject);
        } else if(parser.isTheSame(userInput, "event")) {
            this.eventExceptionHandler(userInput, taskList, fileObject);
        } else if(parser.isTheSame(userInput, "delete")) {
            this.deleteExceptionHandler(userInput, taskList, fileObject);
        }  else if (parser.isTheSame(userInput,"help")) {
            command.commandHelp();
        }else if (parser.isTheSame(userInput, "find")) {
            taskList.find(parser.withoutFind(userInput), ui);
        }  else {
            ui.validCommand();
        }
    }
}
