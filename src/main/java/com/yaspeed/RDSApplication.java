/**
 * @Author wpzhang
 * @Date 2019/8/27
 * @Description
 */
package com.yaspeed;

import com.yaspeed.web.controller.CustomerController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: myProject
 * @description:
 * @author: wpzhang
 * @create: 2019-08-27 09:57
 **/
@EnableTransactionManagement
@MapperScan("com.yaspeed.web.mapper")
@SpringBootApplication
public class RDSApplication {
    public static void main(String[] args) {
        SpringApplication.run(RDSApplication.class,args);
    }
}