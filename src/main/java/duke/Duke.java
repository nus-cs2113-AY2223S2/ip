package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class Duke {

    public static final String FILEPATH = "./data/savedlist.txt";

    public static void printSeperator() {
        System.out.println("____________________________________________________________");
    }

    private static void greet() {
        printSeperator();
        System.out.println(" Hello! I'm Duke\n" + " What can I do for you?");
        printSeperator();
        System.out.println("");
    }


    private static void farewell() {
        printSeperator();
        System.out.println(" Bye. Hope to see you again soon!");
        printSeperator();
    }

    private static void parseResponse(String response, TaskList list) {


        if (response.equals("list")) {
            printSeperator();
            list.listDisplay();
            printSeperator();
        } else if (response.length() >= 5 && response.substring(0, 5).equals("mark ")) {
            printSeperator();
            list.markTask(response.substring(5));
            printSeperator();
        } else if (response.length() >= 7 && response.substring(0, 7).equals("unmark ")) {
            printSeperator();
            list.unmarkTask(response.substring(7));
            printSeperator();
        } else if (response.length() >= 7 && response.substring(0, 7).equals("delete ")) {
            printSeperator();
            list.deleteTask(response.substring(7));
            printSeperator();
        }
        else { // add task
            printSeperator();
            list.listAdd(response);
            printSeperator();
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
        // System.out.println("z = " + object1.z);
    }

    public static void main(String[] args) {

        greet();
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


        Scanner in = new Scanner(System.in);
        String response = in.nextLine();

        while (!response.equals("bye")) {
            parseResponse(response, taskList);
            response = in.nextLine();
        }
        listSave(taskList);
        farewell();

    }
}
