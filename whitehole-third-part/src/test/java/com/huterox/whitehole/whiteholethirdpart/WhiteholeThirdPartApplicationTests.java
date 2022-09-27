package com.huterox.whitehole.whiteholethirdpart;

import com.aliyun.oss.OSS;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class WhiteholeThirdPartApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    OSS ossClient;
    @Test
    void upLoad() throws FileNotFoundException {
        ossClient.putObject(
                "whiteholecloud-dev", "3.jpg",
                new FileInputStream("C:\\Users\\31395\\Pictures\\Saved Pictures\\03.jpg")
        );
        System.out.println("图片上传成功");
    }

}
