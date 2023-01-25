public class Archduke {
    public static void main(String[] args) {
        IO io = new IO();

        IO.printBoxTopBorder();
        IO.printLogo();
        IO.printf("Hello! I'm Archduke. What do you want to do?");
        IO.printBoxBottomBorder();

        while (true) {
            String command = io.readUserInput();

            if (command.equals("bye")) {
                IO.printBoxTopBorder();
                IO.printf("Bye. Hope to see you again soon!");
                IO.printBoxBottomBorder();
                return;
            }

            IO.printBoxTopBorder();
            IO.printf(command);
            IO.printBoxBottomBorder();
        }
    }
}
