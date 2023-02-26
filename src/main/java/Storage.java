import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Storage {
    /**
     * Updates the list of tasks stored in the text file.
     *
     * @param storageFilePath The file path of the text file where the list of tasks is stored.
     * @throws IOException when the text file is corrupted.
     */
    public static void updateTasksSaved(Path storageFilePath) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                (new FileWriter(storageFilePath.toFile())))) {
            for (Task task : Duke.tasks) {
                String line = "error";
                if (task.getTypeOfTask() == TypeOfTask.TODO) {
                    line = task.getTypeOfTask() + Constants.STORAGE_BUFFER
                            + task.getStatusIcon() + Constants.STORAGE_BUFFER
                            + task.getDescription();
                } else if (task.getTypeOfTask() == TypeOfTask.DEADLINE) {
                    line = task.getTypeOfTask() + Constants.STORAGE_BUFFER
                            + task.getStatusIcon() + Constants.STORAGE_BUFFER
                            + task.getDescription() + Constants.STORAGE_BUFFER
                            + ((Deadline) task).getDate();
                } else if (task.getTypeOfTask() == TypeOfTask.EVENT) {
                    line = task.getTypeOfTask() + Constants.STORAGE_BUFFER
                            + task.getStatusIcon() + Constants.STORAGE_BUFFER
                            + task.getDescription() + Constants.STORAGE_BUFFER
                            + ((Event) task).getStartDate() + Constants.STORAGE_BUFFER
                            + ((Event) task).getEndDate();
                }
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * Initializes the program's list with previously saved tasks.
     * Does nothing if the text file is empty.
     *
     * @param storageFilePath The file path of the text file where the list of tasks is stored.
     * @throws IOException when the text file is corrupted.
     */
    public static void initializeListWithSavedTasks(Path storageFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(storageFilePath.toFile()))) {
            String line;
            line = reader.readLine();
            int index = 1;
            while (line != null) {
                String[] commands = line.split(Constants.STORAGE_BUFFER);
                System.out.print(index);
                if (commands[0].equals(TypeOfTask.TODO.toString())) {
                    Todo todo = new Todo((commands[1].equals(Task.markedStatusIcon)), commands[2]);
                    todo.printTask();
                    Duke.tasks.add(todo);
                } else if (commands[0].equals(TypeOfTask.DEADLINE.toString())) {
                    Deadline deadline = new Deadline((commands[1].equals(Task.markedStatusIcon)), commands[2], commands[3]);
                    deadline.printTask();
                    Duke.tasks.add(deadline);
                } else if (commands[0].equals(TypeOfTask.EVENT.toString())) {
                    Event event = new Event((commands[1].equals(Task.markedStatusIcon)), commands[2], commands[3], commands[4]);
                    event.printTask();
                    Duke.tasks.add(event);
                }
                line = reader.readLine();
                index++;
            }
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * Creates the storage folder and the respective text file to store the tasks.
     * If the folder and/or the text file exists, it will only create what is missing and not overwrite any existing
     * folder/file.
     * It also prints a message stating the location of the text file.
     *
     * @return storageFilePath The file path where the text file containing the list of tasks is stored.
     */
    static Path loadStorageFolderAndFile() {
        new File("store").mkdirs();
        String ipFolderPath = System.getProperty("user.dir");
        Path storageFolderPath = java.nio.file.Paths.get(ipFolderPath, Constants.STORE_DIR);
        Path storageFilePath = java.nio.file.Paths.get(storageFolderPath.toString(), Constants.STORAGE_INFO_TXT);

        try {
            java.nio.file.Files.createFile(storageFilePath);
            System.out.println("File created successfully at: " + storageFilePath);
        } catch (IOException e) {
            System.out.println("File already exist: " + e.getMessage());
        }
        return storageFilePath;
    }
}
