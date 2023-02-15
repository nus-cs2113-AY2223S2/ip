package Duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaskUpdater {
    static FileWriter FW;
    static Scanner SC;

    static void setUpReadWrite() {
        try {
            File f = new File("data/duke.txt");
            FW = new FileWriter("data/duke.txt");
            SC = new Scanner(f);
        } catch (IOException e) {
            File f = new File("data/duke.txt");
            System.out.println("Error Occurred");
            e.printStackTrace();
            setUpReadWrite();
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
            FW.append(command);
            FW.close();
        } catch (IOException e) {
            System.out.println("Error Occurred");
            e.printStackTrace();
        }
    }

    static void deleteTask(Task delTask) {
        try {
            String task = convertTaskToCommandString(delTask);
            File file = new File("data/duke.txt");
            List<String> lines = Files.lines(file.toPath())
                    .filter(line -> line.contains(task))
                    .collect(Collectors.toList());
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        }
    }

    static void updateTask(Task newTask) {
        try {
            String task = convertTaskToCommandString(newTask);
            int charAtEnd = newTask.isCompleted() ? 14 : 11;
            String taskWithoutCompletionStatus = task.substring(0,
                    task.length() - (charAtEnd == 14 ? 11 : 14));

            File file = new File("data/duke.txt");
            List<String> lines = Files.lines(file.toPath())
                    .map(line ->
                            line.substring(0, line.length() - charAtEnd)
                                    .equals(taskWithoutCompletionStatus) ?
                                    task :
                                    line
                            )
                    .collect(Collectors.toList());
//            for (String line : lines) {
//                System.out.println(line.substring(0, line.length() - charAtEnd));
//            }
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
}
