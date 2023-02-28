package duke;

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Storage extends TaskManager {

    public static TaskManager loadFile(TaskManager listOfItems) throws FileNotFoundException {
        String filePath = "C:/repos/IP/src/main/java/duke/load.txt";
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String l = s.nextLine();
            if (l.startsWith(" [T]")) {
                loadTodo(listOfItems, l);
            } else if (l.startsWith(" [D]")) {
                loadDeadline(listOfItems, l);
            } else if (l.startsWith(" [E]")) {
                loadEvent(listOfItems, l);
            }
        }
        s.close();
        return listOfItems;
    }

    public static void loadTodo(TaskManager listOfItems, String l) {
        String name = l.substring(7).trim();
        if (l.contains("[ ]")) {
            listOfItems.addTask(name, false);
        } else {
            listOfItems.addTask(name, true);
        }
    }

    public static void loadEvent(TaskManager listOfItems, String l) {
        int fromIndex = l.indexOf("(from: ");
        int toIndex = l.indexOf(" to: ", fromIndex);
        String name = l.substring(8, fromIndex).trim();
        String startTime = l.substring(fromIndex + 7, toIndex);
        String finishTime = l.substring(toIndex + 5, l.length() - 1);
        if (l.contains("[ ]")) {
            listOfItems.addEvent(name, startTime, finishTime, false);
        } else {
            listOfItems.addEvent(name, startTime, finishTime, true);
        }
    }

    public static void loadDeadline(TaskManager listOfItems, String l) {
        int byIndex = l.indexOf("(by: ");
        String name = l.substring(8, byIndex - 1);
        int endIndex = l.indexOf(")");
        String deadline = l.substring(byIndex + 5, endIndex);
        if (l.contains("[ ]")) {
            listOfItems.addDeadline(name, deadline, false);
        } else {
            listOfItems.addDeadline(name, deadline, true);
        }
    }

    public static void saveFile(TaskManager listOfItems) throws IOException {
        String filePath = "C:/repos/IP/src/main/java/duke/load.txt";
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < listOfItems.getSize(); i++) {
            try {
                fw.write(listOfItems.getId(i).toString() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        fw.close();
    }

}
