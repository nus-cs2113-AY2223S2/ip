package duke;

import duke.exception.EmptyCommandException;
import duke.exception.IllegalCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


import java.io.IOException;

public class Duke {

    public static boolean isProgramRunning = true;
    private Storage database = null;
    public TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        database = new Storage();
        tasks = new TaskList(database.tasks);
        Ui.greeting();
    }

    public void run() {
        while (isProgramRunning) {
            String fullCommand = ui.readCommand();
            String firstWord = fullCommand.split(" ")[0];
            if (fullCommand.equals("bye")) {
                exit();
            } else if (fullCommand.equals("list")) {
                Ui.printList(tasks);
            } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                MarkOrUnmarkHandler(fullCommand);
            } else if (firstWord.equals("todo")) {
                todoTaskHandler(fullCommand);
            } else if (firstWord.equals("deadline")) {
                deadlineTaskHandler(fullCommand);
            } else if (firstWord.equals("event")) {
                eventTaskHandler(fullCommand);
            } else if (firstWord.equals("delete")) {
                deleteTaskHandler(fullCommand);
            } else {
                Ui.illegalCommandMessage();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void deleteTaskHandler(String command) {
        try {
            deleteTask(command);
        } catch (IllegalCommandException e) {
            Ui.illegalCommandMessage();
        }
    }

    private void deleteTask(String command) throws IllegalCommandException {
        String[] words = command.trim().split(" ");
        if (isInvalidString(words)) {
            throw new IllegalCommandException();
        }
        int deleteIndex = getIndex(words[1]);
        if (!isValidIndex(deleteIndex)) {
            throw new IllegalCommandException();
        }
        Ui.deleteTaskMessage(deleteIndex, tasks);
        tasks.deleteTaskFromTaskList(deleteIndex);
        try {
            database.updateDatabaseTask();
        } catch (IOException e) {
            Ui.updateDatabaseFailureMessage();
        }
    }

    private void MarkOrUnmarkHandler(String command) {
        try {
            markAndUnmarkTask(command);
        } catch (IllegalCommandException e) {
            Ui.illegalCommandMessage();
        }
    }

    private void todoTaskHandler(String command) {
        try {
            initTodoTask(command);
        } catch (EmptyCommandException e) {
            Ui.emptyCommandMessage("todo");
        }
    }

    private void eventTaskHandler(String command) {
        try {
            initEventTask(command);
        } catch (IllegalCommandException e) {
            Ui.illegalCommandMessage();
        } catch (EmptyCommandException e) {
            Ui.emptyCommandMessage("event");
        }
    }

    private void deadlineTaskHandler(String command) {
        try {
            initDeadlineTask(command);
        } catch (EmptyCommandException e) {
            Ui.emptyCommandMessage("deadline");
        } catch (IllegalCommandException e) {
            Ui.illegalCommandMessage();
        }
    }

    private void initEventTask(String command) throws IllegalCommandException, EmptyCommandException {
        command = command.replace("event", "").trim();
        if (command.isEmpty()) {
            throw new EmptyCommandException();
        }
        String[] stringSplit = command.split(" /from ");
        if (isInvalidString(stringSplit)) {
            throw new IllegalCommandException();
        }
        String[] startToEndTime = stringSplit[1].split(" /to ");
        if (isInvalidString(startToEndTime)) {
            throw new IllegalCommandException();
        }
        Task currTask = new Event(stringSplit[0], startToEndTime[0], startToEndTime[1]);
        addTaskBackgroundProcess(currTask);
    }

    private void addTaskBackgroundProcess(Task currTask) {
        tasks.addTaskToTaskList(currTask);
        addTaskToDatabase(currTask);
        Ui.addSpecialTaskMessage(tasks);
    }


    private static boolean isInvalidString(String[] stringSplit) {
        return stringSplit.length != 2;
    }

    private void initDeadlineTask(String command) throws EmptyCommandException, IllegalCommandException {
        command = command.replace("deadline", "").trim();
        if (command.isEmpty()) {
            throw new EmptyCommandException();
        }
        String[] stringSplit = command.split(" /by ");
        if (isInvalidString(stringSplit)) {
            throw new IllegalCommandException();
        }
        Task currTask = new Deadline(stringSplit[0], stringSplit[1]);
        addTaskBackgroundProcess(currTask);
    }

    private void initTodoTask(String command) throws EmptyCommandException {
        String todo = command.replace("todo", "").trim();
        if (todo.isEmpty()) {
            throw new EmptyCommandException();
        }
        Task currTask = new Todo(todo);
        addTaskBackgroundProcess(currTask);
    }

    private void addTaskToDatabase(Task currTask) {
        try {
            database.saveAddTask(currTask.getTaskString());
        } catch (IOException e) {
            Ui.updateDatabaseFailureMessage();
        }
    }

    private void markAndUnmarkTask(String command) throws IllegalCommandException {
        String[] words = command.split(" ");
        if (words.length != 2) {
            throw new IllegalCommandException();
        }
        int indexOfMarking = getIndex(words[1]);
        if (!isValidIndex(indexOfMarking)) {
            throw new IllegalCommandException();
        }
        createMarkOrUnmark(command, words, indexOfMarking);
    }

    private void createMarkOrUnmark(String command, String[] words, int indexOfMarking) {
        tasks.getTaskFromIndex(indexOfMarking).setDone(words[0]);
        try {
            database.updateDatabaseTask();
        } catch (IOException e) {
            Ui.updateDatabaseFailureMessage();
        }
        Ui.markChangeMessage(command, indexOfMarking, tasks);
    }

    private boolean isValidIndex(int indexOfMarking) {
        if (indexOfMarking < 0 || indexOfMarking > (tasks.getTaskCount() - 1)) {
            return false;
        }
        return true;
    }

    private static void exit() {
        Ui.goodbyeMessage();
        isProgramRunning = false;
    }

    private static int getIndex(String strNum) {
        // Referenced from https://www.baeldung.com/java-check-string-number
        int index = 0;
        if (strNum == null) {
            return -1;
        }
        try {
            index = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return -1;
        }
        index--;
        return index; // Returns 0-index of parsing Integer or -1 if string is not a number or empty
    }
}
