package duke;

import duke.exceptions.InsufficientInputException;
import duke.exceptions.InvalidTaskNumberException;
import duke.exceptions.UnkownCommandException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Duke {
    public static String HorizontalLine = "__________________________\n";
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) throws UnkownCommandException {
        File f = new File("./data.txt");
        try{
            readData(f);
        }catch (IOException e){
            System.out.println("No file exists");
        };
        System.out.println(HorizontalLine + "Hello! I'm Duke\n" + "What can I do for you?\n"
                + HorizontalLine);
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            try {
                String[] inputWords = input.split(" ", 2);
                switch (inputWords[0]) {
                case "list":
                    displayList();
                    break;
                case "mark":
                    markTask(inputWords);
                    break;
                case "unmark":
                    unmarkTask(inputWords);
                    break;
                case "todo":
                    createTodo(inputWords);
                    break;
                case "deadline":
                    createDeadline(inputWords);
                    break;
                case "event":
                    createEvent(inputWords);
                    break;
                case "delete":
                    deleteTask(inputWords);
                    break;
                default:
                    throw new UnkownCommandException("Unknown command, please try again.");
                }
            } catch (InsufficientInputException e) {
                System.out.println(e.getMessage());
            } catch (UnkownCommandException e) {
                System.out.println(e.getMessage());
            } catch (InvalidTaskNumberException e) {
                System.out.println(e.getMessage());
            }
            input = in.nextLine();
        }
        try {
            createTasklistFile();
            saveTasklist("./data.txt", taskList);
        } catch (IOException e) {
            System.out.println("Error occurred while saving file\n");
        }
        System.out.println(HorizontalLine + "Goodbye!" + "\n" + HorizontalLine);
    }

    private static void readData(File f) throws IOException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String input = s.nextLine();
            String[] data = input.split("\\|");
            switch(data[0].trim()) {
            case "todo":
                taskList.add(new ToDo(data[2].trim()));
                taskList.get(Task.maxTaskNumber).setTaskType("todo");
                break;
            case "deadline":
                taskList.add(new Deadline(data[2].trim(), data[3].trim()));
                taskList.get(Task.maxTaskNumber).setTaskType("deadline");
                break;

            case "event":
                taskList.add(new Event(data[2].trim(), data[3].trim(), data[4].trim()));
                taskList.get(Task.maxTaskNumber).setTaskType("event");
                break;
            }
            if(data[1].trim().equals("X")) {
                taskList.get(Task.maxTaskNumber).setDone();
            }
            Task.maxTaskNumber++;
            System.out.println(input);
        }
    }

    private static void createTasklistFile() throws IOException {
        File tasklistFile = new File("./data.txt");
        if (!tasklistFile.createNewFile()) {
            throw new IOException();
        }
    }

    private static void saveTasklist(String path, ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(path, false);
        for (Task i : taskList) {
            String taskType = i.getTaskType();
            switch (taskType) {
            case "todo":
                fw.write(taskType + " | " + i.getDone() + " | " + i.getTaskName() + System.lineSeparator());
                break;

            case "deadline":
                Deadline d = (Deadline) i;
                fw.write(taskType + " | " + i.getDone() + " | " + i.getTaskName() + " | " + d.getBy() + System.lineSeparator());
                break;
            case "event":
                Event e = (Event) i;
                fw.write(taskType + "|" + i.getDone()+ "|" + i.getTaskName() + "|" + e.getStartDate() + "|"
                        + e.getEndDate()  + System.lineSeparator());
            }
        }
        fw.close();
    }

    private static void createEvent(String[] inputWords) throws InsufficientInputException {
        if (inputWords.length < 2) {
            throw new InsufficientInputException("Event command has insufficient input, please try again.");
        }
        String[] event = inputWords[1].split(" /from | /to ", 3);
        if (event.length < 3) {
            throw new InsufficientInputException("Event command has insufficient input, please try again.");
        }
        taskList.add(new Event(event[0], event[1], event[2]));
        taskList.get(Task.maxTaskNumber).setTaskType("event");
        Task.maxTaskNumber++;
        System.out.printf(HorizontalLine + "Event added: %s (from: %s to: %s)\n" + HorizontalLine, event[0], event[1], event[2]);
    }

    private static void createDeadline(String[] inputWords) throws InsufficientInputException {
        if (inputWords.length < 2) {
            throw new InsufficientInputException("Deadline command has insufficient input, please try again.");
        }
        String[] deadline = inputWords[1].split(" /by ", 2);
        if (deadline.length < 2) {
            throw new InsufficientInputException("Deadline command has insufficient input, please try again.");
        }
        taskList.add(new Deadline(deadline[0], deadline[1]));
        taskList.get(Task.maxTaskNumber).setTaskType("deadline");
        Task.maxTaskNumber++;
        System.out.printf(HorizontalLine + "Deadline added: %s (by: %s)\n" + HorizontalLine, deadline[0], deadline[1]);
    }

    private static void createTodo(String[] inputWords) throws InsufficientInputException {
        if (inputWords.length < 2) {
            throw new InsufficientInputException("Todo command has insufficient input, please try again.");
        }
        taskList.add(new ToDo(inputWords[1]));
        taskList.get(Task.maxTaskNumber).setTaskType("todo");
        Task.maxTaskNumber++;
        System.out.println(HorizontalLine + "To do added: " + inputWords[1] + "\n" + HorizontalLine);
    }

    private static void unmarkTask(String[] inputWords) throws InvalidTaskNumberException, InsufficientInputException {
        if (inputWords.length < 2) {
            throw new InsufficientInputException("Task number not specified, please try again");
        }
        int unmarkTaskNumber = Integer.valueOf(inputWords[1]) - 1;
        if (unmarkTaskNumber >= Task.maxTaskNumber || unmarkTaskNumber < 0) {
            throw new InvalidTaskNumberException("Task number not found, please try again.");
            // System.out.println("No such task found\n" + HorizontalLine);
        } else {
            taskList.get(unmarkTaskNumber).unsetDone();
            System.out.println(HorizontalLine + "Task set as undone: " + taskList.get(unmarkTaskNumber).getTaskName() +
                    "\n" + HorizontalLine);
        }
    }

    private static void markTask(String[] inputWords) throws InvalidTaskNumberException {
        int markTaskNumber = Integer.valueOf(inputWords[1]) - 1;
        if (markTaskNumber >= Task.maxTaskNumber || markTaskNumber < 0) {
            throw new InvalidTaskNumberException("Task number not found, please try again.");
            //System.out.println("No such task found\n" + HorizontalLine);
        } else {
            taskList.get(markTaskNumber).setDone();
            System.out.println(HorizontalLine + "Task set as done: " + taskList.get(markTaskNumber).getTaskName() + "\n"
                    + HorizontalLine);
        }
    }

    private static void displayList() {
        if (taskList.size() != 0) {
            System.out.println(HorizontalLine + "List of Tasks: \n");
            for (int i = 0; i < Task.maxTaskNumber; i++) {
                System.out.printf("%d.", i + 1);
                taskList.get(i).getTaskStatus();
            }
            System.out.println(HorizontalLine);
        } else {
            System.out.println(HorizontalLine + "no task added yet\n" + HorizontalLine);
        }
    }
    private static void deleteTask(String[] inputWords) throws InvalidTaskNumberException{
        int taskIndex = Integer.valueOf(inputWords[1])-1;
        if(taskIndex > Task.maxTaskNumber || taskIndex < 0) {
            throw new InvalidTaskNumberException("Task number not found, please try again.");
        }else{
            taskList.remove(taskIndex);
            Task.maxTaskNumber--;
            System.out.printf(HorizontalLine + "Task %d has been deleted\n" + HorizontalLine, taskIndex+1);
        }
    }
}

