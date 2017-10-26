package thread;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created liqi on 2017/9/13.
 */
public class TestCountdownLatch {
    static List<Integer> scorelist = new ArrayList<>();

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(3);
        Random r = new Random();
        int score1 = 0, score2 = 0, score3 = 0;
        new Thread(new Jumper(cdl, "A's first jump", scorelist, r)).start();
        new Thread(new Jumper(cdl, "A's second jump", scorelist, r)).start();
        new Thread(new Jumper(cdl, "A's third jump", scorelist, r)).start();
        new Thread(new CountScore(cdl, "A", scorelist)).start();
    }


}

class Jumper implements Runnable {

    CountDownLatch cdl;
    String name;
    List<Integer> scorelist;
    Random r;


    public Jumper(CountDownLatch cdl, String name, List<Integer> scorelist, Random r) {
        this.cdl = cdl;
        this.name = name;
        this.scorelist = scorelist;
        this.r = r;
    }

    @Override
    public synchronized void run() {
        int waitm = 0;
        if ("A's first jump".equals(this.name)) {
            waitm = 0000;
        }
        if ("A's second jump".equals(this.name)) {
            waitm = 2000;
        }
        if ("A's third jump".equals(this.name)) {
            waitm = 4000;
        }
        try {
            Thread.sleep(waitm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int score = r.nextInt(100);
        System.out.println(name + " get score " + score);
        scorelist.add(score);
        System.out.println(scorelist.size());
        cdl.countDown();
    }
}

class CountScore implements Runnable {
    String name;
    CountDownLatch cdl;
    List<Integer> scorelist;

    CountScore(CountDownLatch cdl, String name, List<Integer> scorelist) {
        this.cdl = cdl;
        this.name = name;
        this.scorelist = scorelist;
    }

    @Override
    public void run() {
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BigDecimal bd = new BigDecimal(0);
        for (Integer getScore :
                scorelist) {
            bd = bd.add(new BigDecimal(getScore));
        }
        bd = bd.divide(BigDecimal.valueOf(scorelist.size()), 2, RoundingMode.HALF_UP);
        System.out.println(name + " get score " + bd);
    }
}