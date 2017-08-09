package set;

import java.util.HashSet;
import java.util.Set;

/**
 * Created liqi on 2017/7/18.
 */
public class TestHashSet {
    public static void main(String[] args){
        TestHashSet test = new TestHashSet();
        test.testProblem();
//        test.testProblem2();
    }

    void testProblem(){
        StringBuffer stringBuffer = new StringBuffer("ss");
        Set<StringBuffer> set = new HashSet<StringBuffer>();
        set.add(stringBuffer);
        StringBuffer stringBuffer2 = new StringBuffer("ssc");
        set.add(stringBuffer2);

        for (StringBuffer o :
                set) {
            System.out.println("before:"+o);
        }

        for (StringBuffer o :
             set) {
            if(o.toString().equals("ss")){
                o.append("c");
                System.out.println("in if");
            }
        }
        String testEquals = "";
        for (StringBuffer o :
                set) {
            System.out.println("after:"+o+" "+o.hashCode());

            System.out.println(o.toString()+" "+testEquals.toString());
            System.out.println(o.toString().equals(testEquals));
            testEquals = o.toString();

        }
    }
    void testProblem2(){
        String stringBuffer = "ss";
        Set<String> set = new HashSet<String>();
        set.add(stringBuffer);
        String stringBuffer2 = "ssc";
        set.add(stringBuffer2);

        for (String o :
                set) {
            System.out.println("before:"+o);
        }

        for (String o :
             set) {
            if(o.equals("ss")){
                o+="c";
                System.out.println("in if");
            }
        }
        for (String o :
                set) {
            System.out.println("after:"+o+" "+o.hashCode());
        }
    }
}
