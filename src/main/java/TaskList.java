/**
 * Represents all tasks that will be saved
 * to a TaskList.
 * Handles all operations related to the task
 * such as list, find, mark, unmark, delete
 * and add ToDos, Events and Deadlines
 */
public class TaskList {

    /**
     * @param description Description given by user
     * @return Returns true if description is empty
     *         Else, return false
     */
    public static boolean isDesEmpty(String description) {
        boolean isEmpty;
        isEmpty = true;

        char val;
        int k;
        for (k = 0; description.length() > k; k += 1) {
            val = description.charAt(k);
            isEmpty = Character.isWhitespace(val);
        }
        return isEmpty;
    }

    /**
     * Prints out every task that is
     * stored in the list
     */
    public static void listTask() {
        Ui.printDash();
        System.out.println("Tasks in list:");
        int k = 0;

        for (Task command : Task.tasks) {
            k += 1;
            System.out.println((k + "." + command));
        }

        Ui.printDash();
    }

    /**
     * Unmarks a task that is currently marked
     * by user
     * @param list The task that user wants to
     *             unmark
     */
    public static void unmarkTask(String[] list) {
        try {
            int pt;
            pt = Integer.parseInt(list[1]);

            if ((pt > Task.taskNum) || (1 > pt)) {
                throw new DukeException();
            }

            Task command;
            command = Task.tasks.get(pt - 1);
            command.markNotDone();
            Storage.saveData();
            Ui.printDash();
            System.out.println("Marking task as undone:\n" + command);
            Ui.printDash();
        } catch (NullPointerException err) {
            Ui.printDash();
            System.out.println("☹ OOPS!!! Not enough tasks here. Try again.");
            Ui.printDash();
        } catch (IndexOutOfBoundsException err) {
            Ui.printDash();
            System.out.println("☹ OOPS!!! Task number is missing. Try again.");
            Ui.printDash();
        } catch (DukeException err) {
            Ui.printDash();
            System.out.println("☹ OOPS!!! Invalid task number. Try again.");
            Ui.printDash();
        }
    }

    /**
     * Marks a task that is currently unmarked
     * @param list The task that user wants
     *             to mark
     */
    public static void markTask(String[] list) {
        try {
            int pt;
            pt = Integer.parseInt(list[1]);

            if ((pt > Task.taskNum) || (1 > pt)) {
                throw new DukeException();
            }

            Task command;
            command = Task.tasks.get(pt - 1);
            command.markAsDone();
            Storage.saveData();
            Ui.printDash();
            System.out.println("Marking task as done:\n" + command);
            Ui.printDash();
        } catch (NullPointerException err) {
            Ui.printDash();
            System.out.println("☹ OOPS!!! Not enough tasks here. Try again.");
            Ui.printDash();
        } catch (IndexOutOfBoundsException err) {
            Ui.printDash();
            System.out.println("☹ OOPS!!! Task number is missing. Try again.");
            Ui.printDash();
        } catch (DukeException err) {
            Ui.printDash();
            System.out.println("☹ OOPS!!! Invalid task number. Try again.");
            Ui.printDash();
        }
    }

    /**
     * Deletes a task that is currently in
     * the list
     * @param list The task that user wants
     *             to delete
     */
    public static void deleteTask(String[] list) {
        try {
            int pt;
            pt = Integer.parseInt(list[1]);

            if ((pt > Task.taskNum) || (1 > pt)) {
                throw new DukeException();
            }

            Task command;
            command = Task.tasks.get(pt - 1);
            Task.tasks.remove(pt - 1);
            Task.taskNum -= 1;
            Storage.saveData();
            Ui.printDash();
            System.out.println("Successfully deleted task:\n" + command);
            System.out.println("You currently have " + Task.taskNum + " task(s) left");
            Ui.printDash();
        } catch (NullPointerException err) {
            Ui.printDash();
            System.out.println("☹ OOPS!!! Not enough tasks here. Try again.");
            Ui.printDash();
        } catch (IndexOutOfBoundsException err) {
            Ui.printDash();
            System.out.println("☹ OOPS!!! Task number is missing. Try again.");
            Ui.printDash();
        } catch (DukeException err) {
            Ui.printDash();
            System.out.println("☹ OOPS!!! Invalid task number. Try again.");
            Ui.printDash();
        }
    }

