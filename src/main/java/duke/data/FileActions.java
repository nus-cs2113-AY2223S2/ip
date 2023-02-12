package duke.data;

import duke.output.StandardOutput;

import java.io.File;

public class FileActions {
    public static void makeDirectory() {
        File dataDirectory = new File(FileNames.DIRECTORY.NAME);
        dataDirectory.mkdirs();
    }

    public static File openDataFile() {
        File dataFile = new File(FileNames.FILE_PATH.NAME);
        try {
            dataFile.createNewFile();
        } catch (Exception e) {
            System.out.println(StandardOutput.CREATE_NEW_FILE_EXCEPTION_MESSAGE.STANDARD_OUTPUT);
        }
        return dataFile;
    }
}
