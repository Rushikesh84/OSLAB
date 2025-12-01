import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Strings {
    private static class SharedResource {
        private String data = "";
        private final ReadWriteLock lock = new ReentrantReadWriteLock();

        public void write(String value) {
            lock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + " is writing: " + value);
                data = value;
                Thread.sleep(100); // Simulate write operation
                System.out.println(Thread.currentThread().getName() + " finished writing");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.writeLock().unlock();
            }
        }

        public void read() {
            lock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + " is reading: " + data);
                Thread.sleep(100); // Simulate read operation
                System.out.println(Thread.currentThread().getName() + " finished reading");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.readLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        // Create reader threads
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    resource.read();
                }
            }, "Reader-" + i).start();
        }

        // Create writer threads
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 2; j++) {
                    resource.write("Data-" + System.currentTimeMillis());
                }
            }, "Writer-" + i).start();
        }
    }
}