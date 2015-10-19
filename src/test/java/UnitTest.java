import com.clj.panda.mapper.test.TestDateMapper;
import com.clj.panda.model.entity.test.TestDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wana on 2015/10/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UnitTest {
    @Resource
    private TestDateMapper testDateMapper;

    @Test
    public void addDate(){
        TestDate testDate = new TestDate();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        testDate.setTimeOne(sdf.format(now));
        testDate.setTimeTwo(now.getTime());
        testDate.setTimeThree(now);
        int key = testDateMapper.insertDate(testDate);
        System.out.println(key);
        System.out.println(testDate.getId());
    }

    @Test
    public void deleteDate(){
        int row = testDateMapper.deleteDateById(1);
        System.out.println(row);
    }
}
