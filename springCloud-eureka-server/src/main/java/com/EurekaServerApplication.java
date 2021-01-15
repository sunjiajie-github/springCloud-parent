package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import cn.hutool.core.util.NetUtil;
/**
 *创建 EurekaServerApplication 启动类。
1. @SpringBootApplication 注解表示它是个启动类
2. @EnableEurekaServer 表示它是个注册中心服务器
3. 默认端口号是 8761
4. 进行端口号判断，如果这个端口已经被占用了，就给出提示信息。
5. 使用 SpringApplicationBuilder 进行启动 
 */
@SpringBootApplication
//表示此类是一个客户端
@EnableEurekaServer
public class EurekaServerApplication {
      
    public static void main(String[] args) {
        //8761 这个端口是默认的，就不要修改了，后面的子项目，都会访问这个端口。
//    	NetUtil 工具中主要的方法包括：
//
//    	longToIpv4 根据long值获取ip v4地址
//    	ipv4ToLong 根据ip地址计算出long型的数据
//    	isUsableLocalPort 检测本地端口可用性
//    	isValidPort 是否为有效的端口
//    	isInnerIP 判定是否为内网IP
//    	localIpv4s 获得本机的IP地址列表
//    	toAbsoluteUrl 相对URL转换为绝对URL
//    	hideIpPart 隐藏掉IP地址的最后一部分为 * 代替
//    	buildInetSocketAddress 构建InetSocketAddress
//    	getIpByHost 通过域名得到IP
//    	isInner 指定IP的long是否在指定范围内
        int port = 8761;
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动%n", port );
            System.exit(1);
        }
//        SpringApplication.run(Application.class, args);不涉及端口的启动SpringBoot项目
//        new SpringApplicationBuilder(EurekaServerApplication.class).properties(“server.port=” +
//        port).run(args); SpringBoot项目指定启动端口启动方式。
        new SpringApplicationBuilder(EurekaServerApplication.class).properties("server.port=" + port).run(args);
    }
}