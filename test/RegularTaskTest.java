import model.RegularTask;
import org.junit.jupiter.api.BeforeEach;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RegularTaskTest extends TaskTest {


    @BeforeEach
    public void setup() {
        testTask = new RegularTask("testTask");
    }


}
