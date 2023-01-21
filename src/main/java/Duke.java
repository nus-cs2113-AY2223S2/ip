import java.util.Scanner;

public class Duke {
    public static void dashSeperator() {//prints a dash line for separating text
        System.out.println("____________________________________________________________");
    }

    public static void sayGreeting() {
        dashSeperator();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        dashSeperator();
    }

    public static void sayGoodbye() {
        dashSeperator();
        System.out.println("Bye. Hope to see you again soon!");
        dashSeperator();
    }

    public static void sayEcho(String input) {
        dashSeperator();
        System.out.println("added: " + input);
        dashSeperator();
    }

    public static void sayFeedback(Task item) {
        String desc = item.getDescription();
        System.out.println("[" + item.getStatusIcon() + "]" + " " + desc);
    }


    public static void handleRequest(String input, Task[] list) {
        if (input.equals("list")) {
            for (int i = 0; i < Task.getTaskNumber() + 1; i++) {
                sayFeedback(list[i]);
            }
        } else {
            if (input.contains("mark")) {
                int whitespace = input.indexOf(" ");
                int index = Integer.parseInt(input.substring(whitespace + 1));
                if (input.indexOf("m") == 0) {
                    list[index - 1].markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    sayFeedback(list[index - 1]);

                } else {
                    list[index - 1].markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    sayFeedback(list[index - 1]);
                }
            } else {
                Task newTask = new Task(input);
                list[Task.getTaskNumber()] = newTask;
                sayEcho(input);
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        sayGreeting();
        boolean byeFlag = false;
        Scanner line = new Scanner(System.in);
        Task[] userList = new Task[100]; //no more than 100 tasks
        while (!byeFlag) {
            String userInput = line.nextLine();
            if (userInput.equals("bye")) {
                byeFlag = true;
                sayGoodbye();
            } else {
                handleRequest(userInput, userList);
            }

        }
    }
}