    /**
     * Creates a task that is a deadline
     * to be added to the list
     * @param list Deadline task that user wants to
     *             add to the list
     */
    public static void makeDeadline(String[] list) {
        try {
            if (isDesEmpty(list[1])) {
                throw new DukeException();
            }

            String[] due;
            due = list[1].split("/by ", 2);
            Task command;
            command = new Deadline(due[0], due[1]);
            Task.tasks.add(Task.taskNum, command);
            Task.taskNum += 1;
            Storage.saveData();
            Ui.printDash();
            System.out.println("Adding this task:\n" + command);
            System.out.println("You currently have " + Task.taskNum + " task(s)");
            Ui.printDash();
        } catch (DukeException | IndexOutOfBoundsException e) {
            Ui.printDash();
            System.out.println("☹ OOPS!!! Due date of deadline or description cannot be empty. Try again.\nFollow the following format: deadline [description] /by [due date]");
            Ui.printDash();
        }
    }

    /**
     * Creates a task that is a To Do
     * to be added to the list
     * @param list To Do task that user wants to
     *             add to the list
     */
    public static void makeToDo(String[] list) {
        try {
            if (isDesEmpty(list[1])) {
                throw new DukeException();
            }

            Task command;
            command = new Todo(list[1]);
            Task.tasks.add(Task.taskNum, command);
            Task.taskNum += 1;
            Storage.saveData();
            Ui.printDash();
            System.out.println("Adding this task:\n" + command);
            System.out.println("You currently have " + Task.taskNum + " task(s)");
            Ui.printDash();
        } catch (DukeException | IndexOutOfBoundsException e) {
            Ui.printDash();
            System.out.println("☹ OOPS!!! Description of a to do cannot be empty. Try again.");
            Ui.printDash();
        }
    }

    /**
     * Creates a task that is an event
     * to be added to the list
     * @param list Event task that user wants to
     *             add to the list
     */
    public static void makeEvent(String[] list) {
        try {
            if (isDesEmpty(list[1])) {
                throw new DukeException();
            }

            String[] beg;
            beg = list[1].split("/from ", 2);
            String[] end;
            end = beg[1].split("/to ", 2);
            Task command = new Event(end[0], end[1], beg[0]);
            Task.tasks.add(Task.taskNum, command);
            Task.taskNum += 1;
            Storage.saveData();
            Ui.printDash();
            System.out.println("Adding this task:\n" + command);
            System.out.println("You currently have " + Task.taskNum + " task(s)");
            Ui.printDash();
        } catch (DukeException | IndexOutOfBoundsException e) {
            Ui.printDash();
            System.out.println("☹ OOPS!!! Date of event or description cannot be empty. Try again.\nFollow the following format: event [description] /from [start] /to [end]");
            Ui.printDash();
        }
    }

    /**
     * Finds for a specific keyword that user intends
     * to view
     * @param list Keyword that user is finding for
     */
    public static void findTask(String[] list) {
        try {
            String word, s;
            int k, matches;
            Ui.printDash();
            word = list[1];
            System.out.println("The keyword is found in these tasks:");
            k = 1;
            matches = 0;

            for (Task command : Task.tasks) {
                s = "" + command;

                if (s.contains(word)) {
                    System.out.println(k + "." + command);
                    matches += 1;
                    k += 1;
                }
            }
            if (0 == matches) {
                System.out.println("No task contains that keyword!");
            }
            Ui.printDash();
        } catch (IndexOutOfBoundsException err) {
            System.out.println("☹ OOPS!!! Please enter a keyword");
            Ui.printDash();
        }
    }
}
