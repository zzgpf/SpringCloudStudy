package gpf.eureka.provider;

import gpf.base.api.ProviderApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ge.pengfei
 */

@RestController
public class ProviderController implements ProviderApi {

    @Value("${server.port}")
    private int port;

    @Override
    public void test1() {
        System.out.println("test1");
    }

    @Override
    public void testRibbonTimeOut() throws InterruptedException {
        System.out.println("进来了");
        // 睡
        Thread.sleep(100000);
    }

    @Override
    public int port() {
        return port;
    }
}
