package mom.exceptions;

public class FileException extends Exception {
    public FileException(String errorMessage) {
        super("File error:" + errorMessage);
    }
}