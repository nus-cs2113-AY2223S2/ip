import java.io.IOException;
import java.util.Scanner;

import Exception.DukeException;
import Parser.Parser;
import Storage.DukeStorage;
import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.TaskList;
import Task.Todo;
import UI.Ui;

public class Duke {

    public Duke(String filePath) throws DukeException, IOException {
        Ui.greet();
        DukeStorage storage = new DukeStorage(filePath);
        Task.numberOfTasks = 0;
        TaskList taskList = new TaskList(storage);
        try (Scanner scan = new Scanner(System.in)) {
            String input = scan.nextLine();
            while (!input.equals("bye")) {
                try {
                    if (input.equals("list")) {
                        Ui.printList(taskList.getTasks());

                    } else if (input.startsWith("mark")) {
                        String[] marks = Parser.processInput(input);
                        int index = Parser.checkActionInputValidity(marks);
                        if (index == -1) {
                            throw new Exception();
                        }
                        taskList.getTasks().get(index).markAsDone();
                        Ui.markMessage(taskList.getTasks(), index);
                    } else if (input.startsWith("unmark")) {
                        String[] marks = Parser.processInput(input);
                        int index = Parser.checkActionInputValidity(marks);
                        if (index == -1) {
                            throw new Exception();
                        }
                        taskList.getTasks().get(index).markAsNotDone();
                        Ui.unmarkMessage(taskList.getTasks(), index);
                    } else if (input.startsWith("todo", 0)) {
                        String toDoDesc = Parser.processTodoString(input);
                        if (toDoDesc == "") {
                            throw new Exception();
                        }
                        Task toDo = new Todo(toDoDesc);
                        taskList.addTask(toDo);
                        Ui.printConfirmation(toDo, "TODO");
                    } else if (input.startsWith("deadline", 0)) {
                        String[] deadlineArray = Parser.processDeadlineString(input);
                        if (deadlineArray.length == 0) {
                            throw new Exception();
                        }
                        String deadlineDesc = deadlineArray[0];
                        String deadlineDay = deadlineArray[1];
                        Task deadline = new Deadline(deadlineDesc, deadlineDay);
                        taskList.addTask(deadline);
                        Ui.printConfirmation(deadline, "DEADLINE");
                    } else if (input.startsWith("event", 0)) {
                        String[] eventArray = Parser.processEventString(input);
                        if (eventArray.length == 0) {
                            throw new Exception();
                        }
                        String eventDesc = eventArray[0];
                        String start = eventArray[1];
                        String end = eventArray[2];
                        Task event = new Event(eventDesc, start, end);
                        taskList.addTask(event);
                        Ui.printConfirmation(event, "EVENT");
                    } else if (input.startsWith("delete")) {
                        String[] deleteStrings = Parser.processInput(input);
                        int toDelete = Parser.checkActionInputValidity(deleteStrings);
                        if (toDelete == -1) {
                            throw new Exception();
                        }
                        Task deleteTask = taskList.getTasks().get(toDelete);
                        taskList.removeTask(deleteTask);
                        Task.decrementNumberOfTasks();
                        Ui.printConfirmation(deleteTask, "DELETE");
                    } else {
                        Ui.printConfirmation(null, "UNRECOGNIZED");
                        ;
                    }
                } catch (Exception e) {
                    Ui.printIncompleteMessage();
                }

                input = scan.nextLine();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Ui.bye();
        storage.saveTaskList(taskList.getTasks());
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("data/duke.txt");
    }

}
