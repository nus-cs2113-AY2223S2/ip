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
            try {
                String[] seperatedWords = userInput.split(" ", 2);
                CommandKeywords keyword = CommandKeywords.valueOf(seperatedWords[0]);
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    createHorizontalLine();
                    break;
                } else if (userInput.equals("help")) {
                    showHelpMessage();
                } else if (userInput.equals("list")) {
                    printItems(tasks);
                } else if (seperatedWords[0].equals("todo")) {
                    if (handleException(seperatedWords,1)) {
                        continue;
                    }
                    addTodoTask(seperatedWords[1], tasks);
                } else if (seperatedWords[0].equals("deadline")) {
                    if (handleException(seperatedWords,1)) {
                        continue;
                    }
                    addDeadlineTask(seperatedWords[1], tasks);
                } else if (seperatedWords[0].equals("event")) {
                    if (handleException(seperatedWords,1)) {
                        continue;
                    }
                    addEventTask(seperatedWords[1], tasks);
                } else if (seperatedWords[0].equals("mark") || seperatedWords[0].equals("unmark")) {
                    if (userInput.split(" ").length != 2) {
                        System.out.println("Please give your command in the following format\n" + seperatedWords[0] + ": Number");
                    } else {
                        changeTaskStatus(tasks, seperatedWords);
                    }
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("This is an invalid input");
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

    private static boolean handleException(String[] stringArray, int index) {
        try {
            String a = stringArray[index];
            return false;
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("This is an invalid input");
            return true;
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
        System.out.println("Format: unmark {Number}\n");
        System.out.println("todo: to add a todo task in your list");
        System.out.println("Format: todo {your task}\n");
        System.out.println("deadline: to add a deadline task in your list");
        System.out.println("Format: deadline {your task} /by {deadline date}\n");
        System.out.println("event: to add an event task in your list");
        System.out.println("Format: event {your task} /from {begin date} /to {end date}\n");
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

    private static void addTodoTask(String userInput, ArrayList<Task> tasks) {
        Task task = new ToDo(userInput);
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("[T][] " + userInput);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static int addDeadlineTask(String userInput, ArrayList<Task> tasks) {
        String[] seperatedWords = userInput.split(" /");
        if (handleException(seperatedWords, 1)) {
            return 0;
        }
        if (seperatedWords[1].startsWith("by ")) {
            String date = seperatedWords[1].split(" ", 2)[1];
            String taskContent = seperatedWords[0];
            Task task = new Deadline(taskContent,date);
            tasks.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("[D][] " + taskContent + " (by: " + date + ")");
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            return 0;
        } else {
            System.out.println("This is an invalid input");
            return 0;
        }
    }

    private static int addEventTask(String userInput, ArrayList<Task> tasks) {
        String[] seperatedWords = userInput.split(" /");
        if (handleException(seperatedWords,2)) {
            return 0;
        }
        String beginDate = seperatedWords[1].split(" ", 2)[1];
        if (handleException(seperatedWords[2].split(" ", 2),1)) {
            return 0;
        }
        String endDate = seperatedWords[2].split(" ", 2)[1];

        if (seperatedWords[1].startsWith("from ") && seperatedWords[2].startsWith("to ")) {
            String taskContent = seperatedWords[0];
            Task task = new Event(taskContent,beginDate,endDate);
            tasks.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("[E][] " + taskContent + " (from: " + beginDate + " to: " + endDate + ")");
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            return 1;
        } else {
            System.out.println("This is an invalid input");
            return 1;
        }
    }

    private static void printItems(ArrayList<Task> container) {
        for (int i = 0; i < container.size(); ++i) {
            Task item = container.get(i);
            if (item == null) {
                break;
            } else if (item.getClassSymbol().equals("T")) {
                System.out.println((i + 1) + ". " + "[" + item.getClassSymbol() + "]" + "["
                        + item.getMarkingStatus() + "] " + item.getContent());
            } else if (item.getClassSymbol().equals("D")) {
                System.out.println((i + 1) + ". " + "[" + item.getClassSymbol() + "]" + "["
                        + item.getMarkingStatus() + "] " + item.getContent() + "(by: " + item.getDate() + ")");
            } else if (item.getClassSymbol().equals("E")) {
                System.out.println((i + 1) + ". " + "[" + item.getClassSymbol() + "]" + "["
                        + item.getMarkingStatus() + "] " + item.getContent() + "(from: " + item.getBeginDate()
                        + " to: " + item.getEndDate() + ")");
            }
        }
    }

}

