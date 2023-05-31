package com.atguigu.mxbmall.product;


import com.aliyun.oss.OSSClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootTest
class MxbProductApplicationTests {

//    @Autowired
//    OSSClient ossClient;
//    @Test
//    public void testUpload() {
//
//        //上传文件流。
//        InputStream inputStream = new FileInputStream("/home/erfenjiao/图片/file/phone.jpg");
//        ossClient.putObject("mxbmall", "phone.jpg", inputStream);
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
//        System.out.println("上传成功.");
//    }

    @Test
    void contextLoads() {
    }

}
