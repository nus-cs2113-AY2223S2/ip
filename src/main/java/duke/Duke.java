package duke;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    //private static Task[] taskList = new Task[100];
    private static ArrayList<Task> taskList = new ArrayList<>() ;
    private static int counter = 0;
    private static final String LINE = "___________________________________________";
    private static final String EXIT_STRING = "bye";
    private static final String LIST_STRING = "list";
    private static final String MARK_STRING = "mark";
    private static final String UNMARK_STRING = "unmark";

    private static final String DELETE_STRING = "delete";

    private static boolean isExecuting;

    public static void printGreeting() {
        System.out.println("\t" + LINE);
        System.out.println("\t Hello I'm duke.Duke, your personal chatbot.");
        System.out.println("\t Is there anything I can do for you");
        System.out.println("\t" + LINE);
    }

    public static void printList() {
        System.out.println("\t" + LINE);
        System.out.println("\t Here are the tasks for your list: ");
        for (int i = 0; i < counter; i++) {
            System.out.println("\t " + (i + 1) + "." + taskList.get(i));
        }
        System.out.println("\t" + LINE);
    }

    public static void printTask(Task task) {
        System.out.println("\t" + LINE);
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\t Now you have " + counter + " tasks in your list.");
        System.out.println("\t" + LINE);
    }

    public static void printBye() {
        System.out.println("\t" + LINE);
        System.out.println("\t Bye! Do let me know if you need any further assistance");
        System.out.println("\t" + LINE);
    }

    public static void markTaskAndPrint(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskList.get(taskIndex) == null) {
            throw new DukeException("Please ensure that you enter the correct task number");
        }
        //only gets executed if no exception is thrown
        taskList.get(taskIndex).isDone = true;
        System.out.println("\t" + LINE);
        System.out.println("\t Nice, I have marked this task as done: ");
        System.out.println("\t [" + taskList.get(taskIndex).getStatusIcon() + "] " + taskList.get(taskIndex).description);
        System.out.println("\t" + LINE);
    }

    public static void unmarkTaskAndPrint(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskList.get(taskIndex) == null) {
            throw new DukeException("Please ensure that you enter the correct task number");
        }

        taskList.get(taskIndex).isDone = false;
        System.out.println("\t" + LINE);
        System.out.println("\t Ouch, I have unmarked this task: ");
        System.out.println("\t [" + taskList.get(taskIndex).getStatusIcon() + "] " + taskList.get(taskIndex).description);
        System.out.println("\t" + LINE);
    }

    public static void deleteTaskAndPrint(int indexToDelete) throws DukeException {
        if (indexToDelete < 0 || taskList.get(indexToDelete) == null) {
            throw new DukeException("Please ensure that you enter the correct task number to remove");
        }

        System.out.println("\t" + LINE);
        System.out.println("\t Noted. I've removed this task: ");
        System.out.println("\t " + taskList.get(indexToDelete));
        System.out.println("\t Now you have " + Integer.toString(taskList.size() -1 )+ " tasks in your list");
        System.out.println("\t" + LINE);
        taskList.remove(indexToDelete);

    }


    public static void handleAddTask(String taskType, String commandInfo) {
        Task newTask = null;
        if (taskType.equals("todo")) {
            newTask = new Task(commandInfo);
        } else if (taskType.equals("deadline")) {
            String[] infoArr = commandInfo.split("/by");
            //infoArr contains descStr and deadlineStr respectively
            newTask = new Deadline(infoArr[0].trim(), infoArr[1].trim());
        } else if (taskType.equals("event")) {
            String[] infoArr = commandInfo.split("/from|/to");
            //infoArr contains descStr, fromStr, and toStr respectively
            newTask = new Event(infoArr[0].trim(), infoArr[1].trim(), infoArr[2].trim());
        }

        taskList.add(newTask) ;
        counter++;
        printTask(newTask);
    }

    public static void handleInput(String inputLine) throws DukeException {

        //splits input based on one or more whitespaces into two words
        String[] inputWords = inputLine.split("\\s+", 2);
        String command = inputWords[0];


        if (command.equals(EXIT_STRING)) {
            printBye();
            isExecuting = false;
        }
        else if (command.equals(LIST_STRING)) {
            printList();
        }
        else if (command.equals(MARK_STRING)) {
            //inputWords[1] is string that no longer contains the command string
            if (inputWords.length < 2) {
                throw new DukeException("Please specify which task you wish to mark");
            } else {
                int indexToMark = Integer.parseInt(inputWords[1]) - 1; //turn it into 0-based
                markTaskAndPrint(indexToMark);
            }
        }
        else if (command.equals(UNMARK_STRING)) {
            if (inputWords.length < 2) {
                throw new DukeException("Please specify which task you wish to unmark");
            } else {
                int indexToUnmark = Integer.parseInt(inputWords[1]) - 1;
                unmarkTaskAndPrint(indexToUnmark);
            }
        }
        else if(command.equals(DELETE_STRING)) {
            if(inputWords.length <2) {
                throw new DukeException("Please specify which task you wish to delete");
            } else {
                int indexToDelete = Integer.parseInt(inputWords[1])-1;
                deleteTaskAndPrint(indexToDelete);
            }
        }
        //check if command string matches either of the string
        else if (command.matches("todo|deadline|event")) {
            if (inputWords.length < 2) {
                throw new DukeException("Please specify the description to the task that you wish to add");
            }
            handleAddTask(command, inputWords[1]);
        } else {
            throw new DukeException("fsgfsuygu I don't know what that means :(");
        }
    }


    public static void main(String[] args) {
        printGreeting();
        isExecuting = true;
        Scanner in = new Scanner(System.in);

        while (isExecuting) {
            String inputLine = in.nextLine();
            try {
                handleInput(inputLine);
            } catch (DukeException ex) {
                System.out.println("Exception occured: " + ex);
            }

        }
    }
}
