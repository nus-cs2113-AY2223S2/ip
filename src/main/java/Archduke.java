public class Archduke {
    public static void main(String[] args) {
        IO io = new IO();
        TaskStore store = new TaskStore();

        IO.printBoxTopBorder();
        IO.printLogo();
        IO.printf("Hello! I'm Archduke. What do you want to do?");
        IO.printBoxBottomBorder();

        while (true) {
            String command = io.readUserInput();

            switch (command) {
            case "list":
                IO.printBoxTopBorder();
                IO.printf("Here are your tasks:");
                store.listTasks();
                IO.printBoxBottomBorder();
                continue;
            case "bye":
                IO.printBoxTopBorder();
                IO.printf("Bye. Hope to see you again soon!");
                IO.printBoxBottomBorder();
                return;
            default:
                store.addTask(command);
                IO.printBoxTopBorder();
                IO.printf("Added task: %s", command);
                IO.printBoxBottomBorder();
            }
        }
    }
}
