import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    // Functions in storage deals with loading tasks from the file and saving tasks in the file
    /***
     * Deletes task in txt file.
     * @param posToDelete Row to delete.
     * @param currLine Tracks the current line that it is on.
     * @throws IOException Thrown when error is detected.
     */
    static void deleteTaskInTxt(int posToDelete, int currLine) throws IOException {
        File dukeInputs = Duke.FILEPATH;
        String newContent = "";
        BufferedReader reader = new BufferedReader(new FileReader(dukeInputs));
        String input = reader.readLine();
        while (input != null) {
            if (posToDelete -1 != currLine) {
                newContent = newContent + input + "\n";
            }
            currLine += 1;
            input = reader.readLine();
        }

        FileWriter writer = new FileWriter(dukeInputs);
        writer.write(newContent);
        reader.close();
        writer.close();
    }


    /***
     * Writes all the inputs made by user to the txt file so that data is saved in hard disk.
     * @param storedValues List of input values made by the user.
     * @throws IOException Thrown when error is detected.
     */
    static void writeToFile(Task storedValues) throws IOException {
        FileWriter fw = new FileWriter(Duke.FILEPATH, true);
        int marked = ((storedValues.getStatusIcon().equals(" ")) ? 0 : 1);
        if (storedValues.getClass().getSimpleName().equals("Todo")) {
            fw.write("T | " + marked + " | " + storedValues.description + "\n");
        } else if (storedValues.getClass().getSimpleName().equals("Deadline")) {
            fw.write("D | " + marked + " | " + storedValues.description + " | " +
                    ((Deadline) storedValues).by + "\n");
        } else {
            fw.write("E | " + marked + " | " + storedValues.description + " | " +
                    ((Event) storedValues).by + " | " + ((Event) storedValues).to + "\n");
        }
        fw.close();
    }

    static void checkIfFolderExists(Path path) {
        boolean directoryExists = Files.exists(path);
        if (!directoryExists) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                System.out.println("Error occurred!\n");
            }
        }
    }

    static Path checkIfFileExists() {
        Path textFile = Paths.get(Duke.HOME, "IdeaProjects", "ip", "src", "main", "data", "duke-inputs.txt");
        try {
            Files.createFile(textFile);
        } catch (IOException e) {
            System.out.println("File already exists!\n");
        }
        return textFile;
    }

    static File createFile() {
        Path path = Paths.get(Duke.HOME, "IdeaProjects", "ip", "src", "main", "data");
        checkIfFolderExists(path);
        Path textFile = checkIfFileExists();

        File data = textFile.toFile();
        return data;
    }

    /***
     * Marks the task in the txt file.
     * @param storedValues List of tasks from user inputs.
     * @param numToMark Task number from user input that is to be marked.
     * @throws IOException Thrown when file cannot be read.
     */
    public static void markTaskInTxt(ArrayList<Task> storedValues, int numToMark) throws IOException {
        storedValues.get(numToMark -1).markAsDone();
        TaskList.formattingLine();
        System.out.println("Nice! I've marked this task as done: \n"
                + storedValues.get(numToMark -1).toString() + "\n");
        TaskList.formattingLine();

        File dukeInputs = Duke.FILEPATH;
        String prevContent = "";
        BufferedReader reader = new BufferedReader(new FileReader(dukeInputs));
        String input = reader.readLine();
        while (input != null) {
            prevContent = prevContent + input + "\n";
            input = reader.readLine();
        }

        char type = storedValues.get(numToMark -1).getClass().toString().substring(6).charAt(0);
        String toReplace = (type + " | 0 | " + storedValues.get(numToMark -1).description);
        String toReplaceWith = (type + " | 1 | " + storedValues.get(numToMark -1).description);
        String newContent = prevContent.replace(toReplace, toReplaceWith);
        FileWriter writer = new FileWriter(dukeInputs);
        writer.write(newContent);
        reader.close();
        writer.close();
    }

    /***
     * Unmarks the tast in the txt file when called.
     * @param storedValues List of tasks from user inputs.
     * @param numToMark The row in txt file to be unmarked.
     * @throws IOException Thrown when file cannot be read.
     */
    public static void unmarkTaskInTxt(ArrayList<Task> storedValues, int numToMark) throws IOException {
        storedValues.get(numToMark -1).unmarkAsDone();
        TaskList.formattingLine();
        System.out.println("OK, I've marked this task as not done yet: \n" +
                storedValues.get(numToMark -1).toString() + "\n");
        TaskList.formattingLine();

        File dukeInputs = Duke.FILEPATH;
        String prevContent = "";
        BufferedReader reader = new BufferedReader(new FileReader(dukeInputs));
        String input = reader.readLine();
        while (input != null) {
            prevContent = prevContent + input + "\n";
            input = reader.readLine();
        }

        char type = storedValues.get(numToMark -1).getClass().toString().substring(6).charAt(0);
        String toReplace = (type + " | 1 | " + storedValues.get(numToMark -1).description);
        String toReplaceWith = (type + " | 0 | " + storedValues.get(numToMark -1).description);
        String newContent = prevContent.replace(toReplace, toReplaceWith);
        FileWriter writer = new FileWriter(dukeInputs);
        writer.write(newContent);
        reader.close();
        writer.close();
    }

}
