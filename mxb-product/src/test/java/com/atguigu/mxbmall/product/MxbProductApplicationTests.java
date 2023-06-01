package com.atguigu.mxbmall.product;


import com.aliyun.oss.OSSClient;
import com.atguigu.mxbmall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MxbProductApplicationTests {

    @Autowired
    CategoryService categoryService;

    @Test
    public void TestFindPath() {
        Long catelogPath[] = categoryService.findCatelogPath(166L);
        log.info("完整路徑：{}", Arrays.asList(catelogPath));
    }

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

//    @Test
//    void contextLoads() {
//    }

}
