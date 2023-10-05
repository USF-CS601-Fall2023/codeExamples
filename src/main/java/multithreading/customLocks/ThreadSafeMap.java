package multithreading.customLocks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeMap {
    private Map<String, String> englishToSpanishMap;
    private ReentrantReadWriteLock lock;

    public ThreadSafeMap() {
        englishToSpanishMap = new HashMap<>();
        lock = new ReentrantReadWriteLock();
    }

    public String getSpanishWord(String englishWord) {
        try {
            lock.readLock().lock();
            return englishToSpanishMap.get(englishWord);
        }
        finally {
            lock.readLock().unlock();

        }
    }

    public void addWord(String englishWord, String spanishWord) {
        try {
            lock.writeLock().lock();
            englishToSpanishMap.put(englishWord, spanishWord);
        }
        finally {
            lock.writeLock().unlock();
        }
    }
}
