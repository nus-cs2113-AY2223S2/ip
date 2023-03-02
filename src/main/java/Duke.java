import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Duke {

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

    public static void printDash() {
        System.out.println("____________________________________________________________");
    }

    public static String toData(Task command) {
        int openB, closeB;
        String val, info, task, eg, due, len;
        Character check, mark;
        val = "" + command;
        check = val.charAt(1);
        mark = val.charAt(4);
        task = val.substring(6);

        if (check.equals('D')) {
            openB = task.indexOf("(");
            closeB = task.indexOf(")");
            eg = task.substring(0, openB);
            int openB1 = openB + 1;
            due = task.substring(openB1, closeB);
            String[] deadline;
            deadline = due.split("by: ");

            if (!mark.equals('X')) {
                info = ("D | 0 |" + eg + " | " + deadline[1]);
            } else {
                info = ("D | 1 |" + eg + " | " + deadline[1]);
            }
        } else if (check.equals('T')) {
            if (!mark.equals('X')) {
                info = ("T | 0 |" + task);
            } else {
                info = ("T | 1 |" + task);
            }
        } else {
            openB = task.indexOf("(");
            closeB = task.indexOf(")");
            eg = task.substring(0, openB);
            int openB6 = openB + 6;
            len = task.substring(openB6, closeB);
            String[] eventLen;
            eventLen = len.split("to: ");

            if (!mark.equals('X')) {
                info = ("E | 0 |" + eg + " | " + eventLen[0].substring(1) + "-" + eventLen[1]);
            } else {
                info = ("E | 1 |" + eg + " | " + eventLen[0].substring(1) + "-" + eventLen[1]);
            }
        }
        return info;
    }

    public static void fromData(String val) {
        String[] task;
        String eg, due, event;
        Character check, valid;

        task = val.split("\\|");
        check = task[0].charAt(0);
        valid = task[1].charAt(1);

        if (check.equals('D')) {
            eg = task[2].substring(1, (task[2].length() - 1));
            due = task[3].substring(1);
            Task command;
            command = new Deadline(eg, due);
            Task.tasks.add(Task.taskNum, command);
            Task.taskNum += 1;

            if (valid.equals('1')) {
                command.markAsDone();
            }
        } else if (check.equals('T')) {
            eg = task[2].substring(1);
            Task command = new Todo(eg);
            Task.tasks.add(Task.taskNum, command);
            Task.taskNum += 1;

            if (valid.equals('1')) {
                command.markAsDone();
            }
        } else {
            eg = task[2].substring(1, (task[2].length() - 1));
            event = task[3].substring(1);
            String[] len;
            len = event.split("-");
            Task command;
            command = new Event(eg, len[0], len[1]);
            Task.tasks.add(Task.taskNum, command);
            Task.taskNum += 1;

            if (valid.equals('1')) {
                command.markAsDone();
            }
        }
    }
    public static void findData() throws FileNotFoundException {
        String path;
        path = "data/duke.txt";

        File file = new File(path);
        Scanner scanner = new Scanner(file);

        String val;
        while (scanner.hasNext()) {
            val = scanner.nextLine();
            fromData(val);
        }
    }

    public static void saveData() {
        String path;
        path = "data/duke.txt";

        try {
            FileOperations.addToFile(path, toData(Task.tasks.get(0)) + System.lineSeparator());
            int k;
            for (k = 1; Task.tasks.size() > k; k += 1) {
                FileOperations.appendFile(path, toData(Task.tasks.get(k)) + System.lineSeparator());
            }
        } catch (IOException err) {
            System.out.println("The following error occurred: " + err.getMessage());
        }
    }

    public static void printExit() {
        printDash();
        System.out.println("Bye, cya soon!");
        printDash();
    }

    public static void listTask() {
        printDash();
        System.out.println("Tasks in list:");
        int k = 0;
        
        for (Task command : Task.tasks) {
            k += 1;
            System.out.println((k + "." + command));
        }
        
        printDash();
    }
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
            saveData();
            printDash();
            System.out.println("Marking task as undone:\n" + command);
            printDash();
        } catch (NullPointerException err) {
            printDash();
            System.out.println("☹ OOPS!!! Not enough tasks here. Try again.");
            printDash();
        } catch (IndexOutOfBoundsException err) {
            printDash();
            System.out.println("☹ OOPS!!! Task number is missing. Try again.");
            printDash();
        } catch (DukeException err) {
            printDash();
            System.out.println("☹ OOPS!!! Invalid task number. Try again.");
            printDash();
        }
    }

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
            saveData();
            printDash();
            System.out.println("Marking task as done:\n" + command);
            printDash();
        } catch (NullPointerException err) {
            printDash();
            System.out.println("☹ OOPS!!! Not enough tasks here. Try again.");
            printDash();
        } catch (IndexOutOfBoundsException err) {
            printDash();
            System.out.println("☹ OOPS!!! Task number is missing. Try again.");
            printDash();
        } catch (DukeException err) {
            printDash();
            System.out.println("☹ OOPS!!! Invalid task number. Try again.");
            printDash();
        }
    }

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
            saveData();
            printDash();
            System.out.println("Successfully deleted task:\n" + command);
            System.out.println("You currently have " + Task.taskNum + " task(s) left");
            printDash();
        } catch (NullPointerException err) {
            printDash();
            System.out.println("☹ OOPS!!! Not enough tasks here. Try again.");
            printDash();
        } catch (IndexOutOfBoundsException err) {
            printDash();
            System.out.println("☹ OOPS!!! Task number is missing. Try again.");
            printDash();
        } catch (DukeException err) {
            printDash();
            System.out.println("☹ OOPS!!! Invalid task number. Try again.");
            printDash();
        }
    }

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
            saveData();
            printDash();
            System.out.println("Adding this task:\n" + command);
            System.out.println("You currently have " + Task.taskNum + " task(s)");
            printDash();
        } catch (IndexOutOfBoundsException | DukeException err) {
            printDash();
            System.out.println("☹ OOPS!!! Due date of deadline or description cannot be empty. Try again.\nFollow the following format: deadline [description] /by [due date]");
            printDash();
        }
    }

    public static void makeToDo(String[] list) {
        try {
            if (isDesEmpty(list[1])) {
                throw new DukeException();
            }

            Task command;
            command = new Todo(list[1]);
            Task.tasks.add(Task.taskNum, command);
            Task.taskNum += 1;
            saveData();
            printDash();
            System.out.println("Adding this task:\n" + command);
            System.out.println("You currently have " + Task.taskNum + " task(s)");
            printDash();
        } catch (IndexOutOfBoundsException | DukeException err) {
            printDash();
            System.out.println("☹ OOPS!!! Description of a to do cannot be empty. Try again.");
            printDash();
        }
    }
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
            saveData();
            printDash();
            System.out.println("Adding this task:\n" + command);
            System.out.println("You currently have " + Task.taskNum + " task(s)");
            printDash();
        } catch (IndexOutOfBoundsException | DukeException err) {
            printDash();
            System.out.println("☹ OOPS!!! Date of event or description cannot be empty. Try again.\nFollow the following format: event [description] /from [start] /to [end]");
            printDash();
        }
    }
    public static void main(String[] args) throws IOException {
        printDash();
        System.out.println("Hola! I'm Duke");
        System.out.println("Let me know your tasks for the day!");
        printDash();

        try {
            findData();
        } catch (IOException err) {
            System.out.println("☹ OOPS!!! Currently creating file as it does not exist");
            String path = "data";
            String file = "data/duke.txt";
            FileOperations.makeFile(file, path);
        }

        Scanner in = new Scanner(System.in);

        String cmd;
        cmd = in .nextLine();

        while (!(cmd.equals("bye"))) {
            if (cmd.equals("list")) {
                listTask();
            } else {
                String[] list = cmd.split(" ", 2);

                if (list[0].equals("mark")) {
                    markTask(list);
                } else if (list[0].equals("unmark")) {
                    unmarkTask(list);
                } else if (list[0].equals("todo")) {
                    makeToDo(list);
                } else if (list[0].equals("event")) {
                    makeEvent(list);
                } else if (list[0].equals("deadline")) {
                    makeDeadline(list);
                } else if (list[0].equals("delete")) {
                    deleteTask(list);
                } else {
                    printDash();
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    printDash();
                }
            }
            cmd = in.nextLine();
            saveData();
        }

        // exit
        printExit();
    }
}