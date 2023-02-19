import duke.*;

/**
 * Represents the task list and has operations to list tasks, find tasks,
 * mark and unmark tasks, delete tasks and add To-dos, Deadlines and Events.
 */
public class TaskList {
    private final Storage storage;

    public TaskList() {
        storage = new Storage();
    }

    /**
     * Prints a horizontal line.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Returns true if task description is blank.
     * Returns false otherwise.
     *
     * @param description Description of a task.
     * @return boolean variable isBlank (True or False).
     */
    public boolean isDescriptionBlank(String description) {
        boolean isBlank = true;
        for (int i = 0; i < description.length(); i++) {
            char c = description.charAt(i);
            isBlank = Character.isWhitespace(c); //true if character is whitespace
        }
        return isBlank;
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
            System.out.println("☹ OOPS!!! Task number is invalid. Please try again.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Task number is missing. Please try again. ");
            printLine();
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! You entered an invalid task number. Please try again.");
            printLine();
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
            System.out.println("☹ OOPS!!! Task number is invalid. Please try again.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Task number is missing. Please try again. ");
            printLine();
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! You entered an invalid task number. Please try again.");
            printLine();
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
            System.out.println("☹ OOPS!!! Task number is invalid. Please try again.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Task number is missing. Please try again. ");
            printLine();
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! You entered an invalid task number. Please try again.");
            printLine();
        }
    }

    /**
     * Adds a To-do object to the list of tasks.
     *
     * @param input The user command.
     */
    public void addTodo(String[] input) {
        try {
            if (isDescriptionBlank(input[1])) {
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
        } catch (IndexOutOfBoundsException e) {
            printLine();
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            System.out.println("Please follow this format: todo [description].");
            printLine();
        } catch (DukeException e) {
            printLine();
            System.out.println("☹ OOPS!!! The description of a todo cannot be blank.");
            System.out.println("Please follow this format: todo [description].");
            printLine();
        }
    }

    /**
     * Adds a Deadline object to the list of tasks.
     *
     * @param input The user command.
     */
    public void addDeadline(String[] input){
        try {
            if (isDescriptionBlank(input[1])) {
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
        } catch (IndexOutOfBoundsException e) {
            printLine();
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty or incomplete.");
            System.out.println("Please follow this format: deadline [description] /by [due date/time]");
            printLine();
        } catch (DukeException e) {
            printLine();
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            System.out.println("Please follow this format: deadline [description] /by [due date/time]");
            printLine();
        }
    }

    /**
     * Adds an Event object to the list of tasks.
     *
     * @param input The user command.
     */
    public void addEvent(String[] input){
        try {
            if (isDescriptionBlank(input[1])) {
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
        } catch (IndexOutOfBoundsException e) {
            printLine();
            System.out.println("☹ OOPS!!! The description of an event cannot be empty or incomplete.");
            System.out.println("Please follow this format: event [description] /from [start] /to [end]");
            printLine();
        } catch (DukeException e) {
            printLine();
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            System.out.println("Please follow this format: event [description] /from [start] /to [end]");
            printLine();
        }
    }
}
