package duke;

import exception.EmptyTaskException;
import exception.InvalidTaskNumberException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;

public class TaskList {

    private final Ui ui = new Ui();
    public ArrayList<Task> tasks = new ArrayList<>();


    public void initialiseTaskList(String line) {
        String[] task = line.split("[|\\-]");

        switch(task[0].trim()) {
        case "T":
            try {
                addTodoTask(task[2].trim());
            } catch (EmptyTaskException e) {
                ui.printImpossible();
            }
            break;
        case "D":
            addDeadlineTask(task[2].trim(), task[3].trim());
            break;
        case "E":
            addEventTask(task[2].trim(), task[3].trim(), task[4].trim());
            break;
        }

        if (task[1].contains("X")) {
            try {
                markTaskDone(tasks.size()-1);
            } catch (NumberFormatException e) {
                ui.printNumberFormatException();
            } catch (InvalidTaskNumberException e) {
                ui.printInvalidTaskNumberException();
            } catch (IndexOutOfBoundsException e) {
                ui.printIndexOutOfBoundsException();
            }
        }
    }

    public void printTaskList() {
        if (tasks.size() == 0) {
            ui.printEmptyTaskList();
        } else {
            for (int i = 0; i < tasks.size(); ++i) {
                System.out.print(i+1 + ".");
                System.out.println(tasks.get(i));
            }
        }
        ui.printDivider();
    }


    public void markTaskDone(Integer taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex > tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            tasks.get(taskIndex).markDone();
        }
    }


    public void markTaskUndone(Integer taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex > tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            tasks.get(taskIndex).markUndone();
        }
    }


    public void addTodoTask(String task) throws EmptyTaskException {
        if (task.equals("")) {
            throw new EmptyTaskException();
        } else {
            tasks.add(new ToDo(task));
        }
    }


    public void addDeadlineTask(String task, String deadline) {
        tasks.add(new Deadline(task, deadline));
    }

    public void addEventTask(String task, String fromDate, String byDate) {
        tasks.add(new Event(task, fromDate, byDate));
    }

    public void printTaskAdded() {
        System.out.println("Got it. I've added this task:\n " + tasks.get(tasks.size()-1)
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void deleteTask(int taskIndex) throws InvalidTaskNumberException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            System.out.println("Noted. I've removed this task:\n " + tasks.get(taskIndex)
                    + "\nNow you have " + (tasks.size()-1) + " tasks in the list.");
            tasks.remove(tasks.get(taskIndex));
        }
    }

    public void findTasks(String taskName) throws EmptyTaskException {
        if (taskName.equals("")) {
            throw new EmptyTaskException();
        } else {
            int index = 1;
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); ++i) {
                if (tasks.get(i).containsTask(taskName)) {
                    System.out.print(index + ".");
                    System.out.println(tasks.get(i));
                    ++index;
                }
            }
        }
    }

}
