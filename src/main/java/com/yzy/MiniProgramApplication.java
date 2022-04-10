package com.yzy;

import com.yzy.common.AppBootUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@MapperScan("com/yzy/mapper")
public class MiniProgramApplication {

    public static void main(String[] args) {
        AppBootUtil.run(MiniProgramApplication.class, args);
    }

}
