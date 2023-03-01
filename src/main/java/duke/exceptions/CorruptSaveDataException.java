package duke.exceptions;

public class CorruptSaveDataException extends Exception {
    private static final String MESSAGE = "Warning: Save data is corrupted." + System.lineSeparator()
            + "All data will be overwritten after the next command is entered." + System.lineSeparator();

    public CorruptSaveDataException(String data) {
        super(MESSAGE + "Corrupt data: " + data);
    }
}
