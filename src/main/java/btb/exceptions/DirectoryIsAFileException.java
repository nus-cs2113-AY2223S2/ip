package btb.exceptions;

public class DirectoryIsAFileException extends DukeException {
    @Override
    public String getMessage() {
        return "\t Oops, ./store is a file. Please change it...";
    }
}
