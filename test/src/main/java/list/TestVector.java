package list;

import java.util.Vector;

/**
 * Created liqi on 2017/7/10.
 */
public class TestVector {

    public static void main(String[] args){
        TestVector test = new TestVector();
        test.testSyn();
    }

    void testSyn(){
        Vector<Object> vector = new Vector<>();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("4");

        TestVectorSyn syn1 = new TestVectorSyn(vector);
        TestVectorSyn2 syn2 = new TestVectorSyn2(vector);

        new Thread(syn1).start();
        new Thread(syn2).start();

    }

    void test1(){
        Vector<Object> vector = new Vector<>();
        Integer i = new Integer(1);
        vector.add(i);
        vector.addElement(2);
        vector.add("3");
        vector.setElementAt("3",1);
        vector.insertElementAt("4",0);
        vector.remove(i);
        vector.remove(0);


        for (Object o :
                vector) {
            System.out.println(o);
        }
    }
}
