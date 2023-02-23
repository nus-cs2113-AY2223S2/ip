import storage.Storage;;

/**
 * The main class where we compile and run.
 */
public class Main {
    private static final Duke duke = new Duke();

    /*
     * Entry point of the project.
     */
    public static void main(String[] args) {
        Storage parser = Storage.getInstance();
        parser.readFromFile();
        duke.run();
    }
}
