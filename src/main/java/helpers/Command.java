package helpers;

import corefunctionalities.FileHandler;
import corefunctionalities.TaskList;
import corefunctionalities.Ui;
import dataypes.Deadlines;
import dataypes.Events;
import dataypes.Task;
import dataypes.Todos;
import exceptions.DeadlineIsBlank;
import exceptions.DeadlineMissingPhrase;
import exceptions.EmptyDeadline;
import exceptions.EmptyEvent;
import exceptions.EmptyList;
import exceptions.EmptyTodo;
import exceptions.EventFromIsBlank;
import exceptions.EventMissingBothPhrases;
import exceptions.EventMissingFromPhrase;
import exceptions.EventMissingToPhrase;
import exceptions.EventToIsBlank;
import exceptions.FromAfterTo;
import exceptions.MarkQualityException;
import exceptions.TaskMarked;
import exceptions.TaskUnMarked;
import exceptions.UnmarkQualityException;
import exceptions.WrongChrono;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * The Command Class can execute all the commands and pass any exceptions to the {@link corefunctionalities.ExceptionHandler}
 *
 * @author Muthya Narayanachary Akhil
 */
public class Command {
    protected Parser parser = new Parser();
    protected ExceptionGenerator exceptionGenerator = new ExceptionGenerator();
    protected Ui ui = new Ui();

    /**
     * List the tasks stored inside {@link TaskList}
     *
     * @param taskList The {@link TaskList} containing the {@link Task} elements
     * @throws EmptyList In the case the List is empty
     */
    public void commandlistTasks(TaskList taskList) throws EmptyList {
        ui.listTasks(taskList);
    }

    /**
     * This command diplays {@link Ui#listTasks(TaskList)}
     */
    public void commandHelp() {
        ui.displayHelper();
    }

    /**
     * This method executes the addition of an {@link Todos} datatype into the {@link TaskList}.
     *
     * @param userInput The userinput which contains the details which can be extracted into the {@link Todos} object
     * @param taskList The {@link TaskList} containing the {@link Task} elements
     * @param fileObject The object of {@link FileHandler} which can write to a file
     * @throws EmptyTodo In the case the description of the Todo is empty
     * @throws IOException In case fileObject cannot write to the file
     */
    public void commandTodo(String userInput, TaskList taskList, FileHandler fileObject) throws EmptyTodo, IOException {
        String[] holder = parser.getTodo(userInput);
        exceptionGenerator.todoExceptionGenerator(holder);
        String input = userInput.replace("todo ", "");
        ui.printLine();
        Todos temp = new Todos(input);
        taskList.addTask(temp); // this will be an issue, weirdly it isn't
        fileObject.addToFile(taskList.getTask(taskList.getSize()-1).enCode() + System.lineSeparator());
        ui.printTaskEnding(taskList);
    }

    /**
     * This method executes the addition of an {@link Deadlines} datatype into the {@link TaskList}.
     *
     * @param userInput The userinput which contains the details which can be extracted into the {@link Deadlines} object
     * @param taskList The {@link TaskList} containing the {@link Task} elements
     * @param fileObject The object of {@link FileHandler} which can write to a file
     * @throws EmptyDeadline In case the deadline description is empty
     * @throws DeadlineMissingPhrase In case /by phrase is missing.
     * @throws DeadlineIsBlank In case the deadline date is blank.
     * @throws IOException In case fileObject cannot write to the file
     * @throws DateTimeParseException In case the /by date is in the wrong format
     * @throws WrongChrono In case the /by date is before the current date.
     */
    public void commandDeadline(String userInput, TaskList taskList, FileHandler fileObject) throws EmptyDeadline, DeadlineMissingPhrase, DeadlineIsBlank, IOException, DateTimeParseException, WrongChrono {
        String[] deadlineAndDescription = parser.getDeadline(userInput);
        exceptionGenerator.deadlineExceptionGenerator(deadlineAndDescription, userInput);
        ui.printLine();
        Deadlines temp = new Deadlines(deadlineAndDescription[0], deadlineAndDescription[1]);
        taskList.addTask(temp);
        fileObject.addToFile(taskList.getTask(taskList.getSize()-1).enCode() + System.lineSeparator());
        ui.printTaskEnding(taskList);
    }

