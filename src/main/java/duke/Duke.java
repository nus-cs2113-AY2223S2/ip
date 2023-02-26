package duke;

import duke.exceptions.EmptyTaskException;
import duke.exceptions.InvalidCommandException;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static duke.command.AddCommand.*;
import static duke.command.ChangeStatusCommand.markTask;
import static duke.command.ChangeStatusCommand.unmarkTask;
import static duke.command.DeleteCommand.deleteTask;
import static duke.command.FindCommand.findTask;
import static duke.parser.Parser.*;
import static duke.storage.Storage.readFileData;
import static duke.storage.Storage.writeToFile;
import static duke.tasklist.TaskList.printList;
import static duke.ui.Ui.*;

public class Duke {



    public static void main(String[] args) {
        Boolean isDukeRunning = true;
        TaskList tasks = new TaskList();
        printGreeting();
        readFileData(tasks);

        String command;
        String[] inputArguments;

        do {
            command = getCommand();
            inputArguments = getArguments();

            try {
                switch (command) {
                    case "list":
                        printList(tasks);
                        break;


                    case "mark":
                        Integer index = Integer.parseInt(inputArguments[1]);
                        try{
                            markTask(tasks, index - 1);
                        } catch(NullPointerException e){
                            System.out.println("Error! Specify a valid task index!");
                            printBorder();
                        }
                        break;


                    case "unmark":
                        Integer index_um = Integer.parseInt(inputArguments[1]);
                        try{
                            unmarkTask(tasks, index_um - 1);
                        } catch(NullPointerException e){
                            System.out.println("Error! Specify a valid task index!");
                            printBorder();
                        }
                        break;

                    case "deadline":
                        try {
                            String[] info = inputArguments[1].split("/by", 2);
                            addDeadline(tasks, info[0], info[1]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("OOPS! The description of task cannot be empty");
                            printBorder();
                        }
                        break;


                    case "todo":
                        try {
                            addTodo(tasks, inputArguments[1]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("OOPS! The description of task cannot be empty");
                            printBorder();
                        } catch (EmptyTaskException e) {
                            e.printErrorMessage();
                            printBorder();
                        } catch (NullPointerException e) {
                            System.out.println("OOPS! The description of task cannot be empty");
                            printBorder();
                        }
                        break;

                    case "event":
                        try {
                            String[] info_e = inputArguments[1].split("/from|/to", 3);
                            addEvent(tasks, info_e[0], info_e[1], info_e[2]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("OOPS! The description of task cannot be empty");
                            printBorder();
                        }
                        break;

                    case "delete":
                        Integer index_dl = Integer.parseInt(inputArguments[1]);
                        try {
                            deleteTask(tasks, index_dl - 1);
                        } catch(IndexOutOfBoundsException e){
                            System.out.println("Error! Specify a valid task index!");
                            printBorder();
                        }
                        break;

                    case "find":
                        String keyword = inputArguments[1];
                        findTask(tasks, keyword);
                        break;

                    case "bye":
                        isDukeRunning = false;
                        printBorder();
                        printExit();
                        printBorder();
                        break;


                    default:
                        throw new InvalidCommandException();

                }
            } catch(InvalidCommandException e){
                e.printErrorMessage();
                printBorder();
            }
        } while(isDukeRunning);

        writeToFile(tasks);

    }
}
