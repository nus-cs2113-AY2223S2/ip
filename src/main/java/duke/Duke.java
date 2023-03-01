package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

import static duke.Storage.fileWrite;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] wordList;
        TaskList taskList = new TaskList();
        ArrayList<String> fileList = new ArrayList<>();
        int Index = 0;
        Storage.store(fileList, taskList, Index);
        int taskIndex = taskList.getIndex();
        UI.printer("welcome", taskList.getTaskArray(), taskIndex);
        while (true) {
            String command = scanner.nextLine(); //reads in input
            wordList = Parser.parser(command);
            if (wordList[0].equals("bye")) {
                UI.printer("bye", taskList.getTaskArray(), taskIndex);
                break;
            }
            switch (wordList[0]) {
            //if command is list, either display empty or display list
            case "List":
                if (taskIndex == 0) {
                    UI.printer("empty", taskList.getTaskArray(), taskIndex);
                } else {
                    UI.printer("list", taskList.getTaskArray(), taskIndex);
                }
                break;
            //if command is mark, mark the appropriate entry in the list
            case "Mark":
                int indexToMark = Integer.parseInt(wordList[1]);
                if (taskIndex == 0 || indexToMark > taskIndex) {
                    UI.printer("notInList", taskList.getTaskArray(), taskIndex);
                } else {
                    taskList.getTaskArray().get(indexToMark).isDone = true;
                    UI.taskPrinter(taskList.getTaskArray(), indexToMark);
                }
                break;
            //if command is unmark, unmark the appropriate entry in the list
            case "Unmark":
                int indexToUnmark = Integer.parseInt(wordList[1]);
                if (taskIndex == 0 || indexToUnmark > taskIndex) {
                    System.out.println("In list, it is not.");
                    break;
                } else {
                    taskList.getTaskArray().get(indexToUnmark).isDone = false;
                    UI.taskPrinter(taskList.getTaskArray(), indexToUnmark);
                }
                break;
            //if command is todo, prompt for task and add into list
            case "Todo":
                try {
                    if (wordList[1] == null) {
                        throw new IllegalCommandException();
                    }
                    Task newTask = new ToDo(wordList[1], false, "[T]");
                    taskList.getTaskArray().add(newTask);
                    UI.printer("addTask", taskList.getTaskArray(), taskIndex);
                    ++taskIndex;
                } catch (IllegalCommandException e) {
                    UI.printer("wrongTodo", taskList.getTaskArray(), taskIndex);
                    break;
                }
                break;
            //if command is Deadline, prompt for task then deadline and add into list
            case "Deadline":
                int demarcator = wordList[1].indexOf("/");
                String work = wordList[1].substring(0, demarcator - 1);
                String date = wordList[1].substring(demarcator + 1, wordList[1].length());
                Task newTask = new Deadline(work, false, "[D]", date);
                taskList.getTaskArray().add(newTask);
                UI.printer("addTask", taskList.getTaskArray(), taskIndex);
                ++taskIndex;
                break;
            //if command is Event, prompt for event, then prompt for start and end date
            case "Event":
                demarcator = wordList[1].indexOf("/");
                work = wordList[1].substring(0, demarcator - 1);
                date = wordList[1].substring(demarcator + 1, wordList[1].length());
                Task newTask1 = new Event(work, false, "[E]", date);
                taskList.getTaskArray().add(newTask1);
                UI.printer("addTask", taskList.getTaskArray(), taskIndex);
                ++taskIndex;
                break;
            case "Delete":
                int indexToDelete = Integer.parseInt(wordList[1]);
                if (taskIndex == 0 || indexToDelete > taskIndex) {
                    UI.printer("notInList", taskList.getTaskArray(), taskIndex);
                } else {
                    taskList.getTaskArray().remove(indexToDelete);
                    taskIndex -= 1;
                    UI.printer("delete", taskList.getTaskArray(), taskIndex);
                }
                break;
            //throws IllegalCommandException if the input is not one of the above cases
            default:
                try {
                    throw new IllegalCommandException();
                } catch (IllegalCommandException e1) {
                    UI.printer("generalError", taskList.getTaskArray(), taskIndex);
                }
            }
        }
        fileWrite(taskList, taskIndex);
    }
}
