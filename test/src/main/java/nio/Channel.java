package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created liqi on 2017/9/16.
 */
public class Channel {
    public static void main(String[] args) throws Exception {
        new Channel().test2();
    }

    public void test1() throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("C:/Users/L/Desktop/test.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1)

        {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    public void test2() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("C:/Users/L/Desktop/test.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("C:/Users/L/Desktop/totest.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

//        toChannel.transferFrom(fromChannel,position, count );

        fromChannel.transferTo(position,count,toChannel);
    }
}
