package exceptions;

public class FileNotCreatedException extends Exception {
    public FileNotCreatedException(String file) {
        System.out.println(file + " creation unsuccessful, exiting program.");
    }
}
