package duke.entity;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Parser parser = new Parser();

    public boolean manageInput(TaskList taskList, Storage storage) {
        Scanner scanner = new Scanner(System.in);
        try {
            String input = scanner.nextLine();
            this.parser.setInput(input);

            if (input.equals("bye")) {
                return false;
            } else if (input.equals("list")) {
                listTasks(taskList.getTaskArrayList());
            } else if (input.startsWith("mark")) {
                selectTask(input, taskList);
                listTasks(taskList.getTaskArrayList());
                storage.updateDukeFile(taskList);
            } else if (input.startsWith("todo")) {
                blankDescription(input);
                Task tempTask = new Todo(input.substring(5));
                taskList.addTask(tempTask);
                printAddedTask(tempTask, taskList.taskSize());
                storage.writeDukeFile(tempTask, true);
            } else if (input.startsWith("deadline") && input.contains("/")) {
                Task tempTask = new Deadline(input.substring(9), parser.parseDeadlineBy());
                taskList.addTask(tempTask);
                printAddedTask(tempTask, taskList.taskSize());
               storage.writeDukeFile(tempTask, true);
            } else if (input.startsWith("event") && parser.validateEventInput()) {
                Task tempTask = new Event(input.substring(6), parser.parseEventFrom(), parser.parseEventTo());
                taskList.addTask(tempTask);
                printAddedTask(tempTask, taskList.taskSize());
                storage.writeDukeFile(tempTask, true);
            } else if (input.startsWith("delete")) {
                selectTask(input, taskList);
            } else if (input.startsWith("find")) {
                findTasks(input, taskList);
            } else {
                invalidInput();
            }
        } catch (DukeException ex) {
            System.out.println(ex.toString());
        }
        return true;
    }

    private void selectTask(String input, TaskList taskList) {
        try {
            if (input.startsWith("mark")) {
                taskList.getTask(parser.parseMarkIndex()).setIsDone(true);
            }
            else if (input.startsWith("delete")) {
                deleteTask(parser.parseDeleteIndex(), taskList);
            }
        } catch (NumberFormatException ex) {
            printMarkError();
        } catch (ArrayIndexOutOfBoundsException ex) {
            printMarkError();
        } catch (IndexOutOfBoundsException ex) {
            printMarkError();
        } catch (NullPointerException ex) {
            printMarkError();
        }
    }

    private void listTasks(ArrayList<Task> storedTask) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < storedTask.size(); i++) {
            System.out.println((i + 1) + ". " + storedTask.get(i).toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    private void deleteTask(int removeIndex, TaskList taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:\n" +
                "       " + taskList.getTask(removeIndex).toString() + "\n" +
                "Now you have " + (taskList.taskSize() - 1) + " tasks in the list.");
        System.out.println("____________________________________________________________\n");
        taskList.removeTask(removeIndex);
    }

    private void findTasks(String input, TaskList taskList) throws DukeException {
        blankDescription(input);
        ArrayList<Task> foundTaskList = taskList.findTaskArrayList(input.substring(5));

        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTaskList.size(); i++) {
            System.out.println((i + 1) + ". " + foundTaskList.get(i).toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    private void blankDescription(String input) throws DukeException {
        if (input.length() == 4 || input.substring(4).isBlank()) {
            throw new DukeException("☹ OOPS!!! Must have a description.\n");
        }
    }

    private void invalidInput() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    private void printMarkError() {
        System.out.println("____________________________________________________________");
        System.out.println("*DID NOT ENTER A VALID NUMBER*");
        System.out.println("____________________________________________________________\n");
    }

    private void printAddedTask(Task task, int counter) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println("____________________________________________________________\n");
    }

    public void goodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");
    }

    public void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________\n");
    }
}
