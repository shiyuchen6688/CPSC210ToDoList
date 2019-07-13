import model.GeneralTask;
import model.Task;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GeneralTaskTest extends TaskTest {


    @BeforeEach
    public void setup() {
        testTask = new GeneralTask("testTask");
    }


}
