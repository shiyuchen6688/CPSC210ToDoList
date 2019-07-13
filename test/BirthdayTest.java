import model.Birthday;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BirthdayTest extends TaskTest {
    private Birthday testBirthday;

    // TODO QUESTION, can I construct another object just to test birthday?
    // TODO QUESTION, how to test two constructor?
    @BeforeEach
    public void setup() {
        testBirthday = new Birthday("shiyu");
        testTask = new Birthday("shiyu");
    }

    @Test
    public void testConstructor() {
        assertTrue("Birthday of shiyu".equals(testBirthday.getTaskName()));
        assertEquals(null, testBirthday.getDueDate());
        assertFalse(testBirthday.getStatus());
        assertTrue(testBirthday.getGreeting().equals("Happy Birthday shiyu !"));
        assertTrue(testBirthday.getGift().equals("Birthday card for shiyu"));
    }


    @Test
    public void testSetGreeting() {
        // check initial and setup
        assertTrue(testBirthday.getGreeting().equals("Happy Birthday shiyu !"));
        // invoke
        String newGreeting = "Greeting changed";
        testBirthday.setGreeting(newGreeting);
        // check outcome
        assertTrue(testBirthday.getGreeting().equals(newGreeting));
    }


    @Test
    public void testSetGift(){
        // check initial and setup
        assertTrue(testBirthday.getGift().equals("Birthday card for shiyu"));
        // invoke
        String newGift = "Gift changed";
        testBirthday.setGift(newGift);
        // check outcome
        assertTrue(testBirthday.getGift().equals(newGift));
    }

    @Test
    public void testGetGreeting() {
        // check initial and setup
        assertTrue(testBirthday.getGreeting().equals("Happy Birthday shiyu !"));
        // invoke
        String newGreeting = "Greeting changed";
        testBirthday.setGreeting(newGreeting);
        // check outcome
        assertTrue(testBirthday.getGreeting().equals(newGreeting));
    }

    @Test
    public void testGetGift() {
        // check initial and setup
        assertEquals("Birthday card for shiyu",testBirthday.getGift());
        // invoke
        String newGift = "Gift changed";
        testBirthday.setGift(newGift);
        // check outcome
        assertTrue(testBirthday.getGift().equals(newGift));
    }

    // TODO QUESTION can I do this?
    @Override
    @Test
    public void testSetTaskName() {
        assertTrue("Birthday of shiyu".equals(testBirthday.getTaskName()));
        testTask.setTaskName("changed");
        Assert.assertEquals("changed", testTask.getTaskName());
    }

    @Override
    @Test
    public void testGetTaskName() {
        assertTrue("Birthday of shiyu".equals(testBirthday.getTaskName()));
    }
}
