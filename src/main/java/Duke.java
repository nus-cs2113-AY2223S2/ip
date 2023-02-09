import java.util.Scanner;

public class Duke {
    private static int taskCount = 0;
    private static Task[] tasks = new Task[100];
    private static final String dividingLine = "\n————————————————————————————————————————————————————————\n";

    public static void printList() {
        System.out.println(dividingLine + "Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
        System.out.println("Now you have " + taskCount + " tasks in the list." + dividingLine);
    }

    public static void addTask(Task task) {
        tasks[taskCount] = task;
        System.out.println(dividingLine);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount]);
        System.out.println("Now you have " + (taskCount + 1) + " tasks in the list.");
        System.out.println(dividingLine);
        taskCount++;
    }

    public static void processTask(String line) throws DukeException, TaskInfoException, MarkIndexException, NoTaskException {
        if (line.equals("list")) {
            printList();
        } else if (line.contains("mark") && !line.contains("unmark")) {
            if(taskCount == 0){
                throw new NoTaskException();
            }
            if(line.length() == 4){
                throw new MarkIndexException();
            }
            int taskNum = Integer.parseInt(line.substring(5)) - 1;
            tasks[taskNum].markAsDone();
            System.out.println(dividingLine + "Nice! I've marked this task as done:");
            System.out.println("[" + tasks[taskNum].getStatusIcon() + "] " + tasks[taskNum].description
                    + dividingLine);
        } else if (line.contains("unmark")) {
            if(taskCount == 0){
                throw new NoTaskException();
            }
            if(line.length() == 6){
                throw new MarkIndexException();
            }
            int unmarkNum = Integer.parseInt(line.substring(7)) - 1;
            tasks[unmarkNum].unmarkAsDone();
            System.out.println(dividingLine + "OK, I've marked this task as not done yet:");
            System.out.println("[" + tasks[unmarkNum].getStatusIcon() + "] " + tasks[unmarkNum].description
                    + dividingLine);
        } else if (line.contains("todo")) {
            addTask(new Todo(line.substring(5)));
        } else if (line.contains("deadline")) {
            line = line.replace("deadline ", "");
            if(line.substring(line.indexOf("/by ") + 4).length() == 0){
                throw new TaskInfoException();
            }
            addTask(new Deadline(line.substring(0, line.indexOf(" /")),
                    line.substring(line.indexOf("/by ") + 4)));
        } else if (line.contains("event")) {
            line = line.replace("event ", "");
            if(line.substring(line.indexOf("/to ") + 4).length() == 0){
                throw new TaskInfoException();
            }
            addTask(new Event(line.substring(0, line.indexOf(" /")),
                    line.substring(line.indexOf("/from ") + 6, line.indexOf(" /to",
                            line.indexOf("/from") - 1)),
                    line.substring(line.indexOf("/to ") + 4)));
        } else {
//            tasks[taskCount] = new Task(line);
            throw new DukeException();
        }
    }

    public static void printTask(String line) {
        try {
            processTask(line);
        } catch (IndexOutOfBoundsException e2) {
            System.out.println(dividingLine + "☹ OOPS!!! The description of a " + line + " cannot be empty."
                    + dividingLine);
        } catch (TaskInfoException e){
            System.out.println(dividingLine + "☹ OOPS!!! Missing information." + dividingLine);
        } catch (DukeException e) {
            System.out.println(dividingLine + "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                    + dividingLine);
        } catch (MarkIndexException e4){
            System.out.println(dividingLine + "☹ OOPS!!! Task index is unspecified." + dividingLine);
        } catch (NoTaskException e){
            System.out.println(dividingLine + "☹ OOPS!!! No task in the list." + dividingLine);
        }
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?" + dividingLine);

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            printTask(line);
            line = in.nextLine();
        }
        System.out.println(dividingLine + "Bye. Hope to see you again soon!" + dividingLine);
    }
}
