package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String filePath = "savedTasks.txt";

        greeting();
        System.out.println("Attempting to retrieve your files..." + System.lineSeparator());
        try {
            printFileContents(filePath, tasks);
        } catch (FileNotFoundException e) {
            System.out.println("File is not found. Created a new file!");
        }

        boolean isExit = false;
        while (!isExit) {
            String messageFromUser;
            messageFromUser = in.nextLine();
            try {
                if (hasTaskKeyword(messageFromUser)) {
                    Task newTask = handleTaskCreation(messageFromUser);
                    addToList(newTask, tasks);
                } else if (messageFromUser.startsWith("mark") || messageFromUser.startsWith("unmark")) {
                    changeTaskStatus(messageFromUser, tasks);
                } else if (messageFromUser.equals("list")) {
                    displayList(tasks);
                } else if (messageFromUser.equals("bye")) {
                    exitGreeting(tasks, filePath);
                    isExit = true;
                } else if (messageFromUser.startsWith("delete")) {
                    deleteFromList(messageFromUser, tasks);
                } else {
                    throw new DukeException();
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Hmm... some details appear to be missing. Please try again.");
                horizontalLine();
            } catch (DukeException e) {
                System.out.println("I'm sorry, but I don't know what that means");
                horizontalLine();
            }
        }
    }

    public static boolean hasTaskKeyword(String messageFromUser) {
        boolean isToDo = messageFromUser.startsWith("todo");
        boolean isDeadline = messageFromUser.startsWith("deadline");
        boolean isEvent = messageFromUser.startsWith("event");
        return (isToDo || isDeadline || isEvent);
    }

    public static Task handleTaskCreation(String messageFromUser) {
        Task newTask;
        if (messageFromUser.startsWith("todo")) {
            newTask = createToDo(messageFromUser);
        } else if (messageFromUser.startsWith("deadline")) {
            newTask = createDeadline(messageFromUser);
        } else {
            newTask = createEvent(messageFromUser);
        }
        return newTask;
    }

    public static void addToList(Task newTask, ArrayList<Task> tasks) {
        horizontalLine();
        System.out.println("Sure, I've added this task: ");
        displayTask(newTask);
        tasks.add(newTask);
        printNumberOfTasks(tasks.size());
        horizontalLine();
    }

    public static void deleteFromList(String messageFromUser, ArrayList<Task> tasks) {
        horizontalLine();
        // messageFromUser is in the format delete INDEX
        String[] splitMessage = messageFromUser.split(" ");
        int indexToDelete = Integer.parseInt(splitMessage[1]);
        // Decrement 1 as ArrayList is index 0 based
        indexToDelete--;
        Task taskToDelete = tasks.get(indexToDelete);
        tasks.remove(indexToDelete);
        System.out.println("Alright, I have removed this task: ");
        displayTask(taskToDelete);
        printNumberOfTasks(tasks.size());
        horizontalLine();
    }

    public static void changeTaskStatus(String sentence, ArrayList<Task> tasks) {
        String[] words = sentence.split(" ");
        int taskNumber = Integer.parseInt(words[1]);
        // Decrement 1 as ArrayList is index 0 based
        taskNumber--;
        Task currentTask = tasks.get(taskNumber);
        if (words[0].trim().equals("mark")) {
            currentTask.markAsDone();
        } else {
            currentTask.markAsUndone();
        }
        horizontalLine();
    }

    public static void displayList(ArrayList<Task> tasks) {
        int totalNumberOfTasks = tasks.size();
        if (totalNumberOfTasks > 0) {
            System.out.println("Here are the tasks in your list: ");
            for (int index = 0; index < totalNumberOfTasks; index += 1) {
                Task currentTask = tasks.get(index);
                System.out.print(index + 1 + ". ");
                displayTask(currentTask);
            }
        } else {
            System.out.println("There are no tasks to display.");
        }
        horizontalLine();
    }

    public static void displayTask(Task currentTask) {
        // Check task type
        String taskType = currentTask.getTaskType();
        System.out.print(taskType + " ");
        System.out.print(currentTask.getStatus());
        System.out.print(currentTask.getTaskInfo());

        switch (taskType) {
        case "[T]":
            System.out.println();
            break;
        case "[D]":
            Deadline currentDeadline = (Deadline) currentTask;
            String due = currentDeadline.getDueInfo();
            System.out.print("(by:" + due + ")");
            System.out.println();
            break;
        case "[E]":
            Event currentEvent = (Event) currentTask;
            String eventStart = currentEvent.getEventStartInfo();
            String eventEnd = currentEvent.getEventEndInfo();
            System.out.print("(from:" + eventStart + "to:" + eventEnd + ")");
            System.out.println();
            break;
        default:
            System.out.println("Unknown task type error!");
        }
    }

    public static void printNumberOfTasks(int currentIndex) {
        if (currentIndex == 1) {
            System.out.println("You have 1 task!");
        } else {
            System.out.println("You currently have " + currentIndex + " tasks in the list.");
        }
    }

    public static ToDo createToDo(String messageFromUser) {
        // Remove the word "todo" from message
        String[] messageComponents = messageFromUser.split(" ", 2);
        ToDo newToDo = new ToDo(messageComponents[1]);
        return newToDo;
    }

    public static Deadline createDeadline(String messageFromUser) {
        // Remove the word "deadline" from message
        messageFromUser = messageFromUser.substring(8);
        String[] messageComponents = messageFromUser.split("/by", 2);
        Deadline newDeadline = new Deadline(messageComponents[0], messageComponents[1]);
        return newDeadline;
    }

    public static Event createEvent(String messageFromUser) {
        // Remove the word "event" from message
        messageFromUser = messageFromUser.substring(5);
        // Event has 3 aspects message /from startDate /to endDate
        String[] messageComponents = messageFromUser.split("/from", 2);
        String[] timeComponents = messageComponents[1].split("/to", 2);
        Event newEvent = new Event(messageComponents[0], timeComponents[0], timeComponents[1]);
        return newEvent;
    }

    public static void greeting() {
        horizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        horizontalLine();
    }

    public static void exitGreeting(ArrayList<Task> tasks, String filePath) {
        initialiseWritingToFile(tasks, filePath);
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }

    public static void horizontalLine() {
        System.out.println("________________________________________");
    }

    // Adapted from CS2113 Week 6 documentation
    // If there are saved tasks, print them out.
    private static void printFileContents(String filePath, ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        if (s.hasNext()) {
            horizontalLine();
            while (s.hasNext()) {
                String lineInFile = s.nextLine();
                copyToList(lineInFile, tasks, filePath);
            }
            displayList(tasks);
            horizontalLine();
        } else {
            System.out.println("There are no saved tasks.");
            System.out.println("Add your first task :)");
            horizontalLine();
        }
    }

    // Copying text file contents over to tasks list
    private static void copyToList(String line, ArrayList<Task> tasks, String filePath) {
        switch (line.substring(0, 1)) {
        case "T":
            copyTodoToList("T", line.substring(2), tasks);
            break;
        case "D":
            copyDeadlineToList("D", line.substring(2), tasks);
            break;
        case "E":
            copyEventToList("E", line.substring(2), tasks);
            break;
        case "S":
            // Saved data text on the first line (initialisation step)
            break;
        default:
            System.out.println("Unknown task type detected...");
            System.out.println("Skipping task...");
        }
    }

    //Does creation of ToDo and copying to tasks
    private static void copyTodoToList(String taskType, String taskInfo, ArrayList<Task> tasks) {
        // Parse line to split task status and task info
        String[] messageComponents = taskInfo.split("/", 2);
        // Create new todo with task info
        ToDo newToDo = new ToDo(messageComponents[1]);
        // Store todo in list of tasks
        tasks.add(newToDo);
        int currentTaskIndex = tasks.size() - 1;
        // Mark task as done if task status was stored as 1
        if (messageComponents[0].equals("1")) {
            Task currentTask = tasks.get(currentTaskIndex);
            currentTask.markAsDone();
        }
    }

    private static void copyDeadlineToList(String taskType, String taskInfo, ArrayList<Task> tasks) {
        String[] messageComponents = taskInfo.split("/", 3);
        Deadline newDeadline = new Deadline(messageComponents[1], messageComponents[2]);
        tasks.add(newDeadline);
        int currentTaskIndex = tasks.size() - 1;
        if (messageComponents[0].equals("1")) {
            Task currentTask = tasks.get(currentTaskIndex);
            currentTask.markAsDone();
        }
    }

    private static void copyEventToList(String taskType, String taskInfo, ArrayList<Task> tasks) {
        String[] messageComponents = taskInfo.split("/", 4);
        Event newEvent = new Event(messageComponents[1], messageComponents[2], messageComponents[3]);
        tasks.add(newEvent);
        int currentTaskIndex = tasks.size() - 1;
        if (messageComponents[0].equals("1")) {
            Task currentTask = tasks.get(currentTaskIndex);
            currentTask.markAsDone();
        }
    }

    private static void initialiseWritingToFile(ArrayList<Task> tasks, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("Saved tasks: " + System.lineSeparator());
            fw.close();
            FileWriter fwAppend = new FileWriter(filePath, true);
            writeTasksToFile(fwAppend, tasks);
            fwAppend.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    private static void writeTasksToFile(FileWriter fwAppend, ArrayList<Task> tasks) throws IOException {
        int totalNumberOfTasks = tasks.size();
        for (int index = 0; index < totalNumberOfTasks; index += 1) {
            Task currentTask = tasks.get(index);
            String taskType = currentTask.getTaskType().substring(1, 2);
            String taskStatus = currentTask.getStatus();
            int isTaskDone = taskStatus.substring(1, 2).equals("X") ? 1 : 0;
            String taskInfo = currentTask.getTaskInfo();
            writeSpecificTaskToFile(fwAppend, taskType, currentTask, taskStatus, isTaskDone, taskInfo);
        }
    }

    private static void writeSpecificTaskToFile(FileWriter fwAppend, String taskType, Task currentTask,
                                                String taskStatus, int isTaskDone, String taskInfo) throws IOException {
        String additionalTaskInfo = "";
        switch (taskType) {
        case "T":
            fwAppend.write(taskType + "/" + isTaskDone + "/" + taskInfo + System.lineSeparator());
            break;
        case "D":
            Deadline currentDeadline = (Deadline) currentTask;
            additionalTaskInfo = currentDeadline.getDueInfo();
            String deadlineDetails = taskInfo + "/" + additionalTaskInfo;
            fwAppend.write(taskType + "/" + isTaskDone + "/" + deadlineDetails + System.lineSeparator());
            break;
        case "E":
            Event currentEvent = (Event) currentTask;
            String eventStart = currentEvent.getEventStartInfo();
            String eventEnd = currentEvent.getEventEndInfo();
            String eventDetails = taskInfo + "/" + eventStart + "/" + eventEnd;
            fwAppend.write(taskType + "/" + isTaskDone + "/" + eventDetails + System.lineSeparator());
            break;
        default:
            System.out.println("Unknown task type error!");
        }
    }


}
