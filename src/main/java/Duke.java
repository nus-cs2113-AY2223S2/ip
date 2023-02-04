import java.util.Scanner;
import java.util.ArrayList;
import tasks.Task;
import tasks.Deadline;
import tasks.Todo;
import tasks.Event;

public class Duke {

    //static messages
    public static String TASKS = "Here are the tasks in your list:";
    public static String DONE = "Nice! I've marked this task as done:";
    public static String UNDONE = "OK, I've marked this task as not done yet:";
    public static String ROGER = "Got it. I've added this task:\n";
    public static String SPACE = "_______________________________";
    public static String BYE = SPACE +"\nBye. Hope to see you again soon!\n"+ SPACE;
    public static String GREET = SPACE + "\nHello! I'm Duke\n" + "What can I do for you?\n" + SPACE;
    public static String MARK = "For mark and unmark, please type the index\n" + "of the task that you wish to mark or unmark!\n";

    public static void main(String[] args) {
        //String logo = " ____        _        \n"
        //        + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String line;
        ArrayList<Task> storage = new ArrayList<>();
        int words; int x; int taskCounter = 0;
        Scanner input = new Scanner(System.in);

        //greet
        System.out.println(GREET);

        //echo
        boolean hi = true;
        while (hi) {
            line = input.nextLine();
            words = line.split("//s").length;
            String[] arr = line.split(" ", 2);
            try {
                switch (arr[0]) {
                    case "bye":
                        hi = false;
                        break;
                    case "list":
                        //list out storage
                        System.out.println(TASKS);
                        for (int i = 0; i < storage.size(); i++) {
                            int index = i + 1;
                            System.out.println(index + ". " + storage.get(i));
                        }
                        System.out.println(SPACE);
                        break;
                    case "mark":
                        x = Integer.parseInt(line.substring(5));
                        storage.get(x - 1).markAsDone();
                        System.out.println(DONE);
                        System.out.println(storage.get(x - 1).toString() + '\n' + SPACE);
                        break;
                    case "unmark":
                        x = Integer.parseInt(line.substring(7));
                        storage.get(x - 1).undo();
                        System.out.println(UNDONE);
                        System.out.println(storage.get(x - 1).toString() + '\n' + SPACE);
                        break;
                    case "deadline":
                        try {
                            Deadline dl = new Deadline(line);
                            storage.add(dl);
                            taskCounter++;
                            System.out.println(ROGER + dl);
                            System.out.println("Now you have " + taskCounter + " tasks in the list.\n" + SPACE);
                        } catch (IndexOutOfBoundsException e){
                            System.out.println("OOPS! Deadline must have a task and a timing separated by a '/'!\n" + SPACE);
                        }
                        break;
                    case "todo":
                        try {
                            Todo todo = new Todo(line);
                            storage.add(todo);
                            taskCounter++;
                            System.out.println(ROGER + todo);
                            System.out.println("Now you have " + taskCounter + " tasks in the list.\n" + SPACE);
                        } catch (IndexOutOfBoundsException e){
                            System.out.println("OOPS! Task to do cannot be left empty!\n" + SPACE);
                        }
                        break;
                    case "event" :
                        try {
                            Event event = new Event(line);
                            storage.add(event);
                            taskCounter++;
                            System.out.println(ROGER + event);
                            System.out.println("Now you have " + taskCounter + " tasks in the list.\n" + SPACE);
                        } catch (IndexOutOfBoundsException e){
                            System.out.println("OOPS! Event cannot be left empty!\n" + SPACE);
                        }
                        break;
                    default:
                        System.out.println("Task must begin with 'todo', 'deadline' or 'event'!\n" + SPACE);
                        break;
                }
            } catch (IndexOutOfBoundsException e){
                System.out.println("OOPS! could you try that again?\n" + SPACE);
            } catch (NumberFormatException e){
                System.out.println(MARK + SPACE);
            }
        }
        //exit
        System.out.println(BYE);
    }
}
