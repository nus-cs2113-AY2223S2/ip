import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TasksLoader {
    private TasksList tasksList;

    public TasksLoader(TasksList tasksList) {
        this.tasksList = tasksList;
    }

    public void loadTasks() {

        try {
            File f = new File("data/sherlock.txt");
            Scanner s = new Scanner(f);

            int lineIndex = 1;
            lineIterator:
            while(s.hasNext()) {
                String line = s.nextLine();
                String[] arguments = line.split("\\|");

//                Check for empty arguments
                int argCount = 1;
                for (String arg: arguments) {
                    if (arg.trim().equals("")){
                        TaskListener.printLines("Argument #" + argCount + " is empty on line #" + lineIndex);
                        continue lineIterator;
                    }
                }

//                Parse tasks from file
                try {
                    String taskType = arguments[0].trim();
                    Boolean isDone = arguments[1].trim().equals("1");
                    String name = arguments[2].trim();

                    switch (taskType){
                        case "TASK":
                            Task task = new Task(name, isDone);
                            tasksList.addTask(task);
                            break;

                        case "T":
                            Todo todo = new Todo(name, isDone);
                            tasksList.addTask(todo);
                            break;
                        case "D":
                            String by = arguments[3].trim();
                            Deadline deadline = new Deadline(name, isDone, by);
                            tasksList.addTask(deadline);
                            break;

                        case "E":
                            String from = arguments[3].trim();
                            String to = arguments[4].trim();
                            Event event = new Event(name, isDone, from, to);
                            tasksList.addTask(event);
                            break;
                        default:
                            System.out.println("An invalid task type "
                                    + taskType + " is given in input file on line #" + lineIndex);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    TaskListener.printLines("Can't parse line #" + lineIndex + ". The number of arguments is invalid");
                }

                lineIndex++;
            }
            TaskListener.printLines("data/sherlock.txt contents are successfully loaded");
        } catch (IOException e) {
            System.out.println("Couldn't read from file data/sherlock.txt");
        }
    }

    public void writeToFile(TasksList tasksList) {
        try {
            FileWriter fw = new FileWriter("data/sherlock.txt");

            StringBuilder output = new StringBuilder();

            ArrayList<Task> tasks = tasksList.getTasks();

//            Construct a file output
            for (Task task : tasks) {
                output.append(task.getFileFormatString()).append(System.lineSeparator());
            }

            fw.write(output.toString());
            fw.close();

        } catch (IOException e) {
            System.out.println("Couldn't add a change to file data/sherlock.txt");
        }

    }
}
