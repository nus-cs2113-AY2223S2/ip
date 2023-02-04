import java.util.Scanner;

public class Duke {

    public static final String DIVIDER  = "______________________________";
    public static void greetUser() {
         String greeting = DIVIDER + System.lineSeparator() + "Hello! I'm Jarvis!"
                 + System.lineSeparator() + "What can I do for you?"
                 + System.lineSeparator() + DIVIDER + System.lineSeparator();
         System.out.println(greeting);
    }

    public static void echoUserInput(String curr) {
        String echo = DIVIDER + System.lineSeparator() + "added: " + curr + System.lineSeparator()
                + DIVIDER + System.lineSeparator();
        System.out.println(echo);
    }

    public static void sayByeToUser() {
        String bye = DIVIDER + System.lineSeparator() + "Bye. Hope to see you again soon!"
                + System.lineSeparator() + DIVIDER + System.lineSeparator();
        System.out.println(bye);
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
        boolean hasEnded = false;

        while(!hasEnded) {
            input = in.nextLine();
            String[] inputText = input.split(" ");

            switch(inputText[0]) {
            case "bye":
                hasEnded = true;
                break;
            case "list":
                System.out.println(DIVIDER + System.lineSeparator()
                        + "Here are the tasks in your list:");
                for (int i = 0; i < taskSum; i++) {
                    System.out.println((i+1) + ". " + "[" + list[i].getStatusIcon() + "] " + list[i].getName());
                }
                System.out.println(DIVIDER);
                break;
            case "mark":
                taskNum = Integer.parseInt(inputText[1]);
                //error handling
                if (taskNum > taskSum || taskNum == 0) {
                    System.out.println(DIVIDER + System.lineSeparator()
                            + "Unable to mark task as done since the task does not exist!"
                            + System.lineSeparator() + DIVIDER);
                }
                else {
                    list[taskNum-1].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + list[taskNum - 1].getName()
                            + System.lineSeparator() + DIVIDER);
                }
                //markAsDone(list, taskNum, taskSum);
                break;
            case "unmark":
                taskNum = Integer.parseInt(inputText[1]);
                //error handling
                if (taskNum > taskSum || taskNum == 0) {
                    System.out.println(DIVIDER + System.lineSeparator()
                            + "Unable to mark task as not done since the task does not exist!"
                            + System.lineSeparator() + DIVIDER);
                }
                else {
                    list[taskNum-1].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[ ] " + list[taskNum - 1].getName()
                            + System.lineSeparator() + DIVIDER);
                }
                //markAsNotDone(list,taskNum, taskSum);
                break;
            default:
                echoUserInput(input);
                list[taskSum] = new Task(input);
                taskSum++;
            }
        }
        sayByeToUser();
    }
}
