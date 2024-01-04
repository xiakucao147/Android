package com.example.androiddemojava.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpConnectUtil {

    public static String BASE_URL= "http://192.168.2.107:8080/webDemo";//根据服务器的地址和程序确定
    private static String TAG = "http";

    public static String postHttpRequest(String urlStr, Map<String,String> parms){ 
        parms.put("device_type","android");
        Log.i(TAG, "POST request");
        StringBuilder result = new StringBuilder();//StringBuilder用于单线程多字符串拼接
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            // 设定请求的方法为"POST"，默认是GET
            connection.setRequestMethod("POST");
            //设置从主机读取数据超时（单位：毫秒）
            connection.setReadTimeout(5000);
            //设置连接主机超时（单位：毫秒）
            connection.setConnectTimeout(5000);
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            connection.setDoInput(true);
            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true, 默认情况下是false;
            connection.setDoOutput(true);

            //设置自动重新定向
            connection.setInstanceFollowRedirects(true);
            // 此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，所以在开发中不调用上述的connect()也可以)。
            OutputStream outputStream = connection.getOutputStream();//从程序向服务器写数据，所以是输出流
            //BufferedWriter:字符缓冲输出流,构造方法BufferedWriter(Writer out) :
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            writer.write(getStringFromOutput(parms));
            //刷新对象输出流，将任何字节都写入潜在的流中
            writer.flush();
            // 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中,
            // 在调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器
            writer.close();
            outputStream.close();
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String temp;
                while((temp = reader.readLine()) != null) {
                    result.append(temp);
                    reader.close();
                }
            }else{
                return "connection error:" + connection.getResponseCode();
            }
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * 将map转换成key1=value1&key2=value2的形式
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String getStringFromOutput(Map<String,String> map) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();//StringBuilder用于单线程多字符串拼接
        boolean isFirst = true;
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(isFirst)
                isFirst = false;
            else
                sb.append("&");
            sb.append(URLEncoder.encode(entry.getKey(),"UTF-8"));//URLEncoder对参数进行编码，在服务器端要相应解码
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
        }
        return sb.toString();
    }


    public static String getHttpResult(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            InputStream input = connect.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String line = null;
            System.out.println(connect.getResponseCode());
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}