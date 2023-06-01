package com.atguigu.mxbmall.thirdparty;


import com.aliyun.oss.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

//
//@RunWith(SpringRunner.class)
@SpringBootTest
public class MxbmallThirdPartyApplicationTests {

//	@Autowired(required = false)
//	OSS ossClient;

	//@Test
//	public void testUpload() throws FileNotFoundException {
////		OSSClient ossClient;
//		// Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
//
//		//密钥泄漏问题，用的时候加上
//
//		// 填写Bucket名称，例如examplebucket。
//		String bucketName = "mxbmall";
//		// 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
//		//String objectName = "exampledir/exampleobject.txt";
//		// 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
//		// 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
//		String filePath= "/home/erfenjiao/图片/file/phone2.jpg";
//
//
//		 //创建OSSClient实例。
//		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//
//		InputStream inputStream = new FileInputStream(filePath);
//		ossClient.putObject(bucketName, "phone3.jpg", inputStream);
//
//		ossClient.shutdown();
//
//		System.out.println("上传完成...");
//
//	}


}
