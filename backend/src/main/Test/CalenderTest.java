import com.hrm.utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;

public class CalenderTest {

    @Test
    public void test01() throws ParseException {

        String currentDatetime = DateUtils.getCurrentDatetime();

        System.out.println(currentDatetime);

        String dueDatetime = DateUtils.setDueDatetime(currentDatetime,2);
        System.out.println(dueDatetime);
    }
}
