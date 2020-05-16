package gpf.eureka.consumer;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author ge.pengfei
 */

@Component
public class ProviderServiceFallbackFactory implements FallbackFactory<ProviderService> {

    @Override
    public ProviderService create(Throwable throwable) {
        return new ProviderService() {
            @Override
            public void test1() {

            }

            @Override
            public void testRibbonTimeOut() throws InterruptedException {

            }

            @Override
            public int port() {
                return Integer.MAX_VALUE;
            }
        };
    }
}
