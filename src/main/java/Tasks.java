public class Tasks {
    private static String[] tasks = new String[100];
    private static int numtasks = 0;

    public static void addTask (String input) {
        if (numtasks == 100) {
            IO.output("Too many tasks!");
            return;
        }
        tasks[numtasks] = input;
        numtasks++;
        IO.output("added: " + input);
    }

    public static void listTask () {
        IO.println();
        for (int i = 0; i < numtasks; i++) {
            System.out.println("        " + Integer.toString(i + 1) + ". " + tasks[i]);
        }
        IO.println();
    }
}