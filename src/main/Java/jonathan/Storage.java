package jonathan;

import task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File file;

    Storage(String filepath) {
        this.file = new File(filepath);
    }

    public ArrayList<Task> load() throws JonathanException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] extract = line.split("\\|");
                String symbol = extract[0].trim();
                String isDone = extract[1].trim();
                String description = extract[2].trim();

                switch (symbol) {
                    case "T":
                        Todo todo = new Todo(description);

                        if (isDone.equals("1")) {
                            todo.setDone(true);
                        }

                        tasks.add(todo);

                        break;
                    case "D":
                        String by = extract[3].trim();
                        Deadline deadline = new Deadline(description, by);

                        if (isDone.equals("1")) {
                            deadline.setDone(true);
                        }

                        tasks.add(deadline);

                        break;
                    case "E":
                        String start = extract[3].trim();
                        String end = extract[4].trim();
                        Event event = new Event(description, start, end);

                        if (isDone.equals("1")) {
                            event.setDone(true);
                        }

                        tasks.add(event);

                        break;
                }

            }

            reader.close();

        } catch (FileNotFoundException e) {
            throw new JonathanException();
        }

        return tasks;
    }

    public void save(TaskList tasks) {
        try {
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);

                if (task instanceof Todo) {
                    Todo todo = (Todo) task;
                    String line = "T | " + todo.getIsDone() + " | " + todo.getDescription() + "\n";
                    fileWriter.write(line);
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    String line = "D | " + deadline.getIsDone() + " | " + deadline.getDescription() +
                            " | " + deadline.getBy() + "\n";
                    fileWriter.write(line);
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    String line = "E | " + event.getIsDone() + " | " + event.getDescription() +
                            " | " + event.getStart() + " | " + event.getEnd() + "\n";
                    fileWriter.write(line);
                }
            }

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
