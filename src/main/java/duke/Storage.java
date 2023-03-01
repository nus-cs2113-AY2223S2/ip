package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static void store(ArrayList<String> fileList, TaskList storageList, int taskIndex) {
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
                        storageList.addTask(listTask);
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
                        storageList.addTask(listTask1);
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
                        storageList.addTask(listTask2);
                        break;
                    }
                }
            }
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void fileWrite (TaskList taskList, int taskIndex) {
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
                    if (taskList.getTaskArray().get(j) instanceof ToDo) {
                        printWriter.println(j + ". " + ((ToDo) taskList.getTaskArray().get(j)).getToDo() + " " + taskList.getTaskArray().get(j).getDoneStatus() + " " + taskList.getTaskArray().get(j).getDescription());
                    }
                    if (taskList.getTaskArray().get(j) instanceof Deadline) {
                        printWriter.println(j + ". " + ((Deadline) taskList.getTaskArray().get(j)).getDeadline() + " " + taskList.getTaskArray().get(j).getDoneStatus() + " " + taskList.getTaskArray().get(j).getDescription() + " (" + ((Deadline) taskList.getTaskArray().get(j)).getDate() + ")");
                    }
                    if (taskList.getTaskArray().get(j) instanceof Event) {
                        printWriter.println(j + ". " + ((Event) taskList.getTaskArray().get(j)).getEvent() + " " + taskList.getTaskArray().get(j).getDoneStatus() + " " + taskList.getTaskArray().get(j).getDescription() + " (" + ((Event) taskList.getTaskArray().get(j)).getStartAndEnd() + ")");
                    }
                }
            }
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
