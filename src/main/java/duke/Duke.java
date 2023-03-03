package duke;

import duke.storage.ReadFromFile;
import duke.storage.SaveToFile;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.Ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {
    final static String FILE_PATH = "savedTasks.txt";
    public static void main(String[] args) {
        Ui ui = new Ui();
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        ui.showWelcomeMessage();
        ui.showSavedTasksRetrievalMessage();
        try {
            printFileContents(FILE_PATH, tasks);
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundMessage();
            ui.showNewFileCreationMessage();
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
                    saveAndExit(tasks, FILE_PATH);
                    isExit = true;
                } else if (messageFromUser.startsWith("delete")) {
                    deleteFromList(messageFromUser, tasks);
                } else {
                    throw new DukeException();
                }
            } catch (IndexOutOfBoundsException e) {
                ui.showMissingAttributesMessage();
            } catch (DukeException e) {
                ui.showIncorrectCommandWarning();
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
        Ui ui = new Ui();
        ui.horizontalLine();
        ui.showTaskAddedMessage();
        displayTask(newTask);
        tasks.add(newTask);
        ui.printNumberOfTasks(tasks.size());
        ui.horizontalLine();
    }

    public static void deleteFromList(String messageFromUser, ArrayList<Task> tasks) {
        Ui ui = new Ui();
        ui.horizontalLine();
        // messageFromUser is in the format delete INDEX
        String[] splitMessage = messageFromUser.split(" ");
        int indexToDelete = Integer.parseInt(splitMessage[1]);
        // Decrement 1 as ArrayList is index 0 based
        indexToDelete--;
        Task taskToDelete = tasks.get(indexToDelete);
        tasks.remove(indexToDelete);
        ui.showTaskRemovedMessage();
        displayTask(taskToDelete);
        ui.printNumberOfTasks(tasks.size());
        ui.horizontalLine();
    }

    public static void changeTaskStatus(String sentence, ArrayList<Task> tasks) {
        Ui ui = new Ui();
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
        ui.horizontalLine();
    }

    public static void displayList(ArrayList<Task> tasks) {
        Ui ui = new Ui();
        int totalNumberOfTasks = tasks.size();
        if (totalNumberOfTasks > 0) {
            ui.showDisplayListHeaderMessage();
            for (int index = 0; index < totalNumberOfTasks; index += 1) {
                Task currentTask = tasks.get(index);
                System.out.print(index + 1 + ". ");
                displayTask(currentTask);
            }
        } else {
            ui.showNoTasksToDisplayMessage();
        }
        ui.horizontalLine();
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



    public static ToDo createToDo(String messageFromUser) {
        // Remove the word "todo" from message
        String[] messageComponents = messageFromUser.split(" ", 2);
        return new ToDo(messageComponents[1]);
    }

    public static Deadline createDeadline(String messageFromUser) {
        // Remove the word "deadline" from message
        messageFromUser = messageFromUser.substring(8);
        String[] messageComponents = messageFromUser.split("/by", 2);
        return new Deadline(messageComponents[0], messageComponents[1]);
    }

    public static Event createEvent(String messageFromUser) {
        // Remove the word "event" from message
        messageFromUser = messageFromUser.substring(5);
        // Event has 3 aspects message /from startDate /to endDate
        String[] messageComponents = messageFromUser.split("/from", 2);
        String[] timeComponents = messageComponents[1].split("/to", 2);
        return new Event(messageComponents[0], timeComponents[0], timeComponents[1]);
    }


    public static void saveAndExit(ArrayList<Task> tasks, String filePath) {
        Ui ui = new Ui();
        SaveToFile saveToFile = new SaveToFile();
        saveToFile.initialiseWritingToFile(tasks, filePath);
        ui.showGoodbyeMessage();
    }

    // If there are saved tasks, print them out.
    private static void printFileContents(String filePath, ArrayList<Task> tasks) throws FileNotFoundException {
        Ui ui = new Ui();
        ReadFromFile readFromFile = new ReadFromFile();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        if (s.hasNext()) {
            ui.horizontalLine();
            while (s.hasNext()) {
                String lineInFile = s.nextLine();
                readFromFile.copyToArrayList(lineInFile, tasks, filePath);
            }
            displayList(tasks);
            ui.horizontalLine();
        } else {
            ui.showNoTasksToDisplayMessage();
            ui.horizontalLine();
        }
    }

}
