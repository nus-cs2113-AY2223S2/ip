import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import Command.Greetings;
import Command.TaskPrinter;
import Entities.Task;
import Entities.Event;
import Entities.Deadline;
import Entities.Todo;
import FileUtils.TaskReader;
import FileUtils.TaskWriter;
import Exceptions.*;

public class Duke {
    private static final String filePath = "data/duke.txt";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks;
        Task newTask = null;
        String[] inputArray;
        String command, taskName;
        String startDate, endDate;
        int startDateIdx, endDateIdx, taskIdx;

        try {
            tasks = TaskReader.readAndReturnTasks(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist! Initialising empty tasks list..\n");
            tasks = new ArrayList<Task>();
        }

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
            startDateIdx = endDateIdx = taskIdx = -1;
            startDate = endDate = null;

            try {
                switch (command) {
                    case "list":
                        System.out.println(TaskPrinter.tasksToStringMessage(tasks));
                        break;

                    case "todo":
                        if (input.length() == command.length()) {
                            throw new NoDescriptionException(command);
                        }

                        taskName = input.substring(command.length() + 1);
                        newTask = new Todo(taskName, false);
                        break;
    
                    case "deadline":
                        if (input.length() == command.length()) {
                            throw new NoDescriptionException(command);
                        }
                    
                        startDateIdx = input.indexOf("/by ");
                        if (startDateIdx == -1) {
                            throw new InsufficientArgumentsException(command, "deadline [task] /by [date]");
                        }

                        taskName = input.substring(command.length() + 1, startDateIdx);
                        startDate = input.substring(startDateIdx + 4);
                        if (taskName.length() == 0 || startDate.length() == 0) {
                            String emptyArgument = 
                                taskName.length() == 0 
                                ? "Task Name" 
                                : "Start Date";
                            throw new EmptyArgumentException(emptyArgument);
                        }

                        newTask = new Deadline(taskName, false, startDate);
                        break;
    
                    case "event":
                        if (input.length() == command.length()) {
                            throw new NoDescriptionException(command);
                        }

                        startDateIdx = input.indexOf("/from ");
                        endDateIdx = input.indexOf("/to ");
                        if (startDateIdx == -1 || endDateIdx == -1) {
                            throw new InsufficientArgumentsException(command, "event [task] /from [startDate] /to [startDate]");
                        }

                        taskName = input.substring(command.length() + 1, startDateIdx);
                        startDate = input.substring(startDateIdx + 6, endDateIdx-1);
                        endDate = input.substring(endDateIdx + 4);

                        if (taskName.length() == 0 || startDate.length() == 0 || endDate.length() == 0) {
                            String emptyArgument = 
                                taskName.length() == 0 
                                ? "Task Name" 
                                : startDate.length() == 0 
                                ? "Start Date" 
                                : "End Date";
                            throw new EmptyArgumentException(emptyArgument);
                        }

                        newTask = new Event(taskName, false, startDate, endDate);
                        break;
    
                    case "mark":
                        if (input.length() == command.length()) {
                            throw new NoDescriptionException(command);
                        }

                        try {
                            taskIdx = Integer.parseInt(input.substring(command.length() + 1)) - 1;
                        } catch (NumberFormatException e) {
                            throw new NonPositiveNumberException(e);
                        }

                        try {
                            tasks.get(taskIdx).setIsDone(true);
                        } catch (IndexOutOfBoundsException e) {
                            throw new InvalidTaskIndexException(tasks.size(), e);
                        }
                        System.out.println(TaskPrinter.markedMessage(tasks.get(taskIdx)));
                        break;
                    
                    case "unmark":
                        if (input.length() == command.length()) {
                            throw new NoDescriptionException(command);
                        }

                        try {
                            taskIdx = Integer.parseInt(input.substring(command.length() + 1)) - 1;
                        } catch (NumberFormatException e) {
                            throw new NonPositiveNumberException(e);
                        }

                        try {
                            tasks.get(taskIdx).setIsDone(false);
                        } catch (IndexOutOfBoundsException e) {
                            throw new InvalidTaskIndexException(tasks.size(), e);
                        }
                        System.out.println(TaskPrinter.unmarkedMessage(tasks.get(taskIdx)));
                        break;
                    
                    case "delete":
                        if (input.length() == command.length()) {
                            throw new NoDescriptionException(command);
                        }

                        try {
                            taskIdx = Integer.parseInt(input.substring(command.length() + 1)) - 1;
                        } catch (NumberFormatException e) {
                            throw new NonPositiveNumberException(e);
                        }

                        try {
                            Task deletedTask = tasks.remove(taskIdx);
                            System.out.println(TaskPrinter.deletedMessage(deletedTask, tasks.size()));
                        } catch (IndexOutOfBoundsException e) {
                            throw new InvalidTaskIndexException(tasks.size(), e);
                        }
                        break;
    
                    default:
                        throw new UnknownInputException();
                }
    
                if (newTask != null) {
                    tasks.add(newTask);
                    System.out.printf(TaskPrinter.taskAddedMessage(newTask, tasks.size()));
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            input = sc.nextLine();
        };

        try {
            TaskWriter.writeTasksToFile(filePath, tasks);
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong when writing to database!");
            System.out.println("Error: " + e.getMessage());
            System.out.println("Aborting...");
        }

        Greetings.goodbye();
    }
}