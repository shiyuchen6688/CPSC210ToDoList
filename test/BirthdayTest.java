import model.Birthday;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ToDoListUsage;

import java.text.ParseException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BirthdayTest extends GeneralTaskTest {
    private Birthday testBirthday;


    @BeforeEach
    public void setup() throws ParseException {
        testBirthday = new Birthday("shiyu", "2019-10-08");
        testTask = new Birthday("shiyu", "2019-10-08");
    }

    @Test
    public void testConstructor() {
        assertEquals("Birthday of shiyu", testBirthday.getName());
        try {
            assertEquals(ToDoListUsage.sdf.parse("2019-10-08"), testBirthday.getDueDate());
        } catch (ParseException e) {

        }
        assertFalse(testBirthday.getStatus());
        assertTrue(testBirthday.getGreeting().equals("Happy Birthday shiyu !"));
        assertTrue(testBirthday.getGift().equals("Birthday card for shiyu"));
    }

    @Test @Override
    public void testGetDueDate() {
        assertEquals("Birthday of shiyu", testBirthday.getName());
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


    @Test @Override
    public void testSetName() {
        assertTrue("Birthday of shiyu".equals(testBirthday.getName()));
        testTask.setName("changed");
        Assert.assertEquals("changed", testTask.getName());
    }

    @Override
    @Test
    public void testGetName() {
        assertTrue("Birthday of shiyu".equals(testBirthday.getName()));
    }
}
