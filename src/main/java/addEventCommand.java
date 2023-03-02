public class addEventCommand extends Command {
    @Override
    public void executeCommand(TaskList taskList, String input) {
        String[] eventSplit = input.split("/", 3);
        String[] eventAndName= eventSplit[0].split(" ", '2');
        String eventName = eventAndName[1];
        String[] fromAndStart = eventSplit[1].split(" ", '2');
        String start = fromAndStart[1];
        String[] toAndEnd = eventSplit[2].split(" ", '2');
        String end = toAndEnd[1];
        Event eventBeingAdded = new Event(eventName, start, end);
        taskList.addTask(eventBeingAdded);
    }
}
