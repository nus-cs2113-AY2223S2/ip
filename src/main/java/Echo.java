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
        String line;
        line = in.nextLine();

        while (!(line.equals("Bye") || line.equals("bye"))) {
            if (line.equals("list")) {
                int index = 1;
                for (int i = 0; i < count; ++i) {
                    System.out.println(index + "." + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                    ++index;
                }
            } else if (line.contains("unmark")) {
                int positionOfSpace = line.indexOf(' ');
                String taskNoString = line.substring(positionOfSpace + 1);
                int taskNoInt = Integer.parseInt(taskNoString);
                tasks[taskNoInt - 1].markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + tasks[taskNoInt - 1].description);
            } else if (line.contains("mark")) {
                int positionOfSpace = line.indexOf(' ');
                String taskNoString = line.substring(positionOfSpace + 1);
                int taskNoInt = Integer.parseInt(taskNoString);
                tasks[taskNoInt - 1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + tasks[taskNoInt - 1].description);
            } else {
                System.out.println("added: " + line);
                tasks[count] = new Task(line);
                ++count;
            }

            line = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again!\n");
    }
}
