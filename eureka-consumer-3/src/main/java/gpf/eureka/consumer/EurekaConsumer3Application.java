package gpf.eureka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ge.pengfei
 */

@SpringBootApplication
@EnableFeignClients
@RestController
@EnableCircuitBreaker
@EnableHystrixDashboard
public class EurekaConsumer3Application {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumer3Application.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/")
    public String home() {
        return "this is EurekaConsumer3Application";
    }
}