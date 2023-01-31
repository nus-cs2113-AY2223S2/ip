import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        showWelcomeMessage();

        String userInput;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            userInput = getInput(in);
            boolean hasAsterisk = userInput.startsWith("*");

            if (hasAsterisk) {
                addTask(userInput.substring(1), tasks);
                continue;
            }

            String[] seperatedWords = userInput.split(" ");
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                createHorizontalLine();
                break;
            } else if (userInput.equals("help")) {
                showHelpMessage();
            } else if (userInput.equals("list")) {
                printItems(tasks);
            } else if (userInput.startsWith("mark ") || userInput.startsWith("unmark ")) {
                if (seperatedWords.length != 2) {
                    System.out.println("Please give your command in the following format\n" + seperatedWords[0] + ": Number");
                } else {
                    changeTaskStatus(tasks, seperatedWords);
                }
            } else {
                addTask(userInput, tasks);
            }
        }
    }

    private static void changeTaskStatus(ArrayList<Task> tasks, String[] seperatedWords) {
        try {
            int lastWordInInteger = Integer.parseInt(seperatedWords[1]);
            if (lastWordInInteger > tasks.size()) {
                System.out.println("The task number is bigger than the number of tasks");
            } else {
                boolean doesCommandContainsUnmark = seperatedWords[0].equals("unmark");
                if (doesCommandContainsUnmark) {
                    tasks.get(lastWordInInteger - 1).unMark();
                    System.out.println("Ok! I've marked this task as not done yet:");
                } else {
                    tasks.get(lastWordInInteger - 1).mark();
                    System.out.println("Nice! I've marked this task as done:");
                }
                System.out.println(lastWordInInteger + ". " + "[" + tasks.get(lastWordInInteger - 1).getMarkingStatus()
                        + "]" + tasks.get(lastWordInInteger - 1).getContent());
            }
        } catch (Exception error) {
            System.out.println("Please give your command in the following format\n" + seperatedWords[0] + ": Number");
        }
    }

    private static void showHelpMessage() {
        System.out.println("This is the list of our commands\n");
        System.out.println("help: to view the instructions for all commands\n");
        System.out.println("bye: to end the program\n");
        System.out.println("list: to view the list of tasks\n");
        System.out.println("mark: to mark a task as done");
        System.out.println("Format: mark {Number}\n");
        System.out.println("unmark: to unmark a task as not done yet");
        System.out.println("Format: unmark {Number}");
    }

    private static void showWelcomeMessage() {
        createHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("Please type 'help' if you want to see the instructions");
        System.out.println("What can I do for you?");
    }

    private static void createHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static String getInput(Scanner in) {
        String userInput;
        createHorizontalLine();
        System.out.println();
        userInput = in.nextLine();
        createHorizontalLine();
        return userInput;
    }

    private static void addTask(String userInput, ArrayList<Task> tasks) {
        Task task = new Task(userInput);
        tasks.add(task);
        System.out.println("added: " + userInput);
    }


    private static void printItems(ArrayList<Task> container) {
        for (int i = 0; i < container.size(); ++i) {
            if (container.get(i) == null) {
                break;
            } else {
                System.out.println((i + 1) + ". " + "[" + container.get(i).getMarkingStatus() + "]" + container.get(i).getContent());
            }
        }
    }

}

