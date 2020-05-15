package gpf.eureka.consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ge.pengfei
 */

@RestController
public class TestController {

    private final ProviderService providerService;

    public TestController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/test1")
    public void test1(){
        providerService.test1();
    }


    /**
     * Feign 默认支持 Ribbon
     * Ribbon 和 Feign 的重试机制有冲突，默认关闭 Feign 的重试机制，使用 Ribbon 的重试机制
     */
    @GetMapping("/testRibbonTimeOut")
    public void testRibbonTimeOut() throws InterruptedException {
        providerService.testRibbonTimeOut();
    }
}
