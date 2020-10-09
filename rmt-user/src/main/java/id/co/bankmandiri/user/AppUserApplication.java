package id.co.bankmandiri.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import feign.Logger;
import id.co.bankmandiri.user.client.exception.FeignErrorDecoder;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@EnableCircuitBreaker Hytrix
public class AppUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppUserApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public Logger.Level feignLoggerLevel()
	{
		return Logger.Level.FULL;
	}
	
	@Bean
	public FeignErrorDecoder feignErrorDecoder() {
		return new FeignErrorDecoder();
	}
}
