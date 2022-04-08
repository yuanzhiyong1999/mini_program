package com.yzy.common;

import java.io.*;
import java.util.Base64;

import org.springframework.util.Base64Utils;



/**
 * @author yuanzhiyong
 * @version 1.0.0
 * @ClassName HandleImg.java
 * @Description 处理Base64图片相关
 * @createTime 2021年04月21日 17:26:00
 */
public class HandleImg {
    /*
    将无头的Base64图片处理后保存，并返回存贮绝对路径
     */
    public static String requirePath(String base64){
        //2,解码成字节数组
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] result=decoder.decode(new String(base64));

        //3,字节流转文件
        String uuid= RandomUtils.generateStr(10);//uuid作为保存时的文件名
        String db_path = "/photo/" + uuid +".jpg"; //存储在数据库的路径

        File picfilepath=new File(System.getProperty("user.dir")+"/src/main/resources/static" + db_path);//保存文件
        File filepath=new File(System.getProperty("user.dir")+"/src/main/resources/static/photo/");//创建文件夹
        if(!filepath.exists()){//如果没有文件夹，新建
            filepath.mkdirs();
        }
        try{
            FileOutputStream out=new FileOutputStream(picfilepath);
            out.write(result);
            out.close();
            return db_path;
        }catch(Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }

    /*
    读取文件并将其转换为Base64，添加头后返回
     */
    public static String requireBase64(String path){

        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            // 返回Base64编码过的字节数组字符串
            return Base64Utils.encodeToString(data);
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
