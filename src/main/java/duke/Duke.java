package duke;


import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Duke {
    private static Parser parser;
    private static UI ui;
    private static Storage storage;
    private static TaskList taskList;
    public static void main(String[] args) throws IOException{
        storage = new Storage();
        ui = new UI();
        parser = new Parser();
        taskList = new TaskList();
        String command = "";
        Scanner in = new Scanner(System.in);
        loadFile();
        ui.showGreetings();
        while (!command.equals("bye")) {
            ui.printLinebreak();
            command = in.nextLine();
            String[] commandLine = parser.parseCommand(command);
            ui.printLinebreak();
            doCommand(commandLine);
        }
    }
    private static void loadFile() throws IOException {
        File file = storage.createFile();
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNext()) {
            String[] savedDataLine;
            savedDataLine = fileReader.nextLine().split("/");
            if (savedDataLine[0].equals("[T]")) {
                taskList.setStoredTasks(parser.parseTodo(savedDataLine, taskList.getStoredTasks()));
            } else if (savedDataLine[0].equals("[D]")) {
                taskList.setStoredTasks(parser.parseDeadline(savedDataLine, taskList.getStoredTasks()));
            } else if (savedDataLine[0].equals("[E]")) {
                taskList.setStoredTasks(parser.parseEvent(savedDataLine, taskList.getStoredTasks()));
            }
            taskList.incrementTaskNum();
        }
    }

    private static void doCommand(String[] commandLine) {
        switch (commandLine[0]) {
        case "todo":
            addTodoTask(commandLine);
            break;
        case "deadline":
            addDeadlineTask(commandLine);
            break;
        case "event":
            addEventTask(commandLine);
            break;
        case "mark":
            markTask(commandLine);
            break;
        case "unmark":
            unmarkTask(commandLine);
            break;
        case "list":
            listTasks();
            break;
        case "delete":
            deleteTask(commandLine);
            break;
        case "find":
            findItem(commandLine);
            break;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            break;
        default:
            try {
                throwErrorInput();
            } catch (IllegalDukeArgumentException e) {
                System.out.println("Oh no! I don't understand what you are saying.");
            }
        }
    }

    private static void throwErrorInput() throws IllegalDukeArgumentException {
        throw new IllegalDukeArgumentException();
    }

    private static void listTasks() {
        listMessage();
        int count = 0;
        for (Task i : taskList.getStoredTasks()) {
            System.out.println((count + 1) + ". " + i.getTypeIcon() +
                    i.getStatusIcon() + " " + i.getDescription());
            count++;
        }
    }

    private static void deleteTask(String[] commandLine) {
        int delIndex = Integer.parseInt((commandLine[1])) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.getStoredTasks().get(delIndex).getTypeIcon()
                    + taskList.getStoredTasks().get(delIndex).getStatusIcon()
                    + " " + taskList.getStoredTasks().get(delIndex).getDescription());
        taskList.removeItem(delIndex);
        try{
            storage.updateFile(taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }

    private static void unmarkTask(String[] commandLine) {
        int unmarkIndex = Integer.parseInt(commandLine[1]) - 1;
        taskList.getStoredTasks().get(unmarkIndex).setDone(false);
        System.out.println("OK, I've marked this task as not done yet.");
        System.out.println(taskList.getStoredTasks().get(unmarkIndex).getTypeIcon() +
                    taskList.getStoredTasks().get(unmarkIndex).getStatusIcon()
                    + " " + taskList.getStoredTasks().get(unmarkIndex).getDescription());
        try{
            storage.updateFile(taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }

    private static void markTask(String[] commandLine) {
        int taskIndex = Integer.parseInt(commandLine[1]) - 1;
        taskList.getStoredTasks().get(taskIndex).setDone(true);
        System.out.println("Nice! I've marked this task as done.");
        System.out.println(taskList.getStoredTasks().get(taskIndex).getTypeIcon() +
                taskList.getStoredTasks().get(taskIndex).getStatusIcon() + " " + taskList.getStoredTasks().get(taskIndex).getDescription());

        try{
            storage.updateFile(taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }

    }

    private static void addEventTask(String[] commandLine) {
        String[] eventString = commandLine[1].split("/from");
        String[] eventStartEnd = eventString[1].split("/to");
        Event ev = new Event(eventString[0], eventStartEnd[0], eventStartEnd[1]);
        taskList.addItem(ev);
        addTaskMessage();
        System.out.println("  " + ev.getTypeIcon() +
                ev.getStatusIcon() + " " + ev.getDescription() + "(from: " + ev.getStart() + " to: " + ev.getEnd() +")");
        taskList.incrementTaskNum();
        displayTasksNum();
        try{
            storage.updateFile(taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }
    private static void addTodoTask(String[] commandLine) {

        try {
            validateTodo(commandLine);
        } catch (IllegalDukeArgumentException e) {
            System.out.println("Oh no! The description of a todo cannot be empty.");
            return;
        }
        Todo td = new Todo(commandLine[1]);
        taskList.addItem(td);
        addTaskMessage();
        System.out.println("  " + td.getTypeIcon() + td.getStatusIcon() + " " + td.getDescription());
        taskList.incrementTaskNum();
        displayTasksNum();
        try {
            storage.updateFile(taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }

    private static void validateTodo(String[] commandLine) throws IllegalDukeArgumentException{
        if (commandLine.length == 1) {
            throw new IllegalDukeArgumentException();
        }
    }

    private static void addDeadlineTask(String[] commandLine) {
        String[] deadlineString = commandLine[1].split("/by");
        Deadline dl = new Deadline(deadlineString[0], deadlineString[1]);
        taskList.addItem(dl);
        addTaskMessage();
        System.out.println("  " + dl.getTypeIcon() +
                dl.getStatusIcon() + " " + dl.getDescription() + "(by: " + dl.getBy() + ")");
        taskList.incrementTaskNum();
        displayTasksNum();
        try{
            storage.updateFile(taskList.getStoredTasks());
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }
    private static void findItem(String[] commandLine) {
        String keyword = commandLine[1];
        int count = 1;
        System.out.println("Here are the matching tasks in your list: ");
        for (Task i : taskList.getStoredTasks()) {
            if (i.getDescription().contains(keyword)) {
                if (i instanceof Deadline) {
                    System.out.println(count + "." + i.getTypeIcon() + i.getStatusIcon() + i.getDescription() + "/by: " + i.getInfo());
                } else if (i instanceof Event) {
                    System.out.println(count + "." + i.getTypeIcon() + i.getStatusIcon() + i.getDescription() + "/start, end: " + i.getInfo());
                } else {
                    System.out.println(count + "." + i.getTypeIcon() + i.getStatusIcon() + i.getDescription() + i.getInfo());
                }
                count++;
            }
        }
    }
    private static void addTaskMessage() {
        System.out.println("Got it. I've added this task.");
    }
    private static void listMessage() {
        System.out.println("Here are the tasks in your list:");
    }
    private static void displayTasksNum() {
        System.out.println("Now you have " + taskList.getTaskNum() + " task(s) in the list.");
    }
}
