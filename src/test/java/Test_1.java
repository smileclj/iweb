import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.clj.panda.dao.StudentDao;
import com.clj.panda.mapper.test.TestStudentMapper;
import com.clj.panda.model.entity.test.TestStudent;
import com.clj.panda.task.TestJob3;
import com.clj.panda.util.HttpUtils;
import com.clj.panda.util.NetUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test_1 extends AbstractJUnit4SpringContextTests {
    @Resource
    private StudentDao studentDao;

    @Resource
    private TestStudentMapper testStudentMapper;

    private void m() {
        System.out.println(1 / 0);
    }

    @Test
    public void insertStudent() {
        TestStudent student = new TestStudent();
        student.setId("4");
        student.setName("小张张");
        student.setAge(20);
        student.setRemark("备注");
        student.setCreationTime(new Date().getTime());
        try {
            studentDao.insertStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateStudent() {
        int count = testStudentMapper.updateAgeStudentById("3", 22);
        System.err.println(count);
    }

    @Test
    public void selectStudent() {
//        TestStudent student = testStudentMapper.selectStudentById("1");
        TestStudent student = testStudentMapper.selectStudentByNameAndAge("小明", 22);
        System.err.println(JSON.toJSONString(student));
    }

    @Test
    public void test2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(new Date(-2)));
    }

    @Test
    public void test3() {
//        try {
//            m();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("结束");

        List<String> list = new ArrayList<>();
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    public void testScheduler() {
        System.out.println("测试任务");
    }

    @Test
    public void testTask1() throws Exception {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(TestJob3.class).withIdentity("testJob").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("testTrigger").
                withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?")).forJob(jobDetail).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private void t1() {
        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(TestJob3.class).withIdentity("testJob", "group1").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("testTrigger").startNow().
                    withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?")).build();

//            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("testTrigger","group1").startNow().
//                withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void common() {
        Map<String, Object> map = new HashMap<String, Object>();
    }

    @Test
    public void getIpCity() {
        //{status:0,info:"LOCATE_FAILED"}
        String localtionJson = NetUtils.get("http://webapi.amap.com/maps/ipLocation",
                "key=a264606ba32884df4e3bf33610a87991" + "&ip=125.121.119.1");
        localtionJson = localtionJson.substring(1).substring(0, localtionJson.length() - 3);
        System.out.println("当前城市信息 ==" + localtionJson);
        JSONObject jsonObject = JSONObject.parseObject(localtionJson);
        System.out.println(jsonObject.getInteger("status"));

    }

    @Test
    public void random() {
//        System.out.println((int)Math.ceil(Math.random()*2));
        System.out.println(Math.ceil(1.99));
    }

    @Test
    public void wechat() {
        String access_token = "a5RxAoU12q95b70ZNfAzus3F9NxqFmsUpr8Z4grlku21Ev7X9Y3ZxYYhLX-JK1Ue07xgejTbTUYzrWGeADEoruXZya9sIwPrMjr_G-zcYMg";
        String url = "https://api.weixin.qq.com/cgi-bin/user/info";
        String openid = "oyv8Qt7vv9SdDsgBXtyA5x-Tlmag";
        StringBuilder params = new StringBuilder();
        params.append("access_token=").append(access_token).append("&openid=").append(openid);
        System.out.println(NetUtils.get(url, params.toString()));
    }

    @Test
    public void submitSlogan() {
        String url = "http://localhost:8080/portal/activity/christmas/rpc/getAwardList.json";
        Map<String, String> params = new HashMap<>();
        params.put("userToken", "ca5d1b8bfa7a4f84be9850bc14874492");

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 500; i++) {
                    System.out.println(HttpUtils.post(url, params));
                }
            }
        };

        Thread t1 = new Thread(r);
//        Thread t2 = new Thread(r);
        t1.start();
//        t2.start();


    }

    @Test
    public void testThread() {
        //在Test中，如果Test结束，则在Test中启动的子线程会立即结束
        Runnable r = new Runnable() {
            @Override
            public void run() {
                String url = "http://localhost:8080/portal/activity/christmas/rpc/getAwardList.json";
                Map<String, String> params = new HashMap<>();
                params.put("userToken", "ca5d1b8bfa7a4f84be9850bc14874492");
                System.out.println(HttpUtils.post(url, params));
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    @Test
    public void testSlogan() {
        String url = "http://localhost:8080/portal/coupon/rpc/praiseSlogan.json";
        for (int i = 3; i <= 1000; i++) {
            Map<String, String> params = new HashMap<>();
            params.put("userToken", String.valueOf(i));
            System.out.println(HttpUtils.post(url, params));
        }
    }

    @Test
    public void testCouponFish() {
        String url = "http://localhost:8080/portal/coupon/rpc/getAwardWithRandom.json";
        for (int i = 0; i <= 1000; i++) {
            Map<String, String> params = new HashMap<>();
            params.put("userToken", "297901");
            params.put("machineId", "nvn3tNVH7IhYIuYVoBhQPNwuN8");
            params.put("uuid", "123");
            System.out.println(HttpUtils.post(url, params));
        }
    }

    @Test
    public void testRandom() {
        Random r1 = new Random();
        List<Integer> result1 = new ArrayList<Integer>();
        Random r2 = new Random(10);
        for (int i = 1; i < 1000; i++) {
            int randomNum = r1.nextInt(100) + 1;
            if (randomNum == 100) {
                result1.add(randomNum);
            }
            System.out.print(randomNum + ",");
        }
        System.out.println();
        System.out.println("1%的命中率个数:" + result1.size());
//        System.out.println();
//        for (int i = 1; i < 1000; i++) {
//            System.out.print(r2.nextInt(i)+",");
//        }
    }

    @Test
    public void testRandom2() {
        Random r1 = new Random(100);
        Random r2 = new Random(100);
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
                System.out.print(r1.nextInt(1) + ",");
            } else {
                System.out.print(r1.nextInt() + ",");
            }
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print(r2.nextInt() + ",");
        }
    }

    @Test
    public void testInteger() {
        System.out.println(Integer.valueOf(null));
    }

    @Test
    public void testFile() { //3*10*300*10*10
        try {
            PrintWriter pw = new PrintWriter("D:\\test.txt");
            for (int i = 0; i < 10*300*10*12*10; i++) {  //5MB
                pw.println("的的的的的的的的的的");
            }
            System.out.println("file print end");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8080/portal/activity/christmas/rpc/getAwardList.json";
        Map<String, String> params = new HashMap<>();
        params.put("userToken", "888d9225fdea4fcaa8b95d46ee77aef2");

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    System.out.println(HttpUtils.post(url, params));
                }
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        Thread t4 = new Thread(r);
        Thread t5 = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

}
