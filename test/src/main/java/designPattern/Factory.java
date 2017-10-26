package designPattern;

/**
 * Created liqi on 2017/9/15.
 */
public class Factory {

    public static void main(String[] args) {
        test2();
    }

    /**
     * Simple factory
     */
    Car createBenz(String type) {
        switch (type) {
            case "250":
                return new BenzGLK250();
            case "550":
                return new BenzGLK550();
            default:
                return null;
        }
    }

    /**
     * 工厂方法模式
     */
    static void test2() {
        BenzFactory factory1 = new Benz250Factory();
        factory1.createBenz();
        BenzFactory factory2 = new Benz550Factory();
        factory2.createBenz();
    }


}

interface BenzFactory {
    public Car createBenz();
}

class Benz550Factory implements BenzFactory {

    @Override
    public BenzGLK550 createBenz() {
        return new BenzGLK550().create();
    }
}

class Benz250Factory implements BenzFactory {

    @Override
    public BenzGLK250 createBenz() {
        return new BenzGLK250().create();
    }
}


abstract class Car {
    public Car create() {
        System.out.println("nothing created");
        return null;
    }
}

class BenzGLK550 extends Car {
    @Override
    public BenzGLK550 create() {
        System.out.println("550 created");
        return new BenzGLK550();
    }
}

class BenzGLK250 extends Car {
    @Override
    public BenzGLK250 create() {
        System.out.println("250 created");
        return new BenzGLK250();
    }
}