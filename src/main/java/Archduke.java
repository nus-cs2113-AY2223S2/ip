public class Archduke {
    public static void main(String[] args) {
        IO io = new IO();
        IO.printBoxTopBorder();
        IO.printLogo();
        IO.printf("Hello! I'm Archduke");
        IO.printf("What can I do for you?");
        IO.printBoxBottomBorder();
        String command = io.readUserInput();
        IO.printBoxTopBorder();
        IO.printf("Your input is \"%s\"", command);
        IO.printBoxBottomBorder();
        IO.printBoxTopBorder();
        IO.printf("Bye. Hope to see you again soon!");
        IO.printBoxBottomBorder();
    }
}
