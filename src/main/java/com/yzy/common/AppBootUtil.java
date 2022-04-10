package com.yzy.common;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class AppBootUtil {
    public static void run(Class<?> appClass, String[] args) {
        long start = System.nanoTime();
        SpringApplication application = new SpringApplication(appClass);
        application.addListeners(AppBootUtil::onApplicationEvent);
        ApplicationContext ctx = application.run(args);
        Environment env = ctx.getEnvironment();
        String[] activeProfiles = env.getActiveProfiles();
        String appName = env.getProperty("app.name");
        String appVer = env.getProperty("app.version");
        String runEnv = env.getProperty("app.env");
        String port = env.getProperty("server.port");
        String url = env.getProperty("spring.datasource.url");

        System.err.println("datasource-url         : " + url);
        System.err.println("application-name         : " + appName);
        System.err.println("application-version      : " + appVer);
        System.err.println("application-run-env      : " + runEnv);
        System.err.println("application-use-profile  : " + Arrays.toString(activeProfiles));
        System.err.println("application-http-port    : " + port);
        System.err.println("application-started-took : " + TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - start) + "(s).");
        System.err.println("application-started-at   : " + LocalDateTime.now());

        System.err.flush();
        System.err.println("================================================================================");
        System.err.println("=================================App Started Success============================");
        System.err.println("================================================================================");
        System.err.flush();
    }

    private static void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartingEvent) {
            System.err.println("=================App Starting=================");
        } else if (event instanceof ApplicationPreparedEvent) {
            System.err.println("=================App Prepared=================");
        } else if (event instanceof ApplicationEnvironmentPreparedEvent) {
            System.err.println("=================App Env Prepared=================");
        } else if (event instanceof ApplicationReadyEvent) {
            System.err.println("=================App Ready=================");
        } else if (event instanceof ApplicationFailedEvent) {
            System.err.println("=================App Started Failed=================");
        }
    }
}
