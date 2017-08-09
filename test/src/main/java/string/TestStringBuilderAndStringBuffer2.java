package string;

/**
 * Created liqi on 2017/7/10.
 */
public class TestStringBuilderAndStringBuffer2 {
    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            new CustomThread(buffer, builder).start();
        }
    }
}

class CustomThread extends Thread {
    private StringBuffer buffer;
    private StringBuilder builder;

    public CustomThread(StringBuffer buffer, StringBuilder builder) {
        this.buffer = buffer;
        this.builder = builder;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            buffer.append("A");
            builder.append("Z");
//            System.out.println("StringBuffer " + buffer);
//            System.out.println("StringBuilder " + builder);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("StringBuffer Size:" + buffer.length()
                + " | "
                + "StringBuilder Size:" + builder.length());
    }
}