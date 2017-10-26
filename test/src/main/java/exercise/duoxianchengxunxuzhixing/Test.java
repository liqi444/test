package exercise.duoxianchengxunxuzhixing;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.demo2();
    }

    private byte[] lock = new byte[0];  //自定义锁对象，这样代价最小，也可已使用当前对象this
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
}