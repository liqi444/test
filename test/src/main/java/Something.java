import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Created liqi on 2017/9/12.
 */
public class Something {
    public static void main(String [] args){
//        boolean b = false;
////        boolean bb = true;
//        assert b;
//        System.out.println("xx");
//
//        Random r = new Random();
//        System.out.println(r.nextInt(100));
//
//        double   f   =   111;
//        BigDecimal bb   =   new   BigDecimal(f).divide(new BigDecimal(3));
////保留2位小数
//        double   f1   =   bb.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println(f1);


        BigDecimal a;
        BigDecimal b;
        a = new BigDecimal(3);
        b = new BigDecimal(81);
        System.out.print(a.divide(b, 2, RoundingMode.HALF_UP));


        String s1 = "abc"+"d";
        String s2 = "ab"+"cd";

        new MyThread("A").start();
        new MyThread("B").start();
    }
}

class MyThread extends Thread{
    String name;
    MyThread(String name){
        this.name = name;
    }
    @Override
    public void run() {
        if("A".equals(name)){
            test1(name);
        }
        if("B".equals(name)){
            test2(name);
        }
    }
    synchronized void test1(String name2){
        if(!name.equals(name2)){
            return;
        }
        for (int i = 0; i <50 ; i++) {
            System.out.println(name+i);
        }
        if("A".equals(name2)){
            test2(name2);
        }
    }
    synchronized void test2(String name2){

        for (int i = 100; i <200 ; i++) {
            System.out.println(name+i);
        }
        if("B".equals(name2)){
            test1(name2);
        }
    }
}
