package duke;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Parser {
    Ui ui = new Ui();
    Storage storage = new Storage();

    public String getInputString() {
        String inputString;
        Scanner in;
        in = new Scanner(System.in);
        inputString = in.nextLine();
        return inputString;
    }

    public int getTaskNumber(String task) {
        return Integer.parseInt(task);
    }

    public void markAsDone(ArrayList<Todo> tasks, int taskNumber) {
        tasks.get(taskNumber - 1).setDone(true);
    }

    public void parse(ArrayList<Todo> tasks, int counter) throws DukeException {
        boolean exit = false;

        while (!exit) {
            try {
                String taskType;
                String task = null;
                String inputString = getInputString();

                int descriptionPosition = inputString.indexOf(" ");
                if (descriptionPosition == -1) {
                    taskType = inputString;
                } else {
                    int endPosition = inputString.length();
                    taskType = inputString.substring(0, descriptionPosition);
                    task = inputString.substring(descriptionPosition + 1, endPosition);
                }

                //switch cases for all specified input types
                switch (taskType) {
                case "bye":
                    exit = true;
                    String absoluteFilePath = storage.findFilePath();
                    storage.writeToFile(tasks, absoluteFilePath, counter);
                    ui.showExitMessage();

                    break;

                case "list":
                    ui.showLine();
                    ui.printListContents(tasks, tasks.size());
                    break;

                case "mark":
                    int taskNumber = getTaskNumber(task);
                    markAsDone(tasks, taskNumber);
                    ui.printMarkedAcknowledgement(tasks, taskNumber);
                    break;

                case "unmark":
                    taskNumber = getTaskNumber(task);
                    tasks.get(taskNumber - 1).setDone(false);
                    ui.printUnmarkedAcknowledgement(tasks, taskNumber);
                    break;

                case "todo":
                    if (task == null) {
                        System.out.println("Invalid command, please try again!");
                        break;
                    } else {
                        tasks.add(new Todo(task));
                        tasks.get(counter).print();
                        counter++;
                        ui.printAddedAcknowledgment(counter);
                        break;
                    }

                case "deadline":
                    if (task == null) {
                        System.out.println("Invalid command, please try again!");
                        break;
                    } else {
                        int deadlinePosition = task.indexOf("/");
                        int endOfLine = task.length();
                        String taskName = task.substring(0, deadlinePosition);
                        String deadline = task.substring(deadlinePosition + 1, endOfLine);
                        tasks.add(new Deadline(taskName, deadline));
                        tasks.get(counter).print();
                        counter++;
                        ui.printAddedAcknowledgment(counter);
                        break;
                    }

                case "event":
                    if (task == null) {
                        System.out.println("Invalid command, please try again!");
                        break;
                    } else {
                        int deadlineStartPosition = task.indexOf("/");
                        int deadlineEndPosition = task.indexOf("|");
                        int endOfLine = task.length();
                        String taskName = task.substring(0, deadlineStartPosition);
                        String deadlineStart = task.substring(deadlineStartPosition + 1, deadlineEndPosition);
                        String deadlineEnd = task.substring(deadlineEndPosition + 1, endOfLine);
                        tasks.add(new Event(taskName, deadlineStart, deadlineEnd));
                        tasks.get(counter).print();
                        counter++;
                        ui.printAddedAcknowledgment(counter);
                        break;
                    }

                case "delete":
                    taskNumber = getTaskNumber(task) - 1;
                    ui.showLine();
                    tasks.get(taskNumber).printInList();
                    ui.showLine();
                    System.out.println("    ");
                    tasks.remove(tasks.get(taskNumber));
                    counter--;
                    break;

                case "find":
                    for (int i = 0; i < tasks.size(); i++) {
                        String taskDescription = tasks.get(i).getDescription();
                        if (taskDescription.contains(task)) {
                            ui.printListContents(tasks, i + 1);
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid command, please try again!");
                    break;
                }
            } catch (IndexOutOfBoundsException | NullPointerException | InputMismatchException e) {
                System.out.println("Invalid command, please try again!");
            }
        }
    }
}
