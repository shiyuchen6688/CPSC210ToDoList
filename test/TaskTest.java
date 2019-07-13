import model.Task;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskTest {
    public final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    protected Task testTask;

    @Test
    public void testConstructor() {
        assertEquals("testTask", testTask.getTaskName());
        assertEquals(null, testTask.getDueDate());
        assertFalse(testTask.getStatus());
    }

    @Test
    public void testGetTaskName() {
        assertEquals("testTask", testTask.getTaskName());
    }

    @Test
    public void testGetDueDate() {
        assertEquals(null, testTask.getDueDate());
    }

    @Test
    public void testGetDueDateAlreadySet() throws ParseException {
        assertEquals(null, testTask.getDueDate());

        String newDate = "2019-07-07";
        testTask.setDueDate(newDate);

        assertTrue(testTask.getDueDate().toString().equals("Sun Jul 07 00:00:00 PDT 2019"));

    }

    @Test
    public void testGetStatus() {
        assertFalse(testTask.getStatus());
    }

    @Test
    public void testSetTaskName() {
        Assert.assertEquals("testTask", testTask.getTaskName());
        testTask.setTaskName("changed");
        Assert.assertEquals("changed", testTask.getTaskName());
    }

    @Test
    public void testSetDueDate() throws ParseException {
        // check and initial setup
        Assert.assertEquals(null, testTask.getDueDate());
        // invoke method
        String newDate = "2019-07-07";
        testTask.setDueDate(newDate);
        // check outcome
        assertTrue(testTask.getDueDate().toString().equals("Sun Jul 07 00:00:00 PDT 2019"));
    }

    @Test
    public void testStatus() {
        assertFalse(testTask.getStatus());
        testTask.setStatus(true);
        assertTrue(testTask.getStatus());
    }

    @Test
    public void testOverdueWhenNull() {
        // check and setup initial
        assertEquals(null, testTask.getDueDate());
        assertFalse(testTask.isOverdue());
    }

    @Test
    public void testOverdueWhenNot() throws ParseException {
        // check and setup initial
        assertEquals(null, testTask.getDueDate());
        testTask.setDueDate("3000-10-10");
        assertFalse(testTask.isOverdue());
    }

    @Test
    public void testOverdueWhenOverdue() throws ParseException {
        // check and setup initial
        assertEquals(null, testTask.getDueDate());
        // invoke behavior
        testTask.setDueDate("1999-01-01");
        // check outcome
        assertTrue(testTask.isOverdue());

    }
}
