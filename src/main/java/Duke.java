import java.util.Scanner;
public class Duke {
    /**
     * Returns false if the second string in the input cannot be converted into a numeric type, true if it
     * can be converted.
     *
     * @param strNum The second string found in the user input.
     * @return Boolean for whether string can be converted into a numeric type (int, double etc.).
     */
    //@@author ngkaiwen123-reused
    //Reused from https://www.baeldung.com/java-check-string-number
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    //@@author

    /**
     * Prints a long string of lines ("___") to separate the outputs.
     */
    public static void lineSeparator() {
        System.out.println("___________________________________________________________________________________");
    }

    /**
     * Executes the "Duke" program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        //Initialisation
        String line = "start";
        Task[] list = new Task[100];
        int listSize = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        //Get commands.
        while (!line.equals("bye")){
            line = input.nextLine();
            String[] command = line.split(" ");
            switch(command[0]) {
            //If the word "list" is the first word in the line of strings.
            case "list":
                if (command.length == 1) {
                    //If user wants to view the list of tasks.
                    lineSeparator();
                    for (int increment = 0; increment < listSize; increment += 1) {
                        System.out.println((increment + 1) + ". " + list[increment].toString());
                    }
                    lineSeparator();
                } else {
                    //If user typed "list" as the first part of a sequence of strings.
                    lineSeparator();
                    System.out.println("Invalid command. Please try again.");
                    lineSeparator();
                }
                break;

            //If the word "mark" is the first word in the line of strings.
            case "mark":
                if (command.length == 2 && isNumeric(command[1])) {
                    //If user wants to mark task as done.
                    int taskNumber = Integer.parseInt(command[1]);
                    if (taskNumber > 0 && taskNumber <= listSize) {
                        //If valid task number is given.
                        list[taskNumber - 1].markDone();
                        lineSeparator();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + list[taskNumber - 1].toString());
                        lineSeparator();
                    } else {
                        //If task number given exceeds total tasks.
                        lineSeparator();
                        System.out.println("Please key in a valid task number!");
                        lineSeparator();
                    }
                } else {
                    //If user types non-integer inputs after "unmark".
                    lineSeparator();
                    System.out.println("Please key in a valid task number!");
                    lineSeparator();
                }
                break;

            //If the word "unmark" is the first word in the line of strings.
            case "unmark":
                if (command.length == 2 && isNumeric(command[1])) {
                    //If user wants to unmark task as done.
                    int taskNumber = Integer.parseInt(command[1]);
                    if (taskNumber > 0 && taskNumber <= listSize) {
                        //If valid task number is given.
                        list[taskNumber - 1].unmarkDone();
                        lineSeparator();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + list[taskNumber - 1].toString());
                        lineSeparator();
                    } else {
                        //If task number given exceeds total tasks in the list.
                        lineSeparator();
                        System.out.println("Please key in a valid task number!");
                        lineSeparator();
                    }
                } else {
                    //If user types non-integer inputs after "unmark".
                    lineSeparator();
                    System.out.println("Please key in a valid task number!");
                    lineSeparator();
                }
                break;

            //If user wants to create a new "todo" task.
            case "todo":
                //Create new entry by detecting the start of the first spacing after the "todo" string.
                list[listSize] = new ToDo(line.substring(line.indexOf(' ') + 1));
                lineSeparator();
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + list[listSize].toString());
                listSize += 1;
                System.out.println("Now you have " + listSize + " tasks in the list.");
                lineSeparator();
                break;

            //If user wants to create a new "deadline" task.
            case "deadline":
                int byIndex = line.indexOf("/by");
                String deadline = line.substring(line.indexOf("deadline") + 9, byIndex - 1);
                list[listSize] = new Deadline(deadline , line.substring(byIndex + 4));
                lineSeparator();
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + list[listSize].toString());
                listSize += 1;
                System.out.println("Now you have " + listSize + " tasks in the list.");
                lineSeparator();
                break;

            //If user wants to create a new "event" task.
            case "event":
                int fromIndex = line.indexOf("/from");
                int toIndex = line.indexOf("/to");
                String event;
                String startDate;
                String endDate;
                if (fromIndex < toIndex) {
                    //If user typed "/from" before "/to".
                    event = line.substring(line.indexOf("event") + 5, fromIndex - 1);
                    startDate = line.substring(fromIndex + 6, toIndex - 1);
                    endDate = line.substring(toIndex + 4);
                } else {
                    //If user typed "/to" before "/from".
                    event = line.substring(line.indexOf("event") + 5, toIndex - 1);
                    startDate = line.substring(toIndex + 4, fromIndex - 1);
                    endDate = line.substring(fromIndex + 6);
                }
                list[listSize] = new Event(event, startDate, endDate);
                lineSeparator();
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + list[listSize].toString());
                listSize += 1;
                System.out.println("Now you have " + listSize + " tasks in the list.");
                lineSeparator();
                break;

            //To terminate program, if the word "bye" is the first word in the line of strings.
            case "bye":
                break;

            //If first string is not one of the above cases, ask user to retype.
            default:
                lineSeparator();
                System.out.println("Invalid command. Please try again.");
                lineSeparator();
                break;
            }
        }
        lineSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        lineSeparator();
    }
}
