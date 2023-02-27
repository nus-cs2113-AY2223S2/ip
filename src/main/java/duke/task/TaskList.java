package duke.task;

import duke.command.Ui;
import duke.exception.EmptyTaskInputException;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(TaskList t) {
        this.tasks = t.tasks;
    }

    public static void loadTask(String markIndicator, String item, TaskType taskType) {
        switch (taskType) {
        case TODO:
            Task task = new Task(item);
            if (markIndicator.equals("M")) {
                task.setIsDone(true);
            }
            tasks.add(task);
            break;
        case DEADLINE:
            task = new Deadline(item);
            if (markIndicator.equals("M")) {
                task.setIsDone(true);
            }
            tasks.add(task);
            break;
        case EVENT:
            task = new Event(item);
            if (markIndicator.equals("M")) {
                task.setIsDone(true);
            }
            tasks.add(task);
            break;
        }
    }

    public static String writeTask() {
        StringBuilder data = new StringBuilder();
        if (tasks.size() > 0) {
            for (Task task : tasks) {
                String taskTypeIndicator = task.getType();
                String markIndicator = task.isDone ? "M" : "U";
                String taskDescription = task.description;
                data.append(taskTypeIndicator).append(" & ").append(markIndicator).append(" & ").append(taskDescription).append("\n");
            }
        }
        return data.toString();
    }

    public static void addTask(String item, TaskType taskType) {
        String taskString;
        Task task = new Task(item);
        try {
            if (item.length() == 0) {
                throw new EmptyTaskInputException();
            }

            switch (taskType) {
            case DEADLINE:
                task = new Deadline(item);
                taskString = task.toString();
                break;
            case EVENT:
                task = new Event(item);
                taskString = task.toString();
                break;
            case TODO:
                taskString = task.toString();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.line();
            System.out.println("You are missing some parameters in the input! Please try again!");
            Ui.line();
            return;
        } catch (EmptyTaskInputException e) {
            Ui.line();
            System.out.println("Your input description cannot be empty! Please try again!");
            Ui.line();
            return;
        }
        tasks.add(task);
        Ui.line();
        System.out.println("Great job adding a new task!");
        System.out.println("Added: " + task);
        System.out.println("You currently have " + tasks.size() + " tasks.");
        Ui.line();
    }

    public static void removeTask(int index) {
        if (index > tasks.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Ui.line();
        System.out.println("OK, the following task has been removed:");
        System.out.println(tasks.get(index - 1));
        tasks.remove(index - 1);
        System.out.println("You currently have " + tasks.size() + " tasks.");
        Ui.line();
    }

    public static void printList() {
        if (tasks.size() > 0) {
            Ui.line();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(Integer.toString(i + 1) + "." + tasks.get(i));
            }
            Ui.line();
        } else {
            Ui.line();
            System.out.println("You have no tasks at the moment. Please add some tasks!");
            Ui.line();
        }
    }

    public static void findInList(String string) {
        if (string.isEmpty()) {
            Ui.line();
            System.out.println("Please input a keyword to search!");
            Ui.line();
            return;
        }

        if (tasks.size() > 0) {
            // Checks if any matches are found
            boolean isMatchFound = false;
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).description.contains(string)) {
                    isMatchFound = true;
                    break;
                }
            }
            Ui.line();
            if (isMatchFound) {
                System.out.println("Here are the tasks in your list:");
                int listIndex = 1;
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i).description.contains(string)) {
                        System.out.println(Integer.toString(listIndex) + "." + tasks.get(i));
                        listIndex += 1;
                    }
                }
            } else {
                System.out.println("No matches are found for your keyword, please search another keyword!");
            }
            Ui.line();
        } else {
            Ui.line();
            System.out.println("You have no tasks at the moment. Please add some tasks before searching!");
            Ui.line();
        }
    }

    public static void markDone(int index) {
        tasks.get(index - 1).setIsDone(true);
        Ui.line();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1));
        Ui.line();
    }

    public static void markUndone(int index) {
        tasks.get(index - 1).setIsDone(false);
        Ui.line();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1));
        Ui.line();
    }
}
