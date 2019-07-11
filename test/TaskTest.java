import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskTest {
    private Task testTask1;

    @BeforeEach
    public void setup() {
        testTask1 = new Task("testTask1");
    }

    @Test
    public void testConstructor() {
        assertEquals("testTask1", testTask1.getTaskName());
        assertEquals(0, testTask1.getDayUntilDue());
        assertFalse(testTask1.getOverdue());
    }

    @Test
    public void testGetTaskName() {
        assertEquals("testTask1", testTask1.getTaskName());
    }

    @Test
    public void testGetDayUntilDue() {
        assertEquals(0, testTask1.getDayUntilDue());
    }

    @Test
    public void testGetOverdue() {
        assertFalse(testTask1.getOverdue());
    }

    @Test
    public void testSetTaskName() {
        assertEquals("testTask1", testTask1.getTaskName());
        testTask1.setTaskName("changed");
        assertEquals("changed", testTask1.getTaskName());
    }

    @Test
    public void testSetDayUntilDue() {
        assertEquals(0, testTask1.getDayUntilDue());
        testTask1.setDayUntilDue(5);
        assertEquals(5, testTask1.getDayUntilDue());
    }

    @Test
    public void testSetOverdue() {
        assertFalse(testTask1.getOverdue());
        testTask1.setOverdue(true);
        assertTrue(testTask1.getOverdue());
    }

}
