import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Deadline;
import task.EmptyDescriptionException;
import task.Task;
import task.TaskMarkException;
import task.ToDo;

public class TestTask {
    @Test
    public void givenDescription_whenGetDescription_thenCorrectDescription() throws EmptyDescriptionException {
        Task task = new ToDo();
        task.setDescription("Some random string");
        assertEquals("Some random string", task.getDescription());
    }

    @Test
    public void givenTask_whenMark_thenPrintX() throws TaskMarkException {
        Task task = new ToDo();
        task.setMark(true);
        assertEquals('X', task.getStatusIcon());
    }
    @Test
    public void givenDeadline_whenGetDescription_thenCorrectDescription() throws EmptyDescriptionException {
        Deadline task = new Deadline();
        task.setDescription("Some random string");
        assertEquals("Some random string", task.getDescription());
    }
    @Test
    public void givenDeadlineDate_whenGetDate_thenCorrectDate() throws EmptyDescriptionException {
        Deadline task = new Deadline();
        task.setEndDate("Some random date");
        assertEquals("Some random date", task.getEndDate());
    }
}