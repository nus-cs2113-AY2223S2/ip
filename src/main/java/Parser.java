public class Parser {

    public static Command parseInput(String action) {
        Command c = new Command(action.split(" ", 2));

        return c;
    }

}
