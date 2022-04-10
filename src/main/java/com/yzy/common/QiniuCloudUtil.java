package com.yzy.common;


import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
@Component
public class QiniuCloudUtil {

    // 设置需要操作的账号的AK和SK

    private static String ACCESS_KEY;

    private static String SECRET_KEY;

    // 要上传的空间
    private static String BucketName;

    //七牛云域名
    private static String Domain;


    public String getUpToken() {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        return auth.uploadToken(BucketName, null, 3600, new StringMap().put("insertOnly", 1));
    }


    //base64方式上传
    public String put64image(byte[] base64, String key) throws Exception {
        String file64 = Base64.encodeToString(base64, 0);
        Integer l = base64.length;
        String url = "http://up-z1.qiniu.com/putb64/" + l + "/key/" + UrlSafeBase64.encodeToString(key);
        //非华东空间需要根据注意事项 1 修改上传域名
        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder().
                url(url).
                addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken())
                .post(rb).build();
        //System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
        //如果不需要添加图片样式，使用以下方式
        return key;
    }

    private void makeDownload(String objectKey, ByteArrayOutputStream bos, HttpServletResponse response) throws IOException {
        OutputStream out = response.getOutputStream();
        byte[] content = bos.toByteArray();
        String fileName = objectKey.substring(objectKey.lastIndexOf("/"));
        response.setHeader("Pragma", "No-cache"); // 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;filename=" +
                new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
        response.setContentType("application/json");
        out.write(content);
        out.flush();
        out.close();
    }

    public static String download(String key) throws UnsupportedEncodingException {
        String domainOfBucket = Domain;
        String encodedFileName = URLEncoder.encode(key, "utf-8").replace("+", "%20");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        publicUrl = publicUrl+"?imageView2/1/w/200/h/200";
        System.out.println(publicUrl);
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
        System.out.println(finalUrl);
        return finalUrl;
    }

    @Value("${qiniuyun.AccessKey}")
    public void setAccessKey(String accessKey) {
        QiniuCloudUtil.ACCESS_KEY = accessKey;
    }

    @Value("${qiniuyun.bucketname}")
    public void setBucketName(String bucketName) {
        QiniuCloudUtil.BucketName = bucketName;
    }

    @Value("${qiniuyun.SecretKey}")
    public void setSecretKey(String secretKey) {
        QiniuCloudUtil.SECRET_KEY = secretKey;
    }

    @Value("${qiniuyun.url}")
    public void setDOMAIN(String domain) {
        QiniuCloudUtil.Domain = domain;
    }
}