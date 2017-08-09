package list;

import java.util.Vector;

/**
 * Created liqi on 2017/7/10.
 */
public class TestVectorSyn2 implements Runnable{

    Vector<Object> v = null;

    public TestVectorSyn2(Vector<Object> v){
        this.v = v;
    }

    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        v.remove(0);
    }
}
