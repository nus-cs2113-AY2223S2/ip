package jonathan;

import task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Model a class to handle storage for the program.
 */
public class Storage {
    private final File file;

    /**
     * Build constructor for the Storage class.
     * @param filepath the filepath of the storage.
     */
    Storage(String filepath) {
        String dirname = filepath.substring(0, filepath.lastIndexOf("/"));
        File dir = new File(dirname);
        dir.mkdirs();
        this.file = new File(filepath);
    }

    /**
     * Method to load the file.
     * @return the ArrayList of the tasks.
     * @throws JonathanException when the file can't be found.
     */
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
                        String byString = extract[3].trim();
                        LocalDateTime by = getDateAndTimeFormat(byString);
                        Deadline deadline = new Deadline(description, by);

                        if (isDone.equals("1")) {
                            deadline.setDone(true);
                        }

                        tasks.add(deadline);

                        break;
                    case "E":
                        String startString = extract[3].trim();
                        String endString = extract[4].trim();
                        LocalDateTime start = getDateAndTimeFormat(startString);
                        LocalDateTime end = getDateAndTimeFormat(endString);
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
            throw new JonathanException("Unfortunately, file can't be found. I'll make a new one!");
        }

        return tasks;
    }

    /**
     * Method to save the current state of the program into a file.
     * @param tasks the list of the task.
     */
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
                            " | " + convertDateTime(deadline.getRawBy()) + "\n";
                    fileWriter.write(line);
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    String line = "E | " + event.getIsDone() + " | " + event.getDescription() +
                            " | " + convertDateTime(event.getRawStart()) + " | " +
                            convertDateTime(event.getRawEnd()) + "\n";
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

    /**
     * Method to convert the String into LocalDateTime.
     * @param substring string to be converted.
     * @return LocalDateTime.
     * @throws JonathanException when the string is not following the format.
     */
    private LocalDateTime getDateAndTimeFormat(String substring) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(substring, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            // do something here
        }
        return null;
    }

    /**
     * Method to convert LocalDateTime into String
     * @param dateTime the LocalDateTime.
     * @return the string representation to be print in the file.
     */
    private String convertDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return dateTime.format(formatter);
    }
}
