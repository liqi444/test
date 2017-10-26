package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created liqi on 2017/9/13.
 */
public class TestCyclicBarrier {
    public static void main(String[] args){
        CyclicBarrier cb = new CyclicBarrier(3);
        Runner r1 = new Runner(cb,"A");
        Runner r2 = new Runner(cb,"B");
        Runner r3 = new Runner(cb,"C");
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        t1.start();
        t2.start();
        t3.start();

    }
}

class Runner implements Runnable{

    String name;

    CyclicBarrier cb ;

    Runner(CyclicBarrier cb,String name){
        this.cb = cb;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            int waiting = 0;
            if("A".equals(name)){
                waiting = 1000;
            }
            if("B".equals(name)){
                waiting = 5000;
            }
            if("C".equals(name)){
                waiting = 3000;
            }
            Thread.sleep(waiting);
            System.out.println(name+" is ready");
            cb.await();
            System.out.println(name+" is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
