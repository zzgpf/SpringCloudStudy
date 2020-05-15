package gpf.base.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ge.pengfei
 */

@RequestMapping("/provider")
public interface ProviderApi {
    /**
     * test1
     */
    @GetMapping("/test1")
    void test1();

    /**
     * 测试 ribbon 超时重试机制
     *
     * @throws InterruptedException
     */
    @GetMapping("/testRibbonTimeOut")
    void testRibbonTimeOut() throws InterruptedException;
}
