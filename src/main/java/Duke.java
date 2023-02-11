import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Duke {

    public static void main(String[] args) {
        String logo = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";



        String line = "    ____________________________________________________________\n";

        Task[] tasks = new Task[100];

        String dataFilePath = "src/main/data/duke.txt";
        int index = 0;
        try {
            index = loadTasks(tasks, dataFilePath);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage() + "notfoundcheebai");
        }
        System.out.println(line);
        System.out.println("     All Tasks loaded from memory");
        System.out.println(line);
        System.out.println(logo);
        Scanner in = new Scanner(System.in);
        String action = in.nextLine();
        String bye = "bye";

        while (!action.equals(bye)) {
            System.out.println(line);

            String[] inputs = action.split(" ", 2);
            String cmd = inputs[0];
            boolean hasDescription = (inputs.length > 1);

            switch (cmd) {
                case "list":

                    listTask(tasks, index);

                    break;
                case "mark":

                    if (hasDescription) {

                        try {

                            markTask(tasks, inputs[1], index);

                        } catch (DukeException e) {

                            System.out.println("       " + e.getMessage());

                        }

                    } else {

                        System.out.println("       ☹ OOPS!!! The task number cannot be empty.");

                    }

                    break;
                case "unmark":

                    if (hasDescription) {

                        try {

                            unmarkTask(tasks, inputs[1], index);

                        } catch (DukeException e) {

                            System.out.println("       " + e.getMessage());

                        }

                    } else {

                        System.out.println("       ☹ OOPS!!! The task number cannot be empty.");

                    }

                    break;
                case "todo":

                    if (hasDescription) {

                        try {

                            index = addTodo(tasks, inputs[1], index);

                        } catch (DukeException e) {

                            System.out.println("       " + e.getMessage());

                        }

                    } else {

                        System.out.println("       ☹ OOPS!!! The description of a todo cannot be empty.");

                    }

                    break;
                case "deadline":

                    if (hasDescription) {

                        try {

                            index = addDeadline(tasks, inputs[1], index);

                        } catch (DukeException e) {

                            System.out.println("       " + e.getMessage());

                        }

                    } else {

                        System.out.println("       ☹ OOPS!!! The description of a deadline cannot be empty.");

                    }

                    break;
                case "event":

                    if (hasDescription) {

                        try {

                            index = addEvent(tasks, inputs[1], index);

                        } catch (DukeException e) {

                            System.out.println("       " + e.getMessage());

                        }

                    } else {

                        System.out.println("       ☹ OOPS!!! The description of an event cannot be empty.");

                    }

                    break;
                default:

                    System.out.println("       ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }

            System.out.println(line);
            action = in.nextLine();
        }

        try {
            saveTasks(tasks, dataFilePath, index);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(line);
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println(line);

    }

    private static void saveTasks(Task[] tasks, String filePath, int index) throws IOException{
        FileWriter fw = new FileWriter(filePath);

        for(int i = 0; i<index; i++) {
            fw.write(tasks[i].getTypeOfTask() + " | " + (tasks[i].isDone? "1" : "0") + " | " + tasks[i].getDetailsToSave() + System.lineSeparator());
        }
        fw.close();
    }

    private static int loadTasks(Task[] tasks, String filePath) throws FileNotFoundException {
        int count = 0;
        File f = new File(filePath);
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] args = line.split(" \\| ");
            System.out.println(args[0] + "==" + args [1] + "==" + args[2] + "==");

            switch (args[0]) {
                case "T":
                    try {
                        count = addTodo(tasks, args[2], count);
                        if(args[1].equals("1")) {
                            markTask(tasks, Integer.toString(count), count);
                        }
                    } catch (DukeException e) {

                    }
                    break;
                case "D":
                    try {
                        count = addDeadline(tasks, args[2], count);
                        if(args[1].equals("1")) {
                            markTask(tasks, Integer.toString(count), count);
                        }
                    } catch (DukeException e) {

                    }
                    break;
                case "E":
                    try {
                        count = addEvent(tasks, args[2], count);
                        if(args[1].equals("1")) {
                            markTask(tasks, Integer.toString(count), count);
                        }
                    } catch (DukeException e) {

                    }
                    break;
                default:
                    break;
            }
        }

        return count;
    }

    private static int addEvent(Task[] tasks, String todoTask, int index) throws DukeException {

        String[] taskAndDeadline = todoTask.split(" /from ");

        if(taskAndDeadline.length < 2) {

            throw new DukeException("The start date/time cannot be empty.");

        }

        String theTask = taskAndDeadline[0];
        String dueBy = taskAndDeadline[1];
        String[] startAndEnd = dueBy.split(" /to ");

        if(startAndEnd.length < 2) {

            throw new DukeException("The end date/time cannot be empty.");

        }

        String start = startAndEnd[0];
        String end = startAndEnd[1];
        tasks[index] = new Event(theTask, start, end);
        index = index + 1;
        System.out.println("     Got it. I've added this task:");
        System.out.println("       [E][ ] " + theTask + " (from: " + start + " to: " + end + ")");
        System.out.println("     Now you have "
                            + index + (index > 1 ? " tasks " : " task ")
                            + "in the list.");
        return index;
    }

    private static int addDeadline(Task[] tasks, String todoTask, int index) throws DukeException {

        String[] taskAndDeadline = todoTask.split(" /by ");

        if(taskAndDeadline.length < 2) {

            throw new DukeException("The due date/time cannot be empty.");

        }

        String theTask = taskAndDeadline[0];
        String dueBy = taskAndDeadline[1];
        tasks[index] = new Deadline(theTask, dueBy);
        index = index + 1;
        System.out.println("     Got it. I've added this task:");
        System.out.println("       [D][ ] " + theTask + " (by: " + dueBy + ")");
        System.out.println("     Now you have "
                            + index + (index > 1 ? " tasks " : " task ")
                            + "in the list.");
        return index;
    }

    private static int addTodo(Task[] tasks, String todoTask, int index) throws DukeException {

        tasks[index] = new Todo(todoTask);
        index = index + 1;
        System.out.println("     Got it. I've added this task:");
        System.out.println("       [T][ ] " + todoTask);
        System.out.println("     Now you have "
                            + index + (index > 1 ? " tasks " : " task ")
                            + "in the list.");
        return index;
    }

    private static void unmarkTask(Task[] tasks, String taskNumber, int index) throws DukeException {

        int ind = Integer.parseInt(taskNumber) - 1;

        if(ind >= index || ind < 0) {
            throw new DukeException("Task " + (ind + 1) + " does not exist.");
        }

        tasks[ind].unmark();
        System.out.println("     OK, I've marked this task as not done yet:\n");
        System.out.println("       "
                            + "[" + tasks[ind].getStatusIcon() + "] "
                            + tasks[ind].getDescription());
    }

    private static void markTask(Task[] tasks, String taskNumber, int index) throws DukeException {

        int ind = Integer.parseInt(taskNumber) - 1;

        if(ind >= index || ind < 0) {
            throw new DukeException("Task " + (ind + 1) + " does not exist.");
        }

        tasks[ind].mark();
        System.out.println("     Nice! I've marked this task as done:\n");
        System.out.println("       "
                            + "[" + tasks[ind].getStatusIcon() + "] "
                            + tasks[ind].getDescription());
    }

    private static void listTask(Task[] tasks, int index) {
        System.out.println("     Here are the tasks in your list:\n");
        for (int i = 0; i < index; i = i + 1) {
            int num = i + 1;
            System.out.println("     " + num
                                + ".[" + tasks[i].getTypeOfTask()
                                + "][" + tasks[i].getStatusIcon()
                                + "] " + tasks[i].getDescription());
        }
    }

}
