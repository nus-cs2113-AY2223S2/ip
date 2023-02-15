package duke;


import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Duke {
    private static int taskNum = 0;
    private static final String filePath = "duke.txt";



    public static void main(String[] args) throws IOException{
        String command = "";
        Scanner in = new Scanner(System.in);
        ArrayList<Task> storedTasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner fileReader = new Scanner(file);

        while (fileReader.hasNext()) {
            String[] savedDataLine;
            savedDataLine = fileReader.nextLine().split("/");
            if (savedDataLine[0].equals("[T]")) {
                Todo todo = new Todo(savedDataLine[2]);
                if (savedDataLine[1].equals("[X]")) {
                    todo.setDone(true);
                }
                storedTasks.add(todo);
            } else if (savedDataLine[0].equals("[D]")) {
                Deadline deadline = new Deadline(savedDataLine[2], savedDataLine[3]);
                if (savedDataLine[1].equals("[X]")) {
                    deadline.setDone(true);
                }
                storedTasks.add(deadline);
            } else if (savedDataLine[0].equals("[E]")) {
                String[] period = savedDataLine[3].split(",");
                Event event = new Event(savedDataLine[2], period[0], period[1]);
                if (savedDataLine[1].equals("[X]")) {
                    event.setDone(true);
                }
                storedTasks.add(event);
            }
            taskNum++;
        }

        showGreetings();

        while (!command.equals("bye")) {
            printLineBreak();
            command = in.nextLine();
            String[] commandLine = command.split(" ", 2);
            printLineBreak();
            doCommand(storedTasks, commandLine);

        }



    }

    private static void doCommand(ArrayList<Task> storedTasks, String[] commandLine) {
        switch (commandLine[0]) {
        case "todo":
            addTodoTask(storedTasks, commandLine);
            break;
        case "deadline":
            addDeadlineTask(storedTasks, commandLine);
            break;
        case "event":
            addEventTask(storedTasks, commandLine);
            break;
        case "mark":
            markTask(storedTasks, commandLine);
            break;
        case "unmark":
            unmarkTask(storedTasks, commandLine);
            break;
        case "list":
            listTasks(storedTasks);
            break;
        case "delete":
            deleteTask(storedTasks, commandLine);
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

    private static void listTasks(ArrayList<Task> storedTasks) {
        listMessage();
        int count = 0;
        for (Task i : storedTasks) {
            System.out.println((count + 1) + ". " + i.getTypeIcon() +
                    i.getStatusIcon() + " " + i.getDescription());
            count++;
        }
    }

    private static void deleteTask(ArrayList<Task> storedTasks, String[] commandLine) {
        int delIndex = Integer.parseInt((commandLine[1])) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(storedTasks.get(delIndex).getTypeIcon() + storedTasks.get(delIndex).getStatusIcon()
                + " " + storedTasks.get(delIndex).getDescription());
        storedTasks.remove(delIndex);
        try{
            updateFile(storedTasks);
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }    }

    private static void unmarkTask(ArrayList<Task> storedTasks, String[] commandLine) {
        int unmarkIndex = Integer.parseInt(commandLine[1]) - 1;
        storedTasks.get(unmarkIndex).setDone(false);
        System.out.println("OK, I've marked this task as not done yet.");
        System.out.println(storedTasks.get(unmarkIndex).getTypeIcon() +
                storedTasks.get(unmarkIndex).getStatusIcon() + " " + storedTasks.get(unmarkIndex).getDescription());
        try{
            updateFile(storedTasks);
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }

    private static void markTask(ArrayList<Task> storedTasks, String[] commandLine) {
        int taskIndex = Integer.parseInt(commandLine[1]) - 1;
        storedTasks.get(taskIndex).setDone(true);
        System.out.println("Nice! I've marked this task as done.");
        System.out.println(storedTasks.get(taskIndex).getTypeIcon() +
                storedTasks.get(taskIndex).getStatusIcon() + " " + storedTasks.get(taskIndex).getDescription());

        try{
            updateFile(storedTasks);
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }

    }

    private static void addEventTask(ArrayList<Task> storedTasks, String[] commandLine) {
        String[] eventString = commandLine[1].split("/from");
        String[] eventStartEnd = eventString[1].split("/to");
        Event ev = new Event(eventString[0], eventStartEnd[0], eventStartEnd[1]);
        storedTasks.add(ev);
        addTaskMessage();
        System.out.println("  " + ev.getTypeIcon() +
                ev.getStatusIcon() + " " + ev.getDescription() + "(from: " + ev.getStart() + " to: " + ev.getEnd() +")");
        taskNum++;
        displayTasksNum();
        try{
            updateFile(storedTasks);
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }
    private static void updateFile(ArrayList<Task> storedTasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task i : storedTasks) {
            String textToAdd = i.getTypeIcon() + "/" + i.getStatusIcon() + "/" + i.getDescription() + "/" +
                    i.getInfo() + "\n";
            fileWriter.write(textToAdd);
        }
        fileWriter.close();
    }

    private static void addTodoTask(ArrayList<Task> storedTasks, String[] commandLine) {

        try {
            validateTodo(commandLine);
        } catch (IllegalDukeArgumentException e) {
            System.out.println("Oh no! The description of a todo cannot be empty.");
            return;
        }
        Todo td = new Todo(commandLine[1]);
        storedTasks.add(td);
        addTaskMessage();
        System.out.println("  " + td.getTypeIcon() + td.getStatusIcon() + " " + td.getDescription());
        taskNum++;
        displayTasksNum();
        try {
            updateFile(storedTasks);
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }

    private static void validateTodo(String[] commandLine) throws IllegalDukeArgumentException{
        if (commandLine.length == 1) {
            throw new IllegalDukeArgumentException();
        }
    }

    private static void addDeadlineTask(ArrayList<Task> storedTasks, String[] commandLine) {
        String[] deadlineString = commandLine[1].split("/by");
        Deadline dl = new Deadline(deadlineString[0], deadlineString[1]);
        storedTasks.add(dl);
        addTaskMessage();
        System.out.println("  " + dl.getTypeIcon() +
                dl.getStatusIcon() + " " + dl.getDescription() + "(by: " + dl.getBy() + ")");
        taskNum++;
        displayTasksNum();
        try{
            updateFile(storedTasks);
        } catch (IOException e) {
            System.out.println("Oops! Something broke: " + e);
        }
    }

    private static void showGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    private static void printLineBreak() {
        System.out.println("--------------------------------------------------------------");
    }

    private static void addTaskMessage() {
        System.out.println("Got it. I've added this task.");
    }
    private static void listMessage() {
        System.out.println("Here are the tasks in your list:");
    }
    private static void displayTasksNum() {
        System.out.println("Now you have " + taskNum + " task(s) in the list.");
    }
}
