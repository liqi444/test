package lock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created liqi on 2017/9/13.
 */
public class TestFairLock {
    public static void main(String [] args){
        FairLock lock = new FairLock();
        int i = 0;
        new Thread(new TestRun(lock,i,"a")).start();
        new Thread(new TestRun(lock,i,"b")).start();
    }

}

class TestRun implements Runnable {
    FairLock lock;
    int i;
    String name;

    TestRun(FairLock lock, int i, String name) {
        this.lock = lock;
        this.i = i;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            while (true) {
                System.out.println(name + ":" + i++);
                if (i > 100) {
                    return;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}


class FairLock {
    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();

    public void lock() throws InterruptedException {
        QueueObject queueObject = new QueueObject();
        boolean isLockedForThisThread = true;
        synchronized (this) {
            waitingThreads.add(queueObject);
        }
        while (isLockedForThisThread) {
            synchronized (this) {
                isLockedForThisThread = isLocked || waitingThreads.get(0) != queueObject;
                if (!isLockedForThisThread) {
                    isLocked = true;
                    waitingThreads.remove(queueObject);
                    lockingThread = Thread.currentThread();
                    return;
                }
            }
            try {
                queueObject.doWait();
            } catch (InterruptedException e) {
                synchronized (this) {
                    waitingThreads.remove(queueObject);
                }
                throw e;
            }
        }
    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        isLocked = false;
        lockingThread = null;
        if (waitingThreads.size() > 0) {
            waitingThreads.get(0).doNotify();
        }
    }
}

class QueueObject {
    private boolean isNotified = false;

    public synchronized void doWait() throws InterruptedException {
        while (!isNotified) {
            this.wait();
        }
        this.isNotified = false;
    }

    public synchronized void doNotify() {
        this.isNotified = true;
        this.notify();
    }

    public boolean equals(Object o) {
        return this == o;
    }
}