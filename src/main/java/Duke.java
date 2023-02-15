import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void printList(List<Task> l1) {
        int index;
        if (l1.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        for (int i = 0; i < l1.size(); i += 1) {
            index = i + 1;
            System.out.println(index + ". [" + l1.get(i).getStatusIcon() + "] " + l1.get(i).getDescription());
        }
        System.out.println();
    }

    public static void printMarked(boolean isChanged, String icon, String description) {
        if (!isChanged) {
            System.out.println('\n' + "Task is originally marked as done." + '\n');
        } else {
            System.out.println('\n' + "Nice! I've marked this task as done:");
            System.out.println("  [" + icon + "] " + description + '\n');
        }
    }
    public static void printUnmarked(boolean isChanged, String icon, String description) {
        if (!isChanged) {
            System.out.println('\n' + "Task is originally marked as not done." + '\n');
        } else {
            System.out.println('\n' + "OK, I've marked this task as not done yet:");
            System.out.println("  [" + icon + "] " + description + '\n');
        }
    }

    public static void printAdded(String line) {
        System.out.println('\n' + "added: " + line + " to the task list!" + '\n');
    }

    public static String[] parseCommands(String line) {
        String[] lineSpaced = line.split(" ");
        for (int i = 2; i < lineSpaced.length; i++) {
            lineSpaced[1].concat(lineSpaced[i]);
        }
        return lineSpaced;
    }

    public static void printLineSpaced(String [] line) {
        for (String a : line) {
            System.out.print(a + " ");
        }
    }

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                      + "|  _ \\ _   _| | _____ \n"
                      + "| | | | | | | |/ / _ \\\n"
                      + "| |_| | |_| |   <  __/\n"
                      + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("Hello! I'm a Robot");
        System.out.println("What can I do for you?\n");
        List<Task> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String line, commandWord, description;
        int listIndex;
        String[] lineSpaced;
        boolean isChanged = false;

        line = in.nextLine();
        lineSpaced = parseCommands(line);
        while(!lineSpaced[0].equals("bye")) {
            commandWord = lineSpaced[0];
            switch (commandWord) {
            case "list":
                printList(list);
                break;
            case "mark":
                description = lineSpaced[1];
                listIndex = Integer.parseInt(description) - 1;
                isChanged = false;
                if (!list.get(listIndex).getIsDone()) {
                    list.get(listIndex).markAsDone();
                    isChanged = true;
                }
                printMarked(isChanged, list.get(listIndex).getStatusIcon(), list.get(listIndex).getDescription());
                break;
            case "unmark":
                description = lineSpaced[1];
                listIndex = Integer.parseInt(description) - 1;
                isChanged = false;
                if (list.get(listIndex).getIsDone()) {
                    list.get(listIndex).markAsUndone();
                    isChanged = true;
                }
                printUnmarked(isChanged, list.get(listIndex).getStatusIcon(), list.get(listIndex).getDescription());
                break;
            default:
                printAdded(line);
                Task temp_task = new Task(line);
                list.add(temp_task);
            }
            line = in.nextLine();
            lineSpaced = parseCommands(line);
        }
        System.out.println('\n' + "Bye. Hope to see you again soon!");
    }
}
