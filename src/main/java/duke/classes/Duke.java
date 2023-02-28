package duke.classes;

import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static void checkError(String input) throws DukeException {
        if (Objects.equals(input, "event") || Objects.equals(input, "todo") || Objects.equals(input, "deadline")) {
            throw new DukeException("The description of the body cannot be empty! Please enter a proper input.");
        } else if (Objects.equals(input, "")) {
            throw new DukeException("You did not enter any input! Please enter a proper input.");
        } else {
            throw new DukeException("I'm sorry, but i don't know what that means. Please enter a proper input.");
        }
    }

    private static ArrayList<Task> listOfTask = new ArrayList<Task>();
    static Tasklist tasklist = new Tasklist(listOfTask);

    public static void printFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            System.out.println(scan.nextLine());
        }
    }

    public static void foundationList(String filepath, ArrayList<Task> listOfTask) throws FileNotFoundException {
        File file = new File(filepath);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String temp = scan.nextLine();
            String type = temp.substring(1,2);
            String status = temp.substring(6,7);
            if (type.equals("T")) {
                String info = temp.substring(7,temp.length());
                Todo task = new Todo(info);
                if (status.equals("X")) {
                    task.isDone = true;
                } else {
                    task.isDone = false;
                }
                tasklist.addTask(task);
            } else if (type.equals("D")) {
                String info = temp.substring(7, temp.indexOf("("));
                String timeBy = temp.substring(temp.indexOf("(")+1, temp.length() - 1);
                Deadline task = new Deadline(info, timeBy);
                if (status.equals("X")) {
                    tasklist.markTask(task);
                } else {
                    tasklist.unmarkTask(task);
                }
                tasklist.addTask(task);
            } else if (type.equals("E")) {
                String info = temp.substring(7,temp.indexOf("("));
                String timeFrom = temp.substring(temp.indexOf("(")+1, temp.lastIndexOf(","));
                String timeBy = temp.substring(temp.lastIndexOf(",")+1, temp.length() - 1);
                Event task = new Event(info, timeFrom, timeBy);
                if (status.equals("X")) {
                    tasklist.markTask(task);
                } else {
                    tasklist.unmarkTask(task);
                }
                tasklist.addTask(task);
            }
        }
    }

    public static void writeFile(String filePath, String textAdd) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(textAdd);
        writer.close();
    }

    public static void updateFile(String filePath, ArrayList<Task> listOfTask) throws IOException {
        String newList = "";
        for (int i = 0; i < listOfTask.size(); i++) {
            newList += listOfTask.get(i).toString() + System.lineSeparator();
        }
        writeFile(filePath, newList);
    }

    public static void main(String[] args) throws IOException {

        Ui ui = new Ui();
        ui.showWelcome();

        try {
            File file = new File("src/duke_list.txt");
            if (file.createNewFile()) {
                ui.showFileCreated();
            } else {
                ui.showFileExists();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count = 0;
        String filePath = "src/duke_list.txt";
        foundationList(filePath, listOfTask);

        for (int i = 0; i < listOfTask.size(); i++) {
            count++;
        }

        try {
            ui.showFileContent();
            printFile("src/duke_list.txt");
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundError();
        }
        ui.showWelcomeEnd();
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        boolean isBye = false;
        while (!isBye) {
            Parser parser = new Parser(input);

            if (Objects.equals(parser.getInputType(), "bye")) {
                isBye = true;
                updateFile(filePath, listOfTask);
                break;
            } else if (Objects.equals(parser.getInputType(), "list")) {
                ui.showTasksMessage();
                tasklist.printTasks();

            } else if (Objects.equals(parser.getInputType(), "mark")) {
                    if(parser.getOrderMark(input) - 1 >= count) {
                        ui.showMarkTaskWarning();
                    } else {
                        Task task = listOfTask.get(parser.getOrderMark(input) - 1);
                        InputUi inputUi = new InputUi(task, count);
                        tasklist.markTask(task);
                        listOfTask.set(parser.getOrderMark(input) - 1, task);
                        updateFile(filePath, listOfTask);
                        inputUi.showMarkedTask();
                    }
            } else if (Objects.equals(parser.getInputType(), "unmark")) {
                    if(parser.getOrderUnmark(input) - 1 >= count) {
                        ui.showUnmarkTaskWarning();
                    } else {
                        Task task = listOfTask.get(parser.getOrderUnmark(input) - 1);
                        InputUi inputUi = new InputUi(task, count);
                        tasklist.unmarkTask(task);
                        listOfTask.set(parser.getOrderUnmark(input) - 1, task);
                        updateFile(filePath, listOfTask);
                        inputUi.showUnmarkedTask();
                    }
            } else if (Objects.equals(parser.getInputType(), "delete")) {
                if(parser.getOrderDelete(input) - 1 >= count) {
                    ui.showDeleteTaskWarning();
                } else {
                    InputUi inputUi = new InputUi(listOfTask.get(parser.getOrderDelete(input) - 1), count - 1);
                    inputUi.showDeletedTask();
                    listOfTask.remove(parser.getOrderDelete(input) - 1);
                    updateFile(filePath, listOfTask);
                    count--;
                    inputUi.showRemainingTasks();
                }
            } else {
                if (Objects.equals(parser.getInputType(), "todo")) {
                    Todo task = new Todo(parser.getTodoInfo(input));
                    task.isDone = false;
                    tasklist.addTask(task);
                    updateFile(filePath, listOfTask);
                    InputUi inputUi = new InputUi(task, count);
                    inputUi.showTaskAdded();
                    count++;
                } else if (Objects.equals(parser.getInputType(), "deadline")) {
                    Deadline task = new Deadline(parser.getDeadlineInfo(input), parser.getDeadlineTimeBy(input));
                    task.isDone = false;
                    tasklist.addTask(task);
                    updateFile(filePath, listOfTask);
                    InputUi inputUi = new InputUi(task, count);
                    inputUi.showTaskAdded();
                    count++;
                } else if (Objects.equals(parser.getInputType(), "event")) {
                    Event task = new Event(parser.getEventInfo(input), parser.getEventTimeFrom(input), parser.getEventTimeBy(input));
                    task.isDone = false;
                    tasklist.addTask(task);
                    updateFile(filePath, listOfTask);
                    InputUi inputUi = new InputUi(task, count);
                    inputUi.showTaskAdded();
                    count++;
                } else {
                    try {
                        checkError(input);
                    } catch(DukeException e) {
                        System.out.println("Error: " + e);
                    }
                }
            }
            input = scan.nextLine();
        }
        ui.showFarewell();
    }
}
