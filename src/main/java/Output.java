public class Output {
    private static void printLogo() {
        String logo =
                "░█████╗░██╗░░██╗██████╗░░█████╗░███╗░░██╗░█████╗░░██████╗\n" +
                        "██╔══██╗██║░░██║██╔══██╗██╔══██╗████╗░██║██╔══██╗██╔════╝\n" +
                        "██║░░╚═╝███████║██████╔╝██║░░██║██╔██╗██║██║░░██║╚█████╗░\n" +
                        "██║░░██╗██╔══██║██╔══██╗██║░░██║██║╚████║██║░░██║░╚═══██╗\n" +
                        "╚█████╔╝██║░░██║██║░░██║╚█████╔╝██║░╚███║╚█████╔╝██████╔╝\n" +
                        "░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝░╚════╝░╚═╝░░╚══╝░╚════╝░╚═════╝░\n";
        System.out.println(logo);

    }

    public static void printWelcome(){
        System.out.println("Hello from\n");
        printLogo();
        System.out.println("Tick, tick, boom! I'm Chronos, your personal time manager.\n");
    }
    public static void printHelp() {
        System.out.println("=============================================================================================================== \n");
        System.out.println("Here are some useful commands to get you started!\n");
        System.out.println("=============================================================================================================== \n");
        System.out.println("'See list': Take a look at your To-Do list to get your day started!");
        System.out.println("'mark <task number>': Marks task as done. Try entering 'mark 1' to mark your first task as done!");
        System.out.println("'unmark <task_number>': Unmarks task as not done. Try entering 'unmark 1' to mark your first task as not done.");
        System.out.println("'Help': If you forgot how to use me, don't be afraid to ask!");
        System.out.println("To add a task, enter a description of said task and I will add it into the list for you :) \n");
        System.out.println("=============================================================================================================== \n");
    }

    public static void printList(Stash stash) {
        System.out.println("Here's what you have to do:");
        stash.printTasks();
    }

    public static void printIsDone(Stash stash, int index) {
        Task task = stash.getTask(index);
        System.out.printf("This task has been marked as %s", task.isDone() ? "done!" : "undone.");
        System.out.printf("\n");
        System.out.printf("  %s", task.toString());
        System.out.printf("\n");
    }

    public static void printNewTask(Task task, int stashSize) {
        System.out.printf("Added New Task: ");
        System.out.printf("\n");
        System.out.printf(" %s", task.toString());
        System.out.printf("\n");
        System.out.printf("You now have %d task(s) in the list.", stashSize);
        System.out.printf("\n");
    }

}
