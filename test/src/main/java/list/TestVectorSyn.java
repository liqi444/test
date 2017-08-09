package list;

import java.util.Vector;

/**
 * Created liqi on 2017/7/10.
 */
public class TestVectorSyn implements Runnable{

    Vector<Object> v = null;

    public TestVectorSyn(Vector<Object> v){
        this.v = v;
    }

    public void run() {
        for (Object o :
             v) {
            System.out.println(o);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
