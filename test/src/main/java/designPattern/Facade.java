package designPattern;

/**
 * Created liqi on 2017/9/16.
 */
public class Facade {

    public static void main(String[] args) {
        //rich back home
        OpenFacade openFacade = OpenFacade.OpenFacadeHolder.getInstance();
        openFacade.open();
        openFacade.open();

    }


}

class Light {
    private boolean isOpen = false;

    public void open() {
        if (!isOpen) {
            System.out.println("Light open");
            isOpen = true;
        }
    }

    public void checkOpen() {
        System.out.println(isOpen);
    }
}

class TV {
    private boolean isOpen = false;

    public void open() {
        if (!isOpen) {
            System.out.println("TV open");
            isOpen = true;
        }
    }

    public void checkOpen() {
        System.out.println(isOpen);
    }
}

class Leg {
    private boolean isOpen = false;

    public void open() {
        if (!isOpen) {
            System.out.println("Leg open");
            isOpen = true;
        }
    }

    public void checkOpen() {
        System.out.println(isOpen);
    }
}

class OpenFacade {

    public static class OpenFacadeHolder {
        public static OpenFacade getInstance() {
            return new OpenFacade();
        }
    }


    private Light light1, light2, light3, light4;
    private TV tv1, tv2, tv3;
    private Leg leg1, leg2;

    private OpenFacade() {
//        light1 = new Light();
//        light2 = new Light();
//        light3 = new Light();
//        light4 = new Light();
//
//        tv1 = new TV();
//        tv2 = new TV();
//        tv3 = new TV();
//
//        leg1 = new Leg();
//        leg2 = new Leg();
    }


    public void open() {
        light1 = new Light();
        light2 = new Light();
        light3 = new Light();
        light4 = new Light();

        tv1 = new TV();
        tv2 = new TV();
        tv3 = new TV();

        leg1 = new Leg();
        leg2 = new Leg();


        light1.open();
        light2.open();
        light3.open();
        light4.open();
        tv1.open();
        tv2.open();
        tv3.open();
        leg1.open();
        leg2.open();
    }
}