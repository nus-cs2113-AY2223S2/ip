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
    static final String COMMAND_BY = "/by";
    static final String COMMAND_FROM = "/from";
    static final String COMMAND_TO = "/to";

    //Data
    static Task[] list = new Task[100];
    static int taskSum = 0;

    public static void printLogo() {
        String logoMessage = "Hello from\n" +
                " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|";
        System.out.println(logoMessage);
    }

    public static void greetUser() {
         String greeting = DIVIDER + System.lineSeparator() + "Hello! I'm Jarvis!"
                 + System.lineSeparator() + "What can I do for you?"
                 + System.lineSeparator() + DIVIDER + System.lineSeparator();
         System.out.println(greeting);
    }

    public static void sayByeToUser() {
        String bye = DIVIDER + System.lineSeparator() + "Bye. Hope to see you again soon!"
                + System.lineSeparator() + DIVIDER + System.lineSeparator();
        System.out.println(bye);
    }

    public static void printList() {
        String listMessage = DIVIDER + System.lineSeparator() + "Here are the tasks in your list:";
        System.out.println(listMessage);
        for (int i = 0; i < taskSum; i++) {
            System.out.println((i+1) + "." + list[i].toString());
        }
        System.out.println(DIVIDER);
    }

    public static void markTask(int taskNum) {
        list[taskNum-1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(DIVIDER + System.lineSeparator() + list[taskNum-1].toString()
                + System.lineSeparator() + DIVIDER);
    }

    public static void unmarkTask(int taskNum) {
        list[taskNum-1].markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(DIVIDER + System.lineSeparator() + list[taskNum-1].toString()
                + System.lineSeparator() + DIVIDER);
    }

    public static void acknowledgementMessage(int taskSum) {
        String acknowledgement = DIVIDER + System.lineSeparator()
                + "Got it. I've added this task: " + System.lineSeparator()
                + list[taskSum].toString();
        System.out.println(acknowledgement);
        System.out.println("Now you have " + (taskSum+1) + " task(s) in the list."
                + System.lineSeparator() + DIVIDER);
    }

    public static void taskManager() {
        String userInput;
        boolean isFinished = false;
        int taskNum;

        while(!isFinished) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            String[] inputText = userInput.split(" ");
            //get the remaining task description after the command word.
            String taskDesc = "";
            for (int i = 1; i < inputText.length; i++) {
                taskDesc = taskDesc + " " + inputText[i];
            }

            switch(inputText[0]) {
            case COMMAND_EXIT:
                isFinished = true;
                break;
            case COMMAND_LIST:
                printList();
                break;
            case COMMAND_MARK:
                try {
                    taskNum = Integer.parseInt(inputText[1]);
                    markTask(taskNum);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(DIVIDER + System.lineSeparator()
                            + "Please key in a task number to mark!" + System.lineSeparator() + DIVIDER);
                } catch (NumberFormatException e) {
                    System.out.println(DIVIDER + System.lineSeparator()
                            + "Please key in a valid task number to mark!" + System.lineSeparator() + DIVIDER);
                } catch (NullPointerException e) {
                    System.out.println(DIVIDER + System.lineSeparator()
                            + "Unable to mark task as done since the task does not exist!"
                            + System.lineSeparator() + DIVIDER);
                }
                break;
            case COMMAND_UNMARK:
                try {
                    taskNum = Integer.parseInt(inputText[1]);
                    unmarkTask(taskNum);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(DIVIDER + System.lineSeparator()
                            + "Please key in a task number to unmark!" + System.lineSeparator() + DIVIDER);
                } catch (NumberFormatException e) {
                    System.out.println(DIVIDER + System.lineSeparator()
                            + "Please key in a valid task number to unmark!" + System.lineSeparator() + DIVIDER);
                } catch (NullPointerException e) {
                    System.out.println(DIVIDER + System.lineSeparator()
                            + "Unable to mark task as not done since the task does not exist!"
                            + System.lineSeparator() + DIVIDER);
                }
                break;
            case COMMAND_TODO:
                try {
                    if (taskDesc.length() == 0) {
                        throw new EmptyDescriptionException();
                    }
                    list[taskSum] = new Todo(taskDesc);
                    acknowledgementMessage(taskSum);
                    taskSum++;
                } catch (EmptyDescriptionException e) {
                    e.emptyDescriptionTodo();
                }
                break;
            case COMMAND_EVENT:
                try {
                    if (taskDesc.length() == 0) {
                        throw new EmptyDescriptionException();
                    }
                    //use string.split to split the string into their different descriptions
                    String[] eventInput = taskDesc.split(COMMAND_FROM);
                    //split into task description and duration
                    String eventTaskDesc = eventInput[0];
                    String eventDuration = eventInput[1];
                    String[] eventStartAndEnd = eventDuration.split(COMMAND_TO);
                    String eventStart = eventStartAndEnd[0];
                    String eventEnd = eventStartAndEnd[1];
                    list[taskSum] = new Event(eventTaskDesc, eventStart, eventEnd);
                    acknowledgementMessage(taskSum);
                    taskSum++;
                } catch (EmptyDescriptionException e) {
                    e.emptyDescriptionEvent();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(DIVIDER + System.lineSeparator()
                            + "The event you keyed in was invalid!" + System.lineSeparator()
                            + "Please key in a valid event!" + System.lineSeparator() + DIVIDER);
                }
                break;
            case COMMAND_DEADLINE:
                try {
                    if (taskDesc.length() == 0) {
                        throw new EmptyDescriptionException();
                    }
                    //use string.split to split the string into their different descriptions
                    String[] deadlineInput = taskDesc.split(COMMAND_BY);
                    //split into task description and duration
                    String deadlineTaskDesc = deadlineInput[0];
                    String deadlineDuration = deadlineInput[1];
                    list[taskSum] = new Deadline(deadlineTaskDesc, deadlineDuration);
                    acknowledgementMessage(taskSum);
                    taskSum++;
                } catch (EmptyDescriptionException e) {
                    e.emptyDescriptionDeadline();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(DIVIDER + System.lineSeparator()
                            + "The deadline you keyed in was invalid!" + System.lineSeparator()
                            + "Please key in a valid deadline!" + System.lineSeparator() + DIVIDER);
                }
                break;
            default:
                System.out.println(DIVIDER + System.lineSeparator()
                        + "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                        + System.lineSeparator() + DIVIDER);
                break;
            }
        }
    }

    public static void main(String[] args) {
        printLogo();
        greetUser();
        taskManager();
        sayByeToUser();
    }
}
