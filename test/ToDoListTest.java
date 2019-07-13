import model.GeneralTask;
import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ToDoListTest {
    private ToDoList testToDoList;

    @BeforeEach
    public void setup() {
        testToDoList = new ToDoList();
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
    public void testAddTask2() {
        checkToDoEmptyDoesntContain();
        testToDoList.addTask(new GeneralTask("test task"));
        checkToDoContainOnce();
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
