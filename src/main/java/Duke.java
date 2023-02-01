import java.util.Scanner;
public class Duke {
    public static Scanner input = new Scanner(System.in);
    public static Task[] list= new Task[100];
    public static String[] words = new String[10];
    public static String[] phrases = new String[10];

    public static int currentTask = 0;
    public static String lineBreaker = "____________________________________________________________\n";
    public static String errorMessage = lineBreaker + "Invalid input. Please try again!\n" + lineBreaker;
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static String greeting = lineBreaker +
            " Hi! I'm Duke\n" +
            " What can I do for you?\n" +
            lineBreaker;
    public static String farewellMessage =
            " Bye. Hope to see you again soon!\n" + lineBreaker;

    public boolean shouldExit = false;
    public void setShouldExit () {
        this.shouldExit = true;
    }
    public static void printList(int currentTask){
        int currentPrintedTask = 0;
        int tempPos = currentTask;
        if (tempPos == 0) {
            System.out.println(lineBreaker);
            System.out.println("No Task!");
        } else {
            System.out.println(lineBreaker);
            while (tempPos > 0) {
                System.out.println(currentPrintedTask + 1 + ". " + list[currentPrintedTask].toString());
                currentPrintedTask++;
                tempPos--;
            }
        }
        System.out.println(lineBreaker);
    }

    public void handleError() {
        System.out.println(errorMessage);
    }

    public void handleRequests(String userInput) {
        if (userInput.startsWith("mark")) {
            words = userInput.split(" ");
            int posOfTask = Integer.parseInt(words[1]) - 1;
            if (!(posOfTask >= 0 && posOfTask <= currentTask)) {
                handleError();
            } else {
                list[posOfTask].markAsDone();
            }
        } else if (userInput.startsWith("unmark")) {
            words = userInput.split(" ");
            int posOfTask = Integer.parseInt(words[1]) - 1;
            if (posOfTask < 0 || posOfTask > currentTask) {
                handleError();
            } else {
                list[posOfTask].markAsUndone();
            }
        } else if (userInput.equals("list")) {
            System.out.println("Here is your list!");
            printList(currentTask);
        } else if (userInput.equals("bye")) {
            System.out.println(farewellMessage);
            setShouldExit();
        } else if (userInput.startsWith("todo")) {
            list[currentTask] = new ToDos(userInput);
            System.out.println(lineBreaker + "Got it. I've added this task:\n" + "  "
                    + list[currentTask].toString()
                    + System.lineSeparator());
            currentTask++;
            System.out.println("Now you have " + currentTask + "tasks in the list.");
        } else if (userInput.startsWith("deadline")) {
            phrases = userInput.split("/by ");
            if (phrases.length < 2) {
                handleError();
            } else {
                list[currentTask] = new Deadline(phrases[0], phrases[1]);
                System.out.println(lineBreaker + "Got it. I've added this task:\n" + "  "
                        + list[currentTask].toString()
                        + System.lineSeparator());
                currentTask++;
                System.out.println("Now you have " + currentTask + " tasks in the list.");
                System.out.println(lineBreaker);
            }
        } else if (userInput.startsWith("event")) {
            phrases = userInput.split("/");
            if (phrases.length < 3) {
                handleError();
            } else {
                list[currentTask] = new Event(phrases[0], phrases[1], phrases[2]);
                System.out.println(lineBreaker + "Got it. I've added this task:\n" + "  "
                        + list[currentTask].toString()
                        + System.lineSeparator());
                currentTask++;
                System.out.println("Now you have " + currentTask + " tasks in the list.");
                System.out.println(lineBreaker);
            }
        } else {
            System.out.println(lineBreaker);
            list[currentTask] = new Task(userInput);
            System.out.println("Added! " + System.lineSeparator() + list[currentTask].toString()
                    + System.lineSeparator());
            currentTask++;
            System.out.println("Now you have " + currentTask + " tasks in the list.");
            System.out.println(lineBreaker);
        }
    }
    public static void main(String[] args) {
        Duke obj = new Duke();
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);
        while (!obj.shouldExit) {
            String userInput = input.nextLine();
            obj.handleRequests(userInput);
        }
    }
}
