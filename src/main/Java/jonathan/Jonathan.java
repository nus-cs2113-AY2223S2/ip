package jonathan;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Jonathan {
    public static void main(String[] args) {


        Scanner input = new Scanner((System.in));
        String command;
        String line = "    ____________________________________________________________";
        String welcome = line + "\n    Hello! I'm Jonathan\n" +
                "    What can I do for you?\n" + line + "\n";
        String goodbye = line + "\n    Bye. Hope to see you again soon!\n" + line;

        ArrayList<Task> list = readData();
        int taskCounter = list.size();

        System.out.println(welcome);

        // Loop until user prompt "bye"
        while (true) {
            command = input.nextLine(); // Prompt input from user

            try {
                if (Objects.equals(command, "bye")) {
                    saveData(list);
                    System.out.println(goodbye);
                    break; // quit the program

                } else if (Objects.equals(command, "list")) {
                    lookupTask(list, taskCounter);

                } else if (command.split(" ")[0].equals("mark") || command.split(" ")[0].equals("unmark")) {
                    boolean isDone = command.split(" ")[0].equals("mark");
                    int taskNum = Integer.parseInt(command.split(" ")[1]);
                    Task task = list.get(taskNum - 1);
                    changeStatus(task, isDone);

                } else if (command.split(" ")[0].equals("todo")) {

                    if (command.split(" ").length == 1) {
                        throw new StringIndexOutOfBoundsException();
                    }

                    String description = command.substring(command.indexOf(" ") + 1);
                    Todo todo = new Todo(description);
                    list.add(todo);
                    taskCounter += 1;
                    addedMessage(todo, taskCounter);

                } else if (command.split(" ")[0].equals("deadline")) {
                    String description = command.substring(command.indexOf(" ") + 1, command.indexOf(" /by"));
                    String by = command.substring(command.indexOf("/by") + 4);
                    Deadline deadline = new Deadline(description, by);
                    list.add(deadline);
                    taskCounter += 1;
                    addedMessage(deadline, taskCounter);

                } else if (command.split(" ")[0].equals("event")) {
                    String description = command.substring(command.indexOf(" ") + 1, command.indexOf(" /from"));
                    String start = command.substring(command.indexOf("/from") + 6, command.indexOf(" /to"));
                    String end = command.substring(command.indexOf("/to") + 4);
                    Event event = new Event(description, start, end);
                    list.add(event);
                    taskCounter += 1;
                    addedMessage(event, taskCounter);

                } else if (command.split(" ")[0].equals("delete")) {
                    int taskNum = Integer.parseInt(command.split(" ")[1]);
                    Task task = list.get(taskNum-1);
                    list.remove(taskNum - 1);
                    taskCounter -= 1;
                    System.out.println("     ____________________________________________________________\n" +
                            "     Noted. I've removed this task:\n" +
                            "       " + task + "\n" +
                            "     Now you have " + taskCounter + " tasks in the list.\n" +
                            "    ____________________________________________________________\n");

                } else {
                    throw new JonathanException();
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("    ____________________________________________________________\n" +
                        "    The task doesn't exist, please type it correctly!\n" +
                        "    ____________________________________________________________\n");

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("    ____________________________________________________________\n" +
                        "    Please type with the following format:\n\n" +
                        "    - todo <description>\n" +
                        "    - deadline <description> /by <time>\n" +
                        "    - event <descripton> /from <time> /to <time>\n\n" +
                        "    note: without the angle bracket\n" +
                        "    ____________________________________________________________\n");

            } catch (JonathanException e) {
                System.out.println("    ____________________________________________________________\n" +
                        "    Wrong input, please type it correctly!\n" +
                        "    ____________________________________________________________\n");
            }

        }
    }

    /*
    Method to print the added message to the user after prompting input
     */
    public static void addedMessage(Task task, int taskCounter) {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       " + task + "\n" +
                "     Now you have " + taskCounter + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    /*
    Method to print all the Task, including the Task's status
     */
    public static void lookupTask(ArrayList<Task> list, int counter) {
        System.out.println("    ____________________________________________________________\n" +
                "    Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println("    " + (i+1) + ". "  + list.get(i));
        }
        System.out.println("    ____________________________________________________________\n");
    }

    /*
    Method to change the status of a Task
     */
    public static void changeStatus(Task task, boolean isDone) {
        task.setDone(isDone);
        // If the Task is mark done
        if (isDone) {
            System.out.println("    ____________________________________________________________\n" +
                    "     Nice! I've marked this task as done:\n" +
                    "       " + task + "\n" +
                    "    ____________________________________________________________\n");
        } else { // Unmark the Task
            System.out.println("    ____________________________________________________________\n" +
                    "     OK, I've marked this task as not done yet:\n" +
                    "       " + task + "\n" +
                    "    ____________________________________________________________\n");
        }
    }

    /*
    Method to export the data into .txt file
     */
    public static void saveData(ArrayList<Task> tasks) {
        try {
            File outFile = new File("data/jonathan.txt");
            outFile.createNewFile();

            FileWriter outFileWriter = new FileWriter(outFile);

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);

                if (task instanceof Todo) {
                    Todo todo = (Todo) task;
                    String line = "T | " + todo.getIsDone() + " | " + todo.getDescription() + "\n";
                    outFileWriter.write(line);
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    String line = "D | " + deadline.getIsDone() + " | " + deadline.getDescription() +
                            " | " + deadline.getBy() + "\n";
                    outFileWriter.write(line);
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    String line = "E | " + event.getIsDone() + " | " + event.getDescription() +
                            " | " + event.getStart() + " | " + event.getEnd() + "\n";
                    outFileWriter.write(line);
                }
            }

            outFileWriter.flush();
            outFileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> readData() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File("data/jonathan.txt");

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
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        return tasks;

    }
}

