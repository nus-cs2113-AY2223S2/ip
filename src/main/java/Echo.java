//import java.util.Scanner;
//
//public class Echo {
//    public static void Echo() {
//        String line;
//        Scanner in = new Scanner(System.in);
//        line = in.nextLine();
//        String[] tasks = new String[100];
//        int count = 0;
//        while (!(line.equals("Bye") || line.equals("bye"))) {
//            if (!line.equals("list")) {
//                System.out.println("added: " + line);
//                tasks[count] = line;
//                ++count;
//                line = in.nextLine();
//            } else {
//                int index = 1;
//                for (int i = 0; i < count; ++i) {
//                    System.out.println(index + ": " + tasks[i]);
//                    ++index;
//                }
//                line = in.nextLine();
//            }
//        }
//        System.out.println("Bye. Hope to see you again!\n");
//    }
//}

import java.util.Scanner;

public class Echo {
    public static void Echo() {
        Task[] tasks = new Task[100];
        int count = 0;

        Scanner in = new Scanner(System.in);
        String readInput = in.nextLine();
        String readInputAsArray[] = readInput.split(" ", 2);
        String command = readInputAsArray[0];
        String task = " ";
        if(readInputAsArray.length > 1) {
            task = readInputAsArray[1];
        }

        while (!(command.equals("Bye") || command.equals("bye"))) {
            if (command.equals("list")) {
                list_Input(tasks, count);
            } else if (command.equals("unmark")) {
                unmark_Input(tasks, task);
            } else if (command.equals("mark")) {
                mark_Input(tasks, task);
            } else if (command.equals("todo")){
                count = todo_Input(tasks, count, task);
            } else if (command.equals("deadline")) {
                count = deadline_Input(tasks, count, task);
            } else if (command.equals("event")) {
                count = event_Input(tasks, count, task);
            }

            readInput = in.nextLine();
            readInputAsArray = readInput.split(" ", 2);
            command = readInputAsArray[0];
            if(readInputAsArray.length > 1) {
                task = readInputAsArray[1];
            }
        }

        System.out.println("Bye. Hope to see you again!\n");
    }

    private static int event_Input(Task[] tasks, int count, String task) {
        System.out.println("Got it. I've added this task:");
        String taskAsArray[] = task.split("/");
        tasks[count] = new Event(taskAsArray[0], taskAsArray[1], taskAsArray[2]);
        System.out.println(tasks[count].toString());
        ++count;
        System.out.println("Now you have " + count + " tasks in the list.");
        return count;
    }

    private static int deadline_Input(Task[] tasks, int count, String task) {
        System.out.println("Got it. I've added this task:");
        String taskAsArray[] = task.split("/");
        tasks[count] = new Deadline(taskAsArray[0], taskAsArray[1]);
        System.out.println(tasks[count].toString());
        ++count;
        System.out.println("Now you have " + count + " tasks in the list.");
        return count;
    }

    private static int todo_Input(Task[] tasks, int count, String task) {
        System.out.println("Got it. I've added this task:");
        tasks[count] = new Todo(task);
        System.out.println(tasks[count].toString());
        ++count;
        System.out.println("Now you have " + count + " tasks in the list.");
        return count;
    }

    private static void mark_Input(Task[] tasks, String task) {
        int taskNoInt = Integer.parseInt(task);
        tasks[taskNoInt - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[taskNoInt - 1].toString());
    }

    private static void unmark_Input(Task[] tasks, String task) {
        int taskNoInt = Integer.parseInt(task);
        tasks[taskNoInt - 1].markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks[taskNoInt - 1].toString());
    }

    private static void list_Input(Task[] tasks, int count) {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (int i = 0; i < count; ++i) {
            System.out.println(index + "." + tasks[i].toString());
            ++index;
        }
    }
}
