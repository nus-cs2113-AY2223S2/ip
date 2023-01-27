import java.util.Scanner;

public class Duke {
    public static void greetUser() {
         String divider = "______________________________";
         String greeting = divider + System.lineSeparator() + "Hello! I'm Jarvis!"
                 + System.lineSeparator() + "What can I do for you?"
                 + System.lineSeparator() + divider + System.lineSeparator();
         System.out.println(greeting);
    }

    public static void echoUserInput(String curr) {
        String divider = "______________________________";
        String echo = divider +System.lineSeparator() + "added: " + curr + System.lineSeparator()
                + divider + System.lineSeparator();
        System.out.println(echo);
    }

    public static void sayByeToUser() {
        String divider = "______________________________";
        String bye = divider + System.lineSeparator() + "Bye. Hope to see you again soon!"
                + System.lineSeparator() + divider + System.lineSeparator();
        System.out.println(bye);
    }

    public static void addToList(Task[] tasks, String curr, int taskSum) {
        tasks[taskSum] = new Task(curr);
    }

    public static void printList(Task[] tasks, int taskSum) {
        String divider = "______________________________";
        System.out.println(divider + System.lineSeparator()
                + "Here are the tasks in your list:");
        for (int i = 0; i < taskSum; i++) {
            System.out.println((i+1) + ". " + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].getName());
        }
        System.out.println(divider);
    }

    public static void markAsDone(Task[]tasks, int taskNum, int taskSum){
        String divider = "______________________________";
        int taskIndex = taskNum - 1;
        if (taskNum > taskSum) {
            System.out.println(divider + System.lineSeparator()
                    + "Unable to mark task as done since the task does not exist!"
                    + System.lineSeparator() + divider);
        } else {
            tasks[taskIndex].isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + tasks[taskIndex].getName()
                    + System.lineSeparator() + divider);
        }
    }

    public static void markAsNotDone(Task[]tasks, int taskNum, int taskSum){
        String divider = "______________________________";
        int taskIndex = taskNum - 1;
        if (taskNum > taskSum) {
            System.out.println(divider + System.lineSeparator()
                    + "Unable to mark task as not done since the task does not exist!"
                    + System.lineSeparator() + divider);
        } else {
            tasks[taskIndex].isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + tasks[taskIndex].getName()
                    + System.lineSeparator() + divider);
        }
    }

    public static void main(String[] args) {
        String input;
        //String[] tasks = new String[100];
        //Use tasks class instead of string class
        Task[] list = new Task[100];
        Scanner in = new Scanner(System.in);
        greetUser();
        int taskSum = 0;
        int taskNum;
        boolean end = false;

        while(!end){
            input = in.nextLine();
            String[] inputText = input.split(" ");
            if(input.equals("bye")) {
                end = true;
            } else if(input.equals("list")) {
                printList(list, taskSum);
            } else if (inputText[0].equals("mark")) {
                taskNum = Integer.parseInt(inputText[1]);
                markAsDone(list, taskNum, taskSum);
            } else if(inputText[0].equals("unmark")) {
                taskNum = Integer.parseInt(inputText[1]);
                markAsNotDone(list,taskNum, taskSum);
            } else {
                echoUserInput(input);
                addToList(list, input, taskSum);
                taskSum++;
            }
        }
        sayByeToUser();
    }
}
