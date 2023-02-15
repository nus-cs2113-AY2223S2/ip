package duke;

import duke.exception.EmptyDescription;
import duke.keycommand.Deadline;
import duke.keycommand.Event;
import duke.keycommand.ToDo;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String MARK_INSTRUCTION = "Please give your command in the following format";
    private static final String HorizontalLine = "____________________________________________________________";
    private static final String HELLO_IM_DUKE = "Hello! I'm Duke";
    private static final String KEYWORD_TO_SEE_THE_INSTRUCTIONS = "Please type 'help' if you want to see the instructions";
    private static final String HELP_USAGE = "help: to view the instructions for all commands\n";
    private static final String BYE_USAGE = "bye: to end the program\n";
    private static final String LIST_USAGE = "list: to view the list of tasks\n";
    private static final String MARK_USAGE = "mark: to mark a task as done";
    private static final String MARK_FORMAT = "Format: mark {Number}\n";
    private static final String UNMARK_USAGE = "unmark: to unmark a task as not done yet";
    private static final String UNMARK_FORMAT = "Format: unmark {Number}\n";
    private static final String TODO_USAGE = "todo: to add a todo task in your list";
    private static final String TODO_FORMAT = "Format: todo {your task}\n";
    private static final String DEADLINE_USAGE = "deadline: to add a deadline task in your list";
    private static final String DEADLINE_FORMAT = "Format: deadline {your task} /by {deadline date}\n";
    private static final String EVENT_USAGE = "event: to add an event task in your list";
    private static final String EVENT_FORMAT = "Format: event {your task} /from {begin date} /to {end date}\n";
    private static final String ADDING_TASK = "Got it. I've added this task:";
    private static final String BIG_NUMBER = "The task number is bigger than the number of tasks";
    private static final String FINISH_UNMARKING_MESSAGE = "Ok! I've marked this task as not done yet:";
    private static final String FINISH_MARKING_MESSAGE = "Nice! I've marked this task as done:";
    private static final String ASKING_MESSAGE = "What can I do for you?";
    public static final String INCORRECT_KEYWORD = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String EMPTY_TODO_DESCRIPTION = "OOPS!!! The description of a todo cannot be empty.";
    public static final String EMPTY_DEADLINE_DESCRIPTION = "OOPS!!! The description of a deadline cannot be empty.";
    public static final String EMPTY_EVENT_DESCRIPTION = "OOPS!!! The description of a event cannot be empty.";
    public static final String INVALID_INPUT = "This is an invalid input, please follow this input format\n";
    public static final String DEADLINE_INVALID_INPUT = INVALID_INPUT + DEADLINE_FORMAT;
    public static final String EVENT_INVALID_INPUT = INVALID_INPUT + EVENT_FORMAT;
    public static final ArrayList<String> KEYWORDS = new ArrayList<>(
            List.of("todo","deadline","event","list","bye","mark","unmark","help","delete")
    );
    public static final String MEANINGLESS_SENTENCE_AFTER_KEYWORD = "OPPS!!! The sentence after keyword has no meaning";

    public static void main(String[] args) {
        showWelcomeMessage();

        String userInput;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            userInput = getInput(in);
            String[] separatedKeyWordAndContent = userInput.split(" ", 2);
            String keyword = separatedKeyWordAndContent[0];
            if (!KEYWORDS.contains(keyword)) {
                System.out.println(INCORRECT_KEYWORD);
                continue;
            }
            if (userInput.equals("bye")) {
                System.out.println(BYE_MESSAGE);
                createHorizontalLine();
                break;
            } else if (userInput.equals("help")) {
                showHelpMessage();
            } else if (userInput.equals("list")) {
                printItems(tasks);
            } else if (keyword.equals("delete")) {
                if (userInput.split(" ").length != 2) {
                    System.out.println(MARK_INSTRUCTION + "\n"
                            + keyword + ": Number");
                } else {
                    deleteTask(tasks, separatedKeyWordAndContent);
                }
            } else if (keyword.equals("todo")) {
                if (doesIndexOutOfBoundsOccur(separatedKeyWordAndContent,1, EMPTY_TODO_DESCRIPTION)) {
                    continue;
                }
                try {
                    addTodoTask(separatedKeyWordAndContent[1], tasks);
                } catch(EmptyDescription e) {
                    System.out.println("OOPS!!! your task can not be empty");
                }
            } else if (keyword.equals("deadline")) {
                if (doesIndexOutOfBoundsOccur(separatedKeyWordAndContent,1, EMPTY_DEADLINE_DESCRIPTION)) {
                    continue;
                }
                try {
                    addDeadlineTask(separatedKeyWordAndContent[1], tasks);
                } catch(EmptyDescription e) {
                    System.out.println("OOPS!!! the deadline can not be empty");
                }
            } else if (keyword.equals("event")) {
                if (doesIndexOutOfBoundsOccur(separatedKeyWordAndContent,1, EMPTY_EVENT_DESCRIPTION)) {
                    continue;
                }
                addEventTask(separatedKeyWordAndContent[1], tasks);
            } else if (keyword.equals("mark") || keyword.equals("unmark")) {
                if (userInput.split(" ").length != 2) {
                    System.out.println(MARK_INSTRUCTION + "\n"
                            + keyword + ": Number");
                } else {
                    changeTaskStatus(tasks, separatedKeyWordAndContent);
                }
            } else {
                System.out.println(MEANINGLESS_SENTENCE_AFTER_KEYWORD);
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

    private static boolean doesIndexOutOfBoundsOccur(String[] stringArray, int index, String outputMessage) {
        try {
            String test = stringArray[index];
            return false;
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println(outputMessage);
            return true;
        }
    }

    private static void addTodoTask(String content, ArrayList<Task> tasks) throws EmptyDescription {
        if (content.trim().isEmpty()) {
            throw new EmptyDescription();
        }
        Task task = new ToDo(content);
        tasks.add(task);
        System.out.println(ADDING_TASK);
        System.out.println("[T][] " + content);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadlineTask(String content, ArrayList<Task> tasks) throws EmptyDescription{
        String[] seperatedWordsInContent = content.split(" /");
        if (doesIndexOutOfBoundsOccur(seperatedWordsInContent, 1, DEADLINE_INVALID_INPUT)) {
            return;
        }
        if (seperatedWordsInContent[1].startsWith("by ")) {
            String date = seperatedWordsInContent[1].split(" ", 2)[1];
            if (date.trim().isEmpty()) {
                throw new EmptyDescription();
            }
            String taskName = seperatedWordsInContent[0];
            Task task = new Deadline(taskName,date);
            tasks.add(task);
            System.out.println(ADDING_TASK);
            System.out.println("[D][] " + taskName + " (by: " + date + ")");
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println(DEADLINE_INVALID_INPUT);
        }
    }

    private static void addEventTask(String content, ArrayList<Task> tasks)  {
        String[] seperatedWordsInContent = content.split(" /");
        if (doesIndexOutOfBoundsOccur(seperatedWordsInContent,2,EVENT_INVALID_INPUT)) {
            return;
        }
        if (doesIndexOutOfBoundsOccur(seperatedWordsInContent[1].split(" ", 2),1,EVENT_INVALID_INPUT)) {
            return;
        }
        String beginDate = seperatedWordsInContent[1].split(" ", 2)[1];
        if (doesIndexOutOfBoundsOccur(seperatedWordsInContent[2].split(" ", 2),1,EVENT_INVALID_INPUT)) {
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
            System.out.println(EVENT_INVALID_INPUT);
        }
    }

    private static void changeTaskStatus(ArrayList<Task> tasks, String[] seperatedWords) {
        try {
            int lastWordInInteger = Integer.parseInt(seperatedWords[1]);
            if (lastWordInInteger > tasks.size()) {
                System.out.println(BIG_NUMBER);
            } else {
                boolean doesCommandContainsUnmark = seperatedWords[0].equals("unmark");
                if (doesCommandContainsUnmark) {
                    tasks.get(lastWordInInteger - 1).unMark();
                    System.out.println(FINISH_UNMARKING_MESSAGE);
                } else {
                    tasks.get(lastWordInInteger - 1).mark();
                    System.out.println(FINISH_MARKING_MESSAGE);
                }
                System.out.println(lastWordInInteger + ". " + tasks.get(lastWordInInteger - 1).getMarkingStatus()
                        + " " + tasks.get(lastWordInInteger - 1).getContent());
            }
        } catch (Exception error) {
            System.out.println(MARK_INSTRUCTION + "\n" + seperatedWords[0] + ": Number");
        }
    }
    
    private static void deleteTask(ArrayList<Task> tasks, String[] seperatedWords) {
        try {
            int taskNumber = Integer.parseInt(seperatedWords[1]);
            if (taskNumber > tasks.size()) {
                System.out.println(BIG_NUMBER);
            } else {
                Task item = tasks.get(taskNumber - 1);
                System.out.println("Noted. I've removed this task:");
                if (item.getClassSymbol().equals("[T]")) {
                    System.out.println(item.getClassSymbol() + item.getMarkingStatus() + " " + item.getContent());
                } else if (item.getClassSymbol().equals("[D]")) {
                    System.out.println(item.getClassSymbol() + item.getMarkingStatus() + " " + item.getContent()
                            + "(by: " + item.getDate() + ")");
                } else if (item.getClassSymbol().equals("[E]")) {
                    System.out.println(item.getClassSymbol() + item.getMarkingStatus() + " " + item.getContent()
                            + "(from: " + item.getBeginDate() + " to: " + item.getEndDate() + ")");
                }
                tasks.remove(taskNumber);
                System.out.println("Now you have " + tasks.size() + " in the list");
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
            } else if (item.getClassSymbol().equals("[T]")) {
                System.out.println((i + 1) + "." + item.getClassSymbol()
                        + item.getMarkingStatus() + " " + item.getContent());
            } else if (item.getClassSymbol().equals("[D]")) {
                System.out.println((i + 1) + "." + item.getClassSymbol()
                        + item.getMarkingStatus() + " " + item.getContent() + "(by: " + item.getDate() + ")");
            } else if (item.getClassSymbol().equals("[E]")) {
                System.out.println((i + 1) + "." + item.getClassSymbol()
                        + item.getMarkingStatus() + " " + item.getContent() + "(from: " + item.getBeginDate()
                        + " to: " + item.getEndDate() + ")");
            }
        }
    }

}

