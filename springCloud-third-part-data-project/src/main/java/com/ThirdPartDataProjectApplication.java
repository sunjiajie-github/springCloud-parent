package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;

@SpringBootApplication
@EnableEurekaClient
public class ThirdPartDataProjectApplication {
   public static void main(String[] args) {
	   int port =8090;
	   int eurekaServerPort=8761;
	   //判断 eurekaServer 服务器接口是否可用 如果可用则服务器未启动
	   if(NetUtil.isUsableLocalPort(eurekaServerPort)) {
		   System.err.printf("检测到%d接口未启用，故判断服务器未启用，因此退出%n",eurekaServerPort);
		   System.exit(1);
	   }
	   //判断main方法中是否传入参数，参数是否是端口号
	   if(null!=args&&0!=args.length) {
		   for(String arg:args) {
			   if(arg.startsWith("port=")) {
				   String strPort=arg.split("=")[1];
				   //String strPort=arg.substring(5);
				   //判断此字符串是否是数字
				   if(NumberUtil.isNumber(strPort)) {
					   port=Integer.parseInt(strPort);
				   }
			   }
		   }
	   }
	   if(!NetUtil.isUsableLocalPort(port)) {
		   System.err.printf("检测到端口号%d被占用，无法启动%n",port);
		   System.exit(1);
	   }
	   //SpringBoot项目指定启动端口启动方式。
	   new SpringApplicationBuilder(ThirdPartDataProjectApplication.class).properties("server.port="+port).run(args);
   }
}
