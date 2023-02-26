package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.data.task.Task;
import duke.data.TaskList;
import duke.data.exception.DuplicateTaskException;
import duke.parser.CsvParser;


public class StorageFile {
    private static final String DEFAULT_DIR = "data";
    private static final String DEFAULT_FILE = "duke.csv";
    private static final String DEFAULT_FILE_PATH = DEFAULT_DIR + "/" + DEFAULT_FILE;
    private final Path path;

    public StorageFile() {
        this(DEFAULT_FILE_PATH);
    }

    public StorageFile(String filePath) {
        path = Paths.get(filePath);
    }

    public String getPath() {
        return path.toString();
    }

    public void writeCsv(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(path.toFile());
        for (Task task : taskList) {
            fw.write(task.toCSV() + System.lineSeparator());
        }
        fw.close();
    }

    public void initCsv() {
        File directory = new File(DEFAULT_DIR);
        directory.mkdir();
        File file = new File(DEFAULT_DIR + "/" + DEFAULT_FILE);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public int loadCsvLoad(TaskList taskList) throws FileNotFoundException {
        File file = new File(path.toFile().toURI());
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        Scanner fileScanner = new Scanner(file);
        int cnt = 0;
        int cnt_success = 0;
        while (fileScanner.hasNext()) {
            cnt += 1;
            try {
                taskList.addTask(new CsvParser().parseLine(fileScanner.nextLine()));
                cnt_success += 1;
            } catch (DuplicateTaskException | IllegalArgumentException e) {
                System.err.printf("Failed to load line %d.\n", cnt);
            }
        }
        return cnt_success;
    }

}
