import model.GeneralTask;
import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ToDoListTest {
    private ToDoList testToDoList;

    private static GeneralTask generalGeneralTaskCPSC210;
    private static GeneralTask generalGeneralTaskWRDS150;
    private static GeneralTask generalGeneralTask1DayLeft;
    private static GeneralTask generalGeneralTask0DayLeft;
    private static GeneralTask generalGeneralTask1DayOverdue;
    private static GeneralTask generalGeneralTask5DayOverdue;

    @BeforeEach
    public void setup() {
        testToDoList = new ToDoList();

        generalGeneralTaskCPSC210 = new GeneralTask("Assignment one for CPSC210");
        generalGeneralTaskWRDS150 = new GeneralTask("Paragraph for WRDS150");
        generalGeneralTask1DayLeft = new GeneralTask("This task should have one day left, not overdue");
        generalGeneralTask1DayLeft.setDayUntilDue(1);
        generalGeneralTask0DayLeft = new GeneralTask("This task should have zero day left, not overdue");
        generalGeneralTask0DayLeft.setDayUntilDue(0);
        generalGeneralTask1DayOverdue = new GeneralTask("This task should OVERDUE 1 day");
        generalGeneralTask1DayOverdue.setDayUntilDue(-1);
        generalGeneralTask5DayOverdue = new GeneralTask("This task should OVERDUE 10 day");
        generalGeneralTask5DayOverdue.setDayUntilDue(-5);
    }


    @Test
    public void testConstructor() {
        checkToDoEmptyDoesntContain();
    }


    @Test
    public void testAddTask() {
        checkToDoEmptyDoesntContain();
        testToDoList.addTask("test task");
        checkToDoContainOnce();
    }

    @Test
    public void testAddTwoTask() {
        checkToDoEmptyDoesntContain();
        testToDoList.addTask("test task");
        testToDoList.addTask("test task 2");
        assertTrue(testToDoList.contains("test task"));
        assertTrue(testToDoList.contains("test task 2"));
        assertEquals(testToDoList.size(), 2);
    }


    @Test
    public void testDeleteTaskExist() {
        checkToDoEmptyDoesntContain();
        testToDoList.addTask("test task");
        checkToDoContainOnce();
        boolean b = testToDoList.deleteTask("test task");
        assertTrue(b);
        checkToDoEmptyDoesntContain();
    }


    @Test
    public void testDeleteTaskDoesntExist() {
        checkToDoEmptyDoesntContain();
        boolean b = testToDoList.deleteTask("test task");
        assertFalse(b);
    }


    @Test
    public void testFindTaskExist() {
        checkToDoEmptyDoesntContain();
        testToDoList.addTask("test task");
        checkToDoContainOnce();
        GeneralTask t = testToDoList.findTask("test task");
        assertEquals(t.getTaskName(), "test task");
        checkToDoContainOnce();
    }


    @Test
    public void testFindTaskDoesntExist() {
        checkToDoEmptyDoesntContain();
        GeneralTask t = testToDoList.findTask("test task");
        assertEquals(t, null);
        checkToDoEmptyDoesntContain();
    }

    @Test
    public void testContainsExist() {
        checkToDoEmptyDoesntContain();
        testToDoList.addTask("test task");
        assertTrue(testToDoList.contains("test task"));
    }

    @Test
    public void testContainsDoesntExist() {
        checkToDoEmptyDoesntContain();
        assertFalse(testToDoList.contains("test task"));
    }

    @Test
    public void testSize() {
        assertEquals(0, testToDoList.size());
    }

    @Test
    public void testSizeContainOne() {
        testToDoList.addTask("test task");
        assertEquals(1, testToDoList.size());
    }

    @Test
    public void testGetAllTaskStringEmpty() {
        checkToDoEmptyDoesntContain();
        assertEquals("", testToDoList.getAllTaskAsString());
    }

    @Test
    public void testGetAllTwoTaskString() {
        checkToDoEmptyDoesntContain();
        testToDoList.addTask("task1");
        testToDoList.addTask("task2");
        assertEquals("\ntask1\ntask2", testToDoList.getAllTaskAsString());
    }

    private void checkToDoContainOnce() {
        assertEquals(testToDoList.size(), 1);
        assertTrue(testToDoList.contains("test task"));
    }

    private void checkToDoEmptyDoesntContain() {
        assertEquals(testToDoList.size(), 0);
        assertFalse(testToDoList.contains("test task"));
    }

}
