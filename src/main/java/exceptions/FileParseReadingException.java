package exceptions;

public class FileParseReadingException extends Exception{
    public FileParseReadingException(String inputText) {
        super(inputText);
    }
}
