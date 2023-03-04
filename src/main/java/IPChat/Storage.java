package IPChat;

import ipchatExceptions.IPChatExceptions;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import static IPChat.TaskList.*;

/**
 * Class to deal with saving files to the SaveInfo.txt file (offline storage) and loading from the SaveInfo.txt file when needed
 *
 * @author DeepanjaliDhawan
 */
public class Storage {
    // Saves

    /**
     * Method to save the tasks in the SaveInfo.txt file (which is an offline storage file)
     *
     * @param tasks store all the task of the IPChatBot
     */
    public static void saveContent(ArrayList<Task> tasks) {
        String path = "C:\\Users\\deepa\\OneDrive\\Documents\\OneNote\\ip\\SaveInfo.txt";
        try {
            FileWriter fw = new FileWriter(path, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
            for (int i = 0; i < tasksCount; i += 1) {
                String value = tasks.get(i).saveStuff() + "\n";
                Files.write(Paths.get(path), value.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to load the saved information to the files if needed
     * @throws FileNotFoundException exception thrown if the file does not exist
     * @throws IPChatExceptions
     */
    public static void loadContent() throws FileNotFoundException, IPChatExceptions {
        File currFile = new File("C:\\Users\\deepa\\OneDrive\\Documents\\OneNote\\ip\\SaveInfo.txt");
        Scanner input = new Scanner(currFile);
        String taskCase;
        String str;
        int taskNum = 1;

        while (input.hasNext()) {
            String info = input.nextLine();
            String[] arr1 = info.split(" \\| ");
            String[] arr2 = arr1[0].split(" ");
            taskCase = arr2[0];
            str = Integer.toString(taskNum);

            switch (taskCase) {
            case "deadline":
                deadlineTasks(arr1[0]);
                if (arr1[1].equals("1")) {
                    markDone(str);
                }
                break;
            case "todo":
                toDoTasks(arr1[0]);
                if (arr1[1].equals("1")) {
                    markDone(str);
                }
                break;
            case "event":
                eventTasks(arr1[0]);
                if (arr1[1].equals("1")) {
                    markDone(str);
                }
                break;
            default:
            }
            taskNum += 1;
        }
        input.close();
    }
}
