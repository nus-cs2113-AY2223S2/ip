import java.util.Scanner;

public class Duke {

    /**
     * Prints out the greeting message to the user
     */
    public static void greetUser() {
        String greetMessage = "Hello! I'm Duke\n"
                + "Send me a list of things to remember!\n"
                + "Type <bye> to exit";
        System.out.println(greetMessage);
    }

    /**
     * Prints out the exit message to the user
     */
    public static void exitProgram() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }

    /**
     * Greets user and responds to user commands from the command line
     *
     * @param args None taken
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String logo = " .----------------.  .----------------.  .----------------.  .----------------. \n" +
                "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "| |  ________    | || | _____  _____ | || |  ___  ____   | || |  _________   | |\n" +
                "| | |_   ___ `.  | || ||_   _||_   _|| || | |_  ||_  _|  | || | |_   ___  |  | |\n" +
                "| |   | |   `. \\ | || |  | |    | |  | || |   | |_/ /    | || |   | |_  \\_|  | |\n" +
                "| |   | |    | | | || |  | '    ' |  | || |   |  __'.    | || |   |  _|  _   | |\n" +
                "| |  _| |___.' / | || |   \\ `--' /   | || |  _| |  \\ \\_  | || |  _| |___/ |  | |\n" +
                "| | |________.'  | || |    `.__.'    | || | |____||____| | || | |_________|  | |\n" +
                "| |              | || |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------' ";
        System.out.println("Hello from\n" + logo);
        greetUser();
        String userInput = "";
        TaskManager myList = new TaskManager();
        while (!userInput.equals("bye")) {
            userInput = input.nextLine();
            String[] userArrayInput = userInput.split(" ");
            if (userInput.equals("bye")) {
                break; //end the program
            }
            if (userArrayInput.length == 0 || userInput.equals("")) {
                continue; //input is invalid, skip
            }
            if (userArrayInput[0].equals("list")) {
                myList.printList();
            } else if (userArrayInput[0].equals("mark")) {
                int taskIndex = Integer.parseInt(userArrayInput[1]) - 1;
                myList.markAsDone(taskIndex);
            } else if (userArrayInput[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(userArrayInput[1]) - 1;
                myList.markAsUndone(taskIndex);
            } else {
                myList.addToList(userInput);
            }
        }
        exitProgram();
    }
}
