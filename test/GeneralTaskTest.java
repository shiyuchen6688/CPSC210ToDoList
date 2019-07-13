import model.GeneralTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GeneralTaskTest {
    private GeneralTask testGeneralGeneralTask1;

    @BeforeEach
    public void setup() {
        testGeneralGeneralTask1 = new GeneralTask("testTask1");
    }

    @Test
    public void testConstructor() {
        assertEquals("testTask1", testGeneralGeneralTask1.getTaskName());
        assertEquals(0, testGeneralGeneralTask1.getDayUntilDue());
        assertFalse(testGeneralGeneralTask1.getOverdue());
    }

    @Test
    public void testGetTaskName() {
        assertEquals("testTask1", testGeneralGeneralTask1.getTaskName());
    }

    @Test
    public void testGetDayUntilDue() {
        assertEquals(0, testGeneralGeneralTask1.getDayUntilDue());
    }

    @Test
    public void testGetOverdue() {
        assertFalse(testGeneralGeneralTask1.getOverdue());
    }

    @Test
    public void testSetTaskName() {
        assertEquals("testTask1", testGeneralGeneralTask1.getTaskName());
        testGeneralGeneralTask1.setTaskName("changed");
        assertEquals("changed", testGeneralGeneralTask1.getTaskName());
    }

    @Test
    public void testSetDayUntilDue() {
        assertEquals(0, testGeneralGeneralTask1.getDayUntilDue());
        testGeneralGeneralTask1.setDayUntilDue(5);
        assertEquals(5, testGeneralGeneralTask1.getDayUntilDue());
    }

    @Test
    public void testSetOverdue() {
        assertFalse(testGeneralGeneralTask1.getOverdue());
        testGeneralGeneralTask1.setOverdue(true);
        assertTrue(testGeneralGeneralTask1.getOverdue());
    }

}
