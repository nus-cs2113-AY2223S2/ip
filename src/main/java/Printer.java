public class Printer {
    /**
     * Prints out the task passed into it.
     *
     * @param task The Task to be printed out
     * @param index The index of the respective task
     */
    public static void printWholeTask(Task task, int index) {
        System.out.print(index);
        task.printTask();
    }

    /**
     * Prints out the entire list of tasks entered by the user.
     */
    public static void printList() {
        int index = 1;
        for (Task task : Duke.tasks) {
            printWholeTask(task, index);
            index++;
        }
        Greeting.printHorizontalLines(Duke.isSinglish);
    }
}
