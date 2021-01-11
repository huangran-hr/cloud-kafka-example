/**
 * 
 */
package com.sjlh.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

/**
 * @author Administrator
 *
 */
//@EnableEurekaClient
//@EnableApolloConfig
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CloudServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudServerApplication.class, args);
	}
}