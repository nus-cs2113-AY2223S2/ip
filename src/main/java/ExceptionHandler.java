import exceptions.*;

import java.io.IOException;

public class ExceptionHandler {
    protected Ui ui = new Ui();
    protected Command command = new Command();
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
            System.out.println("unable to write");
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
            System.out.println("unable to write");
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
        }
    }
}
