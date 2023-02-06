import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;




public class Duke {

    private static final Task[] tasks = new Task[Constants.MAX_NUMBER_OF_ENTRIES];

    public static void main(String[] args) {

        String command;
        int numberOfTasks = 0;
        String latestResponse = "";
        boolean hasAdditionalTask = false;

        greetUser();

        while (numberOfTasks < 100) {
            command = latestResponse;


            if (!hasAdditionalTask) {
                System.out.println("What can I do for your today?");
                command = readTask();
            }
            boolean isValidInput = checkForValidInput(command);

            while (!isValidInput) {
                command = readTask();
                isValidInput = checkForValidInput(command);
            }
            printHorizontalBar();



            if (command.contains("list")) {
                printTaskList();

            } else if (Objects.equals(command, "bye")) {
                break;

            } else if (command.contains("unmark")) {
                boolean isValidUnmarkInput = checkUnmarkInput(command, numberOfTasks);
                if (isValidUnmarkInput) {
                    unmarkTask(command, numberOfTasks);
                }


            } else if (command.contains("mark")) {
                boolean isValidMarkInput = checkMarkInput(command, numberOfTasks);
                if (isValidMarkInput) {
                    markTask(command, numberOfTasks);
                }

            } else if (command.contains("deadline")) {
                boolean isValidDeadlineInput = checkDeadlineInput(command);
                if (isValidDeadlineInput) {
                    createDeadline(command, numberOfTasks);
                    numberOfTasks++;
                }


            } else if (command.contains("event")) {
                boolean isValidEventInput = checkEventInput(command);
                if (isValidEventInput) {
                    createEvent(command, numberOfTasks);
                    numberOfTasks++;
                }



            } else {
                createTask(command, numberOfTasks);
                numberOfTasks++;

            }


            System.out.println("There are currently "+numberOfTasks + " task(s) in the list");
            printHorizontalBar();
            latestResponse = checkForAdditionalTask();
            hasAdditionalTask = true;
            if (Objects.equals(latestResponse, "no")) {
                break;
            }


        }

        //to say bye
        System.out.println("Bye! Hope to see you again soon!\n");
        printHorizontalBar();

    }

    private static boolean checkDeadlineInput(String command) {
        command = command.toLowerCase();
        String keyword = command.substring(0, 8);
        String deadlineName = command.substring(0, command.indexOf("/"));
        String[] deadlineNameWords = deadlineName.split(" ");
        int nameLength = deadlineNameWords.length;

        if (!keyword.equals("deadline")) {
            System.out.println("The keyword 'deadline' should be the first word");
            return false;
        }

        String[] words = command.split(" ");
        char slash = words[words.length - 1].charAt(0);
        int compareChars = Character.compare(slash, '/');
        if (compareChars != 0) {

            System.out.println("ERROR!  INVALID FORMAT! \nThe deadline function is used in the following format:");
            System.out.println("deadline your_item_name /(due date or time)\n");
            return false;
        }

        if (words.length < 3) {
            System.out.println("ERROR! Deadline name is empty");
            return false;
        }

        return true;


    }

    private static boolean checkEventInput(String command) {
        command = command.toLowerCase();
        String keyword = command.substring(0, 5);

        int numberOfErrors = 0;
        if (!keyword.equals("event")) {
            System.out.println("The keyword 'event' should be the first word");
            return false;
        }

        String[] words = command.split(" ");
        char fromSlash = words[words.length - 2].charAt(0);
        char toSlash = words[words.length - 1].charAt(0);
        int compareFromSlash = Character.compare(fromSlash, '/');
        int compareToSlash = Character.compare(toSlash, '/');
        if (compareFromSlash != 0 || compareToSlash != 0) {

            System.out.println("ERROR! INVALID FORMAT! \n" + "The event function is used in the following format:");
            System.out.println("event your_item_name /(start time) /(end time) \n");
            return false;
        }

        if (words.length<4){
            System.out.println("ERROR! Event name is empty");
            return false;
        }


        return true;


    }

    private static boolean checkUnmarkInput(String command, int numberOfTasks) {
        command = command.toLowerCase();
        String[] words = command.split(" ");

        int numberOfErrors = 0;
        if (words.length > 2) {
            System.out.println("The unmark function is used in the following format:");
            System.out.println("Unmark 'task number'");
            numberOfErrors++;
        }
        if (!Objects.equals(words[0], "unmark")) {
            System.out.println("The keyword 'unmark' should be the first word");
            numberOfErrors++;
        }

        int taskNumber = Integer.parseInt(words[1]);

        if (taskNumber > numberOfTasks || taskNumber < 1) {
            System.out.println("The task number is invalid!");
            numberOfErrors++;
        }

        if (numberOfErrors > 0) {
            System.out.println("Please input again in the correct format!");
            return false;
        }
        return true;


    }

