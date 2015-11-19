import com.clj.panda.util.HttpUtils;
import com.clj.panda.util.NetUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lao on 2015/9/28.
 */
public class Test_3 {
    @Test
    public void getUser() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/user/getUser.htm");
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity,"UTF-8"));
        } catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void addLoginLog(){
        for(int i = 0;i<100;i++){
            System.out.println(HttpUtils.get("http://localhost:8080/portal/wechat/rpc/addLoginLog.json?userId=408593&storeId=965"));
        }


    }
    @Test
    public void singleUpload(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost("http://localhost:8080/user/singleUpload.htm");
            URI uri = getClass().getResource("test.txt").toURI();
            FileBody bin = new FileBody(new File(uri));
            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);
            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file", bin).addPart("comment", comment).build();
            httppost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    System.out.println("Response content length: " + resEntity.getContentLength());
                }
                System.out.println(EntityUtils.toString(resEntity));
            } finally {
                response.close();
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void multiUpload(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost("http://localhost:8080/user/multiUpload.htm");
            URI uri1 = getClass().getResource("test.txt").toURI();
            FileBody file1 = new FileBody(new File(uri1));
            URI uri2 = getClass().getResource("test2.txt").toURI();
            FileBody file2 = new FileBody(new File(uri2));
            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);
            HttpEntity reqEntity = MultipartEntityBuilder.create().
                    addPart("file", file1).
                    addPart("file", file2).
                    addPart("comment", comment).build();
            httppost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    System.out.println("Response content length: " + resEntity.getContentLength());
                }
                System.out.println(EntityUtils.toString(resEntity));
            } finally {
                response.close();
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void downloadOne(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/user/downloadOne.htm");
        CloseableHttpResponse response = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            bis = new BufferedInputStream(entity.getContent());
            bos = new BufferedOutputStream(new FileOutputStream(new File("D:\\test_d.txt")));
            while(bis.available()>0){
                bos.write(bis.read());
            }
            bos.flush();
        } catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    public void wanaTest(){
        String url = "http://wa.52wana.com:90/portal/wechat/rpc/addColumnUnionid.json";
        String accessToken = "IUaqwH1JUFrqKRQu3Rwrb3XxUdOE8AgaDt8t2mRp6U4hQs_Vphvdo8L1cU1dDNsRe3ez0lSMRM6Pb5AB44gABfH0E4_aQZvbJrM-yvYK3Uc";
//        Map<String,Object> params = new HashMap<>();
//        params.put("accessToken", accessToken);
        String param = "accessToken=" + accessToken;
        for(int i=0;i<1;i++){
            System.out.println(NetUtils.post(url,param,"utf-8",120000,120000));
        }
        System.out.println("成功");
    }
}
