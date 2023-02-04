public class Manager {
    private static Task[] taskList = new Task[100];
    private static int listSize = 0;

    public static void addTask(Task task) {
        if (listSize >= 100) {
            System.out.println("List size exceeded!");
            return;
        }
        taskList[listSize] = task;
        listSize++;

        String addedMessage = "\tgot it. I've added this task:\n" + "\t  " + task;
        String sizeMessage = "\tNow you have " + listSize + " tasks in the list.";

        String[] messagePacket = {addedMessage, sizeMessage};
        Io.printMessage(messagePacket);
    }

    public static void markTask(int taskNum) {
        Task currentTask = taskList[taskNum - 1];
        if (taskNum > listSize || taskNum <= 0) {
            System.out.println("\tNo such task exists! Please try again");
            return;
        }
        currentTask.markDone();

        String markMessage =
                "\tNice! I've marked this task as done:\n" + "\t  " + currentTask.toString();

        String[] messagePacket = {markMessage};
        Io.printMessage(messagePacket);
    }

    public static void unmarkTask(int taskNum) {
        Task currentTask = taskList[taskNum - 1];
        if (taskNum > listSize || taskNum <= 0) {
            System.out.println("\tNo such task exists! Please try again");
            return;
        }
        currentTask.markUndone();

        String unmarkMessage =
                "\tOK, I've marked this task as not done yet:\n" + "\t  " + currentTask.toString();

        String[] messagePacket = {unmarkMessage};
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

    public static void handleCommand(String[] inputArray) throws InvalidCommandException, InvalidTaskException, InvalidFormatException {
        String command = inputArray[0];

        try { 
            switch (command) {
            case "todo":
                if (inputArray.length == 1) {
                    throw new InvalidTaskException(command);
                }
                String todoDetails = inputArray[1];
                Task todo = new Todo(todoDetails);
                addTask(todo);
                break;

            case "deadline":
                if (inputArray.length == 1) {
                    throw new InvalidTaskException(command);
                }
                if (!inputArray[1].contains("/by")) {
                    throw new InvalidFormatException("/by");
                }
                //splits input according to deadlineDescription, /by
                String[] deadlineDetails = inputArray[1].split(" /by ", 2);

                Task deadline = new Deadline(deadlineDetails[0],deadlineDetails[1]);
                addTask(deadline);
                break;

            case "event":
                if (inputArray.length == 1) {
                    throw new InvalidTaskException(command);
                }
                if (!inputArray[1].contains("/from") && !inputArray[1].contains("/to")) {
                    throw new InvalidFormatException("/from", "/to");
                }
                //splits input according to eventDescription, /from, /to
                String[] eventDetails = inputArray[1].split(" /from | /to ", 3);

                Task event = new Event(eventDetails[0],eventDetails[1],eventDetails[2]);
                addTask(event);
                break;

            case "list":
                printTasks();
                break;

            case "mark":
                if (inputArray.length == 1) {
                    throw new InvalidTaskException(command);
                }
                int taskNum = Integer.parseInt(inputArray[1]);
                markTask(taskNum);
                break;

            case "unmark":
                if (inputArray.length == 1) {
                    throw new InvalidTaskException(command);
                }
                int unmarkTaskNum = Integer.parseInt(inputArray[1]);
                unmarkTask(unmarkTaskNum);
                break;

            case "bye":
                Io.printExit();
                System.exit(0);
                break;

            default:
                throw new InvalidCommandException();
            }
        } catch (InvalidTaskException e) {
            System.out.println(e.getMessage());
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        } catch (InvalidFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
