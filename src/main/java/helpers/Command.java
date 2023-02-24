package helpers;

import corefunctionalities.FileHandler;
import corefunctionalities.TaskList;
import corefunctionalities.Ui;
import dataypes.Deadlines;
import dataypes.Events;
import dataypes.Task;
import dataypes.Todos;
import exceptions.*;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Command {
    protected Parser parser = new Parser();
    protected ExceptionGenerator exceptionGenerator = new ExceptionGenerator();
    protected Ui ui = new Ui();

    public void commandlistTasks(TaskList taskList) {
        ui.listTasks(taskList);
    }
    public void commandHelp() {
        ui.displayHelper();
    }
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
    public void commandDeadline(String userInput, TaskList taskList, FileHandler fileObject) throws EmptyDeadline, DeadlineMissingPhrase, DeadlineIsBlank, IOException, DateTimeParseException, WrongChrono {
        String[] deadlineAndDescription = parser.getDeadline(userInput);
        exceptionGenerator.deadlineExceptionGenerator(deadlineAndDescription, userInput);
        ui.printLine();
        Deadlines temp = new Deadlines(deadlineAndDescription[0], deadlineAndDescription[1]);
        taskList.addTask(temp);
        fileObject.addToFile(taskList.getTask(taskList.getSize()-1).enCode() + System.lineSeparator());
        ui.printTaskEnding(taskList);
    }

    public void commandEvent(String userInput, TaskList taskList, FileHandler fileObject) throws EmptyEvent, EventMissingBothPhrases, EventMissingToPhrase, EventMissingFromPhrase, EventFromIsBlank, EventToIsBlank, IOException, DateTimeParseException, FromAfterTo, WrongChrono {
        String [] eventDescription = parser.getEvent(userInput);
        exceptionGenerator.eventExceptionGenerator(eventDescription, userInput);
        ui.printLine();
        Events temp = new Events(eventDescription[0], eventDescription[1], eventDescription[2]);
        taskList.addTask(temp);
        fileObject.addToFile(taskList.getTask(taskList.getSize()-1).enCode() + System.lineSeparator());
        ui.printTaskEnding(taskList);
    }

    public void commandMarkTask(String userInput, TaskList taskList, FileHandler fileObject) throws MarkQualityException, IOException, NumberFormatException {
        exceptionGenerator.markExceptionGenerator(userInput, taskList);
        taskList.getTask(Integer.parseInt(userInput.split(" ")[1]) - 1).markTask();
        fileObject.populateFile(taskList);
        ui.printMarkedTask(userInput, taskList, fileObject);

    }

    public void commandUnMarkTask(String userInput, TaskList taskList, FileHandler fileObject) throws UnmarkQualityException, IOException, NumberFormatException {
        exceptionGenerator.unMarkExceptionGenerator(userInput, taskList);
        taskList.getTask(Integer.parseInt(userInput.split(" ")[1]) - 1).unMarkTask();
        fileObject.populateFile(taskList);
        ui.printUnmarkedTask(userInput, taskList, fileObject);
    }

    public void commandDeleteTask(String userInput, TaskList taskList, FileHandler fileObject) throws IndexOutOfBoundsException, NumberFormatException, IOException{
        Task item = new Task();
        item = taskList.getTask(Integer.parseInt(userInput.split(" ")[1]) - 1);
        taskList.removeTask(Integer.parseInt(userInput.split(" ")[1]) - 1);
        fileObject.populateFile(taskList);
        ui.printDeleteCommand(item, taskList);
    }
}
