public class PrintNum {

    public static void main(String[] args){
        PrintNum printNum = new PrintNum();
        printNum.demo();
    }
    private byte[] lock = new byte[0];  //自定义锁对象，这样代价最小，也可已使用当前对象this
    
    //test代码1

    public void demo() {
        PrintThread a = new PrintThread("a");
        PrintThread b = new PrintThread("b");
        PrintThread c = new PrintThread("c");
        a.start();
        b.start();
        c.start();
    }

    class PrintThread extends Thread {
        public PrintThread(String name) {
            this.setName(name);
        }

        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println(this.getName() + ": " + i);
                    if (i % 10 == 0 && 0 != i) {
                        try {
                            lock.notifyAll();       //唤醒其他进程
                            lock.wait();   //暂时释放资源
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}  