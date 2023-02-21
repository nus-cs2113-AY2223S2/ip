import storage.Storage;;

public class Main {
    private static final Duke duke = new Duke();
    public static void main(String[] args) {
        Storage parser = Storage.getInstance();
        parser.readFromFile();
        duke.run();
    }
}
