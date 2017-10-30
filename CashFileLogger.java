package epam.spring.core;

import java.util.List;

public class CashFileLogger extends FileEventLogger {
    private int cacheSize;
    List<Event> cache;

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
