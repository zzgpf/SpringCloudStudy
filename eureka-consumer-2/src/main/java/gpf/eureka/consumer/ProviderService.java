package gpf.eureka.consumer;

import gpf.base.api.ProviderApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author ge.pengfei
 */

@FeignClient(name = "eureka-provider-name/eureka-provider")
public interface ProviderService extends ProviderApi {

}
