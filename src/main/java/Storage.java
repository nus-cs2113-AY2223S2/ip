import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
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
            command = new Event(len[0], len[1], eg);
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
}
