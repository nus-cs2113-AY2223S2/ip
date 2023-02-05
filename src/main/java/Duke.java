import java.util.Scanner;

public class Duke {

    public static final String DIVIDER  = "______________________________";

    //Commands
    static final String COMMAND_EXIT = "bye";
    static final String COMMAND_LIST = "list";
    static final String COMMAND_MARK = "mark";
    static final String COMMAND_UNMARK = "unmark";
    static final String COMMAND_TODO = "todo";
    static final String COMMAND_DEADLINE = "deadline";
    static final String COMMAND_EVENT = "event";

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

    public static void okMessage(Task[] tasks, int taskSum) {
        String acknowledgement = DIVIDER + System.lineSeparator()
                + "Got it. I've added this task: " + System.lineSeparator()
                + tasks[taskSum].toString();
        System.out.println(acknowledgement);
        taskSum++;
        System.out.println("Now you have " + taskSum + " task(s) in the list."
                + System.lineSeparator() + DIVIDER);
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
            //get the remaining task description after the command word.
            String taskDesc = "";
            for (int i = 1; i < inputText.length; i++) {
                taskDesc = taskDesc + " " + inputText[i];
            }

            switch(inputText[0]) {
            case COMMAND_EXIT:
                hasEnded = true;
                break;
            case COMMAND_LIST:
                System.out.println(DIVIDER + System.lineSeparator()
                        + "Here are the tasks in your list:");
                for (int i = 0; i < taskSum; i++) {
                    //System.out.println((i+1) + ". " + list[i].toString() + "[" + list[i].getStatusIcon() + "] " + list[i].getName());
                    System.out.println((i+1) + "." + list[i].toString());
                }
                System.out.println(DIVIDER);
                break;
            case COMMAND_MARK:
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
                    System.out.println(DIVIDER + System.lineSeparator() + list[taskNum-1].toString()
                            + System.lineSeparator() + DIVIDER);
                }
                break;
            case COMMAND_UNMARK:
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
                    System.out.println(DIVIDER + System.lineSeparator() + list[taskNum-1].toString()
                            + System.lineSeparator() + DIVIDER);
                }
                break;
            case COMMAND_TODO:
                list[taskSum] = new Todo(taskDesc);
                okMessage(list,taskSum);
                taskSum++;
                break;
            case COMMAND_EVENT:
                //use string.split to split the string into their different descriptions
                String[] eventInput = taskDesc.split("/from");
                String eventDesc = eventInput[0];
                String eventDuration = eventInput[1];
                String[] startAndEnd = eventDuration.split("/to");
                String eventStart = startAndEnd[0];
                String eventEnd = startAndEnd[1];
                list[taskSum] = new Event(eventDesc, eventStart, eventEnd);
                okMessage(list,taskSum);
                taskSum++;
                break;
            case COMMAND_DEADLINE:
                //use string.split to split the string into their different descriptions
                String[] deadlineInput = taskDesc.split("/by");
                String deadlineTaskDesc = deadlineInput[0];
                String deadlineDuration = deadlineInput[1];
                list[taskSum] = new Deadline(deadlineTaskDesc, deadlineDuration);
                okMessage(list,taskSum);
                taskSum++;
                break;
            default:
                echoUserInput(input);
                list[taskSum] = new Task(input);
                taskSum++;
                break;
            }
        }
        sayByeToUser();
    }
}
