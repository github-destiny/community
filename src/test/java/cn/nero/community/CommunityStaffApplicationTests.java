package cn.nero.community;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class CommunityStaffApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void redisTest(){
        redisTemplate.opsForValue().set("k1", "v1");
        System.out.println(redisTemplate.opsForValue().get("k1"));
    }

    @Test
    public void httpTest(){
        String url = "https://c.m.163.com/ug/api/wuhan/app/data/list-total";
        CloseableHttpClient defaultClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = defaultClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            byte[] bytes = new byte[content.available()];
            int read = content.read(bytes);
            if (read != -1) {
                String result = new String(bytes);
                System.out.println(result);
            } else {
                System.out.println("===================================");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendRequestTest(){
        String urlStr = "https://c.m.163.com/ug/api/wuhan/app/data/list-total";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.connect();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while((line = bReader.readLine()) != null){
                sb.append(line);
            }
            bReader.close();
            urlConnection.disconnect();
            System.out.println(sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
