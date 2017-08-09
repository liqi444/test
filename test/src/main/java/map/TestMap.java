package map;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created liqi on 2017/7/10.
 */
public class TestMap {
    public static void main(String[] args) {
        TestMap test = new TestMap();
//        test.test4();
        test.test5();
    }


    public HashMap<String,Integer> hashMap = new HashMap<>();
    public Hashtable<String,Integer> hashtable = new Hashtable<>();



    void test4() {
        PutHashMap test1 = new PutHashMap(hashMap);
        for(int i = 0;i<10;i++)
        new Thread(test1).start();
    }
    void test5() {
        PutHashTable test1 = new PutHashTable(hashtable);
        for(int i = 0;i<10;i++)
        new Thread(test1).start();
    }

}
