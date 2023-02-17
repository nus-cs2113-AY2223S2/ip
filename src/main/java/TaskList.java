import duke.*;

public class TaskList {
    private static Storage storage;

    public TaskList() {
        storage = new Storage();
    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    //check if task description is blank (for to-do)
    public static boolean isDescriptionBlank(String description) {
        boolean isBlank = true;
        for (int i = 0; i < description.length(); i++) {
            char c = description.charAt(i);
            isBlank = Character.isWhitespace(c); //true if character is whitespace
        }
        return isBlank;
    }

    //list tasks
    public static void listTasks(){
        printLine();
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task t : Task.tasks) {
            System.out.println(i + "." + t);
            i++;
        }
        printLine();
    }

    //mark task as done
    public static void markTask(String[] input){
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
            System.out.println("☹ OOPS!!! Task number is blank. Please try again.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Task number is missing. Please try again. ");
            printLine();
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! You entered an invalid task number. Please try again.");
            printLine();
        }
    }

    //mark task as not done
    public static void unmarkTask(String[] input){
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
            System.out.println("☹ OOPS!!! Task number is blank. Please try again.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Task number is missing. Please try again. ");
            printLine();
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! You entered an invalid task number. Please try again.");
            printLine();
        }
    }

    //delete task
    public static void deleteTask(String[] input){
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
            System.out.println("☹ OOPS!!! Task number is blank. Please try again.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Task number is missing. Please try again. ");
            printLine();
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! You entered an invalid task number. Please try again.");
            printLine();
        }
    }

    //to-do
    public static void addTodo(String[] input){
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

    //deadline
    public static void addDeadline(String[] input){
        try {
            if (isDescriptionBlank(input[1])) {
                throw new DukeException();
            }
            String[] doBy = input[1].split("/by ", 2);
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

    //event
    public static void addEvent(String[] input){
        try {
            if (isDescriptionBlank(input[1])) {
                throw new DukeException();
            }
            String[] start = input[1].split("/from ", 2);
            String[] end = start[1].split("/to ", 2);
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
