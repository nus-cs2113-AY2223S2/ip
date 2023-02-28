package Alex;

import Alex.command.ByeCommand;
import Alex.command.Command;
import Alex.command.CommandResult;
import Alex.exception.DukeException;
import Alex.parser.Parser;
import Alex.task.TaskList;
import Alex.task.Deadline;
import Alex.task.Event;
import Alex.task.Task;
import Alex.task.Todo;
import Alex.ui.Ui;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Alex {
    public static TaskList taskList;

    public static void main(String[] args) {
        taskList = new TaskList();
        try {
            readData(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        }
        Ui.showWelcomeMessage();
        /** Constantly asks for user input until bye is received */
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = new Parser().parseCommand();
                CommandResult result = command.execute(taskList);
                String feedback = result.getCommandResult();
                Ui.showOutput(feedback);
                isExit = ByeCommand.isExit(command);

            }catch(IndexOutOfBoundsException e){
                    System.out.println("☹ OOPS!!! The description of a " + e.getMessage() + " cannot be empty.");
                    printLine();
                } /*catch (DukeException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            printLine();
        }*/
            }
        }


    public static void readData(TaskList tm) throws FileNotFoundException {
        String dir = System.getProperty("user.dir");
        Path filePath = Paths.get(dir, "data", "duke.txt");
        File f = new File(filePath.toString());
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String[] info = s.nextLine().trim().split(",");
            /*for(String part :info) {
                System.out.println(part);
            }*/
            if(info[0].equals("T"))
            {
                Task t = new Todo(info[2] , "T");
                if(info[1].equals("1")) {
                    t.markAsDone();
                }
                tm.setTask(t);
            }
            else if(info[0].equals("D"))
            {
                Task t = new Deadline(info[2], "D", info[3]);
                if(info[1].equals("1")) {
                    t.markAsDone();
                }
                tm.setTask(t);
            }
            else if(info[0].equals("E"))
            {
                Task t = new Event(info[2], "E",info[3] ,info[4]);
                if(info[1].equals("1")) {
                    t.markAsDone();
                }
                tm.setTask(t);
            }

        }
    }
    public static void saveData(TaskList tl) throws IOException {
        String toSave = "";
        String dir = System.getProperty("user.dir");
        Path filePath = Paths.get(dir, "data", "duke.txt");
        File file = new File(filePath.toString());
        file.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(file);
        for(Task t : tl.getAllTasks()) {
            if(t.getType().equals("T")) {
                Todo td = (Todo)t;
                int done = td.getDone() ? 1 : 0;
                toSave += "T" + "," + done  + "," + td.getDescription();
            }
            else if (t.getType().equals("D")) {
                Deadline d = (Deadline)t;
                int done = d.getDone() ? 1 : 0;
                toSave += "D" + "," + done + "," + d.getDescription() + "," + d.getBy();
            }
            else if (t.getType().equals("E")) {
                Event e = (Event)t;
                int done = e.getDone() ? 1 : 0;
                toSave += "E" + ","+ done + "," + e.getDescription() + "," + e.getFrom() + "," + e.getTo();
            }
            toSave += System.lineSeparator();
        }
        fw.write(toSave);
        fw.close();
    }
    public static void printLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }
    public static void greeting() {
        printLine();
        String greet = "Hello! I'm Alex\nWhat can I do for you today?";
        System.out.println(greet);
        printLine();
    }
    public static void exit() {
        String exit = "Bye. Hope to chat with you again soon!";
        System.out.println(exit);
        printLine();
    }

}
