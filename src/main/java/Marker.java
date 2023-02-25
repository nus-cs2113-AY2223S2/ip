public class Marker {
    /**
     * Checks the validity of the mark/unmark command.
     * if the task selected is valid, marks or unmark the task depending on the exact command.
     * otherwise, informs the user that the command entered has wrong syntax or task of that index does not exist.
     *
     * @param commands Commands entered by the user.
     * @throws NumberFormatException When the user keys an invalid value for index.
     */
    static void checkAndMarkTask(String[] commands) throws NumberFormatException {
        if (commands[1].matches("\\d+?")) {
            boolean isMark = commands[0].equals(Constants.MARK_COMMAND);
            markTask(Integer.parseInt(commands[1]), isMark);
        } else {
            throw new NumberFormatException();
        }
    }

    /**
     * Marks or Unmark the selected task whether it is done, prints out the selected task alongside its state.
     * If the user gives an invalid index, informs the user about it.
     * Does nothing if the user trys to mark a marked task and vice versa.
     *
     * @param index The index of the task selected to be marked or unmarked.
     * @param isMark Whether to mark or unmark the task.
     * @throws IndexOutOfBoundsException When the user keys an index that is out of bounds.
     */
    public static void markTask(int index, boolean isMark) throws IndexOutOfBoundsException {
        index--;
        if (index < 0 || index >= Duke.tasks.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            if (Duke.tasks.get(index).getIsDone() != isMark) {
                Duke.tasks.get(index).switchIsDone();
            }
            Greeting.sayUpdatedTask(Duke.isSinglish);
            Printer.printTask(Duke.tasks.get(index), index + 1);
        }
        Greeting.printHorizontalLines(Duke.isSinglish);
    }
}
