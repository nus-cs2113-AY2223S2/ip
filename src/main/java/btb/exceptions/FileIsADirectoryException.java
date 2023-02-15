package btb.exceptions;

public class FileIsADirectoryException extends DukeException {
    @Override
    public String getMessage() {
        return "\t Oops, tasks.txt is a directory, please change it.";
    }
}
