package gpf.eureka.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ge.pengfei
 *
 * bug：单独使用 RestController("/consumer3") 无效
 * 需要使用 RequestMapping
 */

@RestController
@RequestMapping("/consumer3")
public class TestController {

    private final RestTemplate template;

    private final ProviderService providerService;

    public TestController(RestTemplate template, ProviderService providerService) {
        this.template = template;
        this.providerService = providerService;
    }

    /**
     * 这里怎么测试，将一个服务断开，就会走到 back 方法中了
     */
    @GetMapping("/test1")
    @HystrixCommand(defaultFallback = "back")
    public int test1() {
        String url ="http://eureka-provider-name/eureka-provider/provider/port";
        return template.getForObject(url, int.class);
    }

    @GetMapping("/test2")
    public int test2() {
        return providerService.port();
    }

    public int back() {
        System.out.println("处理处理处理");
        return 0;
    }
}
