package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void taskPrinter(ArrayList<Task> taskArray, int index) {
        if (taskArray.get(index) instanceof ToDo) {
            System.out.println(index + ". " + ((ToDo) taskArray.get(index)).getToDo() + " " + taskArray.get(index).getDoneStatus()
                    + " " + taskArray.get(index).getDescription());
        }
        if (taskArray.get(index) instanceof Deadline) {
            System.out.println(index + ". " + ((Deadline) taskArray.get(index)).getDeadline() + " " + taskArray.get(index).getDoneStatus()
                    + " " + taskArray.get(index).getDescription() + " (" + ((Deadline) taskArray.get(index)).getDate() + ")");
        }
        if (taskArray.get(index) instanceof Event) {
            System.out.println(index + ". " + ((Event) taskArray.get(index)).getEvent() + " " + taskArray.get(index).getDoneStatus()
                    + " " + taskArray.get(index).getDescription() + " (" + ((Event) taskArray.get(index)).getStartAndEnd() + ")");
        }
    }

    public static void listPrinter(ArrayList<Task> taskArray, int taskIndex) {
        for (int j = 0; j < taskIndex; ++j) {
            taskPrinter(taskArray, j);
        }
    }

    public static void UI(String ui, ArrayList<Task> taskArray, int taskIndex) {
        switch (ui) {
        case "welcome":
            System.out.println("Good day. YodaBot, I am.");
            System.out.println("Assistance, you need?");
            break;
        case "bye":
            System.out.println("See you soon, I hope. Goodbye.");
            break;
        case "empty":
            System.out.println("Empty, list is.");
            break;
        case "list":
            System.out.println("As shown, list is:");
            listPrinter(taskArray, taskIndex);
            break;
        case "marked":
            System.out.println("Marked it, I have:");
            taskPrinter(taskArray, taskIndex);
            break;
        case "unmarked":
            System.out.println("Unmarked it, I have:");
            taskPrinter(taskArray, taskIndex);
            break;
        case "notInList":
            System.out.println("In list, it is not.");
            break;
        case "addTask":
            System.out.println("Added, I have:");
            taskPrinter(taskArray, taskIndex);
            break;
        case "delete":
            System.out.println("Deleted, I have");
            break;
        case "wrongTodo":
            System.out.println("Error: To do what, I ask?");
            break;
        case "generalError":
            System.out.println("Understand, I do not.");
            break;
        default:
            break;
        }
    }

    public static String[] parser(String command) {
        String firstWord;
        String remainingWords;
        String[] wordList = new String[2];
        if (command.contains(" ")) {
            int firstSpaceIndex = command.indexOf(" ");
            firstWord = command.substring(0, firstSpaceIndex);
            remainingWords = command.substring(firstSpaceIndex + 1, command.length());
            wordList[0] = firstWord;
            wordList[1] = remainingWords;
        } else {
            firstWord = command;
            wordList[0] = firstWord;
        }
        return wordList;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] wordList = new String[2];
        ArrayList<Task> taskArray = new ArrayList<Task>();
        ArrayList<String> fileList = new ArrayList<String>();
        int taskIndex = 0;
        UI("welcome", taskArray, taskIndex);

        try {
            File file = new File("duke.txt");
            if (file.exists()) {
                Scanner fileScanner = new Scanner(new File("duke.txt"));
                while (fileScanner.hasNextLine()) {
                    fileList.add(fileScanner.nextLine());
                }
                for (int i = 0; i < fileList.size(); i++) {
                    taskIndex++;
                    String listedTask = fileList.get(i).substring(3, 6); //listedTask == [T] or [D] or [E]
                    String listDone = fileList.get(i).substring(7, 10); //listDone == [ ] or [X]
                    switch (listedTask) {
                    case "[T]":
                        String listedDescription = fileList.get(i).substring(11);
                        Task listTask;
                        if (listDone.equals("[ ]")) {
                            listTask = new ToDo(listedDescription, false, "[T]");
                        } else {
                            listTask = new ToDo(listedDescription, true, "[T]");
                        }
                        taskArray.add(listTask);
                        break;
                    case "[D]":
                        int index1 = fileList.get(i).indexOf("(");
                        String listedDescription1 = fileList.get(i).substring(11, index1 - 1);
                        String listedDate = fileList.get(i).substring(index1 + 1, fileList.get(i).length() - 1);
                        Task listTask1;
                        if (listDone.equals("[ ]")) {
                            listTask1 = new Deadline(listedDescription1, false, "[D]", listedDate);
                        } else {
                            listTask1 = new Deadline(listedDescription1, true, "[D]", listedDate);
                        }
                        taskArray.add(listTask1);
                        break;
                    case "[E]":
                        int index2 = fileList.get(i).indexOf("(");
                        String listedDescription2 = fileList.get(i).substring(11, index2 - 1);
                        String listedDate1 = fileList.get(i).substring(index2 + 1, fileList.get(i).length() - 1);
                        Task listTask2;
                        if (listDone.equals("[ ]")) {
                            listTask2 = new Event(listedDescription2, false, "[E]", listedDate1);
                        } else {
                            listTask2 = new Event(listedDescription2, true, "[E]", listedDate1);
                        }
                        taskArray.add(listTask2);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            String command = scanner.nextLine(); //reads in input
            wordList = parser(command);
            if (wordList[0].equals("bye")) {
                UI("bye", taskArray, taskIndex);
                break;
            }
            switch (wordList[0]) {
            //if command is list, either display empty or display list
            case "List":
                if (taskIndex == 0) {
                    UI("empty", taskArray, taskIndex);
                } else {
                    UI("list", taskArray, taskIndex);
                }
                break;
            //if command is mark, mark the appropriate entry in the list
            case "Mark":
                int indexToMark = Integer.parseInt(wordList[1]);
                if (taskIndex == 0 || indexToMark > taskIndex) {
                    UI("notInList", taskArray, taskIndex);
                } else {
                    taskArray.get(indexToMark).isDone = true;
                    taskPrinter(taskArray, indexToMark);
                }
                break;
            //if command is unmark, unmark the appropriate entry in the list
            case "Unmark":
                int indexToUnmark = Integer.parseInt(wordList[1]);
                if (taskIndex == 0 || indexToUnmark > taskIndex) {
                    System.out.println("In list, it is not.");
                    break;
                } else {
                    taskArray.get(indexToUnmark).isDone = false;
                    taskPrinter(taskArray, indexToUnmark);
                }
                break;
            //if command is todo, prompt for task and add into list
            case "Todo":
                try {
                    if (wordList[1] == null) {
                        throw new IllegalCommandException();
                    }
                    Task newTask = new ToDo(wordList[1], false, "[T]");
                    taskArray.add(newTask);
                    UI("addTask", taskArray, taskIndex);
                    ++taskIndex;
                } catch (IllegalCommandException e) {
                    UI("wrongTodo", taskArray, taskIndex);
                    break;
                }
                break;
            //if command is Deadline, prompt for task then deadline and add into list
            case "Deadline":
                int demarcator = wordList[1].indexOf("/");
                String work = wordList[1].substring(0, demarcator - 1);
                String date = wordList[1].substring(demarcator + 1, wordList[1].length());
                Task newTask = new Deadline(work, false, "[D]", date);
                taskArray.add(newTask);
                UI("addTask", taskArray, taskIndex);
                ++taskIndex;
                break;
            //if command is Event, prompt for event, then prompt for start and end date
            case "Event":
                demarcator = wordList[1].indexOf("/");
                work = wordList[1].substring(0, demarcator - 1);
                date = wordList[1].substring(demarcator + 1, wordList[1].length());
                Task newTask1 = new Event(work, false, "[E]", date);
                taskArray.add(newTask1);
                UI("addTask", taskArray, taskIndex);
                ++taskIndex;
                break;
            case "Delete":
                int indexToDelete = Integer.parseInt(wordList[1]);
                if (taskIndex == 0 || indexToDelete > taskIndex) {
                    UI("notInList", taskArray, taskIndex);
                } else {
                    taskArray.remove(indexToDelete);
                    taskIndex -= 1;
                    UI("delete", taskArray, taskIndex);
                }
                break;
            //throws IllegalCommandException if the input is not one of the above cases
            default:
                try {
                    throw new IllegalCommandException();
                } catch (IllegalCommandException e1) {
                    UI("generalError", taskArray, taskIndex);
                }
            }
        }

        try {
            File file = new File("duke.txt");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            PrintWriter printWriter = new PrintWriter(file);
            if (taskIndex == 0) {
                printWriter.println("Empty, list is.");
            } else {
                for (int j = 0; j < taskIndex; ++j) {
                    if (taskArray.get(j) instanceof ToDo) {
                        printWriter.println(j + ". " + ((ToDo) taskArray.get(j)).getToDo() + " " + taskArray.get(j).getDoneStatus() + " " + taskArray.get(j).getDescription());
                    }
                    if (taskArray.get(j) instanceof Deadline) {
                        printWriter.println(j + ". " + ((Deadline) taskArray.get(j)).getDeadline() + " " + taskArray.get(j).getDoneStatus() + " " + taskArray.get(j).getDescription() + " (" + ((Deadline) taskArray.get(j)).getDate() + ")");
                    }
                    if (taskArray.get(j) instanceof Event) {
                        printWriter.println(j + ". " + ((Event) taskArray.get(j)).getEvent() + " " + taskArray.get(j).getDoneStatus() + " " + taskArray.get(j).getDescription() + " (" + ((Event) taskArray.get(j)).getStartAndEnd() + ")");
                    }
                }
            }
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
