public class Manager {
    private static Task[] taskList = new Task[100];
    private static int listSize = 0;

    public static void addDeadline(String inputMessage, String by) {
        Task deadline = new Deadline(inputMessage, by);
        if (listSize >= 100) {
            System.out.println("List size exceeded!");
            return;
        }
        taskList[listSize] = deadline;
        listSize++;

        String addedMessage = "\tGot it. I've added this task:\n"
                + "\t  " + deadline.toString();
        String sizeMessage = "\tNow you have " + listSize + " tasks in the list.";

        String[] messagePacket = { addedMessage, sizeMessage };
        Io.printMessage(messagePacket);
    }

    public static void addTodo(String inputMessage) {
        Task todo = new Todo(inputMessage);
        if (listSize >= 100) {
            System.out.println("List size exceeded!");
            return;
        }
        taskList[listSize] = todo;
        listSize++;

        String addedMessage = "\tGot it. I've added this task:\n"
                + "\t  " + todo.toString();
        String sizeMessage = "\tNow you have " + listSize + " tasks in the list.";

        String[] messagePacket = { addedMessage, sizeMessage };
        Io.printMessage(messagePacket);
    }

    public static void addEvent(String inputMessage, String from, String to) {
        Task event = new Event(inputMessage, from, to);
        if (listSize >= 100) {
            System.out.println("List size exceeded!");
            return;
        }
        taskList[listSize] = event;
        listSize++;

        String addedMessage = "\tGot it. I've added this task:\n"
                + "\t  " + event.toString();
        String sizeMessage = "\tNow you have " + listSize + " tasks in the list.";

        String[] messagePacket = { addedMessage, sizeMessage };
        Io.printMessage(messagePacket);
    }

    public static void markTask(int taskNum) {
        Task currentTask = taskList[taskNum - 1];
        if (taskNum > listSize || taskNum <= 0) {
            System.out.println("\tNo such task exists! Please try again");
            return;
        }
        currentTask.markDone();

        String markMessage = "\tNice! I've marked this task as done:\n"
                + "\t  " + currentTask.toString();

        String[] messagePacket = { markMessage };
        Io.printMessage(messagePacket);
    }

    public static void unmarkTask(int taskNum) {
        Task currentTask = taskList[taskNum - 1];
        if (taskNum > listSize || taskNum <= 0) {
            System.out.println("\tNo such task exists! Please try again");
            return;
        }
        currentTask.markUndone();

        String unmarkMessage = "\tOK, I've marked this task as not done yet:\n"
                + "\t  " + currentTask.toString();

        String[] messagePacket = { unmarkMessage };
        Io.printMessage(messagePacket);
    }

    public static void printTasks() {
        String[] messagePacket = new String[listSize + 1];
        messagePacket[0] = "\tHere are the tasks in your list:";
        int messageCount = 1;

        for (int i = 0; i < listSize; i++) {
            String line = "\t" + (i + 1) + ". " + taskList[i];
            messagePacket[messageCount++] = line;
        }
        Io.printMessage(messagePacket);
    }

    public static void handleCommand(String[] inputArray) {
        String command = inputArray[0];

        switch (command) {
        case "todo":
            String todoDetails = inputArray[1];
            addTodo(todoDetails);
            break;

        case "deadline":
            if (!inputArray[1].contains("/by")) {
                System.out.println("\tWrong format! Please include /by.");
                break;
            }

            String[] deadlineDetails = inputArray[1].split(" /by ", 2);
            String deadlineDescription = deadlineDetails[0];
            String by = deadlineDetails[1];
            addDeadline(deadlineDescription, by);
            break;

        case "event":
            if (!inputArray[1].contains("/from") && !inputArray[1].contains("/to")) {
                System.out.println("\tWrong format! Please include /from and /to.");
                break;
            }

            String[] eventDetails = inputArray[1].split(" /from | /to ", 3);
            String eventDescription = eventDetails[0];
            String from = eventDetails[1];
            String to = eventDetails[2];
            addEvent(eventDescription, from, to);
            break;

        case "list":
            printTasks();
            break;

        case "mark":
            int taskNum = Integer.parseInt(inputArray[1]);
            markTask(taskNum);
            break;

        case "unmark":
            int unmarkTaskNum = Integer.parseInt(inputArray[1]);
            unmarkTask(unmarkTaskNum);
            break;

        case "bye":
            Io.printExit();
            System.exit(0);
            break;

        default:
            break;
        }
    }
}
