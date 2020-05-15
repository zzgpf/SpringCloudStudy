package gpf.eureka.consumer;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author ge.pengfei
 */

@RestController
public class TestController {

    private final EurekaClient eurekaClient;
    private final DiscoveryClient discoveryClient;
    private final LoadBalancerClient loadBalancerClient;
    private final RestTemplate lbRestTemplate;

    /**
     * 原本的 @Autowired 注入方式改为使用构造器注入
     */
    public TestController(EurekaClient eurekaClient, DiscoveryClient discoveryClient, LoadBalancerClient loadBalancerClient, RestTemplate lbRestTemplate) {
        this.eurekaClient = eurekaClient;
        this.discoveryClient = discoveryClient;
        this.loadBalancerClient = loadBalancerClient;
        this.lbRestTemplate = lbRestTemplate;
    }

    @GetMapping("/test1")
    public void test1() {
        List<InstanceInfo> instances = eurekaClient.getInstancesByVipAddress("eureka-provider-name", false);
        if (CollectionUtils.isEmpty(instances)) {
            return;
        }

        RestTemplate restTemplate = new RestTemplate();
        for (InstanceInfo instanceInfo : instances) {
            System.out.println(ToStringBuilder.reflectionToString(instanceInfo));

            String url = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort() + "/eureka-provider/";
            System.out.println("url:" + url);
            System.out.println(restTemplate.getForObject(url, String.class));
        }
    }

    @GetMapping("test2")
    public void test2() {
        List<String> services = discoveryClient.getServices();
        System.out.println(services.size());
        if (!CollectionUtils.isEmpty(services)) {
            for (String service : services) {
                System.out.println(service);
            }
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("eureka-provider-name");
        System.out.println(instances.size());
        if (!CollectionUtils.isEmpty(instances)) {
            for (ServiceInstance service : instances) {
                System.out.println(service.getInstanceId());
            }
        }
    }

    /**
     * 可以用来测试负载均衡算法
     */
    @GetMapping("/test3")
    public void test3() {
        // ribbon 完成客户端的负载均衡，过滤掉down了的节点，默认使用轮询算法
        ServiceInstance instance = loadBalancerClient.choose("eureka-provider-name");
        System.out.println(instance.getPort());
        // 也可以获取列表，自定义负载均衡算法
    }

    @GetMapping("/test4")
    public void test4() {
        // 自动处理 URL
        String url = "http://eureka-provider-name/eureka-provider/";

        String string = lbRestTemplate.getForObject(url, String.class);
        System.out.println(string);

        ResponseEntity<String> entity = lbRestTemplate.getForEntity(url, String.class);
        System.out.println(entity);

    }
}
