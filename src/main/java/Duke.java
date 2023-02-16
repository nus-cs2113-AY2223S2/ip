// Java program to read data of various types using Scanner class.
import exceptions.EmptyInputException;
import exceptions.IllegalInputException;

import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void printWelcomeMessage() {
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Elzi, your dog!\n" +
                " What can I do for my master?\n" +
                "____________________________________________________________\n";
        System.out.println(welcomeMessage);
    }
    public static void printLine() {
        System.out.print("___________________________******___________________________\n");
    }
    public static void printList(int taskCounter) {
        System.out.println("Your current tasks are as follows: ");
        for (int index = 0; index < tasks.size(); index += 1) {
            System.out.println((index + 1) + "." + tasks.get(index));
        }
    }
    public static void printAddTodo(Todo print, int taskCounter) {
        System.out.println("Got it. I've added this task:");
        System.out.print("   "); System.out.println(print);
        System.out.println("Now you have " + Integer.toString(taskCounter) + " task in the list");
    }
    public static void printAddDeadline(Deadline print, int taskCounter) {
        System.out.println("Got it. I've added this task:");
        System.out.print("   "); System.out.println(print);
        System.out.println("Now you have " + Integer.toString(taskCounter) + " task in the list");
    }
    public static void printAddEvent(Event print, int taskCounter) {
        System.out.println("Got it. I've added this task:");
        System.out.print("   "); System.out.println(print);
        System.out.println("Now you have " + Integer.toString(taskCounter) + " task in the list");
    }
    public static String parseInput(String[] inputs){
        String todoInput = "";
        for (int i = 1; i < inputs.length; i ++) {
            todoInput += inputs[i];
            todoInput += " ";
        }
        return todoInput;
    }
    public static void inputTodo(String[] inputs, int taskCounter) {
        String todoInput = parseInput(inputs);
        Todo todo = new Todo(todoInput);
        tasks.add(todo);
        taskCounter += 1;
        printAddTodo(todo, taskCounter);
    }
    private static boolean readInput() throws EmptyInputException, IllegalInputException {
        Scanner sc = new Scanner(System.in);
        String input = "";
        input = sc.nextLine();

        int taskCounter = 0;


        while (!input.equals("bye")) {


            String[] inputs = input.split(" ");
            String command  = inputs[0];

            printLine();

            switch (command) {
            case "list":
                printList(taskCounter);
                break;
            case "mark":
                int taskIndex = Integer.parseInt(inputs[1]);
                if (taskIndex > tasks.size() || taskIndex <= 0) throw  new IllegalInputException();
                tasks.get(taskIndex-1).markAsDone();
                System.out.println("I have marked this task as done");
                printList(taskCounter);
                break;
            case "unmark":
                taskIndex = Integer.parseInt(inputs[1]);
                if (taskIndex > tasks.size() || taskIndex <= 0) throw  new IllegalInputException();
                tasks.get(taskIndex-1).markAsNotDone();
                System.out.println("I have unmarked this task");
                printList(taskCounter);
                break;
            case "todo":
                inputTodo(inputs, taskCounter);
                break;
            case "deadline":
                String deadlineInput = "";
                String deadlineDeadline = "";
                boolean isDeadline = false;
                for (int i = 1; i < inputs.length; i ++) {
                    if (inputs[i].contains("/")) {
                        isDeadline = true;
                        continue;
                    }
                    if (isDeadline) {
                        deadlineDeadline += inputs[i];
                        deadlineDeadline += " ";
                    }
                    else {
                        deadlineInput += inputs[i];
                        deadlineInput += " ";
                    }
                }
                if (!isDeadline) throw new IllegalInputException();
                Deadline deadline = new Deadline(deadlineInput, deadlineDeadline);
                tasks.add(deadline); taskCounter += 1;
                printAddDeadline(deadline, taskCounter);
                break;
            case "event":
                String eventInput = "";
                String eventFrom = ""; String eventTo = "";
                boolean isFrom = false; boolean isTo = false;
                for (int i = 1; i < inputs.length; i ++) {
                    if (inputs[i].contains("/from")) {
                        isFrom = true;
                        continue;
                    }
                    if (inputs[i].contains("/to")) {
                        isTo = true;
                        continue;
                    }
                    if (isTo) {
                        eventTo += inputs[i];
                        eventTo += " ";
                    }
                    else if (isFrom){
                        eventFrom += inputs[i];
                        eventFrom += " ";
                    }
                    else {
                        eventInput += inputs[i];
                        eventInput += " ";
                    }
                }
                if (! (isTo && isFrom)) throw new IllegalInputException();
                Event event = new Event(eventInput,eventFrom, eventTo);
                tasks.add(event); taskCounter += 1;
                printAddEvent(event, taskCounter);
                break;
            case "delete":
                System.out.print("I have removed");
                printList(taskCounter);
                break;
            default:
                throw new IllegalInputException();
            }

            printLine();

            input = sc.nextLine();
        }
        return true;
    }
    public static void main(String[] args) {
        printWelcomeMessage();
        boolean isBye = false;
        while (!isBye) {
            try {
                isBye = readInput();
            }
            catch (EmptyInputException e) {
                System.out.println("Description Can Not Be Empty!");
            }
            catch (IllegalInputException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printLine();
            }
        }

        // Print end text line
        printLine();
    }
}
