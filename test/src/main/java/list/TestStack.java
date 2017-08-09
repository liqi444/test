package list;

import java.util.Stack;

/**
 * Created liqi on 2017/7/18.
 */
public class TestStack {
    public static void main(String[] args){
        TestStack test = new TestStack();
        test.testPush();
    }

    void testPush(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(1);
        System.out.print(stack.toString());
    }
}
