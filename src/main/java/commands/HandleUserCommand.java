package commands;

import constants.constant;
import exceptions.InvalidTaskException;
import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class HandleUserCommand {
    public static void handleCommand(ArrayList<Task> list) throws InvalidTaskException {
        String userCommand = "start here";
        Scanner in = new Scanner(System.in);
        while (!userCommand.equals("bye")) {
            userCommand = in.nextLine();
            Parser newCommand = new Parser();
            switch (newCommand.parseCommand(userCommand)) {
                case "find":
                    FindTask.findTask(list,userCommand);
                    break;
                case "todo":
                    AddTodo.addTodoTask(list, userCommand);
                    break;
                case "deadline":
                    AddDeadline.addDeadlineTask(list, userCommand);
                    break;
                case "event":
                    AddEvent.addEventTask(list, userCommand);
                    break;
                case "list":
                    if (list.size() == 0) {
                        System.out.println("Your day is clear!");
                        System.out.println(constant.HORIZONTAL_LINE + "\n");
                    } else {
                        ListOfTask.printListOfTasks(list);
                    }
                    break;
                case "mark":
                    markTask(list, userCommand);
                    break;
                case "unmark":
                    unmarkTask(list, userCommand);
                    break;
                case "delete":
                    DeleteTask.deleteTask(list, userCommand);
                    break;
                case "help":
                    Help.getHelpMessage();
                    break;
                case "bye":
                    Bye.bye();
                    Exit.saveFile(list);
                    break;
                default:
                    System.out.println(new InvalidTaskException().call());
                    System.out.println(constant.HORIZONTAL_LINE + "\n");
                    break;
            }

        }
    }

    public static void markTask(ArrayList<Task> list, String ins) {
        try {
            if (list.size() == 0) {
                System.out.println("Your day is clear! there is no task");
                System.out.println(constant.HORIZONTAL_LINE + "\n");
            } else {
                if (ins.length() < 6) {
                    System.out.println("Please specify the task you want to mark :) ");
                    System.out.println(constant.HORIZONTAL_LINE + "\n");
                    return;
                }
                int idx = 5;
                String subStr = ins.substring(idx);
                if (subStr.contains(" ")) {
                    System.out.println("Please specify the task you want to mark :) ");
                } else {
                    int taskNum = Integer.parseInt(subStr);
                    boolean isNumWithinCounter = (taskNum <= list.size()) && (taskNum > 0);
                    if (!isNumWithinCounter) {
                        throw new InvalidTaskException();
                    }
                    System.out.println("Nice! You have done Task " + taskNum);
                    System.out.println(constant.HORIZONTAL_LINE + "\n");
                    list.get(taskNum - 1).setIsDone(true);
                }
            }
        } catch (InvalidTaskException e) {
            System.out.println(e.call());
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        }
    }

    public static void unmarkTask(ArrayList<Task> list, String ins) {
        try {
            if (list.size() == 0) {
                System.out.println("Your day is clear! there is no task");
                System.out.println(constant.HORIZONTAL_LINE + "\n");
            } else {
                if (ins.length() < 6) {
                    System.out.println("Please specify the task you want to mark :) ");
                    System.out.println(constant.HORIZONTAL_LINE + "\n");
                    return;
                }
                int idx = 7;
                String subStr = ins.substring(idx);
                if (subStr.contains(" ")) {
                    System.out.println("Please specify the task you want to mark :) ");
                } else {
                    int taskNum = Integer.parseInt(subStr);
                    boolean isNumWithinCounter = (taskNum <= list.size()) && (taskNum > 0);

                    if (!isNumWithinCounter) {
                        throw new InvalidTaskException();
                    }

                    System.out.println("Okay, I have unmarked Task " + taskNum);
                    System.out.println(constant.HORIZONTAL_LINE + "\n");
                    list.get(taskNum - 1).setIsDone(false);
                }
            }
        } catch (InvalidTaskException e) {
            System.out.println(e.call());
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        }
    }
}
