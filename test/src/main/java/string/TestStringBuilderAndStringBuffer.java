package string;

/**
 * Created liqi on 2017/7/10.
 */
public class TestStringBuilderAndStringBuffer {
    public static void main(String[] args) {
        /*
         * 声明个字符串s，用下划线和井号是因为两个比较好区分。 分别实例化sf和sd两个对象
         */
        String s = "####____";
        StringBuffer sf = new StringBuffer(s);
        StringBuilder sd = new StringBuilder(s);
        /*
         * 对sf和sd各自实例化两个反转他们的类
         */
        ThreadReverseString sfr1 = new ThreadReverseString(sf);
        ThreadReverseString sfr2 = new ThreadReverseString(sf);
        ThreadReverseString sdr1 = new ThreadReverseString(sd);
        ThreadReverseString sdr2 = new ThreadReverseString(sd);
        /*
         * 启动这四个线程，此时sf和sd各自有两个线程在对他们进行字符串反转操作
         */
        new Thread(sfr1).start();
        new Thread(sfr2).start();
        new Thread(sdr1).start();
        new Thread(sdr2).start();
    }
}

class ThreadReverseString implements Runnable {
    /*
     * 这个类用来完成字符串的反转工作，使用了Runnable接口来实现多线程 times是用来表示循环多少次的
     * 因为懒的再写一个变量所以用了一个Object类型的s，后面再转化
     */
    public Object s = null;
    int times = 200;

    /*
     * 两个构造方法把s传进来
     */
    public ThreadReverseString(StringBuffer s) {
        this.s = s;
    }

    public ThreadReverseString(StringBuilder s) {
        this.s = s;
    }

    /*
     * 复写run方法实现多线程 在我的电脑上大概循环200次可以看到效果了，也许换了电脑就得多试几次或者增加循环次数了
     */
    public void run() {
        for (int i = 0; i <= times; i++) {
            /*
             * 似乎sleep一小段效果会好一些 奇奇怪怪的输出格式只是为了便于比较
             */
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (s instanceof StringBuffer) {
                ((StringBuffer) s).reverse();
                System.out.println("BUFFER->" + s);
            } else if (s instanceof StringBuilder) {
                ((StringBuilder) s).reverse();
                System.out.println("        " + s + "<-builder");
            }
        }
    }
}
