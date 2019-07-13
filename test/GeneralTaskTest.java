import model.GeneralTask;
import model.Task;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GeneralTaskTest {
    public final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private Task testGeneralTask;


    @BeforeEach
    public void setup() {
        testGeneralTask = new GeneralTask("testGeneralTask");
    }

    @Test
    public void testConstructor() {
        assertEquals("testGeneralTask", testGeneralTask.getTaskName());
        assertEquals(0, testGeneralTask.getDueDate());
        assertFalse(testGeneralTask.getStatus());
        assertEquals(testGeneralTask.getDueDate(), null);
    }

    @Test
    public void testGetTaskName() {
        assertEquals("testGeneralTask", testGeneralTask.getTaskName());
    }

    @Test
    public void testGetDueDate() {
        assertEquals(null, testGeneralTask.getDueDate());
    }

    @Test
    public void testGetDueDateAlreadySet() throws ParseException {
        assertEquals(null, testGeneralTask.getDueDate());

        String newDate = "2019-07-07";
        testGeneralTask.setDueDate(newDate);

        assertTrue(testGeneralTask.getDueDate().toString().equals("Sun Jul 07 00:00:00 PDT 2019"));

    }


    // TODO KEEP GOING FROM HERE
//    @Test
//    public void testGetOverdue() {
//        assertFalse(testGeneralTask.getOverdue());
//    }

    @Test
    public void testSetTaskName() {
        Assert.assertEquals("testTask1", testGeneralTask.getTaskName());
        testGeneralTask.setTaskName("changed");
        Assert.assertEquals("changed", testGeneralTask.getTaskName());
    }

//    @Test
//    public void testSetDayUntilDue() {
//        Assert.assertEquals(0, testGeneralTask.getDueDate());
//        testGeneralTask.setDueDate(5);
//        Assert.assertEquals(5, testGeneralTask.getDueDate());
//    }
//
//    @Test
//    public void testSetOverdue() {
//        assertFalse(testGeneralTask.getOverdue());
//        testGeneralTask.setOverdue(true);
//        assertTrue(testGeneralTask.getOverdue());
//    }
}
