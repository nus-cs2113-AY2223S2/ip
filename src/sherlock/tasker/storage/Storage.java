package storage;

import data.TasksList;
import data.exceptions.SherlockException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that loads tasks from the given file at the beginning
 * and writes tasks to the same file in the end
 */
public class Storage {
    private String filepath;

    /**
     * @param filepath
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * @param ui
     * @return list of tasks
     * @throws SherlockException
     */
    public ArrayList<Task> loadTasks(Ui ui) throws SherlockException {

        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);

            int lineIndex = 1;
            ArrayList<Task> tasks = new ArrayList<>();

            lineIterator:
            while(s.hasNext()) {
                String line = s.nextLine();
                String[] arguments = line.split("\\|");

                // Check for empty arguments
                int argCount = 1;
                for (String arg: arguments) {
                    if (arg.trim().equals("")){
                        ui.printLines("Argument #" + argCount + " is empty on line #" + lineIndex);
                        continue lineIterator;
                    }
                }

                // Parse tasks from file
                try {
                    String taskType = arguments[0].trim();
                    Boolean isDone = arguments[1].trim().equals("1");
                    String name = arguments[2].trim();

                    switch (taskType){
                        case "TASK":
                            tasks.add(new Task(name, isDone));
                            break;

                        case "T":
                            tasks.add(new Todo(name, isDone));
                            break;
                        case "D":
                            String by = arguments[3].trim();
                            tasks.add(new Deadline(name, isDone, by));
                            break;

                        case "E":
                            String from = arguments[3].trim();
                            String to = arguments[4].trim();
                            tasks.add(new Event(name, isDone, from, to));
                            break;
                        default:
                            System.out.println("An invalid task type "
                                    + taskType + " is given in input file on line #" + lineIndex);
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.printLines("Can't parse line #" + lineIndex + ". The number of arguments is invalid");
                }
                lineIndex++;
            }
            return tasks;
        } catch (IOException e) {
            throw new SherlockException("Couldn't read from file data/sherlock.txt");
        }
    }

    /**
     * Writes tasks to the given file
     * @param tasksList list of tasks
     * @throws SherlockException
     */
    public void writeToFile(TasksList tasksList) throws SherlockException {
        try {
            FileWriter fw = new FileWriter(this.filepath);

            StringBuilder output = new StringBuilder();

            ArrayList<Task> tasks = tasksList.getTasks();

//            Construct a file output
            for (Task task : tasks) {
                output.append(task.getFileFormatString()).append(System.lineSeparator());
            }

            fw.write(output.toString());
            fw.close();

        } catch (IOException e) {
            throw new SherlockException("Couldn't add a change to file data/sherlock.txt");
        }

    }
}
