package com.gpholding.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/***
 * @author : 马晓光
 * @date   : 2019/7/25
 * @description : 优雅关闭当前应用
 **/
@Slf4j
public class CustomShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private volatile  Connector connector;

    private static final Integer TIMEOUT = 30;

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        ///暂时停止外部请求
        this.connector.pause();;
        //获取外部线程池
        Executor executor=this.connector.getProtocolHandler().getExecutor();
        if(executor instanceof ThreadPoolExecutor){
            try {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                    log.warn("Tomcat thread pool did not shut down gracefully within " + TIMEOUT + " seconds. Proceeding with forceful shutdown");
                }
            }catch (Exception e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
