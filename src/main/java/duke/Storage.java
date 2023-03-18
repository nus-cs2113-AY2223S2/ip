package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public int initializeList(ArrayList<Todo> tasks, int counter, String absoluteFilePath) throws FileNotFoundException {
        String taskType;
        String task = null;
        String inputString;

        try {

            File myFile = new File(absoluteFilePath);
            if (myFile.createNewFile()) {
                System.out.println("New List backup is created! List items will be saved to " + "disk after you exit the program!");
            } else {
                System.out.println("List backup already exists and has been initialized.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(new File(absoluteFilePath));
        Scanner in = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            inputString = scanner.nextLine();
            int descriptionPosition = inputString.indexOf(" ");
            if (descriptionPosition == -1) {
                taskType = inputString;
            } else {
                int endPosition = inputString.length();
                taskType = inputString.substring(0, descriptionPosition);
                task = inputString.substring(descriptionPosition + 1, endPosition);
            }

            switch (taskType) {
            case "todo":
                tasks.add(new Todo(task));
                tasks.get(counter).print();
                counter++;
                break;

            case "deadline":
                int deadlinePosition = task.indexOf("/");
                int endOfLine = task.length();
                String taskName = task.substring(0, deadlinePosition);
                String deadline = task.substring(deadlinePosition + 1, endOfLine);
                tasks.add(new Deadline(taskName, deadline));
                tasks.get(counter).print();
                counter++;
                break;

            case "event":
                int deadlineStartPosition = task.indexOf("/");
                int deadlineEndPosition = task.indexOf("|");
                endOfLine = task.length();
                taskName = task.substring(0, deadlineStartPosition);
                String deadlineStart = task.substring(deadlineStartPosition + 1, deadlineEndPosition);
                String deadlineEnd = task.substring(deadlineEndPosition + 1, endOfLine);
                tasks.add(new Event(taskName, deadlineStart, deadlineEnd));
                tasks.get(counter).print();
                counter++;
                break;
            }
        }
        scanner.close();
        return counter;
    }

    public String findFilePath() {
        String workingDirectory = System.getProperty("user.dir");
        String filename = "list.txt";
        String absoluteFilePath = "";
        absoluteFilePath = workingDirectory + File.separator + filename;
        return absoluteFilePath;
    }

    public void writeToFile(ArrayList<Todo> tasks, String absoluteFilePath, int counter) throws FileNotFoundException {
        PrintWriter fw = new PrintWriter(absoluteFilePath);
        for (int i = 0; i < counter; i++) {
            String classType = String.valueOf(tasks.get(i).getClass());
            if (classType.equalsIgnoreCase("Class Duke.Todo")) {
                fw.println("todo " + tasks.get(i).getDescription());
            } else if (classType.equalsIgnoreCase("Class Duke.Event")) {
                fw.println("event " + tasks.get(i).getDescription() + "/" + tasks.get(i).getBy() + "|"
                        + tasks.get(i).getEnd());
            } else if (classType.equalsIgnoreCase("Class Duke.Deadline")) {
                fw.println("deadline " + tasks.get(i).getDescription() + "/" + tasks.get(i).getBy());
            }
        }
        fw.close();
    }
}


