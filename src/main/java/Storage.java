import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
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

    /***
     * Checks if there is an existing folder named data in the user's directory. Folder will be created if
     * it does not exist.
     * @param path The supposed path of the folder data.
     */
    static void checkIfFolderExists(Path path) {
        boolean directoryExists = Files.exists(path);
        if (!directoryExists) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /***
     * Checks if there is an existing txt file in the user's directory named duke-inputs.txt, and will create
     * one if not.
     * @return The path of the textFile.
     */
    static Path checkIfFileExists() {
        Path textFile = Paths.get(Duke.HOME, "ip-data", "duke-inputs.txt");
        try {
            Files.createFile(textFile);
        } catch (IOException e) {
            System.out.println("File already exists!\n");
        }
        return textFile;
    }

    /***
     * The function aims to create the txt file required to store the data inputs from the user.
     * @return The data file.
     */
    static File createFile() {
        Path path = Paths.get(Duke.HOME, "ip-data");
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
        Ui.informUserTaskMarked(storedValues, numToMark);

        File dukeInputs = Duke.FILEPATH;
        String prevContent = "";
        BufferedReader reader = new BufferedReader(new FileReader(dukeInputs));
        prevContent = writingDuplicateArrayList(prevContent, reader);
        try {
            String newContent = rewriteContent(storedValues, numToMark, " | 0 | ", " | 1 | ", prevContent);
            FileWriter writer = new FileWriter(dukeInputs);
            writer.write(newContent);
            reader.close();
            writer.close();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Input will not be updated.");
        }

    }

    /***
     * Unmarks the tast in the txt file when called.
     * @param storedValues List of tasks from user inputs.
     * @param numToMark The row in txt file to be unmarked.
     * @throws IOException Thrown when file cannot be read.
     */
    public static void unmarkTaskInTxt(ArrayList<Task> storedValues, int numToMark) throws IOException {
        Ui.informUserTaskUnmarked(storedValues, numToMark);

        File dukeInputs = Duke.FILEPATH;
        String prevContent = "";
        BufferedReader reader = new BufferedReader(new FileReader(dukeInputs));
        prevContent = writingDuplicateArrayList(prevContent, reader);
        try {
            String newContent = rewriteContent(storedValues, numToMark, " | 1 | ", " | 0 | ", prevContent);
            FileWriter writer = new FileWriter(dukeInputs);
            writer.write(newContent);
            reader.close();
            writer.close();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Input will not be updated in file.");
        }

    }

    /***
     * The function creates a duplicate list which is required to overwrite the data that has to change,
     * following the mark/unmark command that changes it from "X" to " " or vice versa.
     * @param prevContent The string to store the data from txt file.
     * @param reader Java BufferedReader command.
     * @return The string of prevContent after duplicating.
     * @throws IOException Throws error if line cannot be read.
     */
    private static String writingDuplicateArrayList(String prevContent, BufferedReader reader) throws IOException {
        String input = reader.readLine();
        while (input != null) {
            prevContent = prevContent + input + "\n";
            input = reader.readLine();
        }
        return prevContent;
    }

    /***
     * The function identifies the string that needs to be replaced with and replaces it with the new content,
     * thus rewriting the part that needs to be updated in the txt file.
     * @param storedValues The existing list of values stored in the txt file.
     * @param numToMark The line number that requires the update given the previous command.
     * @param before The string of either " " or "X" that needs to be updated.
     * @param after The string of either "X" or " " that should reflect the new update.
     * @param prevContent The duplicated list.
     * @return Returns the new content to be written.
     */
    private static String rewriteContent(ArrayList<Task> storedValues, int numToMark, String before, String after, String prevContent) throws IndexOutOfBoundsException{
        char type = storedValues.get(numToMark - 1).getClass().toString().substring(6).charAt(0);
        String toReplace = (type + before + storedValues.get(numToMark - 1).description);
        String toReplaceWith = (type + after + storedValues.get(numToMark - 1).description);
        String newContent = prevContent.replace(toReplace, toReplaceWith);
        return newContent;
    }
}
