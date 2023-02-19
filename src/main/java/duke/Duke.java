package duke;

import duke.exceptions.InvalidCommandException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Duke {
    private static Ui ui;

    public static final String FILEPATH = "./data/savedlist.txt";


    private static void parseResponse(String response, TaskList list) {


        if (response.equals("list")) {
            ui.printSeparator();
            list.listDisplay();
            ui.printSeparator();
        } else if (response.length() >= 5 && response.substring(0, 5).equals("mark ")) {
            ui.printSeparator();
            list.markTask(response.substring(5));
            ui.printSeparator();
        } else if (response.length() >= 7 && response.substring(0, 7).equals("unmark ")) {
            ui.printSeparator();
            list.unmarkTask(response.substring(7));
            ui.printSeparator();
        } else if (response.length() >= 7 && response.substring(0, 7).equals("delete ")) {
            ui.printSeparator();
            list.deleteTask(response.substring(7));
            ui.printSeparator();
        } else {
            ui.printSeparator();
            try {
                list.listAdd(response);
            } catch (InvalidCommandException e) {
                System.out.println("Invalid task type!");
            }
            ui.printSeparator();
        }
    }

    private static String readFileContents(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        return s.nextLine();

    }

    public static void listSave(TaskList list) {
        // serialisation https://www.geeksforgeeks.org/serialization-in-java/
        try {

            // Saving of object in a file
            FileOutputStream file = new FileOutputStream(FILEPATH);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialisation of object
            out.writeObject(list);
            out.close();
            file.close();
            System.out.println("File successfully saved to: " + FILEPATH);

        } catch (IOException ex) {
            System.out.println("IOException caught");
        }
    }

    public static TaskList listLoad() throws IOException, ClassNotFoundException {
        //deserialisation
        FileInputStream file = new FileInputStream(FILEPATH);
        ObjectInputStream in = new ObjectInputStream(file);

        // Method for deserialisation of object
        TaskList list = (TaskList) in.readObject();

        in.close();
        file.close();

        return list;
    }

    public Duke() {
        ui = new Ui();
    }

    public void run() {
        ui.greet();
        File f = new File(FILEPATH);
        TaskList taskList;
        if (f.exists()) {
            System.out.println("Save file detected.");

            try {
                taskList = listLoad();
            } catch (IOException ex) {
                System.out.println("IOException caught");
                return;
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException caught");
                return;
            }

        } else {
            System.out.println("Save file not detected. Starting with empty list.");
            taskList = new TaskList();
        }



        String userInput = ui.getInput();
        while (!userInput.equals("bye")) {
            parseResponse(userInput, taskList);
            userInput = ui.getInput();
        }
        listSave(taskList);
        ui.farewell();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
