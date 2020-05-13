package gpf.springcloudstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ge.pengfei
 */
@SpringBootApplication
@RestController
public class SpringCloudStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStudyApplication.class, args);
    }

    /**
     * http://localhost:8080/hello
     *
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
