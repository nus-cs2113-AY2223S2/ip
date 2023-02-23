package duke;

import duke.exceptions.DukeCreateDatabaseException;
import duke.exceptions.DukeFileEmptyException;
import duke.exceptions.DukeLoadDatabaseException;
import duke.exceptions.DukeSaveDatabaseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {

    /**
     * Checks for database file on local filesystem. Create parent directory and database file if it does not
     * exist, otherwise reads the data in the database file
     *
     * @return String This returns the data in database in the form of a String. If database did not exist,
     * returns empty String.
     * @throws DukeCreateDatabaseException On failure to create new database file.
     * @throws DukeLoadDatabaseException   On failure to load past database file.
     */
    protected String loadDatabase()
            throws DukeCreateDatabaseException, DukeLoadDatabaseException, DukeFileEmptyException {
        String fileContent;
        Path filepath = Paths.get("data", "duke.txt");
        File databaseFile = filepath.toFile();

        if (!databaseFile.exists()) {
            fileContent = createDatabaseFile(databaseFile);
        } else {
            fileContent = loadDatabaseFile(databaseFile);
        }
        return fileContent;
    }

    /**
     * Saves all the items in the task-list into a local file used as the database. Tasks are saved in a
     * format that can be easily read in order to load the task-list from the database file.
     *
     * @param tasks Task-list to be saved into the database
     * @throws DukeSaveDatabaseException On failure to save task-list into database.
     */
    protected void saveDatabase(TaskList tasks) throws DukeSaveDatabaseException {
        try {
            Path filepath = Paths.get("data", "duke.txt");
            File databaseFile = filepath.toFile();
            FileWriter databaseFileWriter = new FileWriter(databaseFile);

            for (Task task : tasks) {
                String taskInfo = "";
                if (task instanceof Todo) {
                    taskInfo = "T" + "|" + task.getStatusIcon() + "|" + task.getDescription()
                            + System.lineSeparator();
                } else if (task instanceof Deadline) {
                    taskInfo = "D" + "|" + task.getStatusIcon() + "|" + task.getDescription() + "|"
                            + task.getEndDate() + System.lineSeparator();
                } else if (task instanceof Event) {
                    taskInfo = "E" + "|" + task.getStatusIcon() + "|" + task.getDescription() + "|"
                            + task.getEndDate() + "|" + task.getStartDate() + System.lineSeparator();
                }
                databaseFileWriter.write(taskInfo);
            }
            databaseFileWriter.close();
        } catch (IOException error) {
            throw new DukeSaveDatabaseException();
        }
    }

    private String createDatabaseFile(File databaseFile) throws DukeCreateDatabaseException {
        try {
            databaseFile.getParentFile().mkdirs();
            boolean canCreateFile = databaseFile.createNewFile();
            if (!canCreateFile) {
                throw new DukeCreateDatabaseException();
            }
        } catch (IOException error) {
            throw new DukeCreateDatabaseException();
        }
        return "";
    }

    private String loadDatabaseFile(File databaseFile)
            throws DukeLoadDatabaseException, DukeFileEmptyException {
        try {
            FileReader fileReader = new FileReader(databaseFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder fileContent = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }

            bufferedReader.close();
            fileReader.close();

            if (fileContent.toString().isEmpty()) {
                throw new DukeFileEmptyException();
            }

            return fileContent.toString();
        } catch (IOException error) {
            throw new DukeLoadDatabaseException();
        }
    }
}
