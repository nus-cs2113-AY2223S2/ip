package duke;

import duke.commands.task.Deadline;
import duke.commands.task.Event;
import duke.commands.task.Task;
import duke.commands.task.ToDo;
import java.io.*;
import java.util.ArrayList;

public class FileProcessor {

    public File dukeTextFile;

    public FileProcessor(ArrayList<Task> taskList) {
        this.dukeTextFile = new File("duke.txt");
        try {
            if (dukeTextFile.createNewFile()) {
                System.out.println("File created: " + dukeTextFile.getName());
            } else {
                System.out.println("File already exists.");
                BufferedReader reader = new BufferedReader(new FileReader("duke.txt"));
                String line = reader.readLine();
                while (line != null) {
                    String[] splitLine = line.split("/");
                    addToList(taskList, splitLine);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void addToList(ArrayList<Task> taskList, String[] parameters) {
        switch (parameters[0]) {
        case ("[T]"):
            taskList.add(new ToDo(parameters[2]));
            break;
        case ("[D]"):
            taskList.add(new Deadline(parameters[2], parameters[3]));
            break;
        case ("[E]"):
            taskList.add(new Event(parameters[2], parameters[3], parameters[4]));
            break;
        }
    }

    public void writeFile(ArrayList<Task> taskList) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("duke.txt"));
            for (int x = 0; x < taskList.size(); x += 1) {
                Task currentTask = taskList.get(x);
                switch (currentTask.taskChar) {
                case ("[T]"):
                    bufferedWriter.write(currentTask.taskChar + "/" + currentTask.status
                            + "/" + currentTask.taskDescription + "\n");
                    break;
                case ("[D]"):
                    bufferedWriter.write(currentTask.taskChar + "/" + currentTask.status
                            + "/" + currentTask.taskDescription
                            + "/" + ((Deadline) currentTask).by + "\n");
                    break;
                case ("[E]"):
                    bufferedWriter.write(currentTask.taskChar + "/" + currentTask.status
                            + "/" + currentTask.taskDescription
                            + "/" + ((Event) currentTask).from
                            + "/" + ((Event) currentTask).to + "\n");
                    break;
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
