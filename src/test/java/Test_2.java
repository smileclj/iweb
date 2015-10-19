import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lao on 2015/9/28.
 */
public class Test_2 {
    private ClassPathXmlApplicationContext context;
    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    @Test
    public void query(){

    }
}
