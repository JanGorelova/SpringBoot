package epam.spring.core;

import epam.spring.core.events.Event;
import epam.spring.core.events.EventType;
import epam.spring.core.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class App {
    private Client client;
    private EventLogger logger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger logger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.logger = logger;
        this.loggers = loggers;
    }

    public App(Client client) {
        this.client = client;
    }

    private void logEvent(EventType type, String message, Event event) {
        EventLogger eventLogger = loggers.get(type);
        event.setMessage(message);
        Optional<EventLogger> optional = Optional.of(eventLogger);
        optional.orElse(logger);
        optional.get().logEvent(event);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        Event event = (Event) ctx.getBean("event");

        app.logEvent(EventType.INFO,"oh my god!",event);
        app.logEvent(EventType.ERROR,"this is error!",event);

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        context.close();

    }
}
