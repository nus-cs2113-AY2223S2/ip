public class Duke {
    public static void main(String[] args) {

        Io.printWelcome();
        try {
            Io.getInput();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        
        
    }
}
