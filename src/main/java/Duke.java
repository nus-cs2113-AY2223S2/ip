import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void createHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printItems(ArrayList<Task> container) {
        for (int i = 0; i < container.size(); ++i) {
            if (container.get(i) == null) {
                break;
            } else {
                System.out.println((i + 1) + ". " + "[" + container.get(i).getMarkingStatus() + "]" + container.get(i).getContent());
            }
        }
    }

    public static void changeTaskStatus(String command, ArrayList<Task> container) {
        try {
            int lastCharacterInInteger = Integer.parseInt(command.substring(command.length() - 1));
            if (lastCharacterInInteger > container.size()) {
                System.out.println("The task number is bigger than the number of tasks");
            } else {
                boolean doesCommandContainsUnmark = command.contains("unmark ");
                if (doesCommandContainsUnmark) {
                    container.get(lastCharacterInInteger - 1).unMark();
                    System.out.println("Ok! I've marked this task as not done yet:");
                } else {
                    container.get(lastCharacterInInteger - 1).mark();
                    System.out.println("Nice! I've marked this task as done:");
                }
                System.out.println(lastCharacterInInteger + ". " + "[" + container.get(lastCharacterInInteger - 1).getMarkingStatus()
                        + "]" + container.get(lastCharacterInInteger - 1).getContent());
            }
        } catch (NumberFormatException error) {
            System.out.println("Please give your command in the following format\n" + command + ": Number");
        }
    }

    public static void main(String[] args) {
        createHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("If you do not want to use command keywords, please add * at the start of the input");
        System.out.println("What can I do for you?");

        String userInput;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        while (true) {
            createHorizontalLine();
            System.out.println();
            userInput = in.nextLine();
            createHorizontalLine();
            int firstSpace = userInput.indexOf(" ");
            boolean hasAsterisk = (userInput.startsWith("*")) ? true : false;

            if (hasAsterisk) {
                Task task = new Task(userInput.substring(1));
                tasks.add(task);
                System.out.println("added: " + userInput.substring(1));
            } else {
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    createHorizontalLine();
                    break;
                } else if (userInput.equals("list")) {
                    printItems(tasks);
                } else if (userInput.startsWith("mark ") || userInput.startsWith("unmark ")) {
                    changeTaskStatus(userInput, tasks);
                } else {
                    Task task = new Task(userInput);
                    tasks.add(task);
                    System.out.println("added: " + userInput);
                }
            }
        }
    }
}