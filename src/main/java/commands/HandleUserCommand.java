package commands;

import constants.constant;
import exceptions.InvalidTaskException;
import tasks.Task;

import java.util.Scanner;


public class HandleUserCommand extends Command {
    public static void handleCommand(String userCommand, Task[] list, int counter) throws InvalidTaskException {
        Scanner in = new Scanner(System.in);
        while (!userCommand.equals("bye")) {
            userCommand = in.nextLine();
            Parser newCommand = new Parser();
            switch (newCommand.parseCommand(userCommand)) {
                case "todo":
                    AddTodo.addTodoTask(list, counter, userCommand);
                    break;
                case "deadline":
                    AddDeadline.addDeadlineTask(list, userCommand);
                    break;
                case "event":
                    AddEvent.addEventTask(list, counter, userCommand);
                    break;
                case "list":
                    if (Task.getNum() == 0) {
                        System.out.println(constant.HORIZONTAL_LINE + "\n");
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
                case "bye":
                    Bye ins = new Bye();
                    ins.bye();
                    break;
                default:
                    System.out.println(new InvalidTaskException().call());
                    System.out.println(constant.HORIZONTAL_LINE + "\n");
                    break;
            }

        }
    }

    public static void markTask(Task[] list, String ins) {
        try {
            if (Task.getNum() == 0) {
                System.out.println(constant.HORIZONTAL_LINE + "\n");
                System.out.println("Your day is clear! there is no task");
                System.out.println(constant.HORIZONTAL_LINE + "\n");
            } else {
                System.out.println(constant.HORIZONTAL_LINE + "\n");
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
                    boolean isNumWithinCounter = (taskNum <= Task.getNum()) && (taskNum > 0);
                    if (!isNumWithinCounter) {
                        throw new InvalidTaskException();
                    }
                    System.out.println("Nice! You have done Task " + taskNum);
                    list[taskNum - 1].setIsDone(true);
                }
            }
        } catch (InvalidTaskException e) {
            System.out.println(e.call());
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        }
    }

    public static void unmarkTask(Task[] list, String ins) {
        try {
            if (Task.getNum() == 0) {
                System.out.println(constant.HORIZONTAL_LINE + "\n");
                System.out.println("Your day is clear! there is no task");
                System.out.println(constant.HORIZONTAL_LINE + "\n");
            } else {
                System.out.println(constant.HORIZONTAL_LINE + "\n");
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
                    boolean isNumWithinCounter = (taskNum <= Task.getNum()) && (taskNum > 0);

                    if (!isNumWithinCounter) {
                        throw new InvalidTaskException();
                    }

                    System.out.println("Okay, I have unmarked Task " + taskNum);
                    list[taskNum - 1].setIsDone(false);
                }
            }
        } catch (InvalidTaskException e) {
            System.out.println(e.call());
            System.out.println(constant.HORIZONTAL_LINE + "\n");
        }
    }
}
