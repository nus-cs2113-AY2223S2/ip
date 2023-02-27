package DukeFunctions;

import Exceptions.DukeError;
import Exceptions.MissingInputException;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File f;

    /**
     * Creates a new Storage object with the specified file path.
     *
     * @param filePath Path of the file to be read from and written to.
     */
    public Storage(String filePath) {
        f = new File(filePath);


    }

    /**
     * Saves the taskList tasks to the file
     *
     * @param tasks The taskList to be saved.
     * @throws DukeError If there is an error writing to the file.
     */
    public void save(TaskList tasks) throws DukeError {
        try {

            StringBuilder output = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                output.append(i + 1).append(". [")
                        .append(tasks.get(i).getType()).append("][")
                        .append(tasks.get(i).getIsDone()).append("] ")
                        .append(tasks.get(i).toString()).append("\n");
            }
            String fileText = output.toString();


            FileWriter fw = new FileWriter("memory.txt");
            fw.write(fileText);
            fw.close();
        } catch (IOException e) {
            throw new DukeError("IOException");
        }

    }

    /**
     * Loads the list of tasks from the file.
     *
     * @return the TaskList loaded from the file.
     * @throws DukeError If there is an error reading from the file.
     */
    public ArrayList<Todo> load() throws DukeError {

        ArrayList<Todo> tasks = new ArrayList<>();
        try {

            if (!f.exists()) {
                System.out.println("making a new memory file");
                FileWriter fw = new FileWriter("memory.txt");
                fw.close();
            } else {

                Scanner scanner = new Scanner(f);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    // get type
                    String type = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
                    //get status
                    String status = line.substring(line.indexOf("[", line.indexOf("[") + 1) + 1, line.indexOf("]", line.indexOf("]") + 1));

                    String description;
                    if (type.equals("T")) {
                        description = line.substring(line.indexOf("]") + 5);
                        Todo newTodo = new Todo(description);
                        tasks.add(newTodo);

                    } else {
                        int start = line.indexOf("]") + 5;
                        int end = line.indexOf("(") - 1;
                        description = line.substring(start, end);
                    }
                    String by = null;
                    String from = null;
                    String to = null;
                    if (type.equals("D")) {
                        int start = line.indexOf("by:") + 3;
                        int end = line.indexOf(")", line.indexOf("by:"));
                        by = line.substring(start, end);
                        String deadlineInput = description + "/by" + by;
                        Deadline newDeadline = new Deadline(deadlineInput);
                        tasks.add(newDeadline);


                    } else if (type.equals("E")) {
                        int fromIndex = line.indexOf("from:");
                        int toIndex = line.indexOf("to:");
                        from = line.substring(fromIndex + 5, line.indexOf("to:", fromIndex));
                        to = line.substring(toIndex + 3, line.indexOf(")", toIndex));

                        String eventInput = description + "/from" + from + "/to" + to;
                        Event newEvent = new Event(eventInput);
                        tasks.add(newEvent);


                    }

                }

                scanner.close();

            }


        } catch (IOException e) {
            throw new DukeError("IOException");
        }


        return tasks;
    }
}
