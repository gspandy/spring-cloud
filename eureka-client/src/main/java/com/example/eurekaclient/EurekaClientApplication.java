package com.example.eurekaclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@SpringBootApplication

@EnableHystrix
@EnableHystrixDashboard
public class EurekaClientApplication {
	private static final Logger LOG = Logger.getLogger(EurekaClientApplication.class.getName());
	public static void main(String[] args) {

		SpringApplication.run(EurekaClientApplication.class, args);
	}

	@Value("${server.port}")
	String port;

	/*设置断路点*/
	@RequestMapping("/hi")
	@HystrixCommand(fallbackMethod = "hiError")
	public String home(@RequestParam String name) {
		LOG.log(Level.INFO, "调用service-ribbon应用的miya服务 ");
		return restTemplate.getForObject("http://service-ribbon/miya?name="+name, String.class);

	}

	public String hiError(String name) {
		return "hi,"+name+",sorry,error!";
	}
	@RequestMapping("/info1")
	public String info(){
		LOG.log(Level.INFO, "执行service-hi/info ");

		return "i'm service-hi";

	}
	@Autowired

	private RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Bean
	public AlwaysSampler defaultSampler(){
		return new AlwaysSampler();
	}

}
