
/**
 * Created liqi on 2017/7/31.
 */
public class TestThread {
    public static void main(String[] args) throws Exception {
        TestThread2 testThread2 = new TestThread2();
        new Thread(testThread2).start();
        System.out.println(Thread.holdsLock(testThread2.ss)+testThread2.ss);
        Thread.sleep(10000);


//        new TestThread().test1();2


    }


    Object o = new Object();
    public void test1() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(o) {
                    System.out.println("child thread: holdLock: " +
                            Thread.holdsLock(o));
                }
            }
        }).start();
        System.out.println("main thread: holdLock: " + Thread.holdsLock(o));
        Thread.sleep(2000);
    }
}

class TestThread2 implements Runnable {

    String ss="1";

    @Override
    public void run() {
        synchronized(ss) {
            ss = "2";
            System.out.println(Thread.holdsLock(ss)+ss);
        }

    }
}

