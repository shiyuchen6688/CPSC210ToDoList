import model.Task;
import model.ToDoList;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class ToDoListTest {
    private ToDoList testToDoList;

    private static Task taskCPSC210;
    private static Task taskWRDS150;
    private static Task task1DayLeft;
    private static Task task0DayLeft;
    private static Task task1DayOverdue;
    private static Task task5DayOverdue;

    @Before
    public void setup() {
        testToDoList = new ToDoList();

        taskCPSC210 = new Task("Assignment one for CPSC210");
        taskWRDS150 = new Task("Paragraph for WRDS150");
        task1DayLeft = new Task("This task should have one day left, not overdue");
        task1DayLeft.setDayUntilDue(1);
        task0DayLeft = new Task("This task should have zero day left, not overdue");
        task0DayLeft.setDayUntilDue(0);
        task1DayOverdue = new Task("This task should OVERDUE 1 day");
        task1DayOverdue.setDayUntilDue(-1);
        task5DayOverdue = new Task("This task should OVERDUE 10 day");
        task5DayOverdue.setDayUntilDue(-5);
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
        Task t = testToDoList.findTask("test task");
        assertEquals(t.getTaskName(), "test task");
        checkToDoContainOnce();
    }


    @Test
    public void testFindTaskDoesntExist() {
        checkToDoEmptyDoesntContain();
        Task t = testToDoList.findTask("test task");
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
        assertEquals("", testToDoList.getAllTaskString());
    }

    @Test
    public void testGetAllTwoTaskString() {
        checkToDoEmptyDoesntContain();
        testToDoList.addTask("task1");
        testToDoList.addTask("task2");
        assertEquals("\ntask1\ntask2", testToDoList.getAllTaskString());
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
