package tests;

import model.ToDoList;
import model.ToDoMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoMapTest {
    private ToDoMap map;

    @BeforeEach
    public void setUp() {
        map = new ToDoMap();
    }


    @Test
    public void testAddToDoList() {
        String listName = "list 1";
        map.addToDoList(listName);

        assertTrue(map.contains(listName));
        assertEquals(new ToDoList(listName), map.getList(listName));
    }

    @Test
    public void testContains() {
        String listName = "list 1";
        map.addToDoList(listName);

        assertTrue(map.contains(listName));
    }
}

