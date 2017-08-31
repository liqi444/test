public class PrintNum {

    public static void main(String[] args) {
        PrintNum printNum = new PrintNum();
//        printNum.demo();
//        printNum.demo2();
        printNum.demo3();
    }

    private byte[] lock = new byte[0];  //自定义锁对象，这样代价最小，也可已使用当前对象this

    //test代码3

    public void demo() {
        PrintThread a = new PrintThread("a");
        PrintThread b = new PrintThread("b");
        PrintThread c = new PrintThread("c");
        a.start();
        b.start();
        c.start();
    }


    class PrintThread extends Thread {
        public PrintThread(String name) {
            this.setName(name);
        }

        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println(this.getName() + ": " + i);
                    if (i % 10 == 0 && 0 != i) {
                        try {
                            lock.notifyAll();       //唤醒其他进程
                            lock.wait();   //暂时释放资源
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
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
}