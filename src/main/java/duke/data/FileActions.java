package duke.data;

import duke.ui.ErrorMessages;

import java.io.File;

public class FileActions {
    public static void makeDirectory() {
        File dataDirectory = new File(FileNames.DIRECTORY.NAME);
        dataDirectory.mkdirs(); // todo
    }

    public static File openDataFile() {
        File dataFile = new File(FileNames.FILE_PATH.NAME);
        try {
            dataFile.createNewFile(); // todo
        } catch (Exception e) {
            System.out.println(ErrorMessages.CREATE_NEW_FILE_EXCEPTION_MESSAGE.STANDARD_OUTPUT);
        }
        return dataFile;
    }
}
