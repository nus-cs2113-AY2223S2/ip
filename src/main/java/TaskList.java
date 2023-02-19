import duke.*;

/**
 * Represents the task list and has operations to list tasks, find tasks,
 * mark and unmark tasks, delete tasks and add To-dos, Deadlines and Events.
 */
public class TaskList {
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs new Storage object to handle saving of data into
     * text file and new Ui object to handle some error messages.
     */
    public TaskList() {
        storage = new Storage();
        ui = new Ui();
    }

    /**
     * Prints a horizontal line.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out each task in the list of tasks.
     */
    public void listTasks() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        int numOfTasks = 0;
        for (Task t : Task.tasks) {
            System.out.println(i + "." + t);
            i++;
            numOfTasks++;
        }
        if (numOfTasks == 0) {
            System.out.println("Looks like your list is still empty");
        }
        printLine();
    }

    /**
     * Finds a task by searching list of tasks for a keyword.
     * Prints out all tasks with descriptions that contain the keyword.
     *
     * @param input The user command.
     */
    public void findTask(String[] input) {
        try {
            printLine();
            String keyword = input[1];
            System.out.println("Here are the matching tasks in your list:");
            int i = 1;
            int numOfMatches = 0;
            for (Task t : Task.tasks) {
                String s = "" + t;
                if (s.contains(keyword)) {
                    System.out.println(i + "." + t);
                    i++;
                    numOfMatches++;
                }
            }
            if (numOfMatches == 0) {
                System.out.println("I couldn't find any matching tasks :(");
            }
            printLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Keyword is missing. Please try again by giving me a keyword to look for.");
            printLine();
        }
    }

    /**
     * Marks a task as done.
     *
     * @param input The user command.
     */
    public void markTask(String[] input) {
        try {
            int index = Integer.parseInt(input[1]);
            if (index < 1 || index > Task.taskCount) {
                throw new DukeException();
            }
            Task t = duke.Task.tasks.get(index - 1);
            t.markAsDone();
            storage.saveData();
            printLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(t);
            printLine();
        } catch (NumberFormatException e) {
            ui.handleNumberFormatException();
        } catch (IndexOutOfBoundsException e) {
           ui.handleIndexOutOfBoundsException();
        } catch (DukeException e) {
           ui.handleDukeException();
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param input The user command.
     */
    public void unmarkTask(String[] input) {
        try {
            int index = Integer.parseInt(input[1]);
            if (index < 1 || index > Task.taskCount) {
                throw new DukeException();
            }
            Task t = duke.Task.tasks.get(index - 1);
            t.markNotDone();
            storage.saveData();
            printLine();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(t);
            printLine();
        } catch (NumberFormatException e) {
            ui.handleNumberFormatException();
        } catch (IndexOutOfBoundsException e) {
           ui.handleIndexOutOfBoundsException();
        } catch (DukeException e) {
            ui.handleDukeException();
        }
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param input The user command.
     */
    public void deleteTask(String[] input) {
        try {
            int index = Integer.parseInt(input[1]);
            if (index < 1 || index > Task.taskCount) {
                throw new DukeException();
            }
            Task t = duke.Task.tasks.get(index - 1);
            Task.tasks.remove(index - 1);
            Task.taskCount--;
            storage.saveData();
            printLine();
            System.out.println("Noted. I've removed this task:");
            System.out.println(t);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
            printLine();
        } catch (NumberFormatException e) {
            ui.handleNumberFormatException();
        } catch (IndexOutOfBoundsException e) {
            ui.handleIndexOutOfBoundsException();
        } catch (DukeException e) {
            ui.handleDukeException();
        }
    }

    /**
     * Adds a To-do object to the list of tasks.
     *
     * @param input The user command.
     */
    public void addTodo(String[] input) {
        try {
            if (input[1].isBlank()) {
                throw new DukeException();
            }
            Task t = new Todo(input[1]);
            Task.tasks.add(Task.taskCount, t);
            Task.taskCount++;
            storage.saveData();
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
            printLine();
        } catch (IndexOutOfBoundsException | DukeException e) {
            ui.printErrorMessage();
            ui.printTodoFormat();
        }
    }

    /**
     * Adds a Deadline object to the list of tasks.
     *
     * @param input The user command.
     */
    public void addDeadline(String[] input){
        try {
            if (input[1].isBlank()) {
                throw new DukeException();
            }
            String[] doBy = input[1].split(" /by ", 2);
            Task t = new Deadline(doBy[0], doBy[1]);
            Task.tasks.add(Task.taskCount, t);
            Task.taskCount++;
            storage.saveData();
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
            printLine();
        } catch (IndexOutOfBoundsException | DukeException e) {
            ui.printErrorMessage();
            ui.printDeadlineFormat();
        }
    }

    /**
     * Adds an Event object to the list of tasks.
     *
     * @param input The user command.
     */
    public void addEvent(String[] input){
        try {
            if (input[1].isBlank()) {
                throw new DukeException();
            }
            String[] start = input[1].split(" /from ", 2);
            String[] end = start[1].split(" /to ", 2);
            Task t = new Event(start[0], end[0], end[1] );
            Task.tasks.add(Task.taskCount, t);
            Task.taskCount++;
            storage.saveData();
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
            printLine();
        } catch (IndexOutOfBoundsException | DukeException e) {
            ui.printErrorMessage();
            ui.printEventFormat();
        }
    }
}
