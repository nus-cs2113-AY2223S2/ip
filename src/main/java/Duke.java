import java.lang.reflect.Array;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Task[] taskList = new Task[101];
        String HorizontalLine = "__________________________\n";
        System.out.println(HorizontalLine + "Hello! I'm Duke\n" + "What can I do for you?\n"
                + HorizontalLine);
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            String[] inputWords = input.split(" ");
            if (inputWords[0].equals("list")) {
                if (taskList[1] != null) {
                    System.out.println(HorizontalLine + "List of Tasks: \n");
                    for (int i = 1; i < Task.maxTaskNumber; i++) {
                        System.out.println(i + ". " + taskList[i].getTaskName() + " [" +
                                taskList[i].getDone() + "]");
                    }
                    System.out.println(HorizontalLine);
                } else {
                    System.out.println(HorizontalLine + "no task added yet\n" + HorizontalLine);
                }
            } else if (inputWords[0].equals("mark")) {
                int taskNumber = Integer.valueOf(inputWords[1]);
                if (taskNumber >= Task.maxTaskNumber) {
                    System.out.println("No such task found\n" + HorizontalLine);
                } else {
                    taskList[taskNumber].setDone();
                    System.out.println(HorizontalLine + "Task set as done: " + taskList[taskNumber].getTaskName() + "\n"
                            + HorizontalLine);
                }
            } else if (inputWords[0].equals("unmark")) {
                int taskNumber = Integer.valueOf(inputWords[1]);
                if (taskNumber >= Task.maxTaskNumber) {
                    System.out.println("No such task found\n" + HorizontalLine);
                } else {
                    taskList[taskNumber].unsetDone();
                    System.out.println(HorizontalLine + "Task set as undone: " + taskList[taskNumber].getTaskName() +
                            "\n" + HorizontalLine);
                }
            } else {
                taskList[Task.maxTaskNumber] = new Task(input);
                Task.maxTaskNumber++;
                System.out.println(HorizontalLine + "Task added: " + input + "\n" + HorizontalLine);
            }
            input = in.nextLine();
        }
        System.out.println(HorizontalLine + "Goodbye!" + "\n" + HorizontalLine);
    }
}

