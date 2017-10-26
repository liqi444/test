package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created liqi on 2017/9/16.
 */
public class Selector1 {

    public void test1() throws IOException {
        Selector selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("wwww.baidu.com", 80));
        channel.configureBlocking(false);

        SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);

        int interestSet = selectionKey.interestOps();

        //确定某个确定的事件是否在interest 集合中
        boolean isInterestedInAccept = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
        boolean isInterestedInConnect = (interestSet & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT;
        boolean isInterestedInRead = (interestSet & SelectionKey.OP_READ) == SelectionKey.OP_READ;
        boolean isInterestedInWrite = (interestSet & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE;

        //确定是否准备就绪
        int readySet = selectionKey.readyOps();
        boolean isReadyInAccept = (readySet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
        boolean isReadyInConnect = (readySet & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT;
        boolean isReadyInRead = (readySet & SelectionKey.OP_READ) == SelectionKey.OP_READ;
        boolean isReadyInWrite = (readySet & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE;
        selectionKey.isAcceptable();
        selectionKey.isConnectable();
        selectionKey.isReadable();
        selectionKey.isWritable();

        java.nio.channels.Channel channel1 = selectionKey.channel();
        java.nio.channels.Selector selector1 = selectionKey.selector();

        //附加信息  可以跟随通道
        SelectionKey selectionKey1 = channel.register(selector, SelectionKey.OP_READ, "ss");
        String att = (String) selectionKey.attachment();


        Set selectedKeys = selector.selectedKeys();
        Iterator keyIterator = selectedKeys.iterator();
        while (keyIterator.hasNext()) {
            SelectionKey key = (SelectionKey) keyIterator.next();
            if (key.isAcceptable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                // a connection was accepted by a ServerSocketChannel.
            } else if (key.isConnectable()) {
                // a connection was established with a remote server.
            } else if (key.isReadable()) {
                // a channel is ready for reading
            } else if (key.isWritable()) {
                // a channel is ready for writing
            }
            keyIterator.remove();
        }

        channel.close();
        selector.close();
    }

    public void test2() throws IOException {
        Selector selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);
        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) continue;
            Set selectedKeys = selector.selectedKeys();
            Iterator keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = (SelectionKey) keyIterator.next();
                if (key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.
                } else if (key.isReadable()) {
                    // a channel is ready for reading
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                }
                keyIterator.remove();
            }
        }
    }

    public void test3() throws IOException {
        Selector selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("wwww.baidu.com", 80));
        channel.configureBlocking(false);

        SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);

        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) continue;
            Set selectedKeys = selector.selectedKeys();
            Iterator keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = (SelectionKey) keyIterator.next();
                if (key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.
                } else if (key.isReadable()) {
                    // a channel is ready for reading
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                }
                keyIterator.remove();
            }
        }
    }
}
