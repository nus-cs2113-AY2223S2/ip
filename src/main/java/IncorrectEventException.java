public class IncorrectEventException extends DukeException{
    @Override
    public String printError() {
        return "Event input task is invalid, input should follow (/from) and (/to)";
    }
}
