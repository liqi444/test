package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created liqi on 2017/9/16.
 */
public class Buffer {
    public static void main(String[] args) throws IOException {
        new Buffer().test1();
    }

    public void test1() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("C:/Users/L/Desktop/test.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

    //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(24);

        int bytesRead = inChannel.read(buf); //read into buffer.
        int i=0;
        while (bytesRead != -1) {

            buf.flip();  //make buffer ready for read

            while (buf.hasRemaining()) {
                System.out.print((char) buf.get()); // read 1 byte at a time
            }

            buf.clear(); //make buffer ready for writing
            bytesRead = inChannel.read(buf);
            System.out.println("==="+i);
            i++;
        }
        aFile.close();
        inChannel.close();
    }
}
