package multithreading.poolOfThreads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class MultithreadedJsonCounter {
    private int count;
    private ExecutorService poolManager = Executors.newCachedThreadPool();
    private Logger logger = LogManager.getLogger();
    private Phaser phaser = new Phaser();

    public class JsonCounterWorker implements Runnable {
        private Path dir;
        private int localCount = 0;

        public JsonCounterWorker(Path dir) {
            this.dir = dir;
        }

        @Override
        public void run() {
            try (DirectoryStream<Path> filesAndFolders = Files.newDirectoryStream(dir)) {
                for (Path path: filesAndFolders) {
                    if (Files.isDirectory(path)) {
                        // want to create a new worker
                        JsonCounterWorker worker = new JsonCounterWorker(path);
                        poolManager.submit(worker);
                        phaser.register();
                        logger.debug("Created a worker for " + path);

                        // TODO
                    }
                    else {
                        if (path.toString().endsWith(".json")) {
                            //System.out.println(path);
                            localCount++;
                        }
                    }
                }

               updateCount(localCount);

            } catch (IOException e) {
                System.out.println(e);
            }
            finally {
                phaser.arriveAndDeregister();
                logger.debug("Worker working on " + dir + " finished work");
            }

        }
    }

    public synchronized int getCount() {
        return count;
    }

    public synchronized void updateCount(int localCount) {
        this.count += localCount;
        //logger.debug("Count is " + count);
    }



    public int traverseDirectoryAndCountFiles(String directory) {

        Path dir = Paths.get(directory);
        JsonCounterWorker firstWorker = new JsonCounterWorker(dir);
        poolManager.submit(firstWorker);
        phaser.register();
        logger.debug("Created a worker for " + dir);

        phaser.awaitAdvance(phaser.getPhase());
        int c = getCount();
        poolManager.shutdown();
        try {
            poolManager.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return c;
    }



    public static void main(String[] args) {
        MultithreadedJsonCounter multiThCounter = new MultithreadedJsonCounter();
        System.out.println(multiThCounter.traverseDirectoryAndCountFiles("input"));
    }


}
