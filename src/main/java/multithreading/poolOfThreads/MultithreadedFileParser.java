package multithreading.poolOfThreads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class MultithreadedFileParser {
    private int count; // number of lines in .java files that contain a given keyword
    private ExecutorService poolManager = Executors.newCachedThreadPool();
    private Phaser phaser = new Phaser();

    public class FileWorker implements Runnable {
        private Path file;
        private String keyword;
        private int localCount = 0; // number of lines in this file that contain the given keyword

        public FileWorker(Path file, String keyword) {
            this.file = file;
            this.keyword = keyword;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new FileReader(file.toString()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains(keyword)) { // doing something very basic here - count this line if it contains the keyword
                        System.out.println(file);
                        localCount++;
                    }
                }
            } catch (IOException e) {
                System.out.println("Cannot open" + file);
            }
            finally {
                updateCount(localCount);
                phaser.arriveAndDeregister();
            }

        }
    }

    public synchronized int getCount() {
        return count;
    }

    public synchronized void updateCount(int localCount) {
        this.count += localCount;
    }



    public void traverseDirectory(Path directory, String keyword) {
        // Note: in this example, the code below will be executed by the main thread
        try (DirectoryStream<Path> filesAndFolders = Files.newDirectoryStream(directory)) {
            for (Path path: filesAndFolders) {
                if (Files.isDirectory(path)) {
                   traverseDirectory(path, keyword);
                }
                else if (Files.isRegularFile(path) && path.toString().endsWith(".java")) {
                        FileWorker worker = new FileWorker(path, keyword);
                        poolManager.submit(worker);
                        phaser.register();
                }
            }
        } catch (IOException e) {
            System.out.println("Problem traversing a directory " + directory.toString());
        }
    }

    public int computeOccurrences(String dir, String keyword) {
        traverseDirectory(Paths.get(dir), keyword);
        phaser.awaitAdvance(0);
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
        MultithreadedFileParser parser = new MultithreadedFileParser();
        System.out.println(parser.computeOccurrences( "src/main/java/dataStructures", "ArrayList"));
    }

}
