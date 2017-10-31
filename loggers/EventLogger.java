package epam.spring.core.loggers;

import epam.spring.core.events.Event;

public interface EventLogger {
    void logEvent(Event event);
}
