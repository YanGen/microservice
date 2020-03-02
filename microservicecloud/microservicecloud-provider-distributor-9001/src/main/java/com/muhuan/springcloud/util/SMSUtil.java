package com.muhuan.springcloud.util;

import org.springframework.util.DigestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class SMSUtil {
    private static final String SYMBOLS = "0123456789"; // 数字
    private static final Random RANDOM = new SecureRandom();
    /**
     * 获取随机数字
     * @return 随机数字
     * @params digit:几位
     */
    public static String getYZM(Integer digit) {

        // 如果需要4位，那 new char[4] 即可，其他位数同理可得
        char[] nonceChars = new char[digit];

        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }

    public static void sendMessage(String phone,String content) throws Exception{
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
        Calendar calendar = Calendar.getInstance();
        String date = df.format(calendar.getTime());

        String pwd = DigestUtils.md5DigestAsHex(("d41d8cd98f00b204e9800998ecf8427r" + date).getBytes());

        //模板签名短信接口请求地址
        String templateSmsUrl="http://smspaas-gateway.jdcloud.com:9000/HttpSmsMt";
        //模板签名短信接口请求参数
        String formParam="name=yslhhy&pwd="+pwd+"&rpttype=1&phone="+phone+"&content=【优世联合】"+content+"&mttime="+date;
        //参数名"code"需要根据模板自定义需要替换的参数名称,否则会调用失败

        HttpURLConnection connection = (HttpURLConnection) new URL(templateSmsUrl).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.getOutputStream().write(formParam.getBytes());
        connection.getOutputStream().close();

        int code=connection.getResponseCode();
        InputStream stream=null;

        if(code==200){
            stream=connection.getInputStream();
        } else {
            stream=connection.getErrorStream();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "utf-8"));
        String str=null;
        StringBuffer sb = new StringBuffer();
        while((str=br.readLine())!=null){
            sb.append(str);
        }
        System.out.println("调用模板签名短信接口"+(code==200 ? "成功" : "失败")+"返回结果："+sb.toString());
    }

}
