package map;

import java.util.HashMap;

/**
 * Created liqi on 2017/7/10.
 */
public class PutHashMap implements Runnable {

    public HashMap<String,Integer> hashMap=null;

    public PutHashMap(HashMap<String,Integer> hashMap){
        this.hashMap = hashMap;
    }


    public void run() {
        for (int i=0;i<10000;i++) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hashMap.put(i+Thread.currentThread().getName(),i);
        }
        System.out.println("hashMap"+hashMap.size());
    }
}
