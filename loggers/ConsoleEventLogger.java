package epam.spring.core.loggers;

import epam.spring.core.events.Event;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
