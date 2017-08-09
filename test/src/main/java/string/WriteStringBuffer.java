package string;

/**
 * Created liqi on 2017/7/10.
 */
public class WriteStringBuffer implements Runnable {

    public String s = "0";

    public StringBuffer stringBuffer=null;

    public WriteStringBuffer(String s,StringBuffer stringBuffer){
        this.s = s;
        this.stringBuffer = stringBuffer;
    }

    public void write(){
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stringBuffer.append(s);
//        System.out.println("w:"+stringBuffer+" "+Thread.currentThread().getName());
    }

    public void run() {
        for (int i=0;i<10000;i++) {
            write();
        }
        System.out.println("buffer"+stringBuffer.length());
    }
}
