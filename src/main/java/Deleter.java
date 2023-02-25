public class Deleter {
    /**
     * Check the validity of the delete command
     * if the task selected is valid, deletes the task from the list
     * otherwise, informs the user that the command entered has wrong syntax or task of that index does not exist.
     *
     * @param commands Commands entered by the user.
     * @throws NumberFormatException When the user keys an invalid value for index.
     */
    static void checkAndDeleteTask(String[] commands) throws NumberFormatException {
        if (commands[1].matches("\\d+?")) {
            deleteTask(Integer.parseInt(commands[1]));
        } else {
            throw new NumberFormatException();
        }
    }

    /**
     * Deletes the task at the index specfied from the list
     * If the user gives an invalid index, informs the user about it.
     *
     * @param index The index of the task to be deleted
     * @throws IndexOutOfBoundsException yyWhen the user keys an index that is out of bounds.
     */
    public static void deleteTask(int index) {
        index--;
        if (index < 0 || index >= Duke.tasks.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            Greeting.sayDeleteTaskFromList(Duke.isSinglish);
            Printer.printTask(Duke.tasks.get(index), index + 1);
            Duke.tasks.remove(index);
        }
        Greeting.printHorizontalLines(Duke.isSinglish);
    }
}
