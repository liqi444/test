package string;

/**
 * Created liqi on 2017/7/10.
 */
public class TestString {
    public static void main(String[] args) {
        TestString test = new TestString();
//        test1();
//        test2();
//        test3();
        test.test4();
        test.test5();
    }

    static void test1() {
        char[] ca = {'a', 'b'};
        String ss = new String(ca, 0, 2);
        System.out.println(ss);

        int[] ia = {49, 50};
        ss = new String(ia, 0, 2);
        System.out.println(ss);
    }

    public StringBuffer stringBuffer = new StringBuffer();
    public StringBuilder stringBuilder = new StringBuilder();

    void test2() {

        long l = System.currentTimeMillis();
        for (int i = 1; i < 10000000; i++) {
            stringBuffer.append("1");
        }
        System.out.println(System.currentTimeMillis() - l);
        l = System.currentTimeMillis();
        for (int i = 1; i < 10000000; i++) {
            stringBuilder.append("2");
        }
        System.out.println(System.currentTimeMillis() - l);

    }


    void test4() {
        WriteStringBuilder test1 = new WriteStringBuilder("1",stringBuilder);
//        string.WriteStringBuilder test2 = new string.WriteStringBuilder("2",stringBuilder);
        for(int i = 0;i<10;i++)
        new Thread(test1).start();
//        new Thread(test2).start();
    }
    void test5() {
        WriteStringBuffer test1 = new WriteStringBuffer("1",stringBuffer);
//        string.WriteStringBuffer test2 = new string.WriteStringBuffer("2",stringBuffer);
        for(int i = 0;i<10;i++)
        new Thread(test1).start();
//        new Thread(test2).start();
    }

}
