
import java.io.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    /**
     * Writes the tasks in the tasks list to a file.
     * Each task is written in a line in the following format:
     * type icon/completion status/description/by or from-to (depending on the task type)
     */
    public static void writeToFile() {
        try {
            FileWriter writer = new FileWriter("tasks.txt");

            for (Task task : TasksList.tasks) {
                StringBuilder sb = new StringBuilder();
                int x;
                if (task.getStatusIcon().equals(" ")) {
                    x = 0;
                } else {
                    x = 1;
                }
                sb.append(task.getTypeIcon()).append("/").append(x).append("/");

                if (task instanceof Todo) {
                    sb.append(((Todo) task).getDescription());
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    sb.append((deadline.getDescription())).append("/").append((deadline.getBy()));
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    sb.append((event.getDescription())).append("/").append((event.getFrom())).
                            append("/").append((event.getTo()));
                }
                sb.append("\n");

                writer.write(sb.toString());
            }
            writer.close();
            System.out.println("Tasks saved successfully to file.");
        } catch (IOException e) {
            System.out.println("Error in writing to file: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks from a file and updates the tasks list.
     * Each line in the file represents a task and is in the format:
     * type icon/completion status/description/by or from-to (depending on the task type)
     */
    public static void loadFromFile() {
        ArrayList<Task> tasksList = new ArrayList<>();
        try {
            File textFile = new File("tasks.txt");
            if (!textFile.exists()) {
                textFile.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {

            FileReader reader = new FileReader("tasks.txt");
            BufferedReader bf = new BufferedReader(reader);
            String line;
            while ((line = bf.readLine()) != null) {
                String[] info = line.split("/");
                String typeIcon = info[0];
                int completionStatus = Integer.parseInt(info[1]);
                if (typeIcon.equals("T")) {
                    Todo todo = new Todo(info[2]);
                    if (completionStatus == 1) {
                        Task.markAsDone(todo);
                    }
                    tasksList.add(todo);
                } else if (typeIcon.equals("D")) {
                    Deadline deadline = new Deadline(info[2], info[3]);
                    if (completionStatus == 1) {
                        Task.markAsDone(deadline);
                    }
                    tasksList.add(deadline);
                } else if (typeIcon.equals("E")) {
                    Event event = new Event(info[2], info[3], info[4]);
                    if (completionStatus == 1) {
                        Task.markAsDone(event);
                    }
                    tasksList.add(event);
                }
            }
            bf.close();
            TasksList.tasks = tasksList;
            System.out.println("Tasks loaded successfully from file.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}









