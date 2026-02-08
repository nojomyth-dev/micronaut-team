package de.riversroses.infra.db;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.ApplicationShutdownEvent;
import jakarta.inject.Singleton;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

@Singleton
public class H2ConsoleService implements ApplicationEventListener<StartupEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(H2ConsoleService.class);
    private Server webServer;

    @Override
    public void onApplicationEvent(StartupEvent event) {
        try {
            webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            LOG.info("H2 Console started: http://localhost:8082");
        } catch (SQLException e) {
            LOG.error("Failed to start H2 Console", e);
        }
    }

    public void onShutdown(ApplicationShutdownEvent event) {
        if (webServer != null) {
            webServer.stop();
        }
    }
}
