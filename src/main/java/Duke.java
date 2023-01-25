import java.util.Scanner;
import java.util.ArrayList;
import Command.*;
import Entities.*;
import Exceptions.*;

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
                        newTask = new Todo(taskName);
                        break;
    
                    case "deadline":
                        if (input.length() == command.length()) {
                            throw new NoDescriptionException(command);
                        }
                        
                        startIdx = input.indexOf("/by ");
                        if (startIdx == -1) {
                            throw new InsufficientArgumentsException(command, "deadline [task] /by [date]");
                        }

                        taskName = input.substring(command.length() + 1, startIdx);
                        startDate = input.substring(startIdx + 4);
                        if (taskName.length() == 0 || startDate.length() == 0) {
                            String emptyArgument = 
                                taskName.length() == 0 
                                ? "Task Name" 
                                : "Start Date";
                            throw new EmptyArgumentException(emptyArgument);
                        }

                        newTask = new Deadline(taskName, startDate);
                        break;
    
                    case "event":
                        if (input.length() == command.length()) {
                            throw new NoDescriptionException(command);
                        }

                        startIdx = input.indexOf("/from ");
                        endIdx = input.indexOf("/to ");
                        if (startIdx == -1 || endIdx == -1) {
                            throw new InsufficientArgumentsException(command, "event [task] /from [startDate] /to [startDate]");
                        }

                        taskName = input.substring(command.length() + 1, startIdx);
                        startDate = input.substring(startIdx + 6, endIdx-1);
                        endDate = input.substring(endIdx + 4);

                        if (taskName.length() == 0 || startDate.length() == 0 || endDate.length() == 0) {
                            String emptyArgument = 
                                taskName.length() == 0 
                                ? "Task Name" 
                                : startDate.length() == 0 
                                ? "Start Date" 
                                : "End Date";
                            throw new EmptyArgumentException(emptyArgument);
                        }

                        newTask = new Event(taskName, startDate, endDate);
                        break;
    
                    case "mark":
                        if (input.length() == command.length()) {
                            throw new NoDescriptionException(command);
                        }

                        try {
                            startIdx = Integer.parseInt(input.substring(command.length() + 1)) - 1;
                        } catch (NumberFormatException e) {
                            throw new NonPositiveNumberException(e);
                        }

                        try {
                            tasks.get(startIdx).setIsDone(true);
                        } catch (IndexOutOfBoundsException e) {
                            throw new InvalidTaskIndexException(tasks.size(), e);
                        }
                        System.out.println(TaskPrinter.markedMessage(tasks.get(startIdx)));
                        break;
                    
                    case "unmark":
                        if (input.length() == command.length()) {
                            throw new NoDescriptionException(command);
                        }

                        try {
                            startIdx = Integer.parseInt(input.substring(command.length() + 1)) - 1;
                        } catch (NumberFormatException e) {
                            throw new NonPositiveNumberException(e);
                        }

                        try {
                            tasks.get(startIdx).setIsDone(false);
                        } catch (IndexOutOfBoundsException e) {
                            throw new InvalidTaskIndexException(tasks.size(), e);
                        }
                        System.out.println(TaskPrinter.unmarkedMessage(tasks.get(startIdx)));
                        break;
    
                    default:
                        throw new UnknownInputException();
                }
    
                if (newTask != null) {
                    tasks.add(newTask);
                    System.out.printf(TaskPrinter.taskAddedMessage(newTask, tasks.size()));
                }

            } catch (NoDescriptionException e) {
                System.out.println(e.getMessage());
            } catch (InsufficientArgumentsException e) {
                System.out.println(e.getMessage());
            } catch (EmptyArgumentException e) {
                System.out.println(e.getMessage());
            } catch(UnknownInputException e) {
                System.out.println(e.getMessage());
            } catch (NonPositiveNumberException e) {
                System.out.println(e.getMessage());
            } catch (InvalidTaskIndexException e) {
                System.out.println(e.getMessage());
            }

            input = sc.nextLine();
        };

        Greetings.goodbye();
    }
}
