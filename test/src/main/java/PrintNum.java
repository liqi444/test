import lock.Mutex;
import lock.TestFairLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PrintNum {

    public static void main(String[] args) {
        PrintNum printNum = new PrintNum();
//        printNum.demo();
//        printNum.demo2();
//        printNum.demo3();
//        printNum.demo4();
//        printNum.demo5();
//        printNum.demo6();
        printNum.demo7();
    }

    private byte[] lock = new byte[0];  //自定义锁对象，这样代价最小，也可已使用当前对象this

    private Mutex loac = new Mutex();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    //test代码3

    public void demo() {
        PrintThread a = new PrintThread("a");
        PrintThread b = new PrintThread("b");
        PrintThread c = new PrintThread("c");
        a.start();
        b.start();
//        c.start();
    }


    class PrintThread extends Thread {
        public PrintThread(String name) {
            this.setName(name);
        }

        public void run() {
//            synchronized (lock) {
            loac.lock();
            for (int i = 0; i < 100; i++) {
                System.out.println(this.getName() + ": " + i);
                if (i % 10 == 0 && 0 != i) {
//                        try {
//                            lock.notifyAll();       //唤醒其他进程
//                            lock.wait();   //暂时释放资源
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                }
            }
            loac.unlock();
//            }
        }
    }


    public void demo2() {
        AddThread add = new AddThread("add", 1, 11, Thread.MAX_PRIORITY);
        SubThread sub = new SubThread("sub", 10, 0, Thread.MAX_PRIORITY);
        add.start();
        sub.start();
    }


    class AddThread extends Thread {
        int i;
        int max;

        public AddThread(String name, int num, int max, int priority) {
            this.setName(name);
            this.setPriority(priority);
            this.i = num;
            this.max = max;
        }

        public void run() {
            synchronized (lock) {
                if (max < i)
                    throw new IllegalArgumentException("max mast bigger than i");
                while (i < max) {
                    System.out.println(this.getName() + ": " + i);
                    i++;
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    class SubThread extends Thread {
        int i;
        int min;

        public SubThread(String name, int num, int min, int priority) {
            this.setName(name);
            this.setPriority(priority);
            this.i = num;
            this.min = min;
        }

        public void run() {
            synchronized (lock) {
                if (min > i)
                    throw new IllegalArgumentException("max mast smaller than i");
                while (i > min) {
                    System.out.println(this.getName() + ": " + i);
                    i--;
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    AddThread2 add2 = new AddThread2("add", 1, 11, Thread.MAX_PRIORITY);
    SubThread2 sub2 = new SubThread2("sub", 10, 0, Thread.MAX_PRIORITY);

    public void demo3() {

        add2.start();
        sub2.start();
    }


    class AddThread2 extends Thread {
        int i;
        int max;

        public AddThread2(String name, int num, int max, int priority) {
            this.setName(name);
            this.setPriority(priority);
            this.i = num;
            this.max = max;
        }

        public void run() {
//            synchronized (currentThread()) {
            if (max < i)
                throw new IllegalArgumentException("max mast bigger than i");
            while (i < max) {
                System.out.println(this.getName() + ": " + i);
                i++;

                try {
                    this.interrupt();
//                        sub2.join();
                } catch (Exception e) {
//                        e.printStackTrace();
                }
            }
//            }
        }
    }

    class SubThread2 extends Thread {
        int i;
        int min;

        public SubThread2(String name, int num, int min, int priority) {
            this.setName(name);
            this.setPriority(priority);
            this.i = num;
            this.min = min;
        }

        public void run() {
//            synchronized (currentThread()) {
            if (min > i)
                throw new IllegalArgumentException("max mast smaller than i");
            while (i > min) {
                System.out.println(this.getName() + ": " + i);
                i--;

                try {
                    this.interrupt();
//                        add2.join();
                } catch (Exception e) {
//                        e.printStackTrace();
                }
            }
//            }
        }
    }


    private int num = 0;
    private AtomicInteger number = new AtomicInteger(0);

    public void demo4() {
        PrintThread4 a = new PrintThread4("a");
        PrintThread4 b = new PrintThread4("b");
        a.start();
        b.start();
//        PrintThread5 a = new PrintThread5("a");
//        PrintThread5 b = new PrintThread5("b");
//        a.start();
//        b.start();
    }


    class PrintThread4 extends Thread {
        public PrintThread4(String name) {
            this.setName(name);
        }

        public void run() {
            if ("a".equals(this.getName())) {
                readWriteLock.writeLock().lock();
                for (; num < 100; ) {
                    System.out.println(this.getName() + ": " + num++);
                }
                readWriteLock.writeLock().unlock();
            }
            if ("b".equals(this.getName())) {
                readWriteLock.readLock().lock();
                for (int i = 0; i < 100; i++) {
                    System.out.println(this.getName() + ": " + num);
                }
                readWriteLock.readLock().unlock();
            }
        }
    }

    class PrintThread5 extends Thread {
        public PrintThread5(String name) {
            this.setName(name);
        }

        public void run() {
            if ("a".equals(this.getName())) {
                readWriteLock.readLock().lock();
                for (; num < 100; ) {
                    System.out.println(this.getName() + ": " + num++);
                }
                readWriteLock.readLock().unlock();
            }
            if ("b".equals(this.getName())) {
                readWriteLock.readLock().lock();
                for (int i = 0; i < 100; i++) {
                    System.out.println(this.getName() + ": " + num++);
                }
                readWriteLock.readLock().unlock();
            }
        }
    }


    public void demo5() {
        AddThread5 add = new AddThread5("add", 1, 11, Thread.MAX_PRIORITY);
        SubThread5 sub = new SubThread5("sub", 10, 0, Thread.MAX_PRIORITY);
        add.start();
        sub.start();
    }


    class AddThread5 extends Thread {
        int i;
        int max;

        public AddThread5(String name, int num, int max, int priority) {
            this.setName(name);
            this.setPriority(priority);
            this.i = num;
            this.max = max;
        }

        public void run() {
            if (max < i)
                throw new IllegalArgumentException("max mast bigger than i");
            while (i < max) {
                System.out.println(this.getName() + ": " + i);
                i++;
                Thread.yield();
            }
        }
    }

    class SubThread5 extends Thread {
        int i;
        int min;

        public SubThread5(String name, int num, int min, int priority) {
            this.setName(name);
            this.setPriority(priority);
            this.i = num;
            this.min = min;
        }

        public void run() {
            if (min > i)
                throw new IllegalArgumentException("max mast smaller than i");
            while (i > min) {
                System.out.println(this.getName() + ": " + i);
                i--;
                Thread.yield();
            }
        }
    }

    ReentrantReadWriteLock.WriteLock writeLock = new ReentrantReadWriteLock().writeLock();
    FairLock fairLock = new FairLock();

    public void demo6() {
        AddThread6 add = new AddThread6("add", 1, 11, Thread.MAX_PRIORITY);
        SubThread6 sub = new SubThread6("sub", 10, 0, Thread.MAX_PRIORITY);
        add.start();
        sub.start();
    }


    class AddThread6 extends Thread {
        int i;
        int max;

        public AddThread6(String name, int num, int max, int priority) {
            this.setName(name);
            this.setPriority(priority);
            this.i = num;
            this.max = max;
        }

        public void run() {
            if (max < i)
                throw new IllegalArgumentException("max mast bigger than i");
            while (i < max) {
                try {
                    fairLock.lock();
                    System.out.println(this.getName() + ": " + i);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    fairLock.unlock();
                }

            }
        }
    }

    class SubThread6 extends Thread {
        int i;
        int min;

        public SubThread6(String name, int num, int min, int priority) {
            this.setName(name);
            this.setPriority(priority);
            this.i = num;
            this.min = min;
        }

        public void run() {
            if (min > i)
                throw new IllegalArgumentException("max mast smaller than i");
            while (i > min) {
                try {
                    fairLock.lock();
                    System.out.println(this.getName() + ": " + i);
                    i--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    fairLock.unlock();
                }
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

    void demo7() {
        JoinThread j1 = new JoinThread("a");
        j1.setPriority(Thread.MAX_PRIORITY);
        JoinThread j2 = new JoinThread("b");
        JoinThread j3 = new JoinThread("c");

        j1.start();
        j2.start();
        j3.start();
    }

    List<Thread> list = new ArrayList<>();

    class JoinThread extends Thread {
        String name;

        JoinThread(String name) {
            this.name = name;
        }

        int i = 0;

        @Override
        public void run() {
            Thread t = Thread.currentThread();
            synchronized (list) {
                list.add(t);
            }
            while (true) {
                if (list.get(0) == t) {
                    while (i < 100) {
                        System.out.println(name + i++);
                    }
                    synchronized (list) {
                        list.remove(t);
                        if (list.size() > 0) {
                            System.out.println(list.size());
                            try {
                                list.get(0).join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                list.remove(0);
                            }
                            return;
                        }
                    }
//                } else {
//                    try {
//                        t.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                        list.remove(t);
//                    }
                }
            }
        }
    }
}

