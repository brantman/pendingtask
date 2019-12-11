package com.leading.thread;

/**
 * 领先未来异步包装类
 *
 * @author huangxiaohui
 * @see LeadingFeignRunnable
 */
public class LeadingWrappedRunnable implements Runnable {

    private final LeadingFeignCallback leadingFeignCallback;

    public LeadingWrappedRunnable(LeadingFeignCallback leadingFeignCallback) {
        this.leadingFeignCallback = leadingFeignCallback;
    }

    @Override
    public void run() {
        leadingFeignCallback.call();
    }
}
