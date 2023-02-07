import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Methods.printGreetings();

        Task[] actions = new Task[100];
        int actionCounter = 0;

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
            commandChecker currentLoop = new commandChecker(decisions, dates, actionCounter);
            if (currentLoop.hasErrors()) {
                decisions[0] = "Invalidate";
            }
            switch (decisions[0]) {
                case "echo":
                    System.out.println(Methods.findTaskDetails(line));
                    break;

                case "todo":
                    toBeAdded = new Todo(Methods.findTaskDetails(line));
                    actions[actionCounter] = toBeAdded;
                    actionCounter++;
                    Methods.printAcknowledgement(toBeAdded,actionCounter);
                    break;

                case "event":
                    toBeAdded = new Event(Methods.findTaskDetails(dates[0]), Methods.findTaskDetails(dates[1]), Methods.findTaskDetails(dates[2]));
                    actions[actionCounter] = toBeAdded;
                    actionCounter++;
                    Methods.printAcknowledgement(toBeAdded,actionCounter);
                    break;

                case "deadline":
                    toBeAdded = new Deadline(Methods.findTaskDetails(dates[0]), Methods.findTaskDetails(dates[1]));
                    actions[actionCounter] = toBeAdded;
                    actionCounter++;
                    Methods.printAcknowledgement(toBeAdded,actionCounter);
                    break;

                case "mark":
                    taskNumber = Integer.parseInt(decisions[1]) - 1;
                    actions[taskNumber].mark();
                    Methods.printDoneMarkingTasks(actions[taskNumber]);
                    break;

                case "unmark":
                    taskNumber = Integer.parseInt(decisions[1]) - 1;
                    actions[taskNumber].unmark();
                    Methods.printDoneMarkingTasks(actions[taskNumber]);
                    break;

                case "list":
                    for (int iterator = 0; iterator < actionCounter; iterator++) {
                        Methods.printListElement(iterator, actions[iterator]);
                    }
                    break;
                default:
                    Methods.printCurrentSupportedActions();
            }
        } while (!decisions[0].equals("bye"));
        System.out.println("That's all from me! Goodbye!");
    }
}
