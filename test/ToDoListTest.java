import exceptions.TaskNotFoundException;
import model.RegularTask;
import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoListTest {
    private ToDoList testToDoList;

    @BeforeEach
    public void setup() {
        testToDoList = new ToDoList("General");
    }




    // test Constructor
    @Test
    public void testConstructor() {
        checkToDoEmptyDoesntContain();
        assertEquals("General", testToDoList.getName());
    }


    @Test
    public void testGetTasks() {
        checkToDoEmptyDoesntContain();
        Task t = new RegularTask("test");
        testToDoList.addTask(t);
        assertEquals(1, testToDoList.getTasks().size());
        assertEquals(t, testToDoList.getTasks().get(0));
    }

    @Test
    public void testAddTaskWithTaskName() {
        checkToDoEmptyDoesntContain();
        testToDoList.addTask("test task");
        checkToDoContainOnce();
    }

    @Test
    public void testAddTwoTaskWithTaskName() {
        checkToDoEmptyDoesntContain();
        testToDoList.addTask("test task");
        testToDoList.addTask("test task 2");
        assertTrue(testToDoList.contains("test task"));
        assertTrue(testToDoList.contains("test task 2"));
        assertEquals(testToDoList.size(), 2);
    }

    @Test
    public void testAddTaskWithNewTask() {
        checkToDoEmptyDoesntContain();
        testToDoList.addTask(new RegularTask("test task"));
        checkToDoContainOnce();
    }



    @Test
    public void testDeleteTaskExist() throws TaskNotFoundException {
        checkToDoEmptyDoesntContain();
        testToDoList.addTask("test task");
        checkToDoContainOnce();
        try {
            boolean b = testToDoList.deleteTask("test task");
            assertTrue(b);
            checkToDoEmptyDoesntContain();
        } catch (TaskNotFoundException e) {
            fail("Caught TaskNotFound when shouldn't have");
        }

    }


    @Test
    public void testDeleteTaskDoesntExist() throws TaskNotFoundException {
        checkToDoEmptyDoesntContain();
        boolean b = testToDoList.deleteTask("test task");
        assertFalse(b);



    }


    @Test
    public void testFindTaskExist() {
        checkToDoEmptyDoesntContain();
        testToDoList.addTask("test task");
        checkToDoContainOnce();
        try {
            Task t = testToDoList.findTask("test task");
            assertEquals(t.getTaskName(), "test task");
        } catch (TaskNotFoundException e) {
            fail("caught TaskNotFound exception when shouldn't have");
        }

        checkToDoContainOnce();
    }


    @Test
    public void testFindTaskDoesntExist() {
        checkToDoEmptyDoesntContain();
        try {
            Task t = testToDoList.findTask("test task");
            fail();
        } catch (TaskNotFoundException e) {
            // expected
        }
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
        assertEquals("task1\ntask2", testToDoList.getAllTaskAsString());
    }


    // test for exceptions
    @Test
    public void testExpectTaskNotFoundException() {
        Assertions.assertEquals(0, testToDoList.size());

        try {
            testToDoList.findTask("a task");
            fail("No exception thrown");
        } catch (TaskNotFoundException e) {
            // Do nothing
        } catch (Exception e) {
            fail("Wrong exception was thrown");
        }

    }


    @Test
    public void testExpectNoException() {
        testToDoList.addTask("t1");
        Assertions.assertTrue(testToDoList.contains("t1"));

        try {
            testToDoList.findTask("t1");
        } catch (TaskNotFoundException t) {
            fail("Thrown TaskNotFound exception when should not");
        } catch (Exception e) {
            fail("Thrown unexpected exception when should not");
        }
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
