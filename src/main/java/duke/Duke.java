package duke;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("____________________________________________________________");

        Task[] list = new Task[100];
        int nextEmptyIndex = 0;

        boolean isExitCommandReceived = false;

        while (!isExitCommandReceived) {
            line = in.nextLine();
            try  {
                Command command = new Command(line);

                if (command.getCommandType() == CommandType.CREATE_TODO) {
                    list[nextEmptyIndex] = new Task(command.getCommandValue());
                    nextEmptyIndex++;
                    printAddedTask(list, nextEmptyIndex);
                } else if (command.getCommandType() == CommandType.CREATE_DEADLINE) {
                    String description = command.getCommandValue();
                    String deadline = command.getParameterValueByType(ParameterType.DEADLINE);
                    list[nextEmptyIndex] = new Deadline(description, deadline);
                    nextEmptyIndex++;
                    printAddedTask(list, nextEmptyIndex);
                } else if (command.getCommandType() == CommandType.CREATE_EVENT) {
                    String description = command.getCommandValue();
                    String from = command.getParameterValueByType(ParameterType.EVENT_START);
                    String to = command.getParameterValueByType(ParameterType.EVENT_END);
                    list[nextEmptyIndex] = new Event(description, from, to);
                    nextEmptyIndex++;
                    printAddedTask(list, nextEmptyIndex);
                } else if (command.getCommandType() == CommandType.LIST) {
                    handleShowTaskList(list, nextEmptyIndex);
                } else if (command.getCommandType() == CommandType.MARK) {
                    int indexToMark = Integer.parseInt(command.getCommandValue())-1;
                    handleMarkTask(list, indexToMark);
                } else if (command.getCommandType() == CommandType.UNMARK) {
                    int indexToUnmark = Integer.parseInt(command.getCommandValue())-1;
                    handleUnmarkTask(list, indexToUnmark);
                } else if (command.getCommandType() == CommandType.EXIT) {
                    isExitCommandReceived = true;
                } else {
                    System.out.println("ERROR: This should not be possible.");
                }
            } catch (Exception e) {
                handleExceptions(e);
            }
        }
        handleExitProgram();
    }

    public static void printAddedTask(Task[] list, int nextEmptyIndex) {
        System.out.println("____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + list[nextEmptyIndex-1].getLabel());
        System.out.println("    Now you have " + nextEmptyIndex + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void handleShowTaskList(Task[] list, int nextEmptyIndex) {
        System.out.println("____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < nextEmptyIndex; i++) {
            String prefix = (i + 1) + ".";
            System.out.println("    " + prefix + list[i].getLabel());
        }
        System.out.println("____________________________________________________________");
    }

    public static void handleMarkTask(Task[] list, int indexToMark) {
        list[indexToMark].setIsDone(true);
        System.out.println("____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println(list[indexToMark].getLabel());
        System.out.println("____________________________________________________________");
    }

    public static void handleUnmarkTask(Task[] list, int indexToUnmark) {
        list[indexToUnmark].setIsDone(false);
        System.out.println("____________________________________________________________");
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println(list[indexToUnmark].getLabel());
        System.out.println("____________________________________________________________");
    }

    public static void handleExitProgram() {
        System.out.println("____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void handleExceptions(Exception e) {
        switch (e.getClass().getName()) {
            case "IllegalCommandException":
                IllegalCommandException commandError = (IllegalCommandException) e;
                if (commandError.type == IllegalCommandExceptionType.COMMAND_DOES_NOT_EXIST) {
                    System.out.println("____________________________________________________________");
                    System.out.println("    Error: The command '" + commandError.value + "' does not exist!");
                    System.out.println("____________________________________________________________");
                } else if (commandError.type == IllegalCommandExceptionType.MISSING_VALUE) {
                    System.out.println("____________________________________________________________");
                    System.out.println("    Error: Missing value after '" + commandError.value + "' command!");
                    System.out.println("____________________________________________________________");
                } else if (commandError.type == IllegalCommandExceptionType.MISSING_PARAMETER) {
                    System.out.println("____________________________________________________________");
                    System.out.println("    Error: This command requires the '" + commandError.value + "' parameter!");
                    System.out.println("____________________________________________________________");
                }
                break;
            case "IllegalParameterException":
                IllegalParameterException parameterError = (IllegalParameterException) e;
                if (parameterError.type == IllegalParameterExceptionType.PARAMETER_DOES_NOT_EXIST) {
                    System.out.println("____________________________________________________________");
                    System.out.println("    Error: The parameter '" + parameterError.value + "' does not exist!");
                    System.out.println("____________________________________________________________");
                } else if (parameterError.type == IllegalParameterExceptionType.MISSING_VALUE) {
                    System.out.println("____________________________________________________________");
                    System.out.println("    Error: Missing value after '" + parameterError.value + "' parameter!");
                    System.out.println("____________________________________________________________");
                }
                break;
            default:
                break;
        }
    }
}

