package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("____________________________________________________________");

        SaveHandler saveHandler = new SaveHandler();
        ArrayList<Task> list = saveHandler.load();

        boolean isExitCommandReceived = false;

        while (!isExitCommandReceived) {
            line = in.nextLine();
            try  {
                Command command = new Command(line);

                if (command.getCommandType() == CommandType.CREATE_TODO) {
                    list.add(new Task(command.getCommandValue()));
                    saveHandler.save(list);
                    printAddedTask(list);
                } else if (command.getCommandType() == CommandType.CREATE_DEADLINE) {
                    String description = command.getCommandValue();
                    String deadline = command.getParameterValueByType(ParameterType.DEADLINE);
                    list.add(new Deadline(description, deadline));
                    saveHandler.save(list);
                    printAddedTask(list);
                } else if (command.getCommandType() == CommandType.CREATE_EVENT) {
                    String description = command.getCommandValue();
                    String from = command.getParameterValueByType(ParameterType.EVENT_START);
                    String to = command.getParameterValueByType(ParameterType.EVENT_END);
                    list.add(new Event(description, from, to));
                    printAddedTask(list);
                } else if (command.getCommandType() == CommandType.DELETE) {
                    int indexToDelete = Integer.parseInt(command.getCommandValue())-1;
                    handleDeleteTask(list, indexToDelete);
                    saveHandler.save(list);
                    printAddedTask(list);
                } else if (command.getCommandType() == CommandType.LIST) {
                    handleShowTaskList(list);
                } else if (command.getCommandType() == CommandType.MARK) {
                    int indexToMark = Integer.parseInt(command.getCommandValue())-1;
                    handleMarkTask(list, indexToMark);
                    saveHandler.save(list);
                } else if (command.getCommandType() == CommandType.UNMARK) {
                    int indexToUnmark = Integer.parseInt(command.getCommandValue())-1;
                    handleUnmarkTask(list, indexToUnmark);
                    saveHandler.save(list);
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

    public static void printAddedTask(ArrayList<Task> list) {
        System.out.println("____________________________________________________________");
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + list.get(list.size()-1).getLabel());
        System.out.println("    Now you have " + list.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void handleShowTaskList(ArrayList<Task> list) {
        System.out.println("____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            String prefix = (i + 1) + ".";
            System.out.println("    " + prefix + list.get(i).getLabel());
        }
        System.out.println("____________________________________________________________");
    }

    public static void handleDeleteTask(ArrayList<Task> list, int index) {
        list.remove(index-1);
        System.out.println("____________________________________________________________");
        System.out.println("    Noted. I've removed this task:");
        System.out.println(list.get(index-1).getLabel());
        System.out.println("____________________________________________________________");
    }

    public static void handleMarkTask(ArrayList<Task> list, int indexToMark) {
        list.get(indexToMark).setIsDone(true);
        System.out.println("____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println(list.get(indexToMark).getLabel());
        System.out.println("____________________________________________________________");
    }

    public static void handleUnmarkTask(ArrayList<Task> list, int indexToUnmark) {
        list.get(indexToUnmark).setIsDone(false);
        System.out.println("____________________________________________________________");
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println(list.get(indexToUnmark).getLabel());
        System.out.println("____________________________________________________________");
    }

    public static void handleExitProgram() {
        System.out.println("____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void handleExceptions(Exception e) {
        switch (e.getClass().getName()) {
            case "duke.IllegalCommandException":
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
            case "duke.IllegalParameterException":
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
                System.out.println(e);
                break;
        }
    }
}