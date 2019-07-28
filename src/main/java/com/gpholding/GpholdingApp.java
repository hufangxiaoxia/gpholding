package com.gpholding;

import com.gpholding.service.impl.CustomShutdown;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@Slf4j
@MapperScan("com.gpholding.dao")
@SpringBootApplication
public class GpholdingApp {

    public static void main(String[] args) {
        SpringApplication.run(GpholdingApp.class, args);
    }
    @Bean
    public CustomShutdown gracefulShutdown() {
        log.info(" gracefulShutdown ");
        return new CustomShutdown();
    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(final CustomShutdown customShutdown) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(customShutdown);
        log.info(" webServerFactory ");
        return factory;
    }
}
