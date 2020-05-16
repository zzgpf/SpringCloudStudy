package gpf.eureka.consumer;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author ge.pengfei
 */
public class HystrixTest extends HystrixCommand<String> {

    protected HystrixTest(HystrixCommandGroupKey group) {
        super(group);
    }

    public static void main(String[] args) {
        main2();
    }

    /**
     * 同步阻塞方式执行 run()
     * 调用 execute() 之后，hystrix 先创建一个新线程运行 run()，
     */
    private static void main1() {
        HystrixTest hystrixTest = new HystrixTest(HystrixCommandGroupKey.Factory.asKey("ext"));
        System.out.println(hystrixTest.execute());
    }

    /**
     * 异步非阻塞方式执行 run()
     * 调用 queue() 立即返回一个 Future 对象，同时 hystrix 创建一个新线程运行 run()
     * Future.get() 拿到 run() 的返回结果，
     */
    private static void main2() {
        Future<String> futureResult = new HystrixTest(HystrixCommandGroupKey.Factory.asKey("ext")).queue();
        String result = "";
        try {
            result = futureResult.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("程序结果："+result);
    }

    @Override
    protected String run() {
        System.out.println("执行逻辑");
        throw new RuntimeException("抛出异常");
    }

    /**
     * 备用逻辑
     * @return
     */
    @Override
    protected String getFallback() {
        return "getFallback";
    }
}