    /**
     * This method executes the addition of an {@link Events} datatype into the {@link TaskList}.
     *
     * @param userInput The userinput which contains the details which can be extracted into the {@link Events} object
     * @param taskList The {@link TaskList} containing the {@link Task} elements
     * @param fileObject The object of {@link FileHandler} which can write to a file
     * @throws EmptyEvent In case the Event Description is Empty
     * @throws EventMissingBothPhrases In case the Event is missing the /from and /to phrase
     * @throws EventMissingToPhrase IN case the event is missing the /to phrase
     * @throws EventMissingFromPhrase In case the event is missing the /from phrase
     * @throws EventFromIsBlank In case the /from date is blank
     * @throws EventToIsBlank In case the /to date is blank
     * @throws IOException In case fileObject cannot write to the file
     * @throws DateTimeParseException In case the /from and /to dates aren't in the right format
     * @throws FromAfterTo In case the /from date is after the /to date
     * @throws WrongChrono In case /from or /to dates are before the current date.
     */
    public void commandEvent(String userInput, TaskList taskList, FileHandler fileObject) throws EmptyEvent, EventMissingBothPhrases, EventMissingToPhrase, EventMissingFromPhrase, EventFromIsBlank, EventToIsBlank, IOException, DateTimeParseException, FromAfterTo, WrongChrono, ArrayIndexOutOfBoundsException {
        String [] eventDescription = parser.getEvent(userInput);
        exceptionGenerator.eventExceptionGenerator(eventDescription, userInput);
        ui.printLine();
        Events temp = new Events(eventDescription[0], eventDescription[1], eventDescription[2]);
        taskList.addTask(temp);
        fileObject.addToFile(taskList.getTask(taskList.getSize()-1).enCode() + System.lineSeparator());
        ui.printTaskEnding(taskList);
    }

    /**
     * Marks a specific task done in {@link TaskList}
     *
     * @param userInput The userinput which contains the details which can be used to mark
     * @param taskList The {@link TaskList} containing the {@link Task} elements
     * @param fileObject The object of {@link FileHandler} which can write to a file
     * @throws MarkQualityException If the serial number of the task to be marked does not exist
     * @throws IOException In case fileObject cannot write to the file
     * @throws NumberFormatException In case the input is not numerical
     * @throws TaskMarked In case the task is already marked
     */
    public void commandMarkTask(String userInput, TaskList taskList, FileHandler fileObject) throws MarkQualityException, IOException, NumberFormatException, TaskMarked {
        exceptionGenerator.markExceptionGenerator(userInput, taskList);
        taskList.getTask(Integer.parseInt(userInput.split(" ")[1]) - 1).markTask();
        fileObject.populateFile(taskList);
        ui.printMarkedTask(userInput, taskList);

    }
    /**
     * Unmarks a specific task done in {@link TaskList}
     *
     * @param userInput The userinput which contains the details which can be used to unmark
     * @param taskList The {@link TaskList} containing the {@link Task} elements
     * @param fileObject The object of {@link FileHandler} which can write to a file
     * @throws UnmarkQualityException If the serial number of the task to be unmarked does not exist
     * @throws IOException In case fileObject cannot write to the file
     * @throws NumberFormatException In case the input is not numerical
     * @throws TaskUnMarked In case the task is already unmarked
     */

    public void commandUnMarkTask(String userInput, TaskList taskList, FileHandler fileObject) throws UnmarkQualityException, IOException, NumberFormatException, TaskUnMarked {
        exceptionGenerator.unMarkExceptionGenerator(userInput, taskList);
        taskList.getTask(Integer.parseInt(userInput.split(" ")[1]) - 1).unMarkTask();
        fileObject.populateFile(taskList);
        ui.printUnmarkedTask(userInput, taskList);
    }

    /**
     *
     * @param userInput The user input which contains the serial number of the task that needs to be deleted
     * @param taskList The {@link TaskList} containing the {@link Task} elements
     * @param fileObject The object of {@link FileHandler} which can write to a file
     * @throws IndexOutOfBoundsException If the serial number of the task to be unmarked does not exist
     * @throws NumberFormatException In case the input is not numerical
     * @throws IOException In case fileObject cannot write to the file
     */

    public void commandDeleteTask(String userInput, TaskList taskList, FileHandler fileObject) throws IndexOutOfBoundsException, NumberFormatException, IOException, EmptyList{
        if(taskList.getSize()==0) {
            throw new EmptyList();
        }
        Task item = new Task();
        item = taskList.getTask(Integer.parseInt(userInput.split(" ")[1]) - 1);
        taskList.removeTask(Integer.parseInt(userInput.split(" ")[1]) - 1);
        fileObject.populateFile(taskList);
        ui.printDeleteCommand(item, taskList);
    }
}
