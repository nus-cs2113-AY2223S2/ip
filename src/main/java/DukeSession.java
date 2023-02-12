import utility.Methods;
import utility.commandChecker;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.Scanner;
import java.util.ArrayList;


public class DukeSession {
    public static void execute() {
        Methods.printGreetings();

        ArrayList<Task> actions = new ArrayList<>();

        String line;
        Scanner in = new Scanner(System.in);
        int taskNumber;
        String[] decisions;
        Task toBeAdded;
        String[] dates;
        do {
            line = in.nextLine();
            decisions = line.split(" ");
            dates = line.split("/");
            commandChecker currentLoop = new commandChecker(decisions, dates, actions.size());
            if (currentLoop.hasErrors()) {
                decisions[0] = "Invalidate";
            }
            switch (decisions[0]) {
                case "echo":
                    System.out.println(Methods.findTaskDetails(line));
                    break;

                case "todo":
                    toBeAdded = new Todo(Methods.findTaskDetails(line));
                    actions.add(toBeAdded);
                    Methods.printAcknowledgement(toBeAdded, actions.size());
                    break;

                case "event":
                    toBeAdded = new Event(Methods.findTaskDetails(dates[0]), Methods.findTaskDetails(dates[1]), Methods.findTaskDetails(dates[2]));
                    actions.add(toBeAdded);
                    Methods.printAcknowledgement(toBeAdded, actions.size());
                    break;

                case "deadline":
                    toBeAdded = new Deadline(Methods.findTaskDetails(dates[0]), Methods.findTaskDetails(dates[1]));
                    actions.add(toBeAdded);
                    Methods.printAcknowledgement(toBeAdded, actions.size());
                    break;

                case "mark":
                    taskNumber = Integer.parseInt(decisions[1]) - 1;
                    actions.get(taskNumber).mark();
                    Methods.printDoneMarkingTasks(actions.get(taskNumber));
                    break;

                case "unmark":
                    taskNumber = Integer.parseInt(decisions[1]) - 1;
                    actions.get(taskNumber).unmark();
                    Methods.printDoneMarkingTasks(actions.get(taskNumber));
                    break;

                case "list":
                    for (int iterator = 0; iterator < actions.size(); iterator++) {
                        Methods.printListElement(iterator, actions.get(iterator));
                    }
                    break;
                case "delete":
                    taskNumber = Integer.parseInt(decisions[1]) - 1;
                    Methods.printDeleteAcknowledgement(actions.get(taskNumber), actions.size() - 1);
                    actions.remove(taskNumber);
                    break;

                case "bye":
                    break;

                default:
                    Methods.printCurrentSupportedActions();
            }
        } while (!decisions[0].equals("bye"));
        System.out.println("That's all from me! Goodbye!");
    }
}
