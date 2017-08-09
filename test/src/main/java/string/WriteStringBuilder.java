package string;

/**
 * Created liqi on 2017/7/10.
 */
public class WriteStringBuilder implements Runnable {
    public String s = "0";
    public StringBuilder stringBuilder = null;

    public WriteStringBuilder(String s,StringBuilder stringBuilder){
        this.s = s;
        this.stringBuilder = stringBuilder;
    }
    public void write(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stringBuilder.append(s);
//        System.out.println("w:"+stringBuilder+" "+Thread.currentThread().getName());
    }

    public void run() {
        for (int i=0;i<10000;i++) {
            write();
        }
        System.out.println("builder"+stringBuilder.length());
    }
}
