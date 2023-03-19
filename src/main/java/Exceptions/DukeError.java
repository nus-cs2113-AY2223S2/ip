package Exceptions;

public class DukeError extends Exception {

    public String problem;

    public DukeError(String problemGiven) {

        this.problem = problemGiven;
    }

}
