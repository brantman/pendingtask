package com.leading.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 异步调用FeignClient工具类
 * <p>
 * 只是开启线程去异步执行,去除线程池的逻辑
 *
 * @author huangxiaohui
 */
@Component
@Slf4j
public class LeadingFeignRunnable {

    @Resource(name = "simpleAsyncTaskExecutor")
    private SimpleAsyncTaskExecutor simpleAsyncTaskExecutor;


    public void run(LeadingFeignCallback leadingFeignCallback) {
        log.debug("创建异步任务 ");
        simpleAsyncTaskExecutor.execute(new LeadingWrappedRunnable(leadingFeignCallback));
    }

    // 创建线程
    @Bean
    public SimpleAsyncTaskExecutor simpleAsyncTaskExecutor() {
        simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
        return simpleAsyncTaskExecutor;
    }

}
