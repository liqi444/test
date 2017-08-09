package list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created liqi on 2017/7/11.
 */
public class TestLinkedList {



    public static void main(String[] args){
        TestLinkedList test = new TestLinkedList();
        test.testAdd();
        test.testRemove();
    }

    void testRemove(){
        LinkedList<String> list = init();

        list.remove();

        list.removeFirst();
        list.removeLast();
        list.remove(5);//小心数组越界
        System.out.println(list.toString());
    }


    void testAdd(){
        LinkedList<String> list = init();

        list.addFirst("a");
        list.addLast("b");

        List<String> addList = new ArrayList<>();
        addList.add("A");
        addList.add("B");
        addList.add("C");

        list.addAll(addList);

        list.addAll(5,addList);

        System.out.println(list.toString());
    }

    static LinkedList<String> init(){
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");
        linkedList.add("6");
        linkedList.add("7");
        linkedList.add("8");
        linkedList.add("9");
        return linkedList;
    }
}
