package mikeyparts;

import java.util.Scanner;
import static mikeyparts.Storage.saveToFile;
import static mikeyparts.TaskList.deleteTask;
import static mikeyparts.TaskList.findTasks;
import static mikeyparts.TaskList.markDone;
import static mikeyparts.TaskList.newDeadline;
import static mikeyparts.TaskList.newEvent;
import static mikeyparts.TaskList.newTodo;
import static mikeyparts.TaskList.tasks;
import static mikeyparts.TaskList.unmarkDone;
import static mikeyparts.UI.printDeadlineTaskMessage;
import static mikeyparts.UI.printDeleteErrorMessage;
import static mikeyparts.UI.printEventTaskMessage;
import static mikeyparts.UI.printExitMessage;
import static mikeyparts.UI.printFindErrorMessage;
import static mikeyparts.UI.printInvalidInputMessage;
import static mikeyparts.UI.printInvalidTaskMessage;
import static mikeyparts.UI.printList;
import static mikeyparts.UI.printTodoTaskMessage;


public class Parser {

    public TaskList taskList = new TaskList();

    public void parseCommand() throws java.io.IOException {
        Scanner inputText = new Scanner(System.in);
        String userInput = inputText.nextLine();
    String keyword = userInput.split(" ", 0)[0];
        if (keyword.equalsIgnoreCase("bye")) {
            printExitMessage();
            System.exit(0);
            printExitMessage();
        } else if (keyword.equalsIgnoreCase("list")) {
            printList(tasks);
        } else if (keyword.equalsIgnoreCase("mark")) {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            markDone(tasks, taskNumber);
        } else if (keyword.equalsIgnoreCase("unmark")) {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            unmarkDone(tasks, taskNumber);
        } else if (keyword.equalsIgnoreCase("todo")) {
            int start = 0;
            try {
                start = userInput.indexOf(keyword) + 5;
                String taskName = userInput.substring(start);
                newTodo(taskName, 0);
                printTodoTaskMessage(tasks);
                saveToFile(tasks);
            } catch (Exception e) {
                printInvalidTaskMessage();
            }
        } else if (keyword.equalsIgnoreCase("deadline")) {
            int start = 0;
            try {
                start = userInput.indexOf(keyword) + 9;
                int startOfBy = userInput.indexOf("/by");
                String taskName = userInput.substring(start, startOfBy - 1);
                String dateTime = userInput.substring(startOfBy + 4);
                newDeadline(taskName, dateTime, 0);
                printDeadlineTaskMessage(tasks);
                saveToFile(tasks);
            } catch (Exception e) {
                printInvalidTaskMessage();
            }
        } else if (keyword.equalsIgnoreCase("event")) {
            int start = 0;
            try {
                start = userInput.indexOf(keyword) + 6;
                int startOfFrom = userInput.indexOf("/from");
                String taskName = userInput.substring(start, startOfFrom - 1);
                String timeOfEvent = userInput.substring(startOfFrom);
                timeOfEvent = timeOfEvent.replace("from", "from:");
                timeOfEvent = timeOfEvent.replace("to", "to:");
                newEvent(taskName, timeOfEvent, 0);
                printEventTaskMessage(tasks);
                saveToFile(tasks);
            } catch (Exception e) {
                printInvalidTaskMessage();
            }
        } else if (keyword.equalsIgnoreCase("delete")) {
            try {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                deleteTask(taskNumber);
                saveToFile(tasks);
            } catch (Exception e) {
                printDeleteErrorMessage();
            }
        } else if (keyword.equalsIgnoreCase("find")) {
            try {
                int start = userInput.indexOf(keyword) + 5;
                String searchTerm = userInput.substring(start);
                findTasks(searchTerm);
            } catch (Exception e) {
                printFindErrorMessage();
            }
        } else {
            printInvalidInputMessage();
        }
    }

}
