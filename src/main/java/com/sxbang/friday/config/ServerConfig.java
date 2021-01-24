package com.sxbang.friday.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author ：laijinrong
 * @date ：Created in 2020/5/22 23:02
 * @modified By：
 */
@Component
@Slf4j
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {
    private int serverPort;

    public int getPort() {
        return this.serverPort;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
        log.info("Get WebServer port {}", serverPort);
    }
}
