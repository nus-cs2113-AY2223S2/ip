package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    public static final String FILEPATH = "duke.txt";
    public static final String LINE = "    ____________________________________________________________\n";

    /**
     * Takes the list of tasks the user has input and writes it to the data file at the default file path.
     * @param tasks the list of tasks the user has stored.
     * @throws IOException The exception will be thrown if there are errors when trying to write to the data file.
     */
    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        BufferedWriter outputWriter;
        outputWriter = new BufferedWriter(new FileWriter(FILEPATH));
        for (Task x : tasks) {
            outputWriter.write(x.toString() + System.lineSeparator());
        }
        outputWriter.flush();
        outputWriter.close();
    }

    /**
     * Catches errors when writing changes to the data file.
     * @param tasks the task list.
     */
    public void saveChanges(ArrayList<Task> tasks) {
        try {
            writeToFile(tasks);
        } catch (IOException e) {
            System.out.println("    Something went wrong: " + e.getMessage());
        }
    }

    private static void readFile(ArrayList<Task> tasks) throws IOException {
        String line;
        File f = new File(FILEPATH);
        Scanner s = new Scanner(f);
        int taskCount = 0;
        while (s.hasNext()) {
            line = s.nextLine();
            String[] words = line.split("] ", 2);
            String command = words[0];
            if (command.contains("[T]")) {
                String description = words[1];
                Task todo = new Task(description, "T");
                tasks.add(todo);
            } else if (command.contains("[D]")) {
                String[] description = words[1].split(" by: ");
                Deadline deadline = new Deadline(description[0], "D", description[1]);
                tasks.add(deadline);
            } else if (command.contains("[E]")) {
                String[] description = words[1].split(" from: ");
                String[] dates = description[1].split(" to: ");
                Event event = new Event(description[0], "E", dates[0], dates[1]);
                tasks.add(event);
            } else {
                System.out.print(LINE + "    There are invalid inputs in you To-do List, " +
                        "please edit it first." + LINE);
            }
            if (command.contains("X")) {
                tasks.get(taskCount).setDone(true);
            }
            taskCount ++;
        }
    }

    private static void createFile() throws IOException {
        File file = new File(FILEPATH);
        if (file.createNewFile()) {
            System.out.println("     Data file has been created. Your list will be saved.");
        } else {
            System.out.println("     Data file already exists. You list will be updated.");
        }
    }

    /**
     * Checks if the data file exists. Creates it if it does not.
     * Then it will read the data stored in the data file and add
     * data to task list accordingly.
     * @param tasks the task list.
     */
    public static void load(ArrayList<Task> tasks){
        try {
            createFile();
            readFile(tasks);
        } catch (IOException e) {
            System.out.println(LINE + "    Data File Missing! Check if you have accidentally deleted it.\n" + LINE);
        }
    }
}
