package map;

import java.util.Hashtable;

/**
 * Created liqi on 2017/7/10.
 */
public class PutHashTable implements Runnable {

    public Hashtable<String,Integer> hashtable=null;

    public PutHashTable(Hashtable<String,Integer> hashtable){
        this.hashtable = hashtable;
    }


    public void run() {
        for (int i=0;i<10000;i++) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hashtable.put(i+Thread.currentThread().getName(),i);
        }
        System.out.println("hashtable"+hashtable.size());
    }
}
