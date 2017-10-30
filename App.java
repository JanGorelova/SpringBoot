package epam.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger logger;

    public App(Client client, EventLogger logger) {
        this.client = client;
        this.logger = logger;
    }

    public App(Client client) {
        this.client = client;
    }

    private void logEvent(Event event) {
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        ApplicationContext ctx =  new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        Event event = (Event) ctx.getBean("event ");
        app.logEvent(event);
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        context.close();
    }
}