    private static boolean checkMarkInput(String command, int numberOfTasks) {
        command = command.toLowerCase();
        String[] words = command.split(" ");

        int numberOfErrors = 0;
        if (words.length > 2) {
            System.out.println("The mark function is used in the following format:");
            System.out.println("mark 'task number'");
            numberOfErrors++;
        }
        if (!Objects.equals(words[0], "mark")) {
            System.out.println("The keyword 'mark' should be the first word");
            numberOfErrors++;
        }

        int taskNumber = Integer.parseInt(words[1]);

        if (taskNumber > numberOfTasks || taskNumber < 1) {
            System.out.println("The task number is invalid!");
            numberOfErrors++;
        }

        if (numberOfErrors > 0) {
            System.out.println("Please input again in the correct format!");
            return false;
        }
        return true;


    }


    private static void printDuplicateKeywordErrorMessage() {
        System.out.println("ERROR! Multiple keywords have been detected!");
        System.out.println("The keywords are:");
        printAllKeywords();
        System.out.println("Make sure your input only contains 1 keyword!");

    }

    private static void printAllKeywords() {
        String[] keywords = Constants.listOfKeywords;
        int len = keywords.length;
        int i = 0;
        while (i < len) {
            System.out.println((i + 1) + ": " + keywords[i]);
            i++;
        }

    }

    private static boolean checkForValidInput(String input) {
        input = input.toLowerCase();
        String[] contents = input.split(" ");
        int i = 0;
        int len = contents.length;
        boolean isValidFirstWord = checkFirstKeyword(contents[0]);

        if(!isValidFirstWord){
            System.out.println("ERROR! The first word is not a valid keyword!");
        }

        int numberOfKeywords = 0;
        while (i < len) {
            if (contents[i].equals("list") || contents[i].equals("mark") || contents[i].equals("unmark") || contents[i].equals("deadline") || contents[i].equals("event") || contents[i].equals("todo")) {
                numberOfKeywords++;
            }
            i++;
        }

        if (numberOfKeywords > 1) {
            printDuplicateKeywordErrorMessage();
        }

        if (!isValidFirstWord|| numberOfKeywords>1){
            return false;
        }
        return true;
    }

    private static boolean checkFirstKeyword(String content) {
        String[] keywords = Constants.listOfKeywords;
        int len = keywords.length;
        int i = 0;
        while (i<len){
            if (Objects.equals(content, keywords[i])){
                return true;
            }
            i++;
        }
        return false;

    }

    private static void greetUser() {
        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___|
                """;


        System.out.println(logo);
        printHorizontalBar();
        System.out.println("Hello! I'm Duke! \n");
        printHorizontalBar();
    }


    private static void createTask(String command, int numberOfTasks) {
        command = command.substring(5);
        Task task = new Task(command);
        task.isCompleted = false;
        task.taskName = command;
        System.out.println("added:  " + task.taskName);
        tasks[numberOfTasks] = task;
    }

    private static void createEvent(String command, int numberOfTasks) {
        String content = command.substring(5);
        String[] contents = content.split("/");
        String title = contents[0];
        String startTime = contents[1];
        String endTime = contents[2];
        Event newEvent = new Event(title);
        newEvent.setStartTime(startTime);
        newEvent.setEndTime(endTime);
        tasks[numberOfTasks] = newEvent;
        newEvent.newEventResponse();
    }

    private static void createDeadline(String command, int numberOfTasks) {
        String content = command.substring(8);
        String[] contents = content.split("/");
        String title = contents[0];
        String dueDate = contents[1];
        Deadline newDeadline = new Deadline(title);
        newDeadline.setEndTime(dueDate);
        tasks[numberOfTasks] = newDeadline;
        newDeadline.newDeadlineResponse();
    }

    private static void markTask(String command, int numberOfTasks) {
        String[] result = command.split(" ");
        int taskNum = Integer.parseInt(result[1]);
        if (taskNum > numberOfTasks || taskNum <= 0) {
            System.out.println("Error! No such task exists!");
        } else {
            Task target = tasks[taskNum - 1];
            target.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(target.getStatusIcon() + "   " + target.taskName);
        }
    }

    private static void unmarkTask(String command, int numberOfTasks) {
        String[] result = command.split(" ");
        int taskNum = Integer.parseInt(result[1]);
        if (taskNum > numberOfTasks || taskNum <= 0) {
            System.out.println("Error! No such task exists!");
        } else {
            Task target = tasks[taskNum - 1];
            target.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(target.getStatusIcon() + "   " + target.taskName);
        }
    }

    private static void printTaskList() {
        int i = 1;
        for (Task a : Duke.tasks) {
            if (a != null) {
                System.out.print(i);
                a.printTask();
                i++;
            }
        }


    }


    private static void printHorizontalBar() {
        String horizontalBar = "---------------------------------------------------\n";
        System.out.println(horizontalBar);
    }

    private static String readTask() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;

    }

    private static String checkForAdditionalTask() {

        System.out.println("Do you have any other task for me?  ");
        return readTask();


    }


}
