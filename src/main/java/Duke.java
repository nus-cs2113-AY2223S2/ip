import java.util.Scanner;
import java.util.ArrayList;
import Entities.*;
import Communication.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        Task newTask = null;
        String[] inputArray;
        String command, taskName;
        String startDate, endDate;
        int startIdx, endIdx;

        Greetings.introduction();

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            // Empty Input, Do Nothing
            if (input.length() == 0) {
                input = sc.nextLine();
                continue;
            }

            inputArray = input.split(" ");
            command = inputArray[0].toLowerCase();
            newTask = null;
            startIdx = endIdx = -1;
            startDate = endDate = null;

            switch (command) {
                case "todo":
                    taskName = input.substring(command.length() + 1);
                    newTask = new Todo(taskName);
                    break;

                case "deadline":
                    startIdx = input.indexOf("/by ");
                    taskName = input.substring(command.length() + 1, startIdx);
                    startDate = input.substring(startIdx + 4);
                    newTask = new Deadline(taskName, startDate);
                    break;

                case "event":
                    startIdx = input.indexOf("/from ");
                    endIdx = input.indexOf("/to ");
                    taskName = input.substring(command.length() + 1, startIdx);
                    startDate = input.substring(startIdx + 6, endIdx-1);
                    endDate = input.substring(endIdx + 4);
                    newTask = new Event(taskName, startDate, endDate);
                    break;

                case "list":
                    System.out.println(TaskPrinter.tasksToStringMessage(tasks));
                    break;

                case "mark":
                    startIdx = Integer.parseInt(input.substring(command.length() + 1)) - 1;
                    tasks.get(startIdx).setDone(true);
                    System.out.println(TaskPrinter.markedMessage(tasks.get(startIdx)));
                    break;
                
                case "unmark":
                    startIdx = Integer.parseInt(input.substring(command.length() + 1)) - 1;
                    tasks.get(startIdx).setDone(false);
                    System.out.println(TaskPrinter.unmarkedMessage(tasks.get(startIdx)));
                    break;

                default:
                    // throw unknown command exeception
                    break;
            }

            if (newTask != null) {
                tasks.add(newTask);
                System.out.printf(TaskPrinter.taskAddedMessage(newTask, tasks.size()));
            }

            input = sc.nextLine();
        };

        Greetings.goodbye();
    }
}
