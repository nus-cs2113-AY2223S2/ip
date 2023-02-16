import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//TODO: specify error: empty description vs event/ddl timing not found
//TODO: check print new line for printtask

public class Duke {
    public static String line = "____________________________________________________________\n";
    public static int countTask = 0;
    public static Task[] tasks = new Task[100];

    // reading file from hard disc
    public static String home = System.getProperty("user.home");
//    public static Path filePath = java.nio.file.Paths.get(home, "ip", "task_list.txt");
    public static String filePath = "./task_list.txt";

    public static void createFile(String filePath) {
        try {
            File f = new File(filePath);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating file.");
            e.printStackTrace();
        }
    }

    public static void readTasksFromFile(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String task = s.nextLine();
            String[] taskElements = task.trim().split("\\|");
            switch (taskElements[0].trim()) {
            case "T":
                tasks[countTask] = new ToDo(taskElements[2].trim());
                if (taskElements[1].trim().equals("X")) {
                    tasks[countTask].markAsDone();
                }
                countTask++;
                break;
            case "D":
                tasks[countTask] = new Deadline(taskElements[2].trim(), taskElements[3].trim());
                if (taskElements[1].trim().equals("X")) {
                    tasks[countTask].markAsDone();
                }
                countTask++;
                break;
            case "E":
                tasks[countTask] = new Event(taskElements[2].trim(), taskElements[3].trim(), taskElements[4].trim());
                if (taskElements[1].trim().equals("X")) {
                    tasks[countTask].markAsDone();
                }
                countTask++;
                break;
            default:
            }
        }
    }

    /*
     T | X | description
     D |   | description | time
     E |   | description | start time | end time
     */

    public static void saveTasksToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        for (int i = 0; i < countTask; i++) {
            String completeTaskDescription = tasks[i].printTask();
            String taskType = completeTaskDescription.substring(1, 2);
            switch (taskType) {
            case "T":
                fw.write(taskType + " | " + tasks[i].getStatusIcon() + " | " + tasks[i].getDescription() +
                        System.lineSeparator());
                break;
            case "D":
                fw.write(taskType + " | " + tasks[i].getStatusIcon() + " | " + tasks[i].getDescription() + " | " +
                        ((Deadline) tasks[i]).getDeadline() + System.lineSeparator());
                break;
            case "E":
                fw.write(taskType + " | " + tasks[i].getStatusIcon() + " | " + tasks[i].getDescription() + " | " +
                        ((Event) tasks[i]).getStartTime() + " | " + ((Event) tasks[i]).getEndTime() + System.lineSeparator());
                break;
            default:
            }
        }
        fw.close();
    }

    public static void main(String[] args) {

        File f = new File(filePath);
        createFile(filePath);
        try {
            readTasksFromFile(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        String greet = line +
                "Hello! I'm Duke.\n" +
                "To add general tasks to the todo list, simply type in the task and press enter.\n" +
                "You can specify the type of tasks by using " +
                "'todo' / 'deadline' / 'event' keyword before the task description.\n" +
                "You can keep track of deadlines by typing 'deadline' " +
                "followed by task description + '/by' to specify the time of the deadline.\n" +
                "You can also add events to the list by typing 'event' " +
                "followed by task description + '/from' + starting time of event + '/to' ending time of event.\n" +
                "Type 'list' to view the current todo list.\n" +
                "Type 'mark' and task event index to mark the task as done " +
                "and type 'unmark' and index to undo the task.\n" +
                "Type 'bye' to exit Duke.\n" +
                line;
        System.out.println(greet);

        Scanner in = new Scanner(System.in);

        while (true) {
            String userInput = "";
            if (in.hasNextLine()) {
                userInput = in.nextLine().trim();
            } else {
                break;
            }
            try {
                if (userInput.equals("bye")) {
                    System.out.println("Thank you for using Duke. Hope to see you soon!");
                    break;
                } else if (userInput.equals("list")) {
                    printTaskList();
                } else if (userInput.startsWith("mark")) {
                    markTask(userInput);
                } else if (userInput.startsWith("unmark")) {
                    unmarkTask(userInput);
                } else if (userInput.startsWith("todo")) {
                    try {
                        createTodo(userInput);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Description of a todo cannot be empty. Adding todo failed.\n" + line);
                    }
                } else if (userInput.startsWith("deadline")) {
                    try {
                        createDeadline(userInput);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Insufficient input for deadline. Adding deadline failed.\n" + line);
                    }
                } else if (userInput.startsWith("event")) {
                    try {
                        createEvent(userInput);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Insufficient input for event. Adding event failed.\n" + line);
                    }
                } else {
                    throw new TaskTypeException();
                }
            } catch (TaskTypeException e) {
                System.out.println("Sorry, Duke does not recognise the task. " +
                        "Type 'todo'/'deadline'/'event' to add task of respective type.\n" + line);
            } catch (NumberFormatException e) {
                System.out.println("Task number not specified. Please try again.\n" + line);
            } catch (EventTimingException e) {
                System.out.println("Starting and ending time for event are in the wrong order. " +
                        "Please try again.\n" + line);
            }
            try {
                saveTasksToFile(filePath);
            } catch (IOException e) {
                System.out.println("Error saving file.");
            } catch (NullPointerException e) {
                System.out.println("No task found.");
            }
        }

    }

    public static void printTaskList() {
        System.out.println(line + "Here are the tasks in your list:");
        for (int i = 0; i < countTask; i++) {
            int taskIndex = i + 1;
            System.out.print(taskIndex + ". " + tasks[i].printTask() + '\n');
        }
        System.out.println(line);
    }

    public static void markTask(String input) throws NumberFormatException {
        int taskIndex = Integer.parseInt(input.substring(4).trim());
        if (taskIndex >= 1 && taskIndex <= countTask) {
            tasks[taskIndex - 1].markAsDone();
            System.out.println(line + "Task " + taskIndex + " marked as done:\n" +
                    tasks[taskIndex - 1].printTask() + '\n' + line);
        } else {
            System.out.println("Task " + taskIndex + " not found. Please try again.\n" + line);
        }
    }

    public static void unmarkTask(String input) throws NumberFormatException {
        int taskIndex = Integer.parseInt(input.substring(6).trim());
        if (taskIndex >= 1 && taskIndex <= countTask) {
            tasks[taskIndex - 1].markAsUndone();
            System.out.println(line + "Task " + taskIndex + " marked as not done yet:\n" +
                    tasks[taskIndex - 1].printTask() + '\n' + line);
        } else {
            System.out.println("Task " + taskIndex + " not found. Please try again.\n" + line);
        }
    }

    public static void createTodo(String input) throws IndexOutOfBoundsException {
        ToDo todoTask = new ToDo(input.substring(5));
        tasks[countTask] = todoTask;
        countTask++;
        System.out.println(line + "Great! I've added this task:\n" + "   " + todoTask.printTask());
        System.out.println("Now you have " + countTask + " task(s) in the list.\n" + line);
    }

    public static void createDeadline(String input) throws IndexOutOfBoundsException {
        if (!input.contains("/by") || input.indexOf("/by") + 4 > input.length()) {
            throw new StringIndexOutOfBoundsException();
        }
        String deadlineDate = input.substring(input.indexOf("/by") + 4);
        Deadline deadlineTask = new Deadline(input.substring(9, input.indexOf("/by")), deadlineDate);
        tasks[countTask] = deadlineTask;
        countTask++;
        System.out.println(line + "Great! I've added this task:\n" + "   " + deadlineTask.printTask());
        System.out.println("Now you have " + countTask + " task(s) in the list.\n" + line);
    }

    public static void createEvent(String input) throws IndexOutOfBoundsException, EventTimingException {
        if (!input.contains("/from") || !input.contains("/to") || input.indexOf("/from") + 6 > input.length()
                || input.indexOf("/to") + 4 > input.length()) {
            throw new StringIndexOutOfBoundsException();
        } else if (input.indexOf("/from") > input.indexOf("/to")) {
            throw new EventTimingException();
        }
        String startTime = input.substring(input.indexOf("/from") + 6, input.indexOf("/to")).trim();
        String endTime = input.substring(input.indexOf("/to") + 4);
        Event eventTask = new Event(input.substring(6, input.indexOf("/from")), startTime, endTime);
        tasks[countTask] = eventTask;
        countTask++;
        System.out.println(line + "Great! I've added this task:\n" + "   " + eventTask.printTask());
        System.out.println("Now you have " + countTask + " task(s) in the list.\n" + line);
    }

}

