package Duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaskSaver {
    static File txtFile = new File("duke.txt");
    static FileWriter FW;
    static Scanner SC;

    static void setUpReadWrite() {
        try {
            txtFile = new File("duke.txt");
            FW = new FileWriter(txtFile, true);
            SC = new Scanner(txtFile);
        } catch (IOException e) {
            System.out.println("Error Occurred");
            e.printStackTrace();
        }
    }

    static LinkedList<Task> loadTasks() {
        setUpReadWrite();
        LinkedList<Task> tasks = new LinkedList<Task>();
        while (SC.hasNextLine()) {
            String[] taskInfo = SC.nextLine().split(" ");
            Task task = TaskCreator.createSavedTask(taskInfo);
            tasks.add(task);
        }
        return tasks;
    }

    static void addTask(Task newTask) {
        try {
            String command = convertTaskToCommandString(newTask);
            FW = new FileWriter("duke.txt", true);
            FW.append(command + '\n');
            FW.close();
        } catch (IOException e) {
            System.out.println("Error Occurred");
            e.printStackTrace();
        }
    }

    static void deleteTask(Task delTask) {
        // should change from stream to list, the go by index
        try {
            String task = convertTaskToCommandString(delTask);
            String taskWithoutCompletionStatus = removeCompletionStatusFromStr(task);
            List<String> lines = Files.lines(txtFile.toPath())
                    .filter(line -> !line.contains(taskWithoutCompletionStatus))
                    .collect(Collectors.toList());
            Files.write(txtFile.toPath(), lines);
        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        }
    }

    static void updateTask(Task newTask) {
        try {
            String task = convertTaskToCommandString(newTask);
            String taskWithoutCompletionStatus = removeCompletionStatusFromStr(task);

            File file = new File("duke.txt");
            List<String> lines = Files.lines(file.toPath())
                    .map(line ->
                            line.contains(taskWithoutCompletionStatus) ?
                                    task :
                                    line
                            )
                    .collect(Collectors.toList());
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        }
    }

    static String convertTaskToCommandString(Task task) {
        String[] info = task.toString().split(" ");
        for (int i = 0; i < info.length; ++i) {
            if (info[i].equals("(by:")) {
                info[i] = "/by";
            } else if (info[i].equals("(from:")) {
                info[i] = "/from";
            } else if (info[i].equals("to:")) {
                info[i] = "/to";
            } else if (info[i].charAt(0) == '[') {
                info[i] = "";
            } else if (info[i].equals("]")) {
                info[i] = "";
            } else if (info[i].charAt(info[i].length() - 1) == ']'
                    || info[i].charAt(info[i].length() - 1) == ')') {
                info[i] = info[i].substring(0, info[i].length() - 1);
            }
        }
        String command = task.getType() + " ";
        for (String word : info) {
            if (!word.equals("")) command += word + " ";
        }
        command += "/done " + (task.isCompleted() ? "done" : "notdone");
        return command;
    }

    static String removeCompletionStatusFromStr(String taskInStr) {
        int counter = taskInStr.length() - 1;
        while (taskInStr.charAt(counter) != '/') {
            counter--;
        }
        return taskInStr.substring(0, counter - 1);
    }
}
