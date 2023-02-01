import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String MARK_INSTRUCTION = "Please give your command in the following format";
    public static final String INVALID_INPUT = "This is an invalid input";
    public static final String HorizontalLine = "____________________________________________________________";
    public static final String HELLO_IM_DUKE = "Hello! I'm Duke";
    public static final String KEYWORD_TO_SEE_THE_INSTRUCTIONS = "Please type 'help' if you want to see the instructions";
    public static final String HELP_USAGE = "help: to view the instructions for all commands\n";
    public static final String BYE_USAGE = "bye: to end the program\n";
    public static final String LIST_USAGE = "list: to view the list of tasks\n";
    public static final String MARK_USAGE = "mark: to mark a task as done";
    public static final String MARK_FORMAT = "Format: mark {Number}\n";
    public static final String UNMARK_USAGE = "unmark: to unmark a task as not done yet";
    public static final String UNMARK_FORMAT = "Format: unmark {Number}\n";
    public static final String TODO_USAGE = "todo: to add a todo task in your list";
    public static final String TODO_FORMAT = "Format: todo {your task}\n";
    public static final String DEADLINE_USAGE = "deadline: to add a deadline task in your list";
    public static final String DEADLINE_FORMAT = "Format: deadline {your task} /by {deadline date}\n";
    public static final String EVENT_USAGE = "event: to add an event task in your list";
    public static final String EVENT_FORMAT = "Format: event {your task} /from {begin date} /to {end date}\n";
    public static final String ADDING_TASK = "Got it. I've added this task:";
    public static final String BIG_MARK_NUMBER = "The task number is bigger than the number of tasks";
    public static final String FINISH_UNMARKING_MESSAGE = "Ok! I've marked this task as not done yet:";
    public static final String FINISH_MARKING_MESSAGE = "Nice! I've marked this task as done:";
    public static final String ASKING_MESSAGE = "What can I do for you?";

    public static void main(String[] args) {
        showWelcomeMessage();

        String userInput;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            userInput = getInput(in);
            try {
                String[] seperatedKeywordAndContent = userInput.split(" ", 2);
                CommandKeywords keyword = CommandKeywords.valueOf(seperatedKeywordAndContent[0]);
                if (userInput.equals("bye")) {
                    System.out.println(BYE_MESSAGE);
                    createHorizontalLine();
                    break;
                } else if (userInput.equals("help")) {
                    showHelpMessage();
                } else if (userInput.equals("list")) {
                    printItems(tasks);
                } else if (seperatedKeywordAndContent[0].equals("todo")) {
                    if (handleException(seperatedKeywordAndContent,1)) {
                        continue;
                    }
                    addTodoTask(seperatedKeywordAndContent[1], tasks);
                } else if (seperatedKeywordAndContent[0].equals("deadline")) {
                    if (handleException(seperatedKeywordAndContent,1)) {
                        continue;
                    }
                    addDeadlineTask(seperatedKeywordAndContent[1], tasks);
                } else if (seperatedKeywordAndContent[0].equals("event")) {
                    if (handleException(seperatedKeywordAndContent,1)) {
                        continue;
                    }
                    addEventTask(seperatedKeywordAndContent[1], tasks);
                } else if (seperatedKeywordAndContent[0].equals("mark") || seperatedKeywordAndContent[0].equals("unmark")) {
                    if (userInput.split(" ").length != 2) {
                        System.out.println(MARK_INSTRUCTION + "\n"
                                + seperatedKeywordAndContent[0] + ": Number");
                    } else {
                        changeTaskStatus(tasks, seperatedKeywordAndContent);
                    }
                }
            } catch (IllegalArgumentException error) {
                System.out.println(INVALID_INPUT);
            }
        }
    }

    private static void createHorizontalLine() {
        System.out.println(HorizontalLine);
    }

    private static String getInput(Scanner in) {
        String userInput;
        createHorizontalLine();
        System.out.println();
        userInput = in.nextLine();
        createHorizontalLine();
        return userInput;
    }

    private static void showWelcomeMessage() {
        createHorizontalLine();
        System.out.println(HELLO_IM_DUKE);
        System.out.println(KEYWORD_TO_SEE_THE_INSTRUCTIONS);
        System.out.println(ASKING_MESSAGE);
    }

    private static void showHelpMessage() {
        System.out.println("This is the list of our commands\n");
        System.out.println(HELP_USAGE);
        System.out.println(BYE_USAGE);
        System.out.println(LIST_USAGE);
        System.out.println(MARK_USAGE);
        System.out.println(MARK_FORMAT);
        System.out.println(UNMARK_USAGE);
        System.out.println(UNMARK_FORMAT);
        System.out.println(TODO_USAGE);
        System.out.println(TODO_FORMAT);
        System.out.println(DEADLINE_USAGE);
        System.out.println(DEADLINE_FORMAT);
        System.out.println(EVENT_USAGE);
        System.out.println(EVENT_FORMAT);
    }

    private static boolean handleException(String[] stringArray, int index) {
        try {
            String test = stringArray[index];
            return false;
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println(INVALID_INPUT);
            return true;
        }
    }

    private static void addTodoTask(String content, ArrayList<Task> tasks) {
        Task task = new ToDo(content);
        tasks.add(task);
        System.out.println(ADDING_TASK);
        System.out.println("[T][] " + content);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadlineTask(String content, ArrayList<Task> tasks) {
        String[] seperatedWordsInContent = content.split(" /");
        if (handleException(seperatedWordsInContent, 1)) {
            return;
        }
        if (seperatedWordsInContent[1].startsWith("by ")) {
            String date = seperatedWordsInContent[1].split(" ", 2)[1];
            String taskName = seperatedWordsInContent[0];
            Task task = new Deadline(taskName,date);
            tasks.add(task);
            System.out.println(ADDING_TASK);
            System.out.println("[D][] " + taskName + " (by: " + date + ")");
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println(INVALID_INPUT);
        }
    }

    private static void addEventTask(String content, ArrayList<Task> tasks) {
        String[] seperatedWordsInContent = content.split(" /");
        if (handleException(seperatedWordsInContent,2)) {
            return;
        }
        String beginDate = seperatedWordsInContent[1].split(" ", 2)[1];
        if (handleException(seperatedWordsInContent[2].split(" ", 2),1)) {
            return;
        }
        String endDate = seperatedWordsInContent[2].split(" ", 2)[1];

        if (seperatedWordsInContent[1].startsWith("from ") && seperatedWordsInContent[2].startsWith("to ")) {
            String taskName = seperatedWordsInContent[0];
            Task task = new Event(taskName,beginDate,endDate);
            tasks.add(task);
            System.out.println(ADDING_TASK);
            System.out.println("[E][] " + taskName + " (from: " + beginDate + " to: " + endDate + ")");
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println(INVALID_INPUT);
        }
    }

    private static void changeTaskStatus(ArrayList<Task> tasks, String[] seperatedWords) {
        try {
            int lastWordInInteger = Integer.parseInt(seperatedWords[1]);
            if (lastWordInInteger > tasks.size()) {
                System.out.println(BIG_MARK_NUMBER);
            } else {
                boolean doesCommandContainsUnmark = seperatedWords[0].equals("unmark");
                if (doesCommandContainsUnmark) {
                    tasks.get(lastWordInInteger - 1).unMark();
                    System.out.println(FINISH_UNMARKING_MESSAGE);
                } else {
                    tasks.get(lastWordInInteger - 1).mark();
                    System.out.println(FINISH_MARKING_MESSAGE);
                }
                System.out.println(lastWordInInteger + ". " + "[" + tasks.get(lastWordInInteger - 1).getMarkingStatus()
                        + "]" + tasks.get(lastWordInInteger - 1).getContent());
            }
        } catch (Exception error) {
            System.out.println(MARK_INSTRUCTION + "\n" + seperatedWords[0] + ": Number");
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

