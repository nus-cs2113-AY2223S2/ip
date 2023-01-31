import jdk.jfr.Event;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String line = "____________________________________________________________\n";
        String greet = line +
                "Hello! I'm Duke.\n" +
                "Add tasks to the todo list by simply typing in the task and press enter.\n" +
                "Type 'list' to view the current todo list.\n" +
                "Type 'mark' and task event index to mark the task as done \nand type 'unmark'and index to undo the task.\n" +
                line;
        System.out.println(greet);

        //TODO: ignore leading white for user input
        //TODO: Event class

        // Level 3 Mark as Done
        Scanner in = new Scanner(System.in);
        int countTask = 0;
        Task[] tasks = new Task[100];
        while (true) {
            String userInput = in.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Thank you for using Duke. Hope to see you soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println(line + "Here are the tasks in your list:");
                for (int i = 0; i < countTask; i++) {
                    int taskIndex = i + 1;
                    System.out.print(taskIndex + ". " + tasks[i].printTask());
                }
                System.out.println(line);
            } else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.substring(4).trim());
                tasks[taskIndex - 1].markAsDone();
                System.out.println(line + "Task " + taskIndex + " marked as done: \n" +
                        tasks[taskIndex - 1].printTask() + line);
            } else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.substring(6).trim());
                tasks[taskIndex - 1].markAsUndone();
                System.out.println(line + "Task " + taskIndex + " marked as not done yet: \n" +
                        tasks[taskIndex - 1].printTask() + line);
            } else if (userInput.startsWith("todo")) {
                ToDo ToDoTask = new ToDo(userInput.substring(5));
                tasks[countTask] = ToDoTask;
                countTask++;
                System.out.println(line + "Great! I've added this task:\n" + "   " + ToDoTask.printTask());
                System.out.println("Now you have " + countTask + " task(s) in the list.\n" + line);
            } else if (userInput.startsWith("deadline")) {
                Deadline DeadlineTask = new Deadline(userInput.substring(9));
                tasks[countTask] = DeadlineTask;
                countTask++;
                System.out.println(line + "Great! I've added this task:\n" + "   " + DeadlineTask.printTask());
                System.out.println("Now you have " + countTask + " task(s) in the list.\n" + line);
            } else if (userInput.startsWith("event")) {
                Event EventTask = new Event(userInput.substring(9));
                tasks[countTask] = EventTask;
                countTask++;
                System.out.println(line + "Great! I've added this task:\n" + "   " + EventTask.printTask());
                System.out.println("Now you have " + countTask + " task(s) in the list.\n" + line);
            } else {
                Task t = new Task(userInput);
                tasks[countTask] = t;
                countTask++;
                System.out.println(line + "added: " + t.getDescription() + '\n' + line);
            }
        }
    }
}
