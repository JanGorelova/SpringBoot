package epam.spring.core.loggers;

import epam.spring.core.events.Event;

import java.util.List;

public class CashFileEventLogger extends FileEventLogger {
    private int cacheSize;
    List<Event> cache;

    public CashFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCasche();
            cache.clear();
        }
    }

    private void writeEventsFromCasche() {
        for (Event e: cache) {
            super.logEvent(e);
        }
    }

    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCasche();
        }
    }
}
