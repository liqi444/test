package designPattern;

/**
 * Created liqi on 2017/9/15.
 */
public class Single {

    private static Single single;
    private Single(){

    }

    /**
     * not thread safe
     * @return
     */
    public static Single newInstance(){
        if(single == null){
            single = new Single();
        }
        return single;
    }

    /**
     * thread safe but slow
     * @return
     */
    public synchronized static Single newInstance2(){
        if(single == null){
            single = new Single();
        }
        return single;
    }

    /**
     * thread safe not slow not lazy load
     */
    private static Single single3 = new Single();
    public static Single newInstance3(){
        return single;
    }

    /**
     * better than above
     */
    private static class SingleHolder{
        private static final Single single = new Single();
    }
    public static Single newInstance4(){
        return SingleHolder.single;
    }


    private volatile static Single single5;
    public static Single getInstance5(){
        synchronized (Single.class){
            if(single5 ==null){
                single5 = new Single();
            }
        }
        return single5;
    }


    public static void main(String[] args){
        ESingle eSingle = ESingle.getInstance();
    }
}

enum ESingle{
    INSTANCE;
    public static ESingle getInstance(){
        return INSTANCE;
    }
}