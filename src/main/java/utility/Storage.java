package utility;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static void startFileProcessing(ArrayList<Task> tasks) throws IndexOutOfBoundsException {
        try {
            File saveFile = new File("duke.txt");

            if (saveFile.createNewFile()) {
                System.out.println("You do not have a save file! Creating one now...");
            } else {
                System.out.println("You already have a file! Processing contents...");
            }
            final Scanner READ_FILE = new Scanner(saveFile);
            int counter = 0;
            while (READ_FILE.hasNext()) {
                String[] savedTask = READ_FILE.nextLine().split(" / ");

                switch (savedTask[0]) {
                case "T": {
                    String temp = savedTask[2];
                    Todo newTodo = new Todo(temp);
                    tasks.add(newTodo);

                    if (savedTask[1].equals("X")) {
                        tasks.get(counter).setComplete();
                    }
                    break;
                }

                case "D": {
                    String[] deadlineBreakdown = savedTask[2].split(" \\(By: ");
                    String by = deadlineBreakdown[1].substring(0, deadlineBreakdown[1].length() - 1);
                    Deadline deadline = new Deadline(deadlineBreakdown[0], LocalDateTime.parse(by, Ui.FORMATTER).format(Ui.FORMATTER));
                    tasks.add(deadline);

                    if (savedTask[1].equals("X")) {
                        tasks.get(counter).setComplete();
                    }
                    break;
                }

                case "E": {
                    String[] eventBreakdown = savedTask[2].split(" \\(From: ");
                    String eventDescription = eventBreakdown[0];
                    String[] timeBreakdown = eventBreakdown[1].split(" To: ");
                    String from = timeBreakdown[0];
                    String to = timeBreakdown[1].substring(0, timeBreakdown[1].length() - 1);

                    Event event = new Event(eventDescription, LocalDateTime.parse(from, Ui.FORMATTER).format(Ui.FORMATTER), LocalDateTime.parse(to, Ui.FORMATTER).format(Ui.FORMATTER));
                    tasks.add(event);

                    if (savedTask[1].equals("X")) {
                        tasks.get(counter).setComplete();
                    }
                    break;
                }
                }
                counter++;
            }

        } catch (IOException e) {
            System.out.println("An error has occurred. Please try again.");
            System.exit(1);
        }
    }

    /**
     * Saves tasks upon being given the command 'bye'
     * For each task in the arraylist, it saves the task in the format of
     * type / completion status / task description
     */
    public static void endFileProcessing(ArrayList<Task> tasks) {
        try {
            System.out.println("Saving current tasks...");
            FileWriter saveFile = new FileWriter("duke.txt");

            for (int i = 0; i < tasks.size(); ++i) {
                String task = String.valueOf(tasks.get(i));
                String[] splitTaskDescription = task.split("] ", 3);
                saveFile.write(tasks.get(i).getType() + " / " + tasks.get(i).getStatus() + " / " + splitTaskDescription[2] + '\n');
            }
            saveFile.close();
        } catch (IOException e) {
            System.out.println("An error has occurred. Please try again.");
            System.exit(1);
        }
    }
}
