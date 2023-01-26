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
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    //@@author

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
                    //If user just wants to view the list of tasks.
                    for (int x = 0; x < listSize; x += 1) {
                        System.out.println((x + 1) + ".[" + list[x].getStatusIcon() + "] " + list[x].getDescription());
                    }
                } else {
                    //If user wants to include the word "list" as part of the task description.
                    list[listSize] = new Task(line);
                    listSize += 1;
                    System.out.println("added: " + line);
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
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("[X] " + list[taskNumber - 1].getDescription());
                    } else {
                        //If task number given exceeds total tasks.
                        System.out.println("Please key in a valid task number!");
                    }
                } else {
                    //If user wants to include "mark" as part of a new task description.
                    list[listSize] = new Task(line);
                    listSize += 1;
                    System.out.println("added: " + line);
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
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("[ ] " + list[taskNumber - 1].getDescription());
                    } else {
                        //If task number given exceeds total tasks in the list.
                        System.out.println("Please key in a valid task number!");
                    }
                } else {
                    //If user wants to include "unmark" as part of a new task description.
                    list[listSize] = new Task(line);
                    listSize += 1;
                    System.out.println("added: " + line);
                }
                break;

            //To terminate program, if the word "bye" is the first word in the line of strings.
            case "bye":
                break;

            //Add normal user input as part of new task description.
            default:
                list[listSize] = new Task(line);
                listSize += 1;
                System.out.println("added: " + line);
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
